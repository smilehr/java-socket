package com.huarui.servlet;

import java.io.IOException;
import com.huarui.intel.Request;
import com.huarui.intel.Response;
import com.huarui.util.FileUtils;

import net.sf.json.JSONArray;

/**
 * 获取文件内容以及目录内容
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author Hr
 * @createdate 2017年7月11日
 */
public class ShowFileServerlet {
	public static Response getFileServlet(Request request, Response response) throws IOException {
		String path = request.getParm().get("path");
		JSONArray json = FileUtils.showFile(path);
		String head = "HTTP/1.1 200 OK\r\n";
		String type = "Content-Type: text/html\r\n" + "\r\n";
		response.setHeadMessage(head);
		response.setType(type);
		response.setReturnByte(json.toString().getBytes("utf-8"));
		return response;
	}
}