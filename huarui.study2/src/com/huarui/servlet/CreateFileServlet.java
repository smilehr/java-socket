package com.huarui.servlet;

import java.io.IOException;
import com.huarui.intel.Response;
import com.huarui.util.CreateFileUtil;

/**
 * 创建文件或文件夹servlet
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huarui
 * @createdate 2017年7月19日
 */
public class CreateFileServlet {

	public static Response createFile(String name, String path, Response response) throws IOException {
		CreateFileUtil.createFile(name, path);
		String head = "HTTP/1.1 200 OK\r\n";
		String type = "Content-Type: text/html\r\n" + "\r\n";
		response.setHeadMessage(head);
		response.setType(type);
		response.setReturnByte("ok".getBytes("utf-8"));
		return response;
	}
}
