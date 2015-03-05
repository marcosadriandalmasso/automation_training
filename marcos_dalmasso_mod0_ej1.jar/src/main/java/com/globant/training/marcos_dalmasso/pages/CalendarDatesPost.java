package com.globant.training.marcos_dalmasso.pages;

import java.util.Calendar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalendarDatesPost {

	@FindBy(id="home-nav")
	private WebElement goToCalendarDatesPost;
	
	public void goToCalendarDatesPost(WebDriver driver) {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.goToBlogPage(driver);
		goToCalendarDatesPost.click();
	}
	
	public String getActualMonth() {
		String[] actualMonth = {"January "+Calendar.getInstance().get(Calendar.YEAR),
				"February "+Calendar.getInstance().get(Calendar.YEAR),
				"March "+Calendar.getInstance().get(Calendar.YEAR),
				"April "+Calendar.getInstance().get(Calendar.YEAR),
				"May "+Calendar.getInstance().get(Calendar.YEAR),
				"June "+Calendar.getInstance().get(Calendar.YEAR),
				"July "+Calendar.getInstance().get(Calendar.YEAR),
				"August "+Calendar.getInstance().get(Calendar.YEAR),
				"September "+Calendar.getInstance().get(Calendar.YEAR),
				"October "+Calendar.getInstance().get(Calendar.YEAR),
				"November "+Calendar.getInstance().get(Calendar.YEAR),
				"December "+Calendar.getInstance().get(Calendar.YEAR)};
		return actualMonth[Calendar.getInstance().get(Calendar.MONTH)];
	}
}
