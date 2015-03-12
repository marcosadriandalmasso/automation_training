package com.globant.training.marcos_dalmasso.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CustomizeTripPage {

	public void verifyTripInformation(WebDriver driver, WebElement tripInfo) {
		Assert.assertEquals(driver.findElement(By.cssSelector("div[class='productInfo']")), tripInfo);
	}
	
	public void continueButton(WebDriver driver) {
		driver.findElement(By.cssSelector("input[name='_eventId_continue']")).click();
	}
}
