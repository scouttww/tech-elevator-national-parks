package com.techelevator.npgeek.model;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.techelevator.npgeek.tools.*;

public class Weather {
	private String parkCode;
	private int fiveDayForecastValue;
	private String day;
	private int lowFahrenheit;
	private int highFahrenheit;
	private int lowCelcius;
	private int highCelcius;
	private String forecast;
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fiveDayForecastValue;
		result = prime * result + ((forecast == null) ? 0 : forecast.hashCode());
		result = prime * result + highCelcius;
		result = prime * result + highFahrenheit;
		result = prime * result + lowCelcius;
		result = prime * result + lowFahrenheit;
		result = prime * result + ((parkCode == null) ? 0 : parkCode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Weather other = (Weather) obj;
		if (fiveDayForecastValue != other.fiveDayForecastValue)
			return false;
		if (forecast == null) {
			if (other.forecast != null)
				return false;
		} else if (!forecast.equals(other.forecast))
			return false;
		if (highCelcius != other.highCelcius)
			return false;
		if (highFahrenheit != other.highFahrenheit)
			return false;
		if (lowCelcius != other.lowCelcius)
			return false;
		if (lowFahrenheit != other.lowFahrenheit)
			return false;
		if (parkCode == null) {
			if (other.parkCode != null)
				return false;
		} else if (!parkCode.equals(other.parkCode))
			return false;
		return true;
	}
	
	
	public String getParkCode() {
		return parkCode;
	}
	public int getFiveDayForecastValue() {
		return fiveDayForecastValue;
	}
	public int getLowFahrenheit() {
		return lowFahrenheit;
	}
	public int getHighFahrenheit() {
		return highFahrenheit;
	}
	public int getLowCelcius() {
		return lowCelcius;
	}
	public int getHighCelcius() {
		return highCelcius;
	}
	public String getForecast() {
		return forecast;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public void setFiveDayForecastValue(int fiveDayForecastValue) {
		this.fiveDayForecastValue = fiveDayForecastValue;
		setDay();
	}
	// when setting the temperature in Fahrenheit, we also calculate and set the temperature in Celsius
	// as seen in the below setters
	// ************
	public void setLowFahrenheit(int lowFahrenheit) {
		this.lowFahrenheit = lowFahrenheit;
		setLowCelsius();
	}
	private void setLowCelsius() {
		this.lowCelcius = (int) UnitConverter.convertFahrenheitToCelsius(lowFahrenheit);	
	}
	public void setHighFahrenheit(int highFahrenheit) {
		this.highFahrenheit = highFahrenheit;
		setHighCelsius();
	}
	private void setHighCelsius() {
		this.highCelcius = (int) UnitConverter.convertFahrenheitToCelsius(highFahrenheit);	
	}
	// ************
	
	
	// the forecast from the database comes in with spaces in between words
	// however, the structure of the image files is a camelcase version, so this
	// setter automatically converts the input from a database query row
	// to a camelcase version of the same string
	// ************
	public void setForecast(String forecast) {
		this.forecast = StringManipulator.convertToCamelCase(forecast);
	// ************
	}
	
	public String getDay() {
		return day;
	}
	public void setDay() {
		LocalDate date = LocalDate.now();
		DayOfWeek dow = date.getDayOfWeek();
		
		
		this.day = dow.plus(fiveDayForecastValue - 1).toString();
	}

	
	

}
