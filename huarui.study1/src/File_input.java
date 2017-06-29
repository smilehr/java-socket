import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class File_input {
	/**
	  * 
	  * 该函数用于读取文件内容，并转换成数组返回
	  * @param fobj,传入的文件参数
	  * @param file_read,返回的字节数组
	  * @return file_read
	  * @author huarui
	  * @createdate 20170627
	  */
	public static byte[] file2buf(File fobj){
		
		byte[] file_read = null;
		try{
            FileInputStream fis = new FileInputStream(fobj);	//文件读取流
            int file_size = (int) fobj.length();	//设置byte数组大小
            ByteArrayOutputStream baos = new ByteArrayOutputStream(file_size);	//字节输出流
            int i;
            byte[] b = new byte[file_size];
            while((i = fis.read(b)) != -1){
            	baos.write(b,0,i);
            }
            fis.close();
            baos.close();
            file_read = baos.toByteArray();
            
		}catch(FileNotFoundException e){
			System.out.println("文件未找到");
			return null;						
		}catch (IOException e) {  
            e.printStackTrace();  
        }  
		return file_read;
	}
}
