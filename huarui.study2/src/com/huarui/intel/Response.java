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

	public void setHeadMessage(String headMessage) {
		this.headMessage = headMessage;
	}

	public void setBodyMessage(String bodyMessage) {
		this.bodyMessage = bodyMessage;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setReturnByte(byte[] returnByte) {
		this.returnByte = returnByte;
	}

	public void setFileLength(int fileLength) {
		this.fileLength = fileLength;
	}

	private String type = "";

	private byte[] returnByte = null;

	private int fileLength = 0;

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
