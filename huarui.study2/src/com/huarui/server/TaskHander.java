package com.huarui.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import com.huarui.intel.Request;
import com.huarui.intel.Response;
import com.huarui.util.CloseStream;
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

	private InputStream input;

	private OutputStream output;

	public TaskHander(Socket socket) throws IOException {
		this.socket = socket;
	}

	public void run() {
		BufferedReader br = null;
		try {
			input = socket.getInputStream();
			output = socket.getOutputStream();
			br = new BufferedReader(new InputStreamReader(input));
			Request request = IOUtils.parseRequest(br);
			Dispatcher dis = new Dispatcher();
			Response response = new Response();
			response = dis.dispatch(request, response);
			if(response.getReturnByte() != null){			
				SendMessageUtil.sendMessage(response, output);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			CloseStream.close(br, input, output, socket);
		}
	}
}
