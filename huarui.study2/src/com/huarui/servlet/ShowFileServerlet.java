package com.huarui.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.huarui.intel.Response;
import com.huarui.model.FileArray;
import com.huarui.util.FileRead;

import net.sf.json.JSONArray;

/**
 * 根据传来的path参数返回文件目录list,如果是文件，则返回文件内容
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author Hr
 * @createdate 2017年7月11日
 */
public class ShowFileServerlet {
	public Response getFileServlet(String path, Response response) throws IOException {
		ArrayList<FileArray> list = new ArrayList<>();
		FileArray fa = null;
		File root = new File(path);
		if (!root.exists()) {
			fa = new FileArray();
			fa.setType(6);
			list.add(fa);
		}
		if (root.isFile()) {
			fa = new FileArray();
			fa.setType(getFileType(path));
			if (fa.getType() == 0) {
				fa.setContent(getContent(root));
			}
			int beginIndex = path.lastIndexOf("\\");
			String str = path.substring(beginIndex + 1);
			fa.setPath(str);
			list.add(fa);
		}
		if (root.isDirectory()) {
			File[] files = root.listFiles();
			int beginIndex;
			//空文件夹情况
			if (files.length == 0) {
				fa = new FileArray();
				fa.setType(6);
				list.add(fa);
			}
			for (int i = 0; i < files.length; i++) {
				fa = new FileArray();
				File s = files[i].getAbsoluteFile();
				String str = s.toString();
				beginIndex = str.lastIndexOf("\\");
				fa.setPath(str.substring(beginIndex + 1));
				fa.setType(getFileType(s.toString()));
				list.add(fa);
			}
		}
		JSONArray json = JSONArray.fromObject(list);
		response.setReturnByte(json.toString().getBytes("utf-8"));
		String head = "HTTP/1.1 200 OK\r\n";
		String type = "Content-Type: text/html\r\n" + "\r\n";
		response.setHeadMessage(head);
		response.setType(type);
		return response;
	}

	/**
	 * 设置文件类型
	 * @param str
	 * @return
	 */
	private int getFileType(String path) {
		int beginIndex = path.lastIndexOf('.');
		if (beginIndex == -1) {
			return 6;
		}
		String str = path.substring(beginIndex);
		//处理为图片
		if (str.equals(".png") || str.equals(".jpg") || str.equals(".jpeg") || str.equals(".gif")
				|| str.equals(".bmp")) {
			return 1;
		}
		//处理为pdf
		if (str.equals(".pdf")) {
			return 2;
		}
		//处理为MP3格式
		if (str.equals(".mp3")) {
			return 3;
		}
		//处理为MP3格式
		if (str.equals(".mp4")) {
			return 4;
		}
		if (str.equals(".rar")) {
			return 5;
		}
		return 0;
	}

	/**
	 * 当目录为文件时，返回文件内容
	 * @param root
	 * @return
	 * @throws IOException 
	 */
	private String getContent(File root) throws IOException {
		return new String(FileRead.file2buf(root), "utf-8");
	}
}
