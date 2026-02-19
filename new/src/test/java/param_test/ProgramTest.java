package param_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ProgramTest {
	
//	@ParameterizedTest
//	@ValueSource(strings = { "lowol", "dokod", "haallo" })
//	public void ispalindrome(String s) {
//		Program p = new Program();
//		assertTrue(p.ispalindrome(s));
//	}

//	@ParameterizedTest
//	@CsvSource({ "1,2,3", "5,6,11", "8,3,37" })
//	public void addTest(int a, int b, int expectres) {
//		Program p = new Program();
//		int actres = p.add(a, b);
//		assertEquals(expectres,actres);
//	}
	
	@ParameterizedTest
	@CsvFileSource(resources="/test1.csv",numLinesToSkip = 1)
	public void evenoddTest(String input,String expected) {
		EvenOdd eo = new EvenOdd();
		String actualres=eo.evenOrOdd(Integer.parseInt(input));
		assertEquals(expected, actualres);
	
	}
}
