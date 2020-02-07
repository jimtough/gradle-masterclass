package com.jimtough.example;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class AppTest {

	@Test
	public void testGetSuperExcitingMessage() {
		assertNotNull(App.getSuperExcitingMessage());
	}

}
