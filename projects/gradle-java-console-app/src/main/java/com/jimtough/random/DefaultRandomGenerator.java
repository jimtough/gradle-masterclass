package com.jimtough.random;

//import org.apache.commons.math3.random.RandomDataGenerator;

public class DefaultRandomGenerator implements RandomGenerator {

	public String name() {
		return "Default Random Number Generator";
	}

	public int generate() {
		throw new UnsupportedOperationException("stub");
//		final RandomDataGenerator aRandomDataGenerator = new RandomDataGenerator();
//		return aRandomDataGenerator.nextInt(1, 10);
	}

}
