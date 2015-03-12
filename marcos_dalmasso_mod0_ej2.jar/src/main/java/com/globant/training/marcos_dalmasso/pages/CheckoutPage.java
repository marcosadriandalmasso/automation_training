package com.globant.training.marcos_dalmasso.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CheckoutPage {


	public void verifyTripInformation(WebDriver driver, WebElement tripInfo) {
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='contentText']")), tripInfo);
	}
	
	public void completeAndSendCheckoutForm(WebDriver driver) {
		driver.findElement(By.cssSelector("option[value='MR']")).click();
		driver.findElement(By.xpath("//input[contains(@name, 'travelers[0].name.firstName')]")).sendKeys("Marcos");
		driver.findElement(By.xpath("//input[contains(@name, 'travelers[0].name.middleName')]")).sendKeys("A");
		driver.findElement(By.xpath("//input[contains(@name, 'travelers[0].name.lastName')]")).sendKeys("Dalmasso");
		driver.findElement(By.cssSelector("option[value='SR']")).click();
//		driver.findElement(By.cssSelector("option[value='W']")).click();
		driver.findElement(By.cssSelector("option[value='AR']")).click();
		driver.findElement(By.xpath("//input[contains(@name, 'travelers[0].phoneNumber.phoneNumber')]")).sendKeys("3415034627");
		driver.findElement(By.cssSelector("option[value='M']")).click();
		driver.findElement(By.cssSelector("option[value='6']")).click();
		driver.findElement(By.cssSelector("option[value='28']")).click();
		driver.findElement(By.cssSelector("option[value='1985']")).click();
		driver.findElement(By.xpath("//input[contains(@name, 'email.emailAddress')]")).sendKeys("testmdalmasso@gmail.com");
		driver.findElement(By.xpath("//input[@class='needsclick' and @type='radio' and @value='-1']")).click();
	}
}