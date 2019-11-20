package com.techelevator.seleniumtests.pageobjects;

import org.openqa.selenium.*;

public class SurveyResultsPage extends NpGeekAllPages {
	
	public SurveyResultsPage(WebDriver webDriver) {
		super(webDriver);
	}

	public DetailsPage clickLinkToParkDetailsPage() {
		WebElement detailPageLink = webDriver.findElement(By.className("park-img"));
		detailPageLink.click();
		return new DetailsPage(webDriver);
	}
	
	public String getNumberOneAnswer() {
		return webDriver.findElement(By.tagName("h2")).getText();
	}

	public String getNumberOneAnswerNumberOfLikes() {
		return webDriver.findElement(By.className("numberOfLikes")).getText();
	}

}
