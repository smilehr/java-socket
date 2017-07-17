package com.huarui.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import com.huarui.intel.Response;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 发送返回数据
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huarui
 * @createdate 2017年7月14日
 */
public class ResponseServlet implements Response {
	//定义webroot目录
	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

	private OutputStream output;

	private RequestServlet request;
	
	public String headMessage;
	
	private String bodyMessage;
	
	private String conTentType;
	
	private String reBuf;
	
	private int fileLength;


	public void setRequest(RequestServlet request) {
		this.request = request;
	}

	public ResponseServlet(OutputStream output) {
		this.output = output;
	}

	public void setHeadMessage(String head) {
		this.headMessage = head;
	}

	public void setBodyMessage(String body) {
		this.bodyMessage = body;

	}

	public void setContentType(String type) {
		this.conTentType = type;

	}

	public void setReByte(String returnByte) {
		this.reBuf = returnByte;

	}

	public void setFileLength(int length) {
		this.fileLength = length;

	}

	/**
	 * 发送返回数据
	 * @throws IOException 
	 */
	public void sendMessage() throws IOException {
		String method = request.getMethod();
		if (method.equals("GET")) {
			System.out.println("处理GET请求");
			doGet(this.output, this.request);
		}
		if (method.equals("POST")) {
			System.out.println("处理POST请求");
			doPost(this.output, this.request);
		}
	}

	/**
	 * 处理get请求
	 * @param output
	 * @param request
	 * @throws IOException
	 */
	private void doGet(OutputStream output, RequestServlet request) throws IOException {
		String url = request.getUrl();
		byte[] buf = null;
		DoServlet ds = new DoServlet();
		if (url.contains("Servlet?")) {
			ds.doServlet(url,request.getParm(),output);
		}
		if (new File(WEB_ROOT + url).exists()) {
			ds.doFile(WEB_ROOT + url, output);
		}
		if (new File(url).exists() && new File(url).isFile()) {
			ds.doFile(url, output);
		}	
	}

	private void doPost(OutputStream output2, RequestServlet request2) {
	}
}
