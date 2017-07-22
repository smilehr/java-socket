package com.huarui.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.HashMap;

import com.huarui.intel.Request;

/**
 * 对request请求进行解析，并返回request对象
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author Hr
 * @createdate 2017年7月18日
 */
public class IOUtils {

	/**
	 * 解析头部请求
	 * @throws IOException 
	 */
	public static Request parseRequest(Socket socket) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
		Request request = null;
		try {
			String line = br.readLine();
			String method = line.substring(0, 4).trim();
			String url = URLDecoder.decode(line.split(" ")[1], "UTF-8");
			HashMap<String, String> hash = new HashMap<String, String>();
			if (method.equals("GET")) {
				request = doGet(url, hash, request);
			}
			if (method.equals("POST")) {
				request = doPost(line, br, hash, url);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return request;
	}

	/**
	 * 处理get请求
	 * @param endIndex，url里?位置
	 * @param url，请求url
	 * @param hash，参数
	 * @param request
	 */
	private static Request doGet(String url, HashMap<String, String> hash, Request request) {
		if (url.contains("?")) {
			int endIndex = url.indexOf("?");
			String parm = url.substring(endIndex + 1);
			url = url.substring(1, endIndex);
			int index = 0;
			int beginIndex = 0;
			while ((index = parm.indexOf('&')) != -1) {
				beginIndex = parm.indexOf('=') + 1;
				String key = parm.substring(0, beginIndex - 1);
				String value = parm.substring(beginIndex, index);
				hash.put(key, value);
				parm = parm.substring(index + 1);
			}
			if (parm.indexOf('&') == -1) {
				beginIndex = parm.indexOf('=') + 1;
				String key = parm.substring(0, beginIndex - 1);
				String value = parm.substring(beginIndex);
				hash.put(key, value);
			}
			return new Request("GET", url, hash);
		}
		else {
			return new Request("GET", url);
		}
	}

	/**
	 * 处理post请求
	 * @param line，读取的第一行
	 * @param br，字符读取流
	 * @param hash，参数
	 * @param request
	 */
	private static Request doPost(String line, BufferedReader br, HashMap<String, String> hash, String url)
			throws IOException {
		int contentLength = 0;
		url = url.substring(1);
		while (line != null) {
			line = br.readLine();
			if (line.equals("")) {
				break;
			}
			else if (line.indexOf("Content-Length") != -1) {
				contentLength = Integer.parseInt(line.substring(line.indexOf("Content-Length") + 16));
			}
		}
		//继续读取post提交的数据
		System.out.println("begin reading posted data......");
		//用户发送的post数据正文  
		byte[] buf = {};
		int size = 0;
		if (contentLength != 0) {
			buf = new byte[contentLength];
			while (size < contentLength) {
				int c = br.read();
				buf[size++] = (byte) c;
			}
			String parm = new String(buf, 0, size);
			System.out.println(parm);
			int index = 0;
			int beginIndex = 0;
			while ((index = parm.indexOf('&')) != -1) {
				beginIndex = parm.indexOf('=') + 1;
				String key = parm.substring(0, beginIndex - 1);
				String value = parm.substring(beginIndex, index);
				hash.put(key, value);
				parm = parm.substring(index + 1);
			}
			if (parm.indexOf('&') == -1) {
				beginIndex = parm.indexOf('=') + 1;
				String key = parm.substring(0, beginIndex - 1);
				String value = parm.substring(beginIndex);
				hash.put(key, value);
			}
		}
		return new Request("POST", url, hash, contentLength);
	}

	public static void close(Closeable... closeables) {
		if (closeables != null) {
			for (Closeable closeable : closeables) {
				try {
					closeable.close();
				}
				catch (Exception ex) {
					// 忽略  
				}
			}
		}
	}
}
