package com.huarui.servlet;

import java.io.File;
import java.io.UnsupportedEncodingException;

import com.huarui.intel.Request;
import com.huarui.intel.Response;
import com.huarui.util.FileUtils;

/**
 * 删除文件或文件夹
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author Hr
 * @createdate 2017年7月17日
 */
public class DeleteFileServlet {

	/**
	 * 调用deleteFile，并返回response
	 * @param path
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static Response deleteServlet(Request request, Response response) throws UnsupportedEncodingException {
		String path = request.getParm().get("path");
		File file = new File(path);
		FileUtils.deleteFile(file);

		String head = "HTTP/1.1 200 OK\r\n";
		String type = "Content-Type: text/html\r\n" + "\r\n";
		response = new Response(head, type, "ok".getBytes("utf-8"));
		return response;
	}
}
