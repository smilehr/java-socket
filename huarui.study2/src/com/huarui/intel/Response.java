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

	public String getHeadMessage() {
		return headMessage;
	}

	public void setHeadMessage(String headMessage) {
		this.headMessage = headMessage;
	}

	public String getBodyMessage() {
		return bodyMessage;
	}

	public void setBodyMessage(String bodyMessage) {
		this.bodyMessage = bodyMessage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getReturnByte() {
		return returnByte;
	}

	public void setReturnByte(byte[] rerurnByte) {
		this.returnByte = rerurnByte;
	}

	public int getFileLength() {
		return fileLength;
	}

	public void setFileLength(int fileLength) {
		this.fileLength = fileLength;
	}
}
