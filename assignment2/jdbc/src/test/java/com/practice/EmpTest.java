package com.practice;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class EmpTest {
	@Test
	public void validNameTest() {
		Employee e = new Employee(10, "Ram Raj", 20, "CSE");
		assertTrue(e.isValidName());
	}

	@Test
	public void validAgeTest() {
		Employee e = new Employee(10, "Ram Raj", 17, "CSE");
		assertFalse(e.isValidAge());
	}

	@Test
	public void emptytest() {
		assertFalse(EmpMain.createEmployees().isEmpty());
	}

}
