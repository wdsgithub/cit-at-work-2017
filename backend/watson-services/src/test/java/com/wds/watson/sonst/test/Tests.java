package com.wds.watson.sonst.test;

import org.junit.Test;

public class Tests {

	@Test
	public void testClassfierString(){
		String s ="{\"classifier_ids\": [\"Disney_1879524408\"],"
			    + "\"owners\": [\"40f94340-89b4-4c70-b512-2b0b80d5ca82\"]}";
		System.out.println(s);
	}
}
