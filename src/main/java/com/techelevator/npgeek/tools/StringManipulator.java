package com.techelevator.npgeek.tools;

public class StringManipulator {
		
	// This method will allow us to turn a non-camelcase input from a database like
	// "partly cloudy" into the camelcase version "partlyCloudy" to align with the 
	// image notation (partlyCloudy.png)
	public static String convertToCamelCase(String str) {
		String result = ""; 
		
		if (str.equals(" ") || !str.contains(" ")) { // if there is no space, or if it is only a space, it is already in camelcase
			result = str;
		} else {
			String[] strArr = str.split(" ");
			
			for (int i = 0; i < strArr.length; i++) {
				if(strArr[i] == null || strArr[i].length() < 1) {
					continue;
				}

				if (i == 0) {
					result = result + strArr[i].toLowerCase();
				} else {
					result = result + strArr[i].substring(0,1).toUpperCase();
					if (strArr[i].length() > 1) {
						result = result + strArr[i].substring(1);
					}					
				}
			}
		}
		return result;
	}
}
