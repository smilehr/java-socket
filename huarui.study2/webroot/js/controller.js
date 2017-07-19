/**
 * 通过id获取dom元素
 * @param {} fn
 */
function $(id) {
	return document.getElementById(id);
}
/**
 * 动态创建li标签
 *<pre>
 *<li><img/><span></span></li>
 *</pre>
 * @param {} fn
 */
function addElementLi(id, txt, type, path) {
	var ul = $(id);
	var li = document.createElement("li");
	var img = document.createElement("img");
	var span = document.createElement("span");
	var arr_src = ["txt.png", "img.png", "pdf.png", "audio.png", "video.png", "rar.png", "headD.png"];
	img.src = "/images/" + arr_src[type];
	span.innerHTML = txt;
	span.onclick = getFile;
	var a = document.createElement("a");
	a.innerHTML = "删除";
	a.href = "javascript:void(0)";
	a.onclick = function() {
		var ajax_url = "/DeleteFileServlet?path=";
		ajax({
			    method : 'get',
			    url : ajax_url,
			    data : path,
			    success : function(response) {
				    window.location.href = window.location.href;
			    }
		    });
	};
	li.appendChild(img);
	li.appendChild(span);
	li.appendChild(a);
	ul.appendChild(li);
}
/**
 * 动态删除li标签
 * @param {} fn
 */
function deleteElementLi(id) {
	var ul = $(id);
	var length = ul.childNodes.length;
	for (var i = 2; i < length; i++) {
		ul.removeChild(ul.childNodes[2]);
	}
}
/**
 * 动态创建img标签
 * @param {} fn
 */
function addElementImg(id, path) {
	var div = $(id);
	var img = document.createElement("img");
	img.src = path;
	img.setAttribute("id", "pre_img");
	div.appendChild(img);
}
/**
* 创建XMLHttpRequest对象
*/
function createXMLHttp() {
	var xmlHttp; // 全局变量XMLHttpRequest对象
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlHttp = new XMLHttpRequest();
		// 针对某些特定版本的mozillar浏览器的bug进行修正。
		if (xmlHttp.overrideMimeType) {
			xmlHttp.overrideMimeType('text/xml');
		};
	}
	else {// code for IE6, IE5
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return xmlHttp;
}
/* 封装ajax函数
 2  * @param {string}opt.method http连接的方式，包括POST和GET两种方式
 3  * @param {string}opt.url 发送请求的url
 4  * @param {boolean}opt.async 是否为异步请求，true为异步的，false为同步的
 5  * @param {object}opt.data 发送的参数，格式为对象类型
 6  * @param {function}opt.success ajax发送并接收成功调用的回调函数
 7  */
function ajax(opt) {
	// 处理传过来的opt对象
	opt.method = opt.method.toUpperCase() || 'POST';
	opt.url = opt.url || '';
	opt.async = opt.async || true;
	opt.data = opt.data || null;
	opt.success = opt.success || function() {
	};

	var xmlHttp = createXMLHttp();
	if (opt.method == "GET") {
		var get_url = opt.url + opt.data;
		xmlHttp.open(opt.method, get_url, opt.async);
		xmlHttp.send();
	}
	if (opt.method == "POST") {
		xmlHttp.open(opt.method, opt.url, opt.async);
		xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		xmlHttp.send(opt.data);
	}
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			opt.success(xmlHttp.responseText);
		}
	}
}
/**
 * 初始化界面，默认根路径为d盘
 * @type 
 */
function initPage() {
	var span = $("path_span");
	var path = span.innerHTML + "\\";
	var ajax_url = "/ShowFileServlet?path=";
	ajax({
		    method : 'get',
		    url : ajax_url,
		    data : path,
		    success : function(response) {
			    var jobj = eval(response);
			    for (var i = jobj.length - 1; i >= 0; i--) {
				    addElementLi("left_ul", jobj[i].path, jobj[i].type, path + jobj[i].path);
			    }
		    }
	    });
}
window.onload = initPage;
/**
 * input输入后提交按钮事件
 */
window.$("input_btn").onclick = getFile;
/**
 * 请求子目录数据
 */
function getFile() {
	var evt = getEvent();
	var element = evt.srcElement || evt.target;
	var tag = element.tagName;
	var now_path = $("path_span").innerHTML;
	var path;
	if (tag == "SPAN") {
		path = now_path + "\\" + this.innerHTML;
	}
	if (tag == "BUTTON") {
		path = $("host_path").value;
	}
	var img_dom = $("file-cont").getElementsByTagName("img").length;
	ajax({
		    method : 'get',
		    url : '/ShowFileServlet?path=',
		    data : encodeURI(path),
		    success : function(response) {
			    var jobj = eval(response);
			    if (jobj[0].type != 6 && jobj.length == 1) {
				    if (jobj[0].type == 0) {
					    $("save").style.display = "inline";
					    if (jobj.content != "") {
						    $("file-cont-edit").style.display = "inline";
						    $("file-cont-edit").value = jobj[0].content;
						    $("file-cont-edit").title = jobj[0].path;
						    if (img_dom > 0) {
							    $("pre_img").parentNode.removeChild($("pre_img"));
						    }
					    }
				    }
				    if (jobj[0].type == 1) {
					    $("save").style.display = "none";
					    if ($("file-cont-edit").style.display == "inline") {
						    $("file-cont-edit").style.display = "none";
					    }
					    if (img_dom > 0) {
						    $("pre_img").parentNode.removeChild($("pre_img"));
					    }
					    addElementImg("file-cont", jobj[0].path);
					    $("file-cont-edit").style.display = "none";
					    $("pre_img").title = jobj[0].path;
				    }
				    $("download").style.display = "inline";
			    }
			    else {
				    deleteElementLi("left_ul");
				    $("path_span").innerHTML = path;
				    if (jobj[0].path != "") {
					    for (var i = jobj.length - 1; i >= 0; i--) {
						    addElementLi("left_ul", jobj[i].path, jobj[i].type, path, path);
					    }
				    }
			    }
		    }
	    });
}
/**
 * 返回上一级目录
 */
window.$("li_back").onclick = function() {
	var root = $("path_span").innerHTML;
	if (root == "D:") {
		alert("当前为根目录");
	}
	else if (root == "") {
		root = "d:\\";
	}
	else {
		var lastIndex = root.lastIndexOf("\\");
		var path = root.substring(0, lastIndex);
		if (path == "") {
			path = "D:"
		}
		deleteElementLi("left_ul");
		$("path_span").innerHTML = path;
		path = path + "\\";
		ajax({
			    method : 'get',
			    url : '/ShowFileServlet?path=',
			    data : encodeURI(path),
			    success : function(response) {
				    var jobj = eval(response);
				    for (var i = jobj.length - 1; i >= 0; i--) {
					    addElementLi("left_ul", jobj[i].path, jobj[i].type);
				    }
			    }
		    });
	}
}
/**
 * 下载文档
 */
window.$("download").onclick = function() {
	var txt_src = $("file-cont-edit").title || $("pre_img").title;
	var path = $("path_span").innerHTML + "\\" + txt_src;
	window.open("/DownFileServlet?path=" + path, "_parent");
}

$("create_btn").onclick = function() {
	var now_path = $("path_span").innerHTML;
	var path = now_path + "\\";
	var name = $("create_path").value;
	var ajax_url = "/CreateFileServlet?path=";
	var data = {
		path : path,
		name : name
	}
	alert(data.name);
	// ajax({
	// method : 'get',
	// url : ajax_url,
	// data : path,
	// success : function(response) {
	// window.location.href = window.location.href;
	// }
	// });
}
// 解决window.event.srcElement.tagName在火狐上的不兼容性
function getEvent() {
	if (document.all) {
		return window.event;// 如果是ie
	}
	func = getEvent.caller;
	while (func != null) {
		var arg0 = func.arguments[0];
		if (arg0) {
			if ((arg0.constructor == Event || arg0.constructor == MouseEvent) || (typeof(arg0) == "object" && arg0.preventDefault && arg0.stopPropagation)) {
				return arg0;
			}
		}
		func = func.caller;
	}
	return null;
}