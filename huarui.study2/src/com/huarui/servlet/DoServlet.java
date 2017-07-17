package com.huarui.servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.huarui.model.FileArray;
import com.huarui.util.CloseStream;
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
	
	String headMessage;
	
	public String getHead(){
		return headMessage;
	}

	/**
	 * 根据url执行不同的servlet
	 * @param url
	 * @param parm
	 * @return
	 * @throws IOException
	 */
	public JSONArray doServlet(String url, String parm) throws IOException {
		JSONArray json = null;
		if (url.contains("ShowFileServlet")) {
			String path = parm;
			ShowFileServerlet sf = new ShowFileServerlet();
			ArrayList<FileArray> list = sf.getFileServlet(path);
			json = JSONArray.fromObject(list);
			System.out.println(json.toString());
			String head = "HTTP/1.1 200 OK\r\n";
			String type = "Content-Type: text/html\r\n" + "\r\n";
			this.headMessage = head + type;
		}
		return json;
	}

	public byte[] doFile(String url) throws IOException {
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
				type ="Content-Type: text/css\r\n" + "\r\n";
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
			this.headMessage = head+ type;
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			CloseStream.close(fileIn);
		}
		return buf;
	}

	public byte[] doLocalFile(String url) throws IOException {
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
				type ="Content-Type: text/css\r\n" + "\r\n";
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
			this.headMessage = head+ type;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			CloseStream.close(fileIn);
		}
		return buf;
	}
}
