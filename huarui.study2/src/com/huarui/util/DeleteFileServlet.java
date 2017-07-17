package com.huarui.util;

import java.io.File;

/**
 * 删除文件或文件夹
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author Hr
 * @createdate 2017年7月17日
 */
public class DeleteFileServlet {

	public boolean deleteFile(File file) {
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
			return true;
		}
		else {
			System.out.println("所删除的文件不存在");
			return false;
		}
	}
}
