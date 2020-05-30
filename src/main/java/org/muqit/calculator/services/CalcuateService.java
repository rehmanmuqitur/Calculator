package org.muqit.calculator.services;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CalcuateService {
	
	public Integer add(String equation) {
		if(StringUtils.isEmpty(equation)) {
			return 0;
		} else {
			String[] numbers = equation.split(",|\n");
			return getSum(numbers);
		}
	}
	
	public Integer getSum(String[] numbers) {
		Integer sum = 0;
		for(String num: numbers) {
			sum += Integer.parseInt(num);
		}
		return sum;
	}
}
