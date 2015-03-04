package com.globant.training.marcos_dalmasso.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage {

	@FindBy(id = "resultStats")
	private WebElement resultStats;
	
	@FindBy(id = "lst-ib")
	private WebElement searchInput;

	@FindBy(id = "sblsbb")
	private WebElement submitButton;

	public void goToSearchPage(WebDriver driver) {
		driver.get("www.google.com.ar");
	}

	public void search(String query) {
		searchInput.sendKeys(query);
		submitButton.click();
	}
	
	public String getResultStats() {
		return resultStats.getText();
	}
}
