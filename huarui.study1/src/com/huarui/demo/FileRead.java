package com.huarui.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 培训第一题，文件操作
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author Huarui
 * @createdate 2017年6月29日
 */
public class FileRead {

	/**
	  * 该函数用于读取文件内容，并转换成数组返回
	  * @param fobj,传入的文件参数
	  * @param file_read,返回的字节数组
	  * @return 当传入的参数不符合要求或抛出影响结果的异常时，返回null，
	  * 其它时候就返回file_read
	  * @throws IOException 
	  */
	public static byte[] file2buf(File fobj) throws IOException {
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
			try {
				if (fis != null) {
					fis.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
