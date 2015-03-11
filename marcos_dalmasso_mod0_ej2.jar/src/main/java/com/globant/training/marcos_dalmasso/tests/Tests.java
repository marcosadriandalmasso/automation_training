package com.globant.training.marcos_dalmasso.tests;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.globant.training.marcos_dalmasso.pages.HomePage;
import com.globant.training.marcos_dalmasso.pages.LoginPage;

public class Tests {

	WebDriver driver;

//	@BeforeMethod
//	public void before() {
//		driver = new FirefoxDriver();
//		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
//	}

	@BeforeMethod
	public void before() {
		DesiredCapabilities capabilities = DesiredCapabilities
				.internetExplorer();
		capabilities
				.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
		System.setProperty("webdriver.ie.driver",
				"H://Globant//Automation//Instaladores//IEDriverServer.exe");
		driver = new InternetExplorerDriver(capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void after() {
		driver.quit();
	}

	@Test
	public void loginTest() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.goToHomePage(driver);
		homePage.loginLink();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.sendLoginForm("testmdalmasso@gmail.com", "testingapp");
		Assert.assertEquals(loginPage.isLoggedIn(), true);
	}

	@Test
	public void wrongEmailLoginTest() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.goToHomePage(driver);
		homePage.loginLink();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		loginPage.sendLoginForm("wrongTestmdalmasso@gmail.com", "testingapp");
		Assert.assertEquals(loginPage.badEmailLoginTest(), true);
	}

	@Test
	public void wrongPassLoginTest() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.goToHomePage(driver);
		homePage.loginLink();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		loginPage.sendLoginForm("t", "w");
		Assert.assertEquals(loginPage.badEmailLoginTest(), true);
	}

	@Test
	public void logoutTest() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.goToHomePage(driver);
		homePage.loginLink();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.sendLoginForm("testmdalmasso@gmail.com", "testingapp");
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		String parent = driver.getWindowHandle();
		Set<String> pops = driver.getWindowHandles();
		Iterator<String> it = pops.iterator();
		while (it.hasNext()) {
			String popupHandle = it.next().toString();
			if (!popupHandle.contains(parent)) {
				driver.switchTo().window(popupHandle);
				System.out.println("Popu Up Title: "
						+ driver.switchTo().window(popupHandle).getTitle());
			}
		}
		loginPage.logout();
		Assert.assertEquals(loginPage.isLoggedOut(), true);
	}
	
}
