package com.huarui.intel;

import java.io.IOException;

/**
 * Request接口，实现头部解析
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huarui
 * @createdate 2017年7月13日
 */
public interface Request {
	public String getMethod();
	
	public String getUrl();
	
	public String getParm();
	
	public void parseRequest() throws IOException;
}
