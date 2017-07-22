package com.huarui.servlet;

import java.io.IOException;
import java.util.HashMap;

import com.huarui.intel.Request;
import com.huarui.intel.Response;
import com.huarui.util.FileUtils;

/**
 * 创建文件或文件夹servlet
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huarui
 * @createdate 2017年7月19日
 */
public class CreateFileServlet {

	public static Response createFile(Request request, Response response) throws IOException {
		HashMap<String, String> parm = request.getParm();
		FileUtils.createFile(parm.get("name"), parm.get("path"));
		String head = "HTTP/1.1 200 OK\r\n";
		String type = "Content-Type: text/html\r\n" + "\r\n";
		response.setHeadMessage(head);
		response.setType(type);
		response.setReturnByte("ok".getBytes());
		return response;
	}
}
