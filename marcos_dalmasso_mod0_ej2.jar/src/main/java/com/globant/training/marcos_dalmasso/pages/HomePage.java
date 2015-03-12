package com.globant.training.marcos_dalmasso.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	Date date;
	Calendar calendar = Calendar.getInstance();
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
	
	@FindBy(xpath="//li[@class='signInLink socialLogin']/a")
	private WebElement loginLink;
	
	@FindBy(id="search.type.air")
	private WebElement flightOnly;
	
	@FindBy(id="search.ar.type.code.oneWay")
	private WebElement oneWay;
	
	@FindBy(xpath="//form[@class='searchFormForm ']")
	private WebElement tripForm;
//	
//	@FindBy(xpath="//p[starts-with(@class, 'error message')]")
//	private WebElement wrongDateErrorMessage;
	
	public void goToHomePage(WebDriver driver) {
		driver.get("http://www.cheaptickets.com/");
	}
	
	public void loginLink() {
		loginLink.click();
	}
	
	public void selectFlightOnly() {
		flightOnly.click();
	}
	
	public void selectOneWay() {
		oneWay.click();
	}
	
	public void fillTripForm() {
		tripForm.findElement(By.cssSelector("input[name='ar.ow.leaveSlice.orig.key']")).sendKeys("LAS");
		tripForm.findElement(By.cssSelector("input[name='ar.ow.leaveSlice.dest.key']")).sendKeys("LAX");
		tripForm.findElement(By.cssSelector("input[name='ar.ow.leaveSlice.date']")).sendKeys(dateFormat.format(new Date().getTime() + (1000 * 60 * 60 * 24)));
		tripForm.findElement(By.cssSelector("input[name='search']")).click();
	}
	
	public void fillWrongDateTripForm(WebDriver driver) {
		driver.findElement(By.cssSelector("input[name='ar.ow.leaveSlice.orig.key']")).sendKeys("LAS");
		driver.findElement(By.cssSelector("input[name='ar.ow.leaveSlice.dest.key']")).sendKeys("LAX");
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, 350); // adds 350 days to date
		driver.findElement(By.cssSelector("input[name='ar.ow.leaveSlice.date']")).sendKeys(dateFormat.format(calendar.getTime()));
		tripForm.findElement(By.cssSelector("input[name='search']")).click();
	}
	
	public boolean checkForErrors(WebDriver driver) {
		return driver.findElement(By.xpath("//p[starts-with(@class, 'error message')]")).getText().contains("Most airlines only support reservations up to 330 days in advance");
	}
}
