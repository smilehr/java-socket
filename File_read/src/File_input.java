import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
  * 
  * 该函数用于读取文件内容，并转换成数组返回
  * @parm fobj,传入的文件参数
  * @return 
  * @author huarui
  * @createdate 20170627
  */
public class File_input {

	public static byte[] file2buf(File fobj){
		
		byte[] file_read = null ;
		try{
//			if(!fobj.exists()){
//				return file_read;
//			}
            FileInputStream fis = new FileInputStream(fobj);  
            
		}catch(FileNotFoundException e){
			System.out.println("文件未找到");
			return file_read;						
		}catch (IOException e) {  
            e.printStackTrace();  
        }  
		return file_read;
	}
}
