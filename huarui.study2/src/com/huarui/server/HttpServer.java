package com.huarui.server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

	/**
	 * 实现http请求
	 * 监听8080端口，并开始创建服务器
	 */
	public void await() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8080, 1, InetAddress.getByName("127.0.0.1"));
			System.out.println("httpServer running on port " + serverSocket.getLocalPort());
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		boolean loop = true;
		while (loop) {
			try {
				Socket socket = serverSocket.accept();
				System.out.println("New connection accepted " + socket.getInetAddress() + ":" + socket.getPort());
				Request request = new Request(socket);
				Thread thread = new Thread(request);
				thread.start();
			}
			catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
