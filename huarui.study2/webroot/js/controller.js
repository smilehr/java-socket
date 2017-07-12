/**
 * 通过id获取dom元素
 * @param {} fn
 */
function $(id) {
	return document.getElementById(id);
}
/**
 * 动态创建li标签
 * @param {} fn
 */
function addElementLi(id, txt, type) {
	var ul = $(id);
	var li = document.createElement("li");
	var img = document.createElement("img");
	var span = document.createElement("span");
	var arr_src = ["txt.png", "img.png", "pdf.png", "audio.png", "video.png", "rar.png", "headD.png"];
	img.src = "/images/" + arr_src[type];
	span.innerHTML = txt;
	span.onclick = getFile;
	li.appendChild(img);
	li.appendChild(span);
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
/**
 * 初始化界面，默认根路径为d盘
 * @type 
 */
function initPage() {
	var span = $("path_span");
	var path = span.innerHTML + "\\";
	var xmlHttp = createXMLHttp();
	var url = "/ShowFileServlet?path=" + path;
	xmlHttp.open("GET", url, true);
	xmlHttp.send();
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var response = xmlHttp.responseText;
				var jobj = eval(response);
				for (var i = jobj.length - 1; i >= 0; i--) {
					addElementLi("left_ul", jobj[i].path, jobj[i].type);
				}
			}
		}
	}
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
	var xmlHttp = createXMLHttp();
	var path;
	if (tag == "SPAN") {
		path = now_path + "\\" + this.innerHTML;
	}
	if (tag == "BUTTON") {
		path = $("host_path").value;
	}
	var img_dom = $("file-cont").getElementsByTagName("img").length;
	var xmlHttp = createXMLHttp();
	var url = "/ShowFileServlet?path=" + encodeURI(path);
	xmlHttp.open("GET", url, true);
	xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8');
	xmlHttp.send();
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var response = xmlHttp.responseText;
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
					}
					$("download").style.display = "inline";
				}
				else {
					deleteElementLi("left_ul");
					$("path_span").innerHTML = path;
					if (jobj[0].path != "") {
						for (var i = jobj.length - 1; i >= 0; i--) {
							addElementLi("left_ul", jobj[i].path, jobj[i].type);
						}
					}
				}
			}
		}
	}
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
		var xmlHttp = createXMLHttp();
		var url = "/ShowFileServlet?path=" + path + "\\";
		xmlHttp.open("GET", url, true);
		xmlHttp.send();
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					var response = xmlHttp.responseText;
					var jobj = eval(response);
					for (var i = jobj.length - 1; i >= 0; i--) {
						addElementLi("left_ul", jobj[i].path, jobj[i].type);
					}
				}
			}
		}
	}
}
/**
 * 下载文档
 */
window.$("download").onclick = function() {
	var l_img = $("file-cont").getElementsByTagName("img").length;
	if (l_img == 1) {
		var img_src = $("pre_img").src;
		window.open(img_src);
	}
	var l_txt = $("file-cont").getElementsByTagName("textarea").length;
	if (l_txt == 1) {
		var txt_src = $("file-cont-edit").title;
		location.href = txt_src;
	}
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