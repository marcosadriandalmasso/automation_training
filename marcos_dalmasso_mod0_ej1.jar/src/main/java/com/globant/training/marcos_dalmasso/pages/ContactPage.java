package com.globant.training.marcos_dalmasso.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactPage {

	@FindBy(id="nombre")
	private WebElement nameField;
	
	@FindBy(id="email")
	private WebElement emailField;
	
	@FindBy(id="asunto")
	private WebElement subjectField;
	
	@FindBy(id="mensaje")
	private WebElement messageField;
	
	@FindBy(css=".button1")
	private WebElement submitButton;
	
	@FindBy(id="ContactForm")
	private WebElement contactForm;
	
	public void goToConctPage(WebDriver driver) {
		driver.get("http://net-geo.com.ar/contacts.php");
	}
	
	public void sendContactForm(String name, String email, String subject, String message) {
		nameField.sendKeys(name);
		emailField.sendKeys(email);
		subjectField.sendKeys(subject);
		messageField.sendKeys(message);
		submitButton.click();
	}
	
	public String getConfirmMessage() {
		String [] confirmMessage = contactForm.getText().split(":"); 
		return confirmMessage[4].trim();
	}
}
