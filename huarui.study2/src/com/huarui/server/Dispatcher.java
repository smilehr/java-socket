package com.huarui.server;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

import com.huarui.intel.Request;
import com.huarui.intel.Response;
import com.huarui.servlet.CreateFileServlet;
import com.huarui.servlet.DeleteFileServlet;
import com.huarui.servlet.DownFileServlet;
import com.huarui.servlet.InitPageServlet;
import com.huarui.servlet.ModifyFileServlet;
import com.huarui.servlet.ShowFileServerlet;

/**
 * 根据请求的url分配相应的servlet
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author Hr
 * @createdate 2017年7月18日
 */
public class Dispatcher {
	//定义webroot目录
	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

	public static Response dispatch(Request request, Response response, Socket socket) throws IOException {
		String url = request.getUrl();
		if (new File(WEB_ROOT + url).exists()) {
			return InitPageServlet.initPage(WEB_ROOT + url, response);
		}
		if (new File(url).exists() && new File(url).isFile()) {
			return InitPageServlet.initPage(url, response);
		}
		if (url.equals("ShowFileServlet")) {
			return ShowFileServerlet.getFileServlet(request, response);
		}
		if (url.equals("DownFileServlet")) {
			return DownFileServlet.downLoad(request, response, socket);
		}
		if (url.equals("DeleteFileServlet")) {
			return DeleteFileServlet.deleteServlet(request, response);
		}
		if (url.equals("CreateFileServlet")) {
			return CreateFileServlet.createFile(request, response);
		}
		if (url.equals("ModifyFileServlet")) {
			return ModifyFileServlet.modifyServlet(request, response);
		}
		return response;
	}
}
