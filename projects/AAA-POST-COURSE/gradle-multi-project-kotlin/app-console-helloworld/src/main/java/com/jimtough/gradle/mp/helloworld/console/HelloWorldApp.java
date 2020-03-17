package com.jimtough.gradle.mp.helloworld.console;

import com.jimtough.gradle.mp.hellofactory.HelloFactory;
import com.jimtough.gradle.mp.worldfactory.WorldFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldApp.class);

	static String generateHelloWorldString() {
		LOGGER.info("Someone wants to generate a hello world string");
		// What a complicated way to say "Hello, World!"  ;)
		return HelloFactory.INSTANCE.getHello() + ", " + WorldFactory.INSTANCE.getWorld() + "!";
	}

	public static void main(String[] args) {
		System.out.println(generateHelloWorldString());
	}

}
