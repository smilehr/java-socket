package com.huarui.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.huarui.model.FileArray;
import com.huarui.util.CloseStream;
import com.huarui.util.DeleteFileServlet;
import com.huarui.util.DownFileServlet;
import com.huarui.util.ShowFileServerlet;

import net.sf.json.JSONArray;

/**
 * 处理response传来的url，进行servlet分发任务
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huarui
 * @createdate 2017年7月14日
 */
public class DoServlet {

	private byte[] buf;

	private String headMessage;

	public String getHead() {
		return headMessage;
	}

	public byte[] getBuf() {
		return buf;
	}

	/**
	 * 根据url执行不同的servlet
	 * @param url
	 * @param parm
	 * @return
	 * @throws IOException
	 */
	public void doServlet(String url, String parm, OutputStream output) throws IOException {
		JSONArray json = null;
		if (url.contains("ShowFileServlet")) {
			String path = parm;
			ShowFileServerlet sf = new ShowFileServerlet();
			ArrayList<FileArray> list = sf.getFileServlet(path);
			json = JSONArray.fromObject(list);
			String head = "HTTP/1.1 200 OK\r\n";
			String type = "Content-Type: text/html\r\n" + "\r\n";
			this.headMessage = head + type;
			writeResponse(json.toString().getBytes("utf-8"), output);
		}

		if (url.contains("DownFileServlet")) {
			String path = parm;
			DownFileServlet df = new DownFileServlet();
			df.downLoad(path, output);
		}

		if (url.contains("CreateFileServlet")) {
			String path = parm;
			DeleteFileServlet dlf = new DeleteFileServlet();
			String head = "HTTP/1.1 200 OK\r\n";
			String type = "Content-Type: text/html\r\n" + "\r\n";
			this.headMessage = head + type;
			boolean bool = dlf.deleteFile(new File(path));
			if (bool) {
				writeResponse("right".getBytes("utf-8"), output);
			}
			else {
				writeResponse("error".getBytes("utf-8"), output);
			}
		}
	}

	public void doFile(String url, OutputStream output) throws IOException {
		InputStream fileIn = null;
		byte[] buf = null;
		try {
			fileIn = new FileInputStream(url);
			buf = new byte[fileIn.available()];
			fileIn.read(buf);
			String head = "HTTP/1.1 200 OK\r\n";
			String type = null;
			int beginIndex = url.lastIndexOf('.');
			String str = url.substring(beginIndex);
			if (str.equals(".html") || str.equals(".htm")) {
				type = "Content-Type: text/html\r\n" + "\r\n";
			}
			if (str.equals(".css")) {
				type = "Content-Type: text/css\r\n" + "\r\n";
			}
			if (str.equals(".js")) {
				type = "Content-Type: application/x-javascript\r\n" + "\r\n";
			}
			if (str.equals(".jpg")) {
				type = "Content-Type: application/x-jpg\r\n" + "\r\n";
			}
			if (str.equals(".png")) {
				type = "Content-Type: image/png\r\n" + "\r\n";
			}
			this.headMessage = head + type;
			writeResponse(buf, output);
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			CloseStream.close(fileIn);
		}
	}

	private void writeResponse(byte[] buf, OutputStream output) throws IOException {
		output.write(headMessage.getBytes("utf-8"));
		output.write(buf);
		output.flush();
		System.out.println("resopnse complete.");
	}
}
