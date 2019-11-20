package com.techelevator.npgeek.tools;

public class UnitConverter {
	
	public static double convertFahrenheitToCelsius(double tempInDegreesFahrenheit) {
		return (tempInDegreesFahrenheit-32)/1.8;
	}
	
	public static double convertAcresToSqKilometers(double areaInAcres) {
		return areaInAcres/(247.105);	
	}
	
	public static double convertFeetToMeters(double distanceInFeet) {
		return distanceInFeet/(3.28084);
	}
	
	public static double convertMilesToKilometers(double distanceInMiles) {
		return distanceInMiles*(1.60934);
	}

}
