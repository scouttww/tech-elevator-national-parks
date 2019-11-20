package com.techelevator.integrationtests;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.npgeek.dao.*;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.Weather;

import junit.framework.Assert;

public class WeatherDAOIntegrationTest extends DAOIntegrationTest {

	private WeatherDAO dao;
	private JdbcTemplate jdbcTemplate;
	private Park park1;
	private Weather day1;
	private Weather day2;
	private Weather day3;
	private Weather day4;
	private Weather day5;

	@Before
	public void setup() throws SQLException {
		dao = new JdbcWeatherDAO(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
		String truncateSql = "TRUNCATE weather, park";
		jdbcTemplate.update(truncateSql);
		
		//Arrange
		park1 = saveParkToDatabase("aardvark");
		day2 = saveWeatherToDatabase("aardvark", 2);
		day5 = saveWeatherToDatabase("aardvark", 5);
		day3 = saveWeatherToDatabase("aardvark", 3);
		day4 = saveWeatherToDatabase("aardvark", 4);
		day1 = saveWeatherToDatabase("aardvark", 1);
	}
	
	@Test
	public void test_to_get_5_day_forecast_by_parkCode() {
		//Act
		List<Weather> weatherList = dao.get5DayWeatherForecastByParkCode("aardvark");
		
		//Assert
		Assert.assertNotNull(weatherList);
		Assert.assertEquals(weatherList.size(), 5);
		Assert.assertEquals(weatherList.get(0), day1);
		Assert.assertEquals(weatherList.get(4), day5);
	}
	
	private Weather saveWeatherToDatabase(String parkCode, int dayNumber) {
		
		Weather weather = new Weather();
		
		weather.setParkCode(parkCode);
		weather.setFiveDayForecastValue(dayNumber);
		weather.setLowFahrenheit(10);
		weather.setHighFahrenheit(100);
		weather.setForecast("test forecast");
		
		String insertSql = "INSERT INTO weather (parkCode, fiveDayForecastValue, low, high, forecast) "
				+ "VALUES (?, ?, 10, 100, 'test forecast')";
		
		jdbcTemplate.update(insertSql, parkCode, dayNumber);
		
		return weather;
	}
	
	private Park saveParkToDatabase(String parkName) {
		Park park = new Park();
		park.setParkName(parkName);
		park.setParkCode(parkName);
		park.setState("testState");
		park.setAcreage(1);
		park.setElevationInFeet(1);
		park.setMilesOfTrail(1);
		park.setNumberOfCampsites(1);
		park.setClimate("testClimate");
		park.setYearFounded(2000);
		park.setAnnualVisitCount(1);
		park.setInspirationalQuote("Hello World");
		park.setInspirationalQuoteSource("Computer");
		park.setParkDescription("testDescription");
		park.setEntryFee(1);
		park.setNumberOfAnimalSpecies(1);

		String insertSql = "INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, "
				+ "milesOfTrail, numberOfCampsites, climate, yearFounded, annualVisitorCount, "
				+ "inspirationalQuote, inspirationalQuoteSource, parkDescription, entryFee, "
				+ "numberOfAnimalSpecies) VALUES "
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		jdbcTemplate.update(insertSql,
				park.getParkCode(),
				park.getParkName(),
				park.getState(),
				park.getAcreage(),
				park.getElevationInFeet(),
				park.getMilesOfTrail(),
				park.getNumberOfCampsites(),
				park.getClimate(),
				park.getYearFounded(),
				park.getAnnualVisitCount(),
				park.getInspirationalQuote(),
				park.getInspirationalQuoteSource(),
				park.getParkDescription(),
				park.getEntryFee(),
				park.getNumberOfAnimalSpecies());
		
		return park;
	}
	


}
