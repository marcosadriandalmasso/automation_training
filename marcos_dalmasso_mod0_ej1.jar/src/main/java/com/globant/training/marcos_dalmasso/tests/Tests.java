package com.globant.training.marcos_dalmasso.tests;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.globant.training.marcos_dalmasso.pages.ContactPage;
import com.globant.training.marcos_dalmasso.pages.HomePage;
import com.globant.training.marcos_dalmasso.pages.PostPage;
import com.globant.training.marcos_dalmasso.pages.SearchPage;

public class Tests {

	WebDriver driver;
	
	@BeforeMethod
	public void before() {
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		System.setProperty("webdriver.ie.driver", "H://Globant//Automation//Instaladores//IEDriverServer.exe");
		driver = new InternetExplorerDriver(capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void after() {
		driver.quit();
	}
	
	@Test
	public void pageTitleValidation() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.goToHomePage(driver);
		Assert.assertEquals(driver.getTitle(), HomePage.homePageTitle);
	}
	
	@Test
	public void pageSearch() {
		SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
		searchPage.goToSearchPage(driver);
		searchPage.search("Globant");
		Assert.assertEquals(searchPage.getResultStats().contains("Cerca de"), true);
	}
	
	@Test
	public void validatePostDate() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.goToBlogPage(driver);
		String firstPostDate = homePage.getFirstPostDate();
		homePage.goToFirstPost();
		PostPage postPage = PageFactory.initElements(driver, PostPage.class);
		Assert.assertEquals(postPage.getPostDate(), firstPostDate);
	}
	
	@Test
	public void validateContactForm() {
		ContactPage contactPage = PageFactory.initElements(driver, ContactPage.class);
		contactPage.goToConctPage(driver);
		contactPage.sendContactForm("Ma", "m@m.com", "T", "T");
		Assert.assertEquals(contactPage.getConfirmMessage(), "Su mensaje a sido enviado correctamente. Gracias por contactar con nosostros");
	}
	
	@Test
	public void validateWrongContactForm() {
		ContactPage contactPage = PageFactory.initElements(driver, ContactPage.class);
		contactPage.goToConctPage(driver);
		contactPage.sendContactForm("", "", "", "");
		Alert alert = driver.switchTo().alert();
		String alertError = alert.getText();
		alert.accept();
		Assert.assertEquals(alertError, "Debe ingresar un nombre correcto");
	}
	
	@Test
	public void countActualMonthPosts() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.goToBlogPage(driver);
		
	}
}
