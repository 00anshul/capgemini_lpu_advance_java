package com.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ClaculatorTest {
public void addTest() {
	int res= Calculator.add(20,32);
}
@Test
public void reverseTest() {
	String actualres = Calculator.reverseString("data");
	
	assertEquals("atad", actualres);
}
@Test
public void reverseTestNull() {
	String actualres = Calculator.reverseString(null);
	assertEquals("llun", actualres);
}
@Test
public void palindromeTest() {
	int actualres = Calculator.palindrome(2341);
	assertEquals(1432, actualres);
}

@Test
public void factorialTest() {
	int actualres = Calculator.factorial(5);
	assertEquals(120, actualres);
}


@Test
public void AEtest() {
	Calculator c = new Calculator();
	assertThrows(ArithmeticException.class, ()->{c.div(10,0);});
	}

}
