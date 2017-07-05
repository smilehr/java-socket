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

	@Test
	public void testFile2buf() throws IOException {
		//测试不同扩展名文件读取
		File fis_pdf = creatFile("file_create/1.pdf", "utf-8", "测试用例utf-8！");
		Assert.assertEquals("测试用例utf-8！", new String(FileRead.file2buf(fis_pdf), "utf-8"));

		File fis_txt = creatFile("file_create/1.txt", "utf-8", "测试用例utf-8！");
		Assert.assertEquals("测试用例utf-8！", new String(FileRead.file2buf(fis_txt), "utf-8"));

		File fis_doc = creatFile("file_create/1.doc", "utf-8", "测试用例utf-8！");
		Assert.assertEquals("测试用例utf-8！", new String(FileRead.file2buf(fis_doc), "utf-8"));

		File fis_jpg = creatFile("file_create/1.jpg", "utf-8", "测试用例utf-8！");
		Assert.assertEquals("测试用例utf-8！", new String(FileRead.file2buf(fis_jpg), "utf-8"));

		File fis_mp3 = creatFile("file_create/1.mp3", "utf-8", "测试用例utf-8！");
		Assert.assertEquals("测试用例utf-8！", new String(FileRead.file2buf(fis_mp3), "utf-8"));

		//测试相同内容不同编码
		File fis_txt1 = creatFile("file_create/2.txt", "GBK", "测试用例utf-8！");
		Assert.assertEquals(new String(FileRead.file2buf(fis_txt), "utf-8"),
				new String(FileRead.file2buf(fis_txt1), "GBK"));

		//测试不同内容相同编码
		File fis_txt2 = creatFile("file_create/2.txt", "utf-8", "测试用例GBK！");
		Assert.assertNotEquals(new String(FileRead.file2buf(fis_txt), "utf-8"),
				new String(FileRead.file2buf(fis_txt2), "utf-8"));

		//测试不同内容相同编码
		fis_txt2 = creatFile("file_create/2.txt", "GBK", "测试用例GBK！");
		Assert.assertNotEquals(new String(FileRead.file2buf(fis_txt), "utf-8"),
				new String(FileRead.file2buf(fis_txt2), "GBK"));

		//测试大文件
		File big_file = new File("file_create/Foxmail.exe");
		Assert.assertEquals(big_file.length(), FileRead.file2buf(big_file).length);

	}

	/**
	 * 测试特殊情况
	 * @throws IOException 
	 */
	@Test
	public void testFlie2bufSpecial() throws IOException {
		//测试参数为空的情况
		File null_file = null;
		Assert.assertArrayEquals(null, FileRead.file2buf(null_file));

		//测试目录，非文件情况
		File not_file = new File("file_create/");
		Assert.assertArrayEquals(null, FileRead.file2buf(not_file));

		//测试文件不存在的情况
		File no_file = new File("file_create/4.txt");
		Assert.assertArrayEquals(null, FileRead.file2buf(no_file));

		//测试文件过大的情况
		File eor_file = new File("D:\\Applications.rar");
		Assert.assertArrayEquals(null, FileRead.file2buf(eor_file));
	}

	/**
	 * 按照不同的文件扩展名创建文件
	 * @param path,创建文件的路径
	 * @param encoding,文件编码方式
	 * @param content,文件内容
	 * @return file
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

}
