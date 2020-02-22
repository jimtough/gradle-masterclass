package com.jimtough.gradle.mp.helloworld.console;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HelloWorldAppTest {

	@Test
	public void testGenerateHelloWorldString() {
		assertEquals("Hello, World!", HelloWorldApp.generateHelloWorldString());
	}

}
