package com.huarui.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.huarui.server.RequestServer;
import com.huarui.thread.WorkerThread;
import com.huarui.util.CloseStream;

/**
 * 创建http请求，监听8080端口
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huarui
 * @createdate 2017年7月14日
 */
public class MyHttpServlet {

	int workerThreadCount = 5;

	WorkerThread[] workerThread = new WorkerThread[workerThreadCount];

	LinkedList<RequestServlet> taskQueue = new LinkedList<RequestServlet>();

	/**
	 * 创建http请求，监听8080端口
	 */
	public void await() {
		ExecutorService service = Executors.newFixedThreadPool(100);
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8080);
			System.out.println("httpServer running on port " + serverSocket.getLocalPort());
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		Socket socket = null;
		OutputStream out = null;
		InputStream in = null;
		boolean exit = false;
		while (!exit) {
			try {
				socket = serverSocket.accept();
				out = socket.getOutputStream();
				in = socket.getInputStream();
				RequestServlet sockThread = new RequestServlet(in);
				sockThread.run();
				//在向任务队列添加任务是需要同步，也就是要获取对象锁
//				synchronized (taskQueue) {
//					RequestServlet sockThread = new RequestServlet(in);
//					taskQueue.add(sockThread);
					//唤醒一个等待中的worker线程来执行任务队列中的任务
//					taskQueue.notify();
//				}
				ResponseServlet response = new ResponseServlet(out);
				response.setRequest(sockThread);
				response.sendMessage();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			finally{
				CloseStream.close(socket, out, in);
			}
		}

	}

	public void startWorkerThreads() {
		for (int i = 0; i < workerThreadCount; i++) {
			workerThread[i] = new WorkerThread(taskQueue);
			workerThread[i].start();
		}
	}
}
