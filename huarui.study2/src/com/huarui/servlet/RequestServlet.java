package com.huarui.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URLDecoder;

import com.huarui.intel.Request;
import com.huarui.util.CloseStream;

/**
 * 处理http协议头部请求
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huarui
 * @createdate 2017年7月13日
 */
public class RequestServlet implements Request {

	private String method;

	private String url;

	private String parm;

	private InputStream input;

	private BufferedReader br;

	/**
	 * 构造函数
	 * @param socket
	 * @throws IOException
	 */
	public RequestServlet(InputStream input) {
		this.input = input;
		this.br = new BufferedReader(new InputStreamReader(input));
	}

	@Override
	public String getMethod() {
		return this.method;
	}

	@Override
	public String getUrl() {
		return this.url;
	}

	@Override
	public String getParm() {
		return this.parm;
	}

	/**
	 * 解析头部请求
	 */
	@Override
	public void parseRequest() throws IOException {
		String line = br.readLine();
		this.method = line.substring(0, 4).trim();
		//对请求url进行解码
		if (method.equals("GET")) {
			this.url = URLDecoder.decode(line.split(" ")[1], "UTF-8");
			System.out.println(url);
			if (url.contains("?")) {
				int beginIndex = this.url.indexOf('=') + 1;
				this.parm = this.url.substring(beginIndex).trim();
			}
		}
		//			if (method.equals("POST")) {
		//				this.url = URLDecoder.decode(line.split(" ")[1], "UTF-8");
		//				System.out.println(url);
		//				if (url.contains("?")) {
		//					int beginIndex = this.url.indexOf('=') + 1;
		//					this.parm = this.url.substring(beginIndex).trim();
		//				}
		//			}
	}

	public void run() {
		try {
			parseRequest();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
