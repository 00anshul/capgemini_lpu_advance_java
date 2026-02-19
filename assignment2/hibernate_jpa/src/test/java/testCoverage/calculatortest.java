package testCoverage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class calculatortest {
	@Test
	public void dividetest() {
		calculator c =new calculator();
		int actualres = c.divide(10, 2);
		assertEquals(5, actualres);
	
	}
	@Test
	public void dividetestzero() {
		calculator c =new calculator();
		int actualres = c.divide(10, 0);
		assertEquals(0, actualres);
	
	}
	
	
}
