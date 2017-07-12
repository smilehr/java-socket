package com.huarui.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.ArrayList;

import com.huarui.model.FileArray;
import com.huarui.util.ShowFileServerlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 处理并发送http请求
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author Hr
 * @createdate 2017年7月7日
 */
public class Request implements Runnable {
	//定义webroot目录
	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

	final static String CRLF = "\r\n";

	private String requestPath;

	private Socket socket;

	BufferedReader br;

	//post提交请求的正文的长度  
	private int contentLength = 0;

	/**
	 * 构造函数
	 * @param socket
	 */
	public Request(Socket socket) throws Exception {
		this.socket = socket;
		this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	/**
	 *  实现Runnable 接口的run()方法
	 */
	public void run() {
		try {
			parse();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 接收并处理浏览器请求
	 */
	public void parse() throws Exception {
		while (true) {
			String line = br.readLine(); 
			String method = line.substring(0, 4).trim();
			OutputStream out = socket.getOutputStream();
			//对请求url进行解码
			this.requestPath = URLDecoder.decode(line.split(" ")[1],"UTF-8");
			System.out.println(this.requestPath);
			if ("GET".equalsIgnoreCase(method)) {
				System.out.println("do get......");
				this.doGet(br, out);
			}
			else if ("POST".equalsIgnoreCase(method)) {
				System.out.println("do post......");
				this.doPost(br, out);
			}
			// 关闭套接字和流
			socket.close();
		}
	}

	/**
	 * 处理get方式
	 * @param reader
	 * @param out
	 * @throws Exception
	 */
	private void doGet(BufferedReader reader, OutputStream out) throws Exception {
		//返回数据
		if (this.requestPath.contains("ShowFileServlet")) {
			int beginIndex = this.requestPath.indexOf('=') + 1;
			String path = this.requestPath.substring(beginIndex).trim();
			ShowFileServerlet sf = new ShowFileServerlet();
			ArrayList<FileArray> list = sf.getFileServlet(path);
			JSONArray json = JSONArray.fromObject(list);
			System.out.println(json.toString());
			String headMessage = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "\r\n";
			out.write(headMessage.getBytes("utf-8"));
			PrintStream pw = new PrintStream(out);
			pw.print(json);
			if (out != null) {
				out.close();
			}
			if (reader != null) {
				reader.close();
			}
			System.out.println("request complete.");

		}
		if (new File(WEB_ROOT + this.requestPath).exists()) {
			//从服务器根目录下找到用户请求的文件并发送回浏览器  
			InputStream fileIn = new FileInputStream(WEB_ROOT + this.requestPath);
			byte[] buf = new byte[fileIn.available()];
			fileIn.read(buf);
			String headMessage = null;
			int beginIndex = this.requestPath.lastIndexOf('.');
			String str = this.requestPath.substring(beginIndex);
			if (str.equals(".html") || str.equals(".htm")) {
				headMessage = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "\r\n";
			}
			if (str.equals(".css")) {
				headMessage = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/css\r\n" + "\r\n";
			}
			if (str.equals(".js")) {
				headMessage = "HTTP/1.1 200 OK\r\n" + "Content-Type: application/x-javascript\r\n" + "\r\n";
			}
			if (str.equals(".jpg")) {
				headMessage = "HTTP/1.1 200 OK\r\n" + "Content-Type: application/x-jpg\r\n" + "\r\n";
			}
			if (str.equals(".png")) {
				headMessage = "HTTP/1.1 200 OK\r\n" + "Content-Type: image/png\r\n" + "\r\n";
			}
			out.write(headMessage.getBytes("UTF-8"));
			out.write(buf);
			if (out != null) {
				out.close();
			}
			if (fileIn != null) {
				fileIn.close();
			}
			if (reader != null) {
				reader.close();
			}
		}
		if (new File(this.requestPath).exists() && new File(this.requestPath).isFile()) {
			//从请求目录下找到用户请求的文件并发送回浏览器  
			InputStream fileIn = new FileInputStream(this.requestPath);
			byte[] buf = new byte[fileIn.available()];
			fileIn.read(buf);
			String headMessage = null;
			int beginIndex = this.requestPath.lastIndexOf('.');
			String str = this.requestPath.substring(beginIndex);
			if (str.equals(".jpg")) {
				headMessage = "HTTP/1.1 200 OK\r\n" + "Content-Type: application/x-jpg\r\n" + "\r\n";
			}
			if(str.equals(".js")){
				headMessage = "HTTP/1.1 200 OK\r\n" + "Content-Type: application/x-javascript\r\n" + "\r\n";
			}
			out.write(headMessage.getBytes("UTF-8"));
			out.write(buf);
			if (out != null) {
				out.close();
			}
			if (fileIn != null) {
				fileIn.close();
			}
			if (reader != null) {
				reader.close();
			}
		}
		System.out.println("request complete.");
	}
	/**
	 * 处理post请求  
	 * @param reader
	 * @param out
	 * @throws Exception
	 */
	private void doPost(BufferedReader reader, OutputStream out) throws Exception {
		String line = reader.readLine();
		while (line != null) {
			System.out.println(line);
			line = reader.readLine();
			if ("".equals(line)) {
				break;
			}
			else if (line.indexOf("Content-Length") != -1) {
				this.contentLength = Integer.parseInt(line.substring(line.indexOf("Content-Length") + 16));
			}
		}
		//继续读取post提交的数据  
		System.out.println("begin reading posted data......");
		//用户发送的post数据正文  
		byte[] buf = {};
		int size = 0;
		if (this.contentLength != 0) {
			buf = new byte[this.contentLength];
			while (size < this.contentLength) {
				int c = reader.read();
				buf[size++] = (byte) c;

			}
			System.out.println("The data user posted: " + new String(buf, 0, size));
		}
		//发送回浏览器的内容  
		String response = "";
		String headMessage = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "\r\n";
		response += headMessage;
		response += "Server: Sunpache 1.0/n";
		response += "Content-Type: text/html/n";
		response += "Last-Modified: Mon, 11 Jan 1998 13:23:42 GMT/n";
		response += "Accept-ranges: bytes";
		response += "/n";
		String body = "<html><head><title>test server</title></head><body><p>post ok:</p>" + new String(buf, 0, size)
				+ "</body></html>";
		System.out.println(body);
		out.write(response.getBytes());
		out.write(body.getBytes());
		out.flush();
		reader.close();
		out.close();
		System.out.println("request complete.");
	}
}
