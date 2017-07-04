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
		String path_pdf = "D:\\test.pdf";
		String path_doc = "D:\\test.doc";
		fis_txt = creatFile(path_txt, "utf-8", "测试用例1！");
		fis_txt1 = creatFile(path_txt1, "utf-8", "测试用例1！");
		fis_mp3 = creatFile(path_mp3, "utf-8", "测试用例3！");
		
		//测试不同扩展名文件读取
		File fis_pdf = creatFile(path_pdf,"utf-8","测试用例！");
		Assert.assertEquals(fis_pdf.length(), FileRead.file2buf(fis_pdf).length);
		File fis_doc = creatFile(path_doc,"utf-8","测试用例！");
		Assert.assertEquals(fis_pdf.length(), FileRead.file2buf(fis_doc).length);
		Assert.assertEquals(fis_txt.length(), FileRead.file2buf(fis_txt).length);
		Assert.assertEquals(fis_mp3.length(), FileRead.file2buf(fis_mp3).length);
		//相同内容相同编码名字不同
		
		Assert.assertArrayEquals(FileRead.file2buf(fis_txt), FileRead.file2buf(fis_txt1));
		
		//相同内容不同编码
		fis_txt = creatFile(path_txt, "utf-8", "测试用例2！");
		fis_txt1 = creatFile(path_txt1, "GBK", "测试用例2！");
		Assert.assertNotEquals(FileRead.file2buf(fis_txt), FileRead.file2buf(fis_txt1));
		
		//不同内容相同编码
		fis_txt = creatFile(path_txt, "utf-8", "测试用例2！");
		fis_txt1 = creatFile(path_txt1, "utf-8", "测试用例23！");
		Assert.assertNotEquals(FileRead.file2buf(fis_txt), FileRead.file2buf(fis_txt1));
		//不同内容不同编码
		fis_txt1 = creatFile(path_txt1, "GBK", "测试用例23！");
		Assert.assertNotEquals(FileRead.file2buf(fis_txt), FileRead.file2buf(fis_txt1));				
		
		//相同内容相同编码不同扩展名
		fis_txt = creatFile(path_txt, "utf-8", "测试用例3！");		
		Assert.assertArrayEquals(FileRead.file2buf(fis_txt), FileRead.file2buf(fis_mp3));
		
		//测试目录，非文件情况
		File not_file = new File("D:\\");
		Assert.assertArrayEquals(null, FileRead.file2buf(not_file));
		
		//测试大文件的情况
		File big_file = new File("D:\\Office 2013(64位VOL版)+激活工具.rar");
		Assert.assertEquals(big_file.length(), FileRead.file2buf(big_file).length);
		
		//测试文件不存在的情况
		File no_file = new File("D:\\1.txt");
		Assert.assertArrayEquals(null, FileRead.file2buf(no_file));
		
		//测试文件过大的情况
		File eor_file = new File("D:\\Applications.rar");
		Assert.assertArrayEquals(null, FileRead.file2buf(eor_file));
		
	}
}
