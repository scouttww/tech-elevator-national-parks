package com.techelevator.integrationtests;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.npgeek.dao.*;
import com.techelevator.npgeek.model.*;

public class SurveyDAOIntegrationTest extends DAOIntegrationTest {

	private SurveyDAO dao;
	private JdbcTemplate jdbcTemplate;

	@Before
	public void setup() throws SQLException {
		dao = new JdbcSurveyDAO(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
		String truncateSql = "TRUNCATE survey_result, weather, park";
		jdbcTemplate.update(truncateSql);
	}

	@Test
	public void test_to_add_new_survey_to_db() {
		// Arrange
		String parkCode = "parkTest";
		Survey submitted = createSurvey(parkCode);

		// Act
		Survey returnedSurvey = dao.addNewSurveyToDatabase(submitted);

		// Assert
		Assert.assertNotNull(returnedSurvey);
		Assert.assertEquals(returnedSurvey, submitted);

	}
	
	@Test
	public void test_to_see_list_of_parks_with_submitted_surveys() {
		// Arrange
		Park parkA = saveParkToDatabase("parkA");
		Park parkB = saveParkToDatabase("parkB");
		
		// submit 5 surveys declaring Park A as the favorite
		submitSurveys(parkA, 5); // Submit 5 surveys for Park A
		submitSurveys(parkB, 3);
		
		// Act
		List<FavoritePark> favoriteParks = dao.getAllFavoritedParks();
		
		// Assert
		Assert.assertNotNull(favoriteParks);
		Assert.assertEquals(favoriteParks.size(), 2);
		Assert.assertEquals(favoriteParks.get(0).getParkName(),parkA.getParkName());
		Assert.assertEquals(favoriteParks.get(1).getParkName(),parkB.getParkName());
		Assert.assertEquals(favoriteParks.get(0).getNumberOfSurveys(),5);
		Assert.assertEquals(favoriteParks.get(1).getNumberOfSurveys(),3);

	}

	@Test
	public void test_to_see_list_of_parks_with_submitted_surveys_shows_descending_order_of_number_surveys_then_alphabetically() {
		// Arrange
		Park parkA = saveParkToDatabase("parkA");
		Park parkB = saveParkToDatabase("parkB");
		Park parkC = saveParkToDatabase("parkC");
		Park parkD = saveParkToDatabase("parkD");
		
		submitSurveys(parkA, 5); // Submit 5 surveys for Park A
		submitSurveys(parkB, 3);
		submitSurveys(parkC, 5);
		submitSurveys(parkD, 9);
		
		// Act
		List<FavoritePark> favoriteParks = dao.getAllFavoritedParks();
		
		// Assert
		Assert.assertNotNull(favoriteParks);
		Assert.assertEquals(favoriteParks.size(), 4);
		Assert.assertEquals(favoriteParks.get(0).getParkName(),parkD.getParkName());
		Assert.assertEquals(favoriteParks.get(1).getParkName(),parkA.getParkName());
		Assert.assertEquals(favoriteParks.get(2).getParkName(),parkC.getParkName());
		Assert.assertEquals(favoriteParks.get(3).getParkName(),parkB.getParkName());
	}

	@Test
	public void test_to_see_list_of_parks_with_submitted_surveys_shows_no_parks_when_no_surveys_subitted() {
		// Arrange
		// Add parks BUT DON'T ADD SURVEYS
		Park parkA = saveParkToDatabase("parkA");
		Park parkB = saveParkToDatabase("parkB");
		
		// Act
		List<FavoritePark> favoriteParks = dao.getAllFavoritedParks();
		
		// Assert
		Assert.assertNotNull(favoriteParks);
		Assert.assertEquals(favoriteParks.size(), 0);
	}

	@Test
	public void test_to_see_list_of_parks_with_submitted_surveys_shows_one_parks_when_only_surveys_submitted_go_to_a_single_park() {
		// Arrange
		// Add parks BUT DON'T ADD SURVEYS to A or C, Add several Surveys to Park B
		Park parkA = saveParkToDatabase("parkA");
		Park parkB = saveParkToDatabase("parkB");
		Park parkC = saveParkToDatabase("parkC");
		
		// submit 5 surveys declaring Park B as the favorite
		submitSurveys(parkB, 5);

		// Act
		List<FavoritePark> favoriteParks = dao.getAllFavoritedParks();
		
		// Assert
		Assert.assertNotNull(favoriteParks);
		Assert.assertEquals(favoriteParks.size(), 1);
		Assert.assertEquals(favoriteParks.get(0).getParkName(),parkB.getParkName());
		Assert.assertEquals(favoriteParks.get(0).getNumberOfSurveys(),5);
	}

	private Survey createSurvey(String parkCode) {
		Survey survey = new Survey();

		survey.setParkCode(parkCode);
		survey.setEmailAddress("test");
		survey.setState("test");
		survey.setActivityLevel("test");

		return survey;
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
	
	private void submitSurveys(Park park, int numSurveysSubmitted) {
		Survey survey;
		for (int i = 0; i < numSurveysSubmitted; i++) {
			survey = createSurvey(park.getParkCode());
			dao.addNewSurveyToDatabase(survey);
		}		
	}
	
}
