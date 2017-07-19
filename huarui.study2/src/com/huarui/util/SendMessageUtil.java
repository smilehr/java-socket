package com.huarui.util;

import java.io.IOException;
import java.io.OutputStream;
import com.huarui.intel.Response;

/**
 * 发送返回的数据
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huarui
 * @createdate 2017年7月18日
 */
public class SendMessageUtil {

	public static void sendMessage(Response response, OutputStream output) {
		try {
			output.write(response.getHeadMessage().getBytes("utf-8"));
			output.write(response.getType().getBytes("utf-8"));
			output.write(response.getReturnByte());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
