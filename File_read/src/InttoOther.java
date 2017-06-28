import java.util.Scanner;


public class InttoOther {
	/**
	  * 培训第二题，十进制转换成十六进制
	  * 
	  * @parm obj,传入的整数数值
	  * @return hex，StringBuilder类型，返回转换好的十六进制字符串
	  * @author huarui
	  * @createdate 20170627
	  */	
	public static StringBuilder IntToHex(int obj){
		
		int[] int_hex = new int[32];
		StringBuilder hex = new StringBuilder("");
		int i,sum,all;
		
		//当传入的十进制数为负数的情况
		if(obj < 0){
			int obj1 = Math.abs(obj);
			//计算二进制下的反码
			for(i = 31;i >= 0;i--){
				if(obj1 == 0){
					int_hex[i] = 1;
				}else {
					int_hex[i] = obj1 % 2;
					if(int_hex[i] == 0) int_hex[i] = 1;
					else int_hex[i] = 0;
					obj1 = obj1 /2;
				}			
			}
			//以二进制的算法将反码+1
			for(i=31;i>=0;i--){	
				sum = int_hex[i] + 1;
				if(sum == 2){
					int_hex[i] = 0;
				}else {
					int_hex[i] += 1;
					break;
				}
			}		
		}
		
		//处理输入的整数为非负数的情况
		else{
			for(i = 31;i>=0;i--){
				if(obj == 0){
					int_hex[i] = 0;
				}else{
					int_hex[i] = obj % 2;
					obj = obj /2;
				}
			}
		}
		//将二进制数组int_hex转换成十六进制字符串hex
		for(i = 0;i<32;i+=4){
			sum = int_hex[i+3] + int_hex[i+2]*2 + int_hex[i+1]*2*2 + int_hex[i]*2*2*2;
			if(sum == 0 && hex.length()==0) continue;	//进行首位去0处理
			switch (sum) {
				case 10: 
					hex.append("a");
					break;
				case 11: 
					hex.append("b");
					break;
				case 12: 
					hex.append("e");
					break;
				case 13: 
					hex.append("d");
					break;
				case 14: 
					hex.append("e");
					break;
				case 15: 
					hex.append("f");
					break;

				default:
					hex.append(sum);
					break;
			}
		}
		return hex;
		
	}
}
