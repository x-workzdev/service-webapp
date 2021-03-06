package com.xworkz.rental.utility.passwordGenerater;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class AutoGenerateString {

	 public static String autoGenerateString() {
		 return RandomStringUtils.randomAlphanumeric(8);
	 }
	 
	 public static String autoGenerateTicket() {
		 return RandomStringUtils.randomNumeric(8);
	 }
}
