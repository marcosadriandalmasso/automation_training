package com.globant.training.marcos_dalmasso.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.globant.training.marcos_dalmasso.pages.HomePage;
import com.globant.training.marcos_dalmasso.pages.LoginPage;

public class Tests {

	WebDriver driver;
	WebDriverWait wait;
	
	public void pausa(int s) {
		try {
			Thread.sleep(s * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}
	
//	@BeforeMethod
//	public void before() {
//		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
//		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//		System.setProperty("webdriver.ie.driver", "H://Globant//Automation//Instaladores//IEDriverServer.exe");
//		driver = new InternetExplorerDriver(capabilities);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	}
	
	@BeforeMethod
	public void before(){
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
//	@AfterMethod
//	public void after() {
//		driver.quit();
//	}
//	
//	@Test
//	public void loginTest() {
//		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
//		homePage.goToHomePage(driver);
//		homePage.loginLink();
//		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
//		loginPage.sendLoginForm("testmdalmasso@gmail.com", "testingapp");
//		Assert.assertEquals(loginPage.isLoggedIn(), true);
//	}
//
//	@Test
//	public void wrongEmailLoginTest() {
//		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
//		homePage.goToHomePage(driver);
//		homePage.loginLink();
//		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
//		loginPage.sendLoginForm("wrongTestmdalmasso@gmail.com", "testingapp");
//		Assert.assertEquals(loginPage.badEmailLoginTest(), true);
//	}
//	
//	@Test
//	public void wrongPassLoginTest() {
//		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
//		homePage.goToHomePage(driver);
//		homePage.loginLink();
//		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
//		loginPage.sendLoginForm("testmdalmasso@gmail.com", "wrongTestingApp");
//		Assert.assertEquals(loginPage.badEmailLoginTest(), true);
//	}
	
	@Test
	public void logoutTest() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.goToHomePage(driver);
		homePage.loginLink();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.sendLoginForm("testmdalmasso@gmail.com", "testingApp");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		loginPage.logout();
		Assert.assertEquals(loginPage.isLoggedOut(), true);
	}
}
