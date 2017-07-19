package com.huarui.servlet;

import java.io.File;
import java.io.UnsupportedEncodingException;

import com.huarui.intel.Response;

/**
 * 删除文件或文件夹
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author Hr
 * @createdate 2017年7月17日
 */
public class DeleteFileServlet {

	/**
	 * 调用deleteFile，并返回response
	 * @param path
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public Response deleteServlet(String path, Response response) throws UnsupportedEncodingException {
		File file = new File(path);
		String head = "HTTP/1.1 200 OK\r\n";
		String type = "Content-Type: text/html\r\n" + "\r\n";
		response.setHeadMessage(head);
		response.setType(type);
		deleteFile(file);
		response.setReturnByte("ok".getBytes("utf-8"));
		return response;
	}

	/**
	 * 删除文件或文件夹
	 * @param file，传入的文件参数
	 * @return
	 */
	public void deleteFile(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			}
			else if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					this.deleteFile(files[i]);
				}
				file.delete();
			}
		}
		else {
			System.out.println("所删除的文件不存在");
		}
	}
}
