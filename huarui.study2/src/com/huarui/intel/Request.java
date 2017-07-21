package com.huarui.intel;

import java.util.HashMap;

/**
 * Request接口，实现头部解析
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huarui
 * @createdate 2017年7月13日
 */
public class Request {

	private String method = "GET";

	private String url = "index.html";

	private HashMap<String, String> parm = null;

	private int contentLength = 0;

	public Request(String method, String url, HashMap<String, String> parm, int length) {
		this.method = method;
		this.url = url;
		this.parm = parm;
		this.contentLength = length;
	}

	public Request(String method, String url, HashMap<String, String> parm) {
		this.method = method;
		this.url = url;
		this.parm = parm;
	}

	public Request(String method, String url) {
		this.method = method;
		this.url = url;
	}

	public Request() {
		this.url = "index.html";
	}

	public String getMethod() {
		return method;
	}

	public String getUrl() {
		return url;
	}

	public HashMap<String, String> getParm() {
		return parm;
	}

	public int getContentLength() {
		return contentLength;
	}
}
