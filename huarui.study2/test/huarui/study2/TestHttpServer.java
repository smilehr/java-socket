package huarui.study2;

import java.io.IOException;

import org.junit.Test;

import com.huarui.server.HttpServer;
import com.huarui.server.HtttServlet;
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
	public void testHttpServer() throws Exception {
		HttpServer hs = new HttpServer();
		hs.await();
	}
	
	@Test
	public void testGetFileServlet() throws IOException{
		ShowFileServerlet fs =new ShowFileServerlet();
		System.out.println(fs.getFileServlet("D:\\"));
	}
	
	@Test
	public void testHttpServerlet(){
		HtttServlet m = new HtttServlet();
		m.startWorkerThreads();
		m.await();
	}
}
