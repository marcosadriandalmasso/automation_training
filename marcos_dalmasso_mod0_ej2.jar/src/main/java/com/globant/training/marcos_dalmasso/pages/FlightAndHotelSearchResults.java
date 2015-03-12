package com.globant.training.marcos_dalmasso.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class FlightAndHotelSearchResults {

	public void sortHotelResultByReviewerScore(WebDriver driver) {
		driver.findElement(By.cssSelector("div[class='content']>ul>li:nth-child(6)>a")).click();
	}
	
	public void sortHotelResultByFourStarAndUp(WebDriver driver) {
		driver.findElement(By.cssSelector("div[class='rating']>ul>li:nth-child(4)>a")).click();
	}
	
	public void verifyResultsPage(WebDriver driver) {
		Assert.assertNotNull(driver.findElement(By.cssSelector("html>head>title"))); // Page title
		Assert.assertNotNull(driver.findElement(By.cssSelector("div[data-context='pkgResultsChangeSearch']"))); // Resume package
		Assert.assertNotNull(driver.findElement(By.cssSelector("div[class='headerContent']>h2"))); // Process title
		Assert.assertNotNull(driver.findElement(By.cssSelector("div[class='pkgResultsHotelMapMod ']"))); // View hotels on map
		Assert.assertNotNull(driver.findElement(By.cssSelector("div[class='location']"))); // Most booked areas
	}
	
	public void verifyHotelFilterResults(WebDriver driver) {
		Assert.assertNotNull(driver.findElement(By.cssSelector("div[class='content noneBlock']>ul>li:nth-child(6)[class='selected']"))); // Check if "Sort by reviewer score" is selected
		Assert.assertNotNull(driver.findElement(By.cssSelector("div[class='rating']>ul>li:nth-child(4)[class='selected']"))); // Check if "4-star hotels and up" is selected
	}
	
	public void verifyTripDetailResults(WebDriver driver) {
		Assert.assertNotNull(driver.findElement(By.cssSelector("div[class='productCrumb airIcon selected']"))); // Check if "Select flight" is selected
		Assert.assertNotNull(driver.findElement(By.cssSelector("div[data-context='pkgResults-pkgResultsSelectedCard-header']"))); // Check if the Header Title is shown
		Assert.assertNotNull(driver.findElement(By.cssSelector("div[class='hotelSummary']>h3>a[data-wt-ti='hotelCard-name']"))); // Check if the Hotel Name is shown
		Assert.assertNotNull(driver.findElement(By.cssSelector("div[class='pkgSummaryInfo']"))); // Check if the Pkg Summary Info table is shown
		Assert.assertNotNull(driver.findElement(By.cssSelector("div[class='pkgResultsMod resultSetAir incremental']"))); // Check if the list of available flights is shown
	}
	
	public void getFirstFlightAndHotelResult(WebDriver driver) {
		List<WebElement> list = driver.findElements(By.cssSelector("div[data-context='pkgResults']"));
		list.get(0).findElement(By.cssSelector("div[class='IM_ssn_button_red']>a")).click();
	}
}
