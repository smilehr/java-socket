import java.util.Scanner;

/**
  * 培训第二题，十进制转换成十六进制
  * 
  * @parm obj
  * @return
  * @author huarui
  * @createdate 20170627
  */
public class InttoOther {
	
	public static StringBuilder IntToHex(int obj){
		
		StringBuilder hex = new StringBuilder(""); //参数，返回字符串	
		int rmd;	//obj转换时余数
		if(obj %16 ==0){
			hex.append("0");
		}
		while(obj%16 != 0){
			rmd = obj % 16;
			obj = obj / 16;
			switch(rmd){
				case 10:	hex.append('a');	break;
				case 11:	hex.append('b');	break;
				case 12:	hex.append('c');	break;
				case 13:	hex.append('d');	break;
				case 14:	hex.append('e');	break;
				case 15:	hex.append('f');	break;
				default: 	hex.append(rmd);	
			}
		}
		System.out.print(hex);
		return hex;
		
	}
	
}
