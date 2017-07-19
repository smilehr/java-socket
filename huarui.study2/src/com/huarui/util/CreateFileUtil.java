package com.huarui.util;

import java.io.File;
import java.io.IOException;

/**
 * 创建文件或文件夹工具类
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huarui
 * @createdate 2017年7月19日
 */
public class CreateFileUtil {

	public static void createFile(String name, String path) {
		File file = new File(path + "/" + name);
		if (name.contains(".")) {
			if (!file.exists()) {
				try {
					file.createNewFile();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else {
			if (!file.exists()) {
				file.mkdir();
			}
		}
	}
}
