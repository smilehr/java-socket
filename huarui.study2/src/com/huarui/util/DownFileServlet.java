package com.huarui.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * 下载文件
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huarui
 * @createdate 2017年7月17日
 */
public class DownFileServlet {

	private String filename;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	long length;

	/**
	 * 下载文件，返回字节数组
	 * @param path
	 * @throws IOException 
	 */
	public void downLoad(String path, OutputStream output) throws IOException {
		File file = new File(path);
		String filename = file.getName();
		setFilename(filename);
		//获取后缀名
		String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
		long leng = filename.length();
		int fileSize = (int) file.length();
		setLength(length);
		String head = "Content-Disposition: attachment; filename = " + new String(filename.getBytes("utf-8")) + "\r\n";
		String length = "Content-Length: " + fileSize + "\r\n";
		String type = "Content-type: application/octet-stream" + "\r\n";
		String headMessage = "HTTP/1.1 200 OK\r\n" + type + head;
		output.write(headMessage.getBytes("utf-8"));
		FileInputStream fis = null;
		int file_size = (int) file.length(); //根据文件大小设置byte数组大小
		int off = 0;
		int len = 0;
		int rest = 0;
		try {
			fis = new FileInputStream(file);//文件读取流
			byte[] file_read = new byte[file_size];
			while ((len = fis.read(file_read, off, rest)) != -1) {				
				output.write(file_read, off, rest);
				off += len;
				rest = file_size - off > 1024 ? 1024 : file_size - off;
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (fis != null) {
				try {
					fis.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
