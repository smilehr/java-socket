package com.huarui.servlet;

import java.io.File;
import java.io.IOException;
import com.huarui.intel.Response;
import com.huarui.util.FileRead;

/**
 * 下载文件
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huarui
 * @createdate 2017年7月17日
 */
public class DownFileServlet {

	private String filename;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	long length;

	/**
	 * 下载文件，返回字节数组
	 * @param path
	 * @throws IOException 
	 */
	public Response downLoad(String path, Response response) throws IOException {
		File file = new File(path);
		String filename = file.getName();
		setFilename(filename);
		int fileSize = (int) file.length();
		setLength(length);
		String headMessage = "HTTP/1.1 200 OK\r\n";
		String head = "Content-Disposition: attachment; filename = " + new String(filename.getBytes("utf-8")) + "\r\n"
				+ "\r\n";
		String length = "Content-Length: " + fileSize + "\r\n";
		String type = "Content-type: application/octet-stream" + length + head;
		response.setHeadMessage(headMessage);
		response.setType(type);
		response.setReturnByte(FileRead.file2buf(file));
		return response;
	}
}
