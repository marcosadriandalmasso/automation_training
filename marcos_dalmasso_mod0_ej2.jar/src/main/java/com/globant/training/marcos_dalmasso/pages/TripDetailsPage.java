package com.globant.training.marcos_dalmasso.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class TripDetailsPage {

	@FindBy(xpath = "/html/head/title")
	private WebElement pageTitle;

	@FindBy(id = "preMain")
	private WebElement tripTitle;

	@FindBy(xpath = "//div[@class='perk-cards']")
	private WebElement reasonsToBook;

	@FindBy(css = "div[class='airItinerary']")
	private WebElement airItinerary;

	@FindBy(css = "table[class='costSummary']")
	private WebElement costSummary;

	@FindBy(xpath = "//input[@name='_eventId_checkout']")
	private WebElement continueButton;
	
	@FindBy(xpath = "//div[@class='contentText']")
	private WebElement tripInfo;

	public void verifyTripDetailsPage() {
		Assert.assertNotNull(pageTitle);
		Assert.assertNotNull(tripTitle);
		Assert.assertNotNull(reasonsToBook);
		Assert.assertNotNull(airItinerary);
		Assert.assertNotNull(costSummary);
	}
	
	public void continueButton() {
		continueButton.click();
	}
	
	public WebElement getTripInformation() {
		return tripInfo;
	}
}
