package com.techelevator.integrationtests;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.npgeek.dao.*;
import com.techelevator.npgeek.model.*;

public class ParksDAOIntegrationTest extends DAOIntegrationTest {

	private ParksDAO dao;
	private JdbcTemplate jdbcTemplate;
	private Park park1;
	private Park park2;
	private Park park3;

	@Before
	public void setup() throws SQLException {
		dao = new JdbcParksDAO(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
		String truncateSql = "TRUNCATE weather, park";
		jdbcTemplate.update(truncateSql);

		// Arrange
		park1 = saveParkToDatabase("aardvark");
		park2 = saveParkToDatabase("zebra");
		park3 = saveParkToDatabase("lion");

	}

	@Test
	public void test_get_all_parks_alphabetically() {

		// Act
		List<Park> parksList = dao.getAllParksAlphabetically();

		// Assert
		Assert.assertNotNull(parksList);
		Assert.assertEquals(parksList.size(), 3);
		Assert.assertEquals(parksList.get(0), park1); //aardvark
		Assert.assertEquals(parksList.get(1), park3); //lion
		Assert.assertEquals(parksList.get(2), park2); //zebra
	}

	@Test
	public void test_get_park_details_by_park_code_proper_code_returns_park() {

		// Act
		Park result = dao.getParkDetailsByParkCode("aardvark"); // should return park

		// Assert
		Assert.assertNotNull(result);
		Assert.assertEquals(result, park1);
	}
	@Test
	public void test_get_park_details_by_park_code_improper_code_returns_null() {

		// Act
		Park result = dao.getParkDetailsByParkCode("rhino"); // should return null

		// Assert
		Assert.assertNull(result);
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
