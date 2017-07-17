package com.huarui.intel;
/**
 * 定义response接口
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huarui
 * @createdate 2017年7月13日
 */
public interface Response {

	public void setHeadMessage(String head);
	
	public void setBodyMessage(String body);
	
	public void setContentType(String type);
	
	public void setReByte(String returnByte);
	
	public void setFileLength(int length);
}
