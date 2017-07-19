package com.huarui.util;

import java.io.BufferedReader;
import java.io.IOException;
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
	 */
	public static Request parseRequest(BufferedReader br) {
		Request request = new Request();
		try {
			String line = br.readLine();
			String method = line.substring(0, 4).trim();
			request.setMethod(method);
			if (method.equals("GET")) {
				String url = URLDecoder.decode(line.split(" ")[1], "UTF-8");
				request.setUrl(url);
				if (url.contains("?")) {
					int endIndex = url.indexOf('?');
					String parm = url.substring(endIndex + 1);
					url = url.substring(1, endIndex);
					request.setUrl(url);
					HashMap<String, String> hash = new HashMap<String, String>();
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
					request.setParm(hash);
				}
			}
			if (method.equals("POST")) {
				int contentLength = 0;
				while (line != null) {
					System.out.println(line);
					line = br.readLine();
					if (line.equals("")) {
						break;
					}
					else if (line.indexOf("Content-Length") != -1) {
						contentLength = Integer.parseInt(line.substring(line.indexOf("Content-Length") + 16));
						request.setContentLength(contentLength);
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
					//					request.setParm(parm);
					System.out.println("The data user posted: " + parm);
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return request;
	}
}
