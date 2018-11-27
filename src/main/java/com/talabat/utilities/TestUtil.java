package com.talabat.utilities;

import com.talabat.base.TestBase;

public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT=40;
	public static long IMPLICIT_WAIT=40;
	
	public void switchToFrame(String frameName) {
		DRIVER.switchTo().frame(frameName);
	}
	
}
