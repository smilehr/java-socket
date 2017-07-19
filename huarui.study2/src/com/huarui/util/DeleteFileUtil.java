package com.huarui.util;

import java.io.File;

/**
 * 删除文件 工具类
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author Hr
 * @createdate 2017年7月19日
 */
public class DeleteFileUtil {
	/**
	 * 删除文件或文件夹
	 * @param file，传入的文件参数
	 * @return
	 */
	public void deleteFile(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			}
			else if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					this.deleteFile(files[i]);
				}
				file.delete();
			}
		}
		else {
			System.out.println("所删除的文件不存在");
		}
	}
}
