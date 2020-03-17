package com.jimtough.gradle.mp.helloworld.springboot.mvc;

import com.jimtough.gradle.mp.hellofactory.HelloFactory;
import com.jimtough.gradle.mp.worldfactory.WorldFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyRestController.class);

	private static final String RESPONSE_HTML_TEMPLATE = "<html><body><h1>%s</h1></body></html>";

	private String generateHelloWorldResponse() {
		LOGGER.info("Someone wants to generate a hello world string");
		// What a complicated way to say "Hello, World!"  ;)
		return HelloFactory.INSTANCE.getHello() + ", " + WorldFactory.INSTANCE.getWorld() + "!";
	}

	@RequestMapping("/")
	public String methodThatHandlesAllRequests() {
		LOGGER.debug("request received");
		return String.format(RESPONSE_HTML_TEMPLATE, generateHelloWorldResponse());
	}

}
