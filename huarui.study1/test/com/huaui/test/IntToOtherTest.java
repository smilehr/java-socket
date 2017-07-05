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

	@Test
	public void testIntToHex() {
		assertEquals(ito.intToHex(4), Integer.toHexString(4));
		assertEquals(ito.intToHex(Integer.MIN_VALUE), Integer.toHexString(Integer.MIN_VALUE));
		assertEquals(ito.intToHex(Integer.MAX_VALUE), Integer.toHexString(Integer.MAX_VALUE));
		assertEquals(ito.intToHex(0), Integer.toHexString(0));
		assertEquals(ito.intToHex(-0), Integer.toHexString(-0));
		assertEquals(ito.intToHex(-3), Integer.toHexString(-3));
	}
}
