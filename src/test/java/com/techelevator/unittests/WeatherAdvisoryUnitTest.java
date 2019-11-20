package com.techelevator.unittests;

import java.util.List;

import org.junit.*;

import com.techelevator.npgeek.model.*;

public class WeatherAdvisoryUnitTest {
	private Weather weather;
	private WeatherAdvisory weatherAdvisory;
	
	@Before
	public void setup() {
		weather = new Weather();
	}

	@Test
	public void test_forecast_snow_displays_proper_advisory() {
		weather.setForecast("snow");
		weather.setLowFahrenheit(30);
		weather.setHighFahrenheit(40);
		weatherAdvisory = new WeatherAdvisory(weather);
		List<String> advisories = weatherAdvisory.getAdvisories();
		Assert.assertNotNull(advisories);
		Assert.assertEquals(advisories.size(), 1);
		Assert.assertTrue(advisories.get(0).startsWith("It's gonna snow!!"));
	}
	
	@Test
	public void test_forecast_sunny_displays_proper_advisory() {
		weather.setForecast("sunny");
		weather.setLowFahrenheit(30);
		weather.setHighFahrenheit(40);
		weatherAdvisory = new WeatherAdvisory(weather);
		List<String> advisories = weatherAdvisory.getAdvisories();
		Assert.assertNotNull(advisories);
		Assert.assertEquals(advisories.size(), 1);
		Assert.assertTrue(advisories.get(0).startsWith("It's gonna be a sunny day!"));
	}
	
	@Test
	public void test_forecast_rain_displays_proper_advisory() {
		weather.setForecast("rain");
		weather.setLowFahrenheit(30);
		weather.setHighFahrenheit(40);
		weatherAdvisory = new WeatherAdvisory(weather);
		List<String> advisories = weatherAdvisory.getAdvisories();
		Assert.assertNotNull(advisories);
		Assert.assertEquals(advisories.size(), 1);
		Assert.assertTrue(advisories.get(0).startsWith("It might rain"));
	}
	
	@Test
	public void test_forecast_thunder_displays_proper_advisory() {
		weather.setForecast("thunderstorms");
		weather.setLowFahrenheit(30);
		weather.setHighFahrenheit(40);
		weatherAdvisory = new WeatherAdvisory(weather);
		List<String> advisories = weatherAdvisory.getAdvisories();
		Assert.assertNotNull(advisories);
		Assert.assertEquals(advisories.size(), 1);
		Assert.assertTrue(advisories.get(0).startsWith("It might storm"));
	}
	
	@Test
	public void test_forecast_heat_displays_proper_advisory() {
		weather.setForecast("noAdvisory");
		weather.setLowFahrenheit(70);
		weather.setHighFahrenheit(80);
		weatherAdvisory = new WeatherAdvisory(weather);
		List<String> advisories = weatherAdvisory.getAdvisories();
		Assert.assertNotNull(advisories);
		Assert.assertEquals(advisories.size(), 1);
		Assert.assertTrue(advisories.get(0).startsWith("Phew... It's going to be hot out!"));
	}
	
	@Test
	public void test_forecast_cold_displays_proper_advisory() {
		weather.setForecast("noAdvisory");
		weather.setLowFahrenheit(10);
		weather.setHighFahrenheit(20);
		weatherAdvisory = new WeatherAdvisory(weather);
		List<String> advisories = weatherAdvisory.getAdvisories();
		Assert.assertNotNull(advisories);
		Assert.assertEquals(advisories.size(), 1);
		Assert.assertTrue(advisories.get(0).startsWith("Brrr... It's going to be chilly"));
	}
	
	@Test
	public void test_forecast_temp_diff_displays_proper_advisory() {
		weather.setForecast("noAdvisory");
		weather.setLowFahrenheit(30);
		weather.setHighFahrenheit(60);
		weatherAdvisory = new WeatherAdvisory(weather);
		List<String> advisories = weatherAdvisory.getAdvisories();
		Assert.assertNotNull(advisories);
		Assert.assertEquals(advisories.size(), 1);
		Assert.assertTrue(advisories.get(0).startsWith("The temperature will change a lot."));
	}
	
	@Test
	public void test_forecast_no_conditions_displays_no_advisory() {
		weather.setForecast("noAdvisory");
		weather.setLowFahrenheit(60);
		weather.setHighFahrenheit(60);
		weatherAdvisory = new WeatherAdvisory(weather);
		List<String> advisories = weatherAdvisory.getAdvisories();
		Assert.assertNotNull(advisories);
		Assert.assertEquals(advisories.size(), 0);
	}
	
	@Test
	public void test_forecast_4_conditions_displays_4_advisories() {
		weather.setForecast("snow"); // snow advisory
		weather.setLowFahrenheit(10); // cold advisory
		weather.setHighFahrenheit(80); // heat and temp diff advisories
		weatherAdvisory = new WeatherAdvisory(weather);
		List<String> advisories = weatherAdvisory.getAdvisories();
		Assert.assertNotNull(advisories);
		Assert.assertEquals(advisories.size(), 4);
	}
	
}
