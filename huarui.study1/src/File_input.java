import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 培训第一题，文件操作
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author Hr
 * @createdate 2017年6月29日
 */
public class File_input {

	/**
	  * 该函数用于读取文件内容，并转换成数组返回
	  * @param fobj,传入的文件参数
	  * @param file_read,返回的字节数组
	  * @return 
	  * @throws IOException 
	  */
	public static byte[] file2buf(File fobj) throws IOException {
		byte[] file_read = null;
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
		try {
			fis = new FileInputStream(fobj);//文件读取流
			int file_size = (int) fobj.length(); //根据文件大小设置byte数组大小
			file_read = new byte[file_size];
			while (fis.read(file_read) != -1) {
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			fis.close();
		}
		return file_read;
	}
}
