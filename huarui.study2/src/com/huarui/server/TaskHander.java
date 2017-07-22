package com.huarui.server;

import java.io.IOException;
import java.net.Socket;
import com.huarui.intel.Request;
import com.huarui.intel.Response;
import com.huarui.util.IOUtils;
import com.huarui.util.SendMessageUtil;

/**
 * 处理http窗口连接的任务
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huarui
 * @createdate 2017年7月18日
 */
public class TaskHander implements Runnable {

	private Socket socket;

	public TaskHander(Socket socket) throws IOException {
		this.socket = socket;
	}

	public void run() {
		try {
			Request request;
			request = IOUtils.parseRequest(socket);
			Response response = new Response();
			response = Dispatcher.dispatch(request, response, socket);
			if (response.getReturnByte() != null) {
				SendMessageUtil.sendMessage(response, socket);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			IOUtils.close(socket);
		}
	}
}
