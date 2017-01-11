package calculator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest {
	private StringCalculator calculator;
	@Before
	public void set(){
		calculator = new StringCalculator();
	}
	@Test
	public void add_null_공백() {
		assertEquals(0, calculator.add(""));
		assertEquals(0, calculator.add(null));
		
	}
	@Test
	public void add_매개변수 (){
		assertEquals(1, calculator.add("1"));
	}
	@Test
	public void add_쉼표구분자 (){
		assertEquals(3, calculator.add("1,2"));
	}
	@Test
	public void add_쉼표이외구분(){
		assertEquals(6, calculator.add("1,2:3"));
	}
	@Test
	public void add_커스텀구분(){
		assertEquals(6,calculator.add("//;\n1;2;3"));
	}

}
