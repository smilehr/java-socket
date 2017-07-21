package com.huarui.intel;

/**
 * 定义response接口
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huarui
 * @createdate 2017年7月13日
 */
public class Response {

	private String headMessage = "";

	private String bodyMessage = "";

	private String type = "";

	private byte[] returnByte = null;

	private int fileLength = 0;

	public Response(String head, String body, String type, byte[] bt, int length) {
		this.headMessage = head;
		this.bodyMessage = body;
		this.type = type;
		this.returnByte = bt;
		this.fileLength = length;
	}

	public Response(String head, String type, byte[] bt) {
		this.headMessage = head;
		this.type = type;
		this.returnByte = bt;
	}

	public Response() {

	}

	public String getHeadMessage() {
		return headMessage;
	}

	public String getBodyMessage() {
		return bodyMessage;
	}

	public String getType() {
		return type;
	}

	public byte[] getReturnByte() {
		return returnByte;
	}

	public int getFileLength() {
		return fileLength;
	}
}
