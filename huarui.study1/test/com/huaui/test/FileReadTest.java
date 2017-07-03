package com.huaui.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.junit.Assert;
import org.junit.Test;

import com.huarui.demo.FileRead;

import junit.framework.TestCase;

/**
 * 测试fileread类
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huarui
 * @createdate 2017年7月3日
 */
public class FileReadTest extends TestCase {

	FileRead fr = new FileRead();

	/**
	 * 按照不同的文件扩展名创建文件
	 * @throws IOException
	 */
	public File creatFile(String path, String encoding, String content) throws IOException {
		File file = new File(path);
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));
			bw.write(content);
			bw.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (fw != null) {
				fw.close();
			}
			if (bw != null) {
				bw.close();
			}
		}
		return file;
	}

	@Test
	public void testFile2buf() throws IOException {
		File fis_txt = null;
		File fis_txt1 = null;
		File fis_mp3 = null;
		String path_txt = "D:\\test.txt";
		String path_txt1 = "D:\\test1.txt";
		String path_mp3 = "D:\\test.mp3";
		//相同内容相同编码
//		fis_txt = creatFile(path_txt, "utf-8", "测试用例！");
//		fis_txt1 = creatFile(path_txt1, "utf-8", "测试用例！");
		
		//相同内容不同编码
//		fis_txt = creatFile(path_txt, "utf-8", "测试用例！");
//		fis_txt1 = creatFile(path_txt1, "GBK", "测试用例！");
		
		//相同内容相同编码不同扩展名
//		fis_txt = creatFile(path_txt, "utf-8", "测试用例！");
//		fis_mp3 = creatFile(path_mp3, "utf-8", "测试用例！");
		
		Assert.assertArrayEquals(FileRead.file2buf(fis_txt), FileRead.file2buf(fis_mp3));
	}
}
