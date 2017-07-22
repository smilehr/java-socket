package com.huarui.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import com.huarui.intel.Request;
import com.huarui.intel.Response;
import com.huarui.util.IOUtils;

/**
 * 下载文件
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huarui
 * @createdate 2017年7月17日
 */
public class DownFileServlet {

	/**
	 * 下载文件，返回字节数组
	 * @param path
	 * @throws IOException 
	 */
	public static Response downLoad(Request request, Response response, Socket socket) throws IOException {
		String path = request.getParm().get("path");
		File file = new File(path);
		String filename = file.getName();
		int fileSize = (int) file.length();
		String headMessage = "HTTP/1.1 200 OK\r\n";
		String head = "Content-Disposition: attachment; filename = " + new String(filename.getBytes("utf-8")) + "\r\n"
				+ "\r\n";
		String length = "Content-Length: " + fileSize + "\r\n";
		String type = "Content-type: application/octet-stream" + length + head;
		OutputStream output = socket.getOutputStream();
		output.write(headMessage.getBytes("utf-8"));
		output.write(type.getBytes("utf-8"));
		FileInputStream fis = null;
		int len = 0;
		try {
			fis = new FileInputStream(file);//文件读取流
			byte[] buf = new byte[1024];
			while ((len = fis.read(buf)) != -1) {
				output.write(buf, 0 , len);
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("文件读取错误！");
		}
		finally {
			IOUtils.close(fis);
		}
		return response;
	}
}
