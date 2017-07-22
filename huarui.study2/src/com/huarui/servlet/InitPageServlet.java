package com.huarui.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import com.huarui.intel.Response;
import com.huarui.util.IOUtils;

public class InitPageServlet {

	public static Response initPage(String url, Response response) {
		InputStream fileIn = null;
		byte[] buf = null;
		try {
			fileIn = new FileInputStream(url);
			buf = new byte[fileIn.available()];
			fileIn.read(buf);
			String headMessage = "HTTP/1.1 200 OK\r\n";
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
			response.setHeadMessage(headMessage);
			response.setType(type);
			response.setReturnByte(buf);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			IOUtils.close(fileIn);
		}
		return response;
	}
}
