package com.huarui.demo;

/**
 * 培训第二题，十进制转换成十六进制
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author HuaRui
 * @createdate 2017年6月29日
 */
public class IntToOther {

	/**
	 * 字符数组，byte数组转换为字符串媒介
	 */
	private final static char[] HEX_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	/**
	 * 将负整数转换成byte数组
	 * @param obj,传入的整数数
	 * @param bytes[] 返回的byte数组
	 * @return
	 */
	public static byte[] negIntToBinary(int obj, byte[] bytes) {
		int rem;
		boolean isCarry = true;
		for (int i = 7; i >= 0; i--) {
			rem = obj % 16;
			bytes[i] = (byte) (rem ^ 0xF);
			obj >>= 4;
			if(isCarry){
				if(bytes[i] == 15){
					bytes[i] = 0;
				}
				else{
					bytes[i]++;
					isCarry = false;
				}
			}			
		}
		return bytes;
	}

	/**
	 * 将正整数转换为byte数组
	 * @param obj,传入的整数
	 * @param bytes[] 返回的二进制数组
	 * @return
	 */
	private static byte[] posIntToBinary(int obj, byte[] bytes) {
		for (int i = 7; i >= 0; i--) {
			if (obj == 0) {
				bytes[i] = 0;
			}
			else {
				bytes[i] = (byte) (obj % 16);
				obj = obj >> 4;
			}
		}
		return bytes;
	}

	/**
	  * 将整数转换成十六进制，并返回字符串
	  * @param bytes，存放obj转换的十六进制数值
	  * @param obj,传入的整数数值
	  * @param hex，转换好的十六进制字符串
	  * <pre>
	  * IntToOther.intToHex(2147483647) = "7fffffff";
	  * IntToOther.intToHex(-3) = "fffffffd";
	  * IntToOther.intToHex(1) = "1";
	  * IntToOther.intToHex(-2147483648) = "80000000";
	  * </pre>
	  * @return
	  */
	public String intToHex(int obj) {

		StringBuilder hex = new StringBuilder(8);
		//当传入的十进制数为int最小值的情况
		if (obj == Integer.MIN_VALUE) {
			hex.append("80000000");
			return hex.toString();
		}
		byte[] bytes = new byte[8];
		//获取整数转换成16进制后的byte数组
		if (obj < 0) {
			bytes = negIntToBinary(Math.abs(obj), bytes);
		}
		else {
			bytes = posIntToBinary(obj, bytes);
		}
		//将byte数组bytes转换成字符串hex
		for (int i = 0; i < 8; i++) {
			if (bytes[i] == 0 && hex.length() == 0) {
				if(i == 7){
					hex.append('0');
				}
				continue; //进行首位去0处理
			}
			//字符串拼接
			hex.append(HEX_CHARS[bytes[i]]);
		}
		return hex.toString();
	}
}
