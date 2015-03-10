package com.globant.training.marcos_dalmasso.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	@FindBy(xpath=".//*[@id='headerStandard']/div[1]/div[1]/ul[1]/li[2]/a")
	private WebElement loginLink;
	
	public void goToHomePage(WebDriver driver) {
		driver.get("http://www.cheaptickets.com/");
	}
	
	public void loginLink() {
		loginLink.click();
	}
}
