package com.huarui.server;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.huarui.intel.Request;
import com.huarui.intel.Response;
import com.huarui.servlet.CreateFileServlet;
import com.huarui.servlet.DeleteFileServlet;
import com.huarui.servlet.DownFileServlet;
import com.huarui.servlet.InitPageServlet;
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

	public Response dispatch(Request request, Response response) throws IOException {
		HashMap<String, String> parm = request.getParm();
		String url = request.getUrl();
		if (new File(WEB_ROOT + url).exists()) {
			return InitPageServlet.initPage(WEB_ROOT + url);
		}
		if (new File(url).exists() && new File(url).isFile()) {
			return InitPageServlet.initPage(url);
		}
		if (url.equals("ShowFileServlet")) {
			ShowFileServerlet sf = new ShowFileServerlet();
			return sf.getFileServlet(parm.get("path"), response);
		}
		if (url.equals("DownFileServlet")) {
			DownFileServlet df = new DownFileServlet();
			return df.downLoad(parm.get("path"), response);
		}
		if (url.equals("DeleteFileServlet")) {
			DeleteFileServlet delete = new DeleteFileServlet();
			return delete.deleteServlet(parm.get("path"), response);
		}
		if (url.equals("CreateFileServlet")) {
			return CreateFileServlet.createFile(parm.get("name"), parm.get("path"), response);
		}
		return response;
	}
}
