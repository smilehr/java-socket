package huarui.study2;

import java.io.IOException;

import org.junit.Test;
import com.huarui.servlet.MyHttpServlet;
import com.huarui.util.ShowFileServerlet;

/**
 * 测HttpServer类
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author Hr
 * @createdate 2017年7月6日
 */
public class TestHttpServer {
	
	@Test
	public void testGetFileServlet() throws IOException{
		ShowFileServerlet fs =new ShowFileServerlet();
		System.out.println(fs.getFileServlet("D:\\"));
	}
	
	@Test
	public void testMyHttpServerlet(){
		MyHttpServlet m = new MyHttpServlet();
		m.await();
	}
}
