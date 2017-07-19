package com.huarui.server;

import java.io.File;
import java.io.IOException;
import com.huarui.intel.Request;
import com.huarui.intel.Response;
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
		String url = request.getUrl();
		if (new File(WEB_ROOT + url).exists()) {
			response = InitPageServlet.initPage(WEB_ROOT + url);
		}
		if (new File(url).exists() && new File(url).isFile()) {
			response = InitPageServlet.initPage(url);
		}
		if (url.equals("ShowFileServlet")) {
			ShowFileServerlet sf = new ShowFileServerlet();
			response = sf.getFileServlet(request.getParm(), response);
		}
		if (url.equals("DownFileServlet")) {
			System.out.println(request.getParm());
			DownFileServlet df = new DownFileServlet();
			df.downLoad(request.getParm(), response);
		}
		return response;
	}
}
