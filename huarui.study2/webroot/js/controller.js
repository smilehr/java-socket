/**
 * 通过id获取dom元素
 * @param {} fn
 */
function $(id) {
	return document.getElementById(id);
}
/**
 *js方法预处理，对多个函数进行处理
 */
function addLoadListener(fn) {
	if (typeof window.addEventListener != 'undefined') {
		window.addEventListener('load', fn, false);
	}
	else if (typeof document.addEventListener != 'undefined') {
		document.addEventListener('load', fn, false);
	}
	else if (typeof window.attachEvent != 'undefined') {
		window.attachEvent('onload', fn);
	}
	else {
		var oldfn = window.onload;
		if (typeof window.onload != 'function') {
			window.onload = fn;
		}
		else {
			window.onload = function() {
				oldfn();
				fn();
			};
		}
	}
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
 * input输入后提交按钮事件
 */
window.$("input_btn").onclick = getFile;
/**
 * 请求目录数据
 */
function getFile() {
	var path = $("host_path").value;
	var span = $("path_span");
	if (path != null) {
		span.innerHTML = path;
	}
	var xmlHttp = createXMLHttp();
	xmlHttp.onreadystatechanged = callback;
	var url = "/parse?path=" + path;
	xmlHttp.open("GET", url, true);
}
/**
 * 请求目录后的回调函数
 */
function callback() {
	if (xmlhttp.readyState == 4) {
		if (xmlhttp.status == 200) {
			var response = xmlhttp.responseText;

		}
	}
}
/**
 * 为多个li标签绑定函数
 */
var left_menu = $("left-menu");
var left_ul = left_menu.getElementsByTagName("ul");
var list = left_ul[0].getElementsByTagName("li");
for (var i = 0; i < list.length; i++) {
	list[i].onclick = function showContent() {
		alert(this.innerHTML);
	}
}
//addLoadListener(showContent);
