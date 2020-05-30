package org.muqit.calculator.services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Before;
import org.junit.Test;
public class CalculateServiceTest {

	
	CalcuateService calcuateService;
	
	@Before
    public void setup() {
		calcuateService = new CalcuateService();
	}
	
	@Test
	public void shouldReturnZeroForEmptyString() {
		
		Integer sum = calcuateService.add("");
		assertEquals(sum, 0);
	}
	
	@Test
	public void shouldCalculateSumForCommaSeparatedNumbers() {
		
		Integer sum = calcuateService.add("1,2,3");
		assertEquals(sum, 6);
	}
	
	@Test
	public void shouldCalculateSumForCommaORNewLineSeparatedNumbers() {
		
		Integer sum = calcuateService.add("1\n2,4\n7");
		assertEquals(sum, 14);
	}
	
}
