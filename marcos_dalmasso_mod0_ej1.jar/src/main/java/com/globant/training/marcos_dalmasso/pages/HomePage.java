package com.globant.training.marcos_dalmasso.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	public static String homePageTitle = "Globant | Globant";

	@FindBy(css=".post-date>a")
	private WebElement homePagePost;
	
	@FindBy(id="post-river")
	private WebElement postContent;
	
	public void goToHomePage(WebDriver driver) {
		driver.get("http://globant.com");
	}
	
	public void goToBlogPage(WebDriver driver) {
		driver.get("http://en.blog.wordpress.com/");
	}
	
	
	public void goToFirstPost() {
		homePagePost.click();
	}
	
	public String getFirstPostDate() {
		return homePagePost.getText();
	}
}
