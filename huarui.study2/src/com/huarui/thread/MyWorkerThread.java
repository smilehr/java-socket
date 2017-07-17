package com.huarui.thread;

import java.util.LinkedList;
import java.util.List;

import com.huarui.server.RequestServer;
import com.huarui.servlet.RequestServlet;

/**
 * 工作线程类,以线程池的方式启动requestServer
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author Hr
 * @createdate 2017年7月13日
 */
public class MyWorkerThread extends Thread{
	LinkedList<RequestServer> taskQueue;

	public MyWorkerThread(LinkedList<RequestServer> taskQueue) {
		this.taskQueue = taskQueue;
	}

	public List<RequestServer> getTaskQueue() {
		return taskQueue;
	}

	public void setTaskQueue(LinkedList<RequestServer> taskQueue) {
		this.taskQueue = taskQueue;
	}

	@Override
	public void run() {
		while (true) {
			RequestServer task = null;
			synchronized (taskQueue) {
				if (taskQueue.isEmpty()) {
					try {
						taskQueue.wait();
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else {
					task = taskQueue.removeFirst();
				}
			}

			if (task != null) {
				//直接调用run，并不是启动线程，只是执行了这个对象的run方法，和调用一个类的方法没区别
				task.run();
			}
		}
	}
}
