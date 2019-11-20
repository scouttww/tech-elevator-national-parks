package com.techelevator.seleniumtests.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class SurveyPage extends NpGeekAllPages {

	public SurveyPage(WebDriver webDriver) {
		super(webDriver);
	}
	
	public SurveyPage enterFavoritePark(String parkName) {
		Select parkField = new Select(webDriver.findElement(By.id("park")));
		parkField.selectByVisibleText(parkName);
		return this;
	}
	
	public SurveyPage enterEmail(String email) {
		WebElement emailField = webDriver.findElement(By.id("email"));
		emailField.sendKeys(email);
		return this;
	}
	
	public SurveyPage enterState(String state) {
		Select stateField = new Select(webDriver.findElement(By.id("state")));
		stateField.selectByVisibleText(state);
		return this;
	}
	
	public SurveyPage selectActivityLevelRadio(String activityLevel) {
		WebElement activityRadioField = webDriver.findElement(By.id(activityLevel));
		activityRadioField.click();
		return this;
	}
	
	public SurveyResultsPage submitFormProperly() {
		WebElement submitButton = webDriver.findElement(By.id("submit-button"));
		submitButton.click();
		return new SurveyResultsPage(webDriver);
	}
	
	public SurveyPage submitFormImproperly() {
		WebElement submitButton = webDriver.findElement(By.id("submit-button"));
		submitButton.click();
		return this;
	}
	
	public String badFormEntryGetErrorMessageImproperPark() {
		return webDriver.findElement(By.id("park-error")).getText();
	}
	
	
	public String badFormEntryGetErrorMessageImproperEmailEmpty() {
		return webDriver.findElement(By.id("email-error")).getText();
	}
	
	
	public String badFormEntryGetErrorMessageImproperEmailBadFormat() {
		return webDriver.findElement(By.id("email-error")).getText();
	}
	
	
	public String badFormEntryGetErrorMessageImproperState() {
		return webDriver.findElement(By.id("state-error")).getText();
	}
	
	
	public String badFormEntryGetErrorMessageUnselectedRadio() {
		return webDriver.findElement(By.id("activity-error")).getText();
	}
	
}
