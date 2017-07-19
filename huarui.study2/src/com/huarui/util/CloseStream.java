package com.huarui.util;

import java.io.Closeable;

/**
 * 关闭流或者Socket
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huarui
 * @createdate 2017年7月18日
 */
public class CloseStream {
	public static void close(Closeable... closeables) {
		if (closeables != null) {
			for (Closeable closeable : closeables) {
				try {
					closeable.close();
				}
				catch (Exception ex) {
					// 忽略  
				}
			}
		}
	}
}
