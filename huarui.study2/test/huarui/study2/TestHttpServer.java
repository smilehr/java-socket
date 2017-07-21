package huarui.study2;

import org.junit.Test;

import com.huarui.server.HttpServer;

/**
 * 测HttpServer类
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author Hr
 * @createdate 2017年7月6日
 */
public class TestHttpServer {

	@Test
	public void testHttpServer() {
		HttpServer h = new HttpServer();
		h.await();
	}
}
