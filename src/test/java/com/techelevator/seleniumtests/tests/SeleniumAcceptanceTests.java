package com.techelevator.seleniumtests.tests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.seleniumtests.pageobjects.*;

public class SeleniumAcceptanceTests {
	private static SingleConnectionDataSource dataSource;	
	private static WebDriver webDriver;
	private HomePage homePage;
	private static final String TEST_GOOD_EMAIL = "test@GoodEmail.scout.wallace.brandon.gardner";

	
	@BeforeClass
	public static void openWebBrowserForTesting() {
		
		String homeDir = System.getProperty("user.home");
		System.setProperty("webdriver.chrome.driver", homeDir+"/dev-tools/chromedriver/chromedriver");
		webDriver = new ChromeDriver();
		
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/* The following line disables autocommit for connections
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false);

	}
	
	@Before
	public void openHomePage() {
		webDriver.get("http://localhost:8080/m3-java-capstone/");
		homePage = new HomePage(webDriver);
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@AfterClass
	public static void closeWebBrowser() throws SQLException {
		deleteInsertedTestSurveyInputs();
		dataSource.getConnection().commit();
		dataSource.destroy();
		webDriver.close();
		
	}

	@Test
	public void forms_can_be_edited_and_submitted_and_impact_the_total_count() {
		SurveyResultsPage resultPage = homePage.clickLinkToViewSurveyResults();
		String number1Park = resultPage.getNumberOneAnswer();
		String number1ParkNumberOfVotes = resultPage.getNumberOneAnswerNumberOfLikes();
				
		resultPage = homePage.clickLinkToSubmitSurvey()
				.enterFavoritePark(number1Park)
				.enterEmail(TEST_GOOD_EMAIL)
				.enterState("OH")
				.selectActivityLevelRadio("inactive")
				.submitFormProperly();
		
		assertEquals(number1Park, resultPage.getNumberOneAnswer());
		String newNumber1ParkNumberOfVotes = resultPage.getNumberOneAnswerNumberOfLikes();
		assertNotEquals(number1ParkNumberOfVotes, newNumber1ParkNumberOfVotes);
		
		int lastInt1 = Integer.parseInt(number1ParkNumberOfVotes.substring(number1ParkNumberOfVotes.length()-1));
		int lastInt2 = Integer.parseInt(newNumber1ParkNumberOfVotes.substring(newNumber1ParkNumberOfVotes.length()-1));
	
		lastInt1 = (lastInt1 + 1)%10; // add 1, then get just the last digit
		lastInt2 = lastInt2%10; // get just the last digit (shouldn't be a problem since we got the substring of only the last digit)
		
		assertEquals(lastInt1, lastInt2);
	}
	
	@Test
	public void forms_can_be_improperly_filled_out_to_get_error_messages() {
		SurveyPage surveyPage = homePage.clickLinkToSubmitSurvey()
				.submitFormImproperly();
		
		assertTrue(surveyPage.badFormEntryGetErrorMessageImproperPark().contains("You must make a selection"));
		assertTrue(surveyPage.badFormEntryGetErrorMessageImproperEmailEmpty().contains("Email address is required"));
		assertTrue(surveyPage.badFormEntryGetErrorMessageImproperState().contains("You must make a selection"));
		assertTrue(surveyPage.badFormEntryGetErrorMessageUnselectedRadio().contains("Activity level is required"));
	}

	@Test
	public void forms_can_be_improperly_filled_out_to_get_error_messages_bad_email() {
		SurveyPage surveyPage = homePage.clickLinkToSubmitSurvey()
				.enterEmail("badEmailFormat")
				.submitFormImproperly();
		
		assertTrue(surveyPage.badFormEntryGetErrorMessageImproperPark().contains("You must make a selection"));
		assertTrue(surveyPage.badFormEntryGetErrorMessageImproperEmailBadFormat().contains("Email must be a valid email address"));
		assertTrue(surveyPage.badFormEntryGetErrorMessageImproperState().contains("You must make a selection"));
		assertTrue(surveyPage.badFormEntryGetErrorMessageUnselectedRadio().contains("Activity level is required"));
	}

	@Test
	public void forms_can_be_improperly_filled_out_to_get_error_messages_then_properly_filled_out_to_get_to_results_page() {
		SurveyResultsPage resultPage = homePage.clickLinkToViewSurveyResults();
		String number1Park = resultPage.getNumberOneAnswer();
		
		SurveyPage surveyPage = homePage
				.clickLinkToSubmitSurvey()
				.submitFormImproperly();
		
		assertTrue(surveyPage.badFormEntryGetErrorMessageImproperPark().contains("You must make a selection"));
		assertTrue(surveyPage.badFormEntryGetErrorMessageImproperEmailEmpty().contains("Email address is required"));
		assertTrue(surveyPage.badFormEntryGetErrorMessageImproperState().contains("You must make a selection"));
		assertTrue(surveyPage.badFormEntryGetErrorMessageUnselectedRadio().contains("Activity level is required"));
		
		surveyPage = surveyPage
				.enterEmail("badEmailFormat")
				.submitFormImproperly();
	
		assertTrue(surveyPage.badFormEntryGetErrorMessageImproperPark().contains("You must make a selection"));
		assertTrue(surveyPage.badFormEntryGetErrorMessageImproperEmailBadFormat().contains("Email must be a valid email address"));
		assertTrue(surveyPage.badFormEntryGetErrorMessageImproperState().contains("You must make a selection"));
		assertTrue(surveyPage.badFormEntryGetErrorMessageUnselectedRadio().contains("Activity level is required"));

		resultPage = surveyPage
				.enterFavoritePark(number1Park)
				.enterEmail(TEST_GOOD_EMAIL)
				.enterState("OH")
				.selectActivityLevelRadio("active")
				.submitFormProperly();
		
		assertEquals(resultPage.getNumberOneAnswer(),number1Park);
	
	}


	@Test
	public void can_loop_through_site_many_times() {
		String firstParkName = homePage.getFirstParkName();
		
		HomePage newHomePage = null;
		
		for(int i=0; i<10; i++) {
			newHomePage = homePage
				.clickLogoLinkToGoToHome()
				.clickLinkToParkDetailsPage()
				.clickImperialNotationLink()
				.clickScientificNotationLink()
				.clickImperialNotationLink()
				.clickNavLinkToGoToHome()
				.clickLinkToSubmitSurvey()
				.clickLinkToSubmitSurvey()
				.clickLinkToViewSurveyResults()
				.clickLogoLinkToGoToHome();
		}
		
		assertNotNull(newHomePage);
		assertEquals(newHomePage.getFirstParkName(), firstParkName);
	}

	
	@Test
	public void given_start_in_imperial_system_when_clicking_make_scientific_standard_makes_degrees_go_to_celsius() {
		DetailsPage details = homePage
				.clickLinkToParkDetailsPage()
				.clickImperialNotationLink();
		
		assertTrue(details.getDegreesScaleLow().endsWith("F"));
		assertTrue(details.getDegreesScaleHigh().endsWith("F"));
		
		details = details.clickScientificNotationLink();
		
		assertTrue(details.getDegreesScaleLow().endsWith("C"));
		assertTrue(details.getDegreesScaleHigh().endsWith("C"));	
	}

	@Test
	public void given_start_in_imperial_system_when_clicking_make_scientific_standard_makes_degrees_go_to_celsius_but_can_switch_back() {
		DetailsPage details = homePage
				.clickLinkToParkDetailsPage()
				.clickImperialNotationLink();
				
		details = details.clickScientificNotationLink();
		
		assertTrue(details.getDegreesScaleLow().endsWith("C"));
		assertTrue(details.getDegreesScaleHigh().endsWith("C"));
		
		details = details.clickImperialNotationLink();
		
		assertTrue(details.getDegreesScaleLow().endsWith("F"));
		assertTrue(details.getDegreesScaleHigh().endsWith("F"));
	}

	@Test
	public void when_changing_temp_scale_show_that_it_persists_in_session() {
		DetailsPage details = homePage
				.clickLinkToParkDetailsPage()
				.clickImperialNotationLink();
				
		details = details
				.clickScientificNotationLink()
				.clickLinkToSubmitSurvey()
				.clickLinkToViewSurveyResults()
				.clickLinkToParkDetailsPage();
		
		assertTrue(details.getDegreesScaleLow().endsWith("C"));
		assertTrue(details.getDegreesScaleHigh().endsWith("C"));
		
		details = details
				.clickImperialNotationLink()
				.clickLinkToSubmitSurvey()
				.clickLinkToViewSurveyResults()
				.clickLinkToParkDetailsPage();
		
		assertTrue(details.getDegreesScaleLow().endsWith("F"));
		assertTrue(details.getDegreesScaleHigh().endsWith("F"));
	}

	

	private static void deleteInsertedTestSurveyInputs() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String deleteTestSurveysByEmail = "DELETE FROM survey_result "
										+ "WHERE emailaddress "
										+ "ILIKE '%' || ? || '%'";
		jdbcTemplate.update(deleteTestSurveysByEmail, TEST_GOOD_EMAIL);
		
	}
	
}
