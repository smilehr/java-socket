package com.huarui.intel;

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

	private String parm = null;

	private int contentLength = 0;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParm() {
		return parm;
	}

	public void setParm(String parm) {
		this.parm = parm;
	}

	public int getContentLength() {
		return contentLength;
	}

	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}
}
