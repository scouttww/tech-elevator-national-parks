package com.techelevator.seleniumtests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class NpGeekAllPages {

	protected WebDriver webDriver;

	public NpGeekAllPages(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public HomePage clickLogoLinkToGoToHome() {
		WebElement detailPageLink = webDriver.findElement(By.className("logo"));
		detailPageLink.click();
		return new HomePage(webDriver);
	}
	
	public SurveyPage clickLinkToSubmitSurvey() {
		WebElement detailPageLink = webDriver.findElement(By.linkText("Submit a Survey"));
		detailPageLink.click();
		return new SurveyPage(webDriver);
	}

	public HomePage clickNavLinkToGoToHome() {
		WebElement detailPageLink = webDriver.findElement(By.linkText("Home Page"));
		detailPageLink.click();
		return new HomePage(webDriver);
	}

	public SurveyResultsPage clickLinkToViewSurveyResults() {
		WebElement detailPageLink = webDriver.findElement(By.linkText("See Survey Results"));
		detailPageLink.click();
		return new SurveyResultsPage(webDriver);
	}

}
