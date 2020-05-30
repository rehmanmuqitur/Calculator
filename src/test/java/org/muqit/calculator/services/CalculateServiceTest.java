package org.muqit.calculator.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.muqit.calculator.constants.MessageConstants;
import org.muqit.calculator.exceptions.NegetiveNumberPresentException;

public class CalculateServiceTest {

	
	CalcuateService calcuateService;
	
	@Before
    public void setup() {
		calcuateService = new CalcuateService();
	}
	
	// Test Case - 1
	@Test
	public void shouldReturnZeroForEmptyString() {
		
		Integer sum = calcuateService.add("");
		assertEquals(sum, 0);
	}
	
	// Test Case - 2
	@Test
	public void shouldCalculateSumForCommaSeparatedNumbers() {
		
		Integer sum = calcuateService.add("1,2,3");
		assertEquals(sum, 6);
	}
	
	// Test Case - 3
	@Test
	public void shouldCalculateSumForCommaORNewLineSeparatedNumbers() {
		
		Integer sum = calcuateService.add("1\n2,4\n7");
		assertEquals(sum, 14);
	}
	
	// Test Case - 5-a
	@Test(expected = NegetiveNumberPresentException.class)
	public void shouldThrowExceptionIfNegativeNumberExists() {
		
		calcuateService.add("1,-2,3");
	}
	
	// Test Case - 5-b
	@Test()
	public void shouldThrowExceptionIfMultipleNegativeNumberExists() {
		
		final String equation = "1,-2,-3,4,5,-8\\n9,-7";
		final String allNegetiveNumbersInEquation = "-2,-3,-8,-7";
				
		Integer sum = null;
		try {
			sum = calcuateService.add(equation);
			
		} catch (NegetiveNumberPresentException ex) {
			assertEquals(MessageConstants.MULTIPLE_NEGETIVE_NUMBERS_MESSAGE + allNegetiveNumbersInEquation, ex.getMessage());
		}
		
		assertEquals(sum, null);
	}
	
	// Test Case - 6
	@Test()
	public void shouldIgnoreNumbersGreaterThanOneThousand() {
		
		Integer sum = calcuateService.add("1,2,3,3000,2000,6");
		
		assertEquals(sum, 12);
	}
	
}
