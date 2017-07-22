package com.huarui.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.huarui.model.FileArray;

import net.sf.json.JSONArray;

/**
 * 文件操作工具类
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author Hr
 * @createdate 2017年7月21日
 */
public class FileUtils {

	/**
	 * 根据名字和路径创建文件或文件夹
	 * @param name
	 * @param path
	 */
	public static void createFile(String name, String path) {
		File file = new File(path + "/" + name);
		if (name.contains(".")) {
			if (!file.exists()) {
				try {
					file.createNewFile();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else {
			if (!file.exists()) {
				file.mkdir();
			}
		}
	}

	/**
	 * 删除文件或文件夹
	 * @param file，传入的文件参数
	 * @return
	 */
	public static void deleteFile(File file) {
		if (!file.exists()) {
			System.out.println("所删除的文件不存在");
			return;
		}
		if (file.isFile()) {
			file.delete();
			return;
		}
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteFile(files[i]);
			}
			file.delete();
		}
	}

	/**
	 * 修改文件内容
	 * @param file
	 * @param content
	 */
	public static void modifyFile(File file, String content) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			bw.write(content);
			bw.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			IOUtils.close(fw, bw);
		}
	}

	/**
	 * 展示path目录下的内容或显示文件内容
	 * @param path
	 * @throws IOException 
	 * @throws  
	 */
	public static JSONArray showFile(String path) throws IOException {
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
				fa.setContent(new String(file2buf(root), "UTF-8"));
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
		return json;
	}

	/**
	 * 设置文件类型
	 * @param str
	 * @return
	 */
	private static int getFileType(String path) {
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
	  * 读取文件内容，并转换成数组返回
	  * @param fobj,传入的文件参数
	  * @param file_read,返回的字节数组
	  * @return 当传入的参数不符合要求或抛出影响结果的异常时，返回null，
	  * 其它时候就返回file_read
	  * @throws IOException 
	  */
	private static byte[] file2buf(File fobj) throws IOException {
		if (fobj == null) {
			System.out.println("传入的参数为空！");
			return null;
		}
		if (!fobj.exists()) {
			System.out.println("文件不存在！");
			return null;
		}
		if (!fobj.isFile()) {
			System.out.println("传入的并不是一个文件！");
			return null;
		}
		if (fobj.length() > Integer.MAX_VALUE) {
			System.out.println("文件过大！");
			return null;
		}
		FileInputStream fis = null;
		int file_size = (int) fobj.length(); //根据文件大小设置byte数组大小
		int off = 0;
		int len = 0;
		int rest = 0;
		try {
			fis = new FileInputStream(fobj);//文件读取流
			byte[] file_read = new byte[file_size];
			while (off < file_size && (len = fis.read(file_read, off, rest)) != -1) {
				off += len;
				rest = file_size - off > 4096 ? 4096 : file_size - off;
			}
			return file_read;
		}
		catch (FileNotFoundException e) {
			System.out.println("文件读取错误！");
		}
		finally {
			IOUtils.close(fis);
		}
		return null;
	}
}
