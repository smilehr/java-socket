package com.huaui.test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.huarui.demo.IntToOther;

/**
 * 测试十进制转为十六进制
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huarui
 * @createdate 2017年7月3日
 */
public class IntToOtherTest {
	IntToOther ito = new IntToOther();

	StringBuilder res = ito.intToHex(4);
	StringBuilder res2 = ito.intToHex(-2147483648);
	StringBuilder res3 = ito.intToHex(2147483647);
	StringBuilder res4 = ito.intToHex(0);
	StringBuilder res5 = ito.intToHex(-3);

	@Test
	public void testIntToHex() {
		assertEquals(res.toString(), Integer.toHexString(4));
		assertEquals(res2.toString(), Integer.toHexString(-2147483648));
		assertEquals(res3.toString(), Integer.toHexString(2147483647));
		assertEquals(res4.toString(),  Integer.toHexString(0));
		assertEquals(res5.toString(),  Integer.toHexString(-3));
	}
}
