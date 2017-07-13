package com.huarui.util;

import java.io.Closeable;

public class CloseStream {
	// 关闭流或者Socket  
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
