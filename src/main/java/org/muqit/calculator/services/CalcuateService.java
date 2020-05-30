package org.muqit.calculator.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.muqit.calculator.constants.CalculationLogicConstant;
import org.muqit.calculator.constants.MessageConstants;
import org.muqit.calculator.constants.RejexConstants;
import org.muqit.calculator.exceptions.NegetiveNumberPresentException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CalcuateService {
	
	public Integer add(String equation) {
		if(StringUtils.isEmpty(equation)) {
			return 0;
		} else {
			return identifyNatureAndCaluclate(equation);
		}
	}
	
	public Integer identifyNatureAndCaluclate(String equation) {
		
		if(isDelimeteredContent(equation)) {
			return calculateForDelimeters(equation);
			
		} else if (isNegetiveNumbersPresent(equation)) {
			return handleNegetiveNumbers(equation);
			
		} else {
			String[] numbers = equation.split(",|\n");
			return getSum(numbers);
		}
		
	}
	
	private boolean isDelimeteredContent(String equation) {
		return equation.startsWith("//");
	}
	
	private boolean isNegetiveNumbersPresent(String equation) {
		return equation.contains("-");
	}
	
	public Integer getSum(String[] numbers) {
		Integer sum = 0;
		for(String num: numbers) {
			Integer number = Integer.parseInt(num);
			if(number < CalculationLogicConstant.ONE_THOUSAD) {
				sum += number;
			}
		}
		return sum;
	}
	
	private Integer handleNegetiveNumbers(String equation) throws NegetiveNumberPresentException {
	
		StringBuilder sb = new StringBuilder();
		Matcher matcher =  Pattern.compile(RejexConstants.NEGETIVE_NUMBERS_ONLY_REJEX).matcher(equation);
		
		int negetiveCount = 0;
		while(matcher.find()) {
			
			if(negetiveCount++ > 0) {
				sb.append(",");
			}
			sb.append(matcher.group());
		}
		if(negetiveCount > 1) {
			throw new NegetiveNumberPresentException(MessageConstants.MULTIPLE_NEGETIVE_NUMBERS_MESSAGE + sb);
		} else {
			throw new NegetiveNumberPresentException();
		}
	}
	
	private Integer calculateForDelimeters(String equation) {
		
		/*
		 * so it is considered only valid(as described in question) input will be passed.
		 * 
		 * other wise multiple Rejex can used to verify input for eg full match on this Rejex: "//[^\\w]+\n([\\d]+[^\\w]+)+"  
		 * could verify input: '//;;\n1;2;8;;88;;8" and reject input: "//;;\n1;2;8;;;88;;"
		 */
		
		
		/*
		 * using Rejex: "[\\d]+" to get all Numbers from Input
		 */
		Integer sum = 0;
		Matcher matcher =  Pattern.compile(RejexConstants.NUMBERS_ONLY_REJEX).matcher(equation);
		while(matcher.find()) {
			sum += Integer.parseInt(matcher.group());
		}
		return sum;
	}
	
}
