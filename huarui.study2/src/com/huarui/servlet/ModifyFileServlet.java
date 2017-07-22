package com.huarui.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.huarui.intel.Request;
import com.huarui.intel.Response;
import com.huarui.util.FileUtils;

/**
 * 修改文件类
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author Hr
 * @createdate 2017年7月21日
 */
public class ModifyFileServlet {

	public static Response modifyServlet(Request request, Response response) throws IOException {
		HashMap<String, String> parm = request.getParm();
		File file = new File(parm.get("path"));
		FileUtils.modifyFile(file, parm.get("content"));
		String head = "HTTP/1.1 200 OK\r\n";
		String type = "Content-Type: text/html\r\n" + "\r\n";
		response.setHeadMessage(head);
		response.setType(type);
		response.setReturnByte("ok".getBytes());
		return response;
	}
}
