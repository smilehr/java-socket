package com.huarui.servlet;

import java.io.File;
import java.io.IOException;

import com.huarui.intel.Request;
import com.huarui.intel.Response;

/**
 * 下载文件
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huarui
 * @createdate 2017年7月17日
 */
public class DownFileServlet {

	/**
	 * 下载文件，返回字节数组
	 * @param path
	 * @throws IOException 
	 */
	public static Response downLoad(Request request, Response response) throws IOException {
		String path = request.getParm().get("path");
		File file = new File(path);
		String filename = file.getName();
		int fileSize = (int) file.length();
		String headMessage = "HTTP/1.1 200 OK\r\n";
		String head = "Content-Disposition: attachment; filename = " + new String(filename.getBytes("utf-8")) + "\r\n"
				+ "\r\n";
		String length = "Content-Length: " + fileSize + "\r\n";
		String type = "Content-type: application/octet-stream" + length + head;
		response = new Response(headMessage, type, "ok".getBytes("utf-8"));
		return response;
	}
}
