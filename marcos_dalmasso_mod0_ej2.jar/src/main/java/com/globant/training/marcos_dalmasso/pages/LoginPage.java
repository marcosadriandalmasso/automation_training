package com.globant.training.marcos_dalmasso.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	@FindBy(xpath="//input[@type='email']")
	private WebElement emailField;
	
	@FindBy(xpath="//input[@type='password']")
	private WebElement passwordField;
	
	@FindBy(xpath=".//*[@id='main']/div[2]/div[1]/div/div[1]/div[4]/input")
	private WebElement signinButton;
	
	@FindBy(xpath="//li[@class='signOutLink']/a")
	private WebElement logoutButtom;
	
	@FindBy(xpath="//li[@class='welcomeText']")
	private WebElement welcomeText;
	
	@FindBy(xpath="//p[starts-with(@class, 'error')]")
	private WebElement errorText;
	
	@FindBy(xpath="//h3[@class='socialLoginHeading']")
	private WebElement h3Text;
	
	public void goToLoginPage(WebDriver driver) {
		driver.get("https://www.cheaptickets.com/account/login");
	}
	
	public void sendLoginForm(String email, String password) {
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		signinButton.click();
	}
	
	public boolean isLoggedIn() {
		return welcomeText.getText().contains("test mdalmasso");
	}
	
	public boolean isLoggedOut() {
		return welcomeText.getText().contains("CheapTickets");
	}
	
	public void logout() {
		logoutButtom.click();
	}
	
	public boolean badEmailLoginTest() {
		return errorText.getText().contains("The e-mail and password you have entered do not match") || 
				errorText.getText().contains("Please sign in using your e-mail address") ||
				errorText.getText().contains("Please enter your password") ||
				errorText.getText().contains("The e-mail address that you have entered is not properly formatted");
	}
}
