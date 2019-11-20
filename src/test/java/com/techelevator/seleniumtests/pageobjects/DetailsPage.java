package com.techelevator.seleniumtests.pageobjects;

import org.openqa.selenium.*;

public class DetailsPage extends NpGeekAllPages {

	public DetailsPage(WebDriver webDriver) {
		super(webDriver);
	}
	
	public DetailsPage clickScientificNotationLink() {
		WebElement scientificNotationLink = webDriver.findElement(By.linkText("Scientific Notation\n" + 
				"(°C, m, km)"));
		scientificNotationLink.click();
		return new DetailsPage(webDriver);
	}
	
	public DetailsPage clickImperialNotationLink() {
		WebElement imperialNotationLink = webDriver.findElement(By.linkText("Imperial Notation\n" + 
				"(°F, ft, mi)"));
		imperialNotationLink.click();
		return new DetailsPage(webDriver);
	}

	public String getDegreesScaleLow() {
		return webDriver.findElement(By.className("low-temp")).getText();
	}
	
	public String getDegreesScaleHigh() {
		return webDriver.findElement(By.className("low-temp")).getText();
	}
	
}
