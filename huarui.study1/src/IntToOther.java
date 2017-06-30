/**
 * 培训第二题，十进制转换成十六进制
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author HuaRui
 * @createdate 2017年6月29日
 */
public class IntToOther {

	/**
	 * 字符数组，二进制转16进制时进行数值转换
	 */
	private final static char[] KEY_HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	/**
	 * 将负整数转换成二进制数组
	 * @param obj,传入的整数数
	 * @param int_hex[] 返回的二进制数组
	 * @return
	 */
	public static int[] negIntToBinary(int obj, int[] int_hex) {
		for (int i = 31; i >= 0; i--) {
			if (obj == 0) {
				int_hex[i] = 1;
			}
			else {
				int_hex[i] = obj % 2;
				obj >>= 1;
			}
		}
		return int_hex;
	}

	/**
	 * 将正整数转换成二进制数组
	 * @param obj,传入的整数
	 * @param int_hex[] 返回的二进制数组
	 * @return
	 */
	public static int[] posIntToBinary(int obj, int[] int_hex) {
		for (int i = 31; i >= 0; i--) {
			if (obj == 0) {
				int_hex[i] = 0;
			}
			else {
				int_hex[i] = obj % 2;
				obj = obj >> 1;
			}
		}
		return int_hex;
	}

	/**
	  * 将整数转换成十六进制字符串
	  * @param int_hex，存放obj转换的二进制数值
	  * @param obj,传入的整数数值
	  * @param sum,二进制转换成十六进制时临时变量
	  * @param hex，转换好的十六进制字符串
	  * @param key_hex,预定义数组，用来处理字符串拼接
	  * @return
	  */
	public static StringBuilder intToHex(int obj) {

		if (obj > Integer.MAX_VALUE || obj < Integer.MIN_VALUE) {
			System.out.print("输入的数值超出范围");
			return null;
		}
		StringBuilder hex = new StringBuilder();
		//当传入的十进制数为负数的情况
		if (obj == Integer.MIN_VALUE) {
			hex.append('0');
			return hex;
		}
		int[] int_hex = new int[32];
		int sum;

		//获取整数转换成二进制后的数组
		if (obj < 0) {
			int_hex = negIntToBinary(Math.abs(obj), int_hex);
		}
		else {
			int_hex = posIntToBinary(obj, int_hex);
		}
		//将二进制数组int_hex转换成十六进制字符串hex
		for (int i = 0; i < 32; i += 4) {
			sum = int_hex[i + 3] + (int_hex[i + 2] << 1) + (int_hex[i + 1] << 2) + (int_hex[i] << 3);
			if (sum == 0 && hex.length() == 0) {
				continue; //进行首位去0处理
			}
			//字符串拼接
			hex.append(KEY_HEX[sum]);
		}
		return hex;
	}
}
