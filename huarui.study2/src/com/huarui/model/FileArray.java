package com.huarui.model;

public class FileArray {
	private int type;//0:文本文档;1:图片;2:pdf;3:mp3;4:mp4;5:rar压缩文档;6:目录

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	private String path;

	private String content;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
