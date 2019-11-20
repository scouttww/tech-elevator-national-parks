package com.techelevator.seleniumtests.pageobjects;

import org.openqa.selenium.*;

public class HomePage extends NpGeekAllPages {
	
	public HomePage(WebDriver webDriver) {
		super(webDriver);
	}

	public DetailsPage clickLinkToParkDetailsPage() {
		WebElement detailPageLink = webDriver.findElement(By.className("park-img"));
		detailPageLink.click();
		return new DetailsPage(webDriver);
	}
	
	public String getFirstParkName() {
		return webDriver.findElement(By.tagName("h2")).getText();
	}
}
