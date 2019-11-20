package com.techelevator.unittests;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.junit.*;

import com.techelevator.npgeek.model.*;

public class WeatherUnitTest {
	private Weather weather;
	
	@Before
	public void setup() {
		weather = new Weather();
	}

	@Test
	public void test_setting_fahrenheit_sets_celsius() {
		weather.setHighFahrenheit(212);
		weather.setLowFahrenheit(32);
		
		Assert.assertNotNull(weather.getHighCelcius());
		Assert.assertNotNull(weather.getLowCelcius());
		
		Assert.assertEquals(weather.getHighCelcius(), 100);
		Assert.assertEquals(weather.getLowCelcius(), 0);
	}
	
	@Test
	public void test_setting_5_day_value_today_correctly_identifies_day() {
		weather.setFiveDayForecastValue(1); //today
		LocalDate date = LocalDate.now();
		DayOfWeek dow = date.getDayOfWeek();
		
		Assert.assertNotNull(weather.getDay());
		
		Assert.assertEquals(weather.getDay(), dow.toString());
	}

	@Test
	public void test_setting_5_day_value_correctly_identifies_day() {
		weather.setFiveDayForecastValue(8); //same day of week as today
		LocalDate date = LocalDate.now();
		DayOfWeek dow = date.getDayOfWeek();
		
		Assert.assertNotNull(weather.getDay());
		
		Assert.assertEquals(weather.getDay(), dow.toString());
	}

	
}
