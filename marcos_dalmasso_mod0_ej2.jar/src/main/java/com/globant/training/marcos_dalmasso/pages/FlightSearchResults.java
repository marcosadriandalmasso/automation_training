package com.globant.training.marcos_dalmasso.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class FlightSearchResults {
	
	public void verifyResultsPage(WebDriver driver) {
		Assert.assertNotNull(driver.findElement(By.cssSelector("html>head>title"))); // Title
		Assert.assertNotNull(driver.findElement(By.cssSelector("div[data-context='airLowestPrice']"))); // Cheapest price
		Assert.assertNotNull(driver.findElement(By.cssSelector("span[data-context='originalTotal']"))); // Matching results
		Assert.assertNotNull(driver.findElement(By.cssSelector("table[class='control matrix block']"))); // Flight matrix
		Assert.assertNotNull(driver.findElement(By.cssSelector("table[class='tripSummary oneWay']"))); // Trip summary
	}
	
	public void getFirstFlightResult(WebDriver driver) {
		List<WebElement> list = driver.findElements(By.cssSelector("div[data-context='airResultsCard']"));
		list.get(0).findElement(By.cssSelector("a[data-context='selectButton']")).click();
	}
}
