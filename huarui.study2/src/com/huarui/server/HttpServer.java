package com.huarui.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建http请求，监听8080端口
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huarui
 * @createdate 2017年7月18日
 */
public class HttpServer {

	public void await() {
		try {
			ServerSocket serverSocket = new ServerSocket(8080);
			Socket socket = null;
			System.out.println("httpServer running on port " + serverSocket.getLocalPort());
			ExecutorService es = Executors.newFixedThreadPool(5);
			boolean exit = false;
			while (!exit) {
				try {
					socket = serverSocket.accept();
					TaskHander task = new TaskHander(socket);
					es.execute(task);
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
