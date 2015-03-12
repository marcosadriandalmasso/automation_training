package com.globant.training.marcos_dalmasso.tests;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.globant.training.marcos_dalmasso.pages.CheckoutPage;
import com.globant.training.marcos_dalmasso.pages.CustomizeTripPage;
import com.globant.training.marcos_dalmasso.pages.FlightAndHotelSearchResults;
import com.globant.training.marcos_dalmasso.pages.FlightSearchResults;
import com.globant.training.marcos_dalmasso.pages.HomePage;
import com.globant.training.marcos_dalmasso.pages.LoginPage;
import com.globant.training.marcos_dalmasso.pages.TripDetailsPage;

public class Tests {

	WebDriver driver;
	WebDriverWait wait;

	public void sleep(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
		}
	}

	@BeforeMethod
	public void before() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	//
	// @BeforeMethod
	// public void before() {
	// DesiredCapabilities capabilities = DesiredCapabilities
	// .internetExplorer();
	// capabilities
	// .setCapability(
	// InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
	// true);
	// System.setProperty("webdriver.ie.driver",
	// "H://Globant//Automation//Instaladores//IEDriverServer.exe");
	// driver = new InternetExplorerDriver(capabilities);
	// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	// }
	//
	// @BeforeMethod
	// public void before() {
	// DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	// System.setProperty("webdriver.chrome.driver","H://Globant//Automation//Instaladores//chromedriver.exe");
	// driver = new ChromeDriver(capabilities);
	// }

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
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
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

	@Test
	public void flightOnlySearchTest() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.goToHomePage(driver);
		sleep(5);
		homePage.selectFlightOnly();
		sleep(5);
		homePage.selectOneWay();
		sleep(5);
		homePage.fillTripForm();
		sleep(15);

		FlightSearchResults flightSearchResults = PageFactory.initElements(
				driver, FlightSearchResults.class);
		flightSearchResults.verifyResultsPage(driver);
		flightSearchResults.getFirstFlightResult(driver);
		sleep(15);

		TripDetailsPage tripDetailsPage = PageFactory.initElements(driver,
				TripDetailsPage.class);
		tripDetailsPage.verifyTripDetailsPage();
		tripDetailsPage.continueButton();
		sleep(15);

		CustomizeTripPage customizeTripPage = PageFactory.initElements(driver,
				CustomizeTripPage.class);
		sleep(5);
		customizeTripPage.verifyTripInformation(driver,
				tripDetailsPage.getTripInformation());
		customizeTripPage.continueButton(driver);
		sleep(15);

		CheckoutPage checkoutPage = PageFactory.initElements(driver,
				CheckoutPage.class);
		checkoutPage.verifyTripInformation(driver,
				tripDetailsPage.getTripInformation());
		checkoutPage.completeAndSendCheckoutForm(driver);
		sleep(15);
	}

	@Test
	public void flightSearchWrongDateTest() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.goToHomePage(driver);
		sleep(5);
		homePage.selectFlightOnly();
		sleep(5);
		homePage.selectOneWay();
		sleep(5);
		homePage.fillWrongDateTripForm(driver);
		sleep(5);
		Assert.assertEquals(homePage.checkForErrors(driver), true);
	}

	@Test
	public void positiveFlightAndBookTest() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.goToHomePage(driver);
		sleep(5);
		homePage.selectFlightOnly();
		sleep(5);
		homePage.selectOneWay();
		sleep(5);
		homePage.fillTripForm();
		sleep(15);

		FlightSearchResults flightSearchResults = PageFactory.initElements(
				driver, FlightSearchResults.class);
		flightSearchResults.verifyResultsPage(driver);
	}

	@Test
	public void logedInFlightOnlySearchTest() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.goToHomePage(driver);
		sleep(1);
		homePage.selectFlightOnly();
		sleep(1);
		homePage.selectOneWay();
		sleep(1);
		homePage.fillTripForm();
		sleep(5);

		FlightSearchResults flightSearchResults = PageFactory.initElements(
				driver, FlightSearchResults.class);
		flightSearchResults.verifyResultsPage(driver);
		flightSearchResults.getFirstFlightResult(driver);
		sleep(15);

		TripDetailsPage tripDetailsPage = PageFactory.initElements(driver,
				TripDetailsPage.class);
		tripDetailsPage.verifyTripDetailsPage();
		tripDetailsPage.continueButton();
		sleep(15);

		CustomizeTripPage customizeTripPage = PageFactory.initElements(driver,
				CustomizeTripPage.class);
		sleep(5);
		customizeTripPage.verifyTripInformation(driver,
				tripDetailsPage.getTripInformation());
		customizeTripPage.continueButton(driver);
		sleep(15);

		CheckoutPage checkoutPage = PageFactory.initElements(driver,
				CheckoutPage.class);
		checkoutPage.verifyTripInformation(driver,
				tripDetailsPage.getTripInformation());
		checkoutPage.completeAndSendCheckoutFormSignedIn(driver,
				"testmdalmasso@gmail.com", "testingapp", "3415034627");
		sleep(15);
	}

	@Test
	public void flightAndHotelSearchTest() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.goToHomePage(driver);
		sleep(5);
		homePage.selectFlightAndHotel(driver);
		sleep(5);
		homePage.fillTripForm(driver);
		sleep(5);

		FlightAndHotelSearchResults flightAndHotelSearchResults = PageFactory
				.initElements(driver, FlightAndHotelSearchResults.class);
		flightAndHotelSearchResults.verifyResultsPage(driver);
		flightAndHotelSearchResults.getFirstFlightAndHotelResult(driver);
		sleep(5);
		flightAndHotelSearchResults.sortHotelResultByReviewerScore(driver);
		sleep(5);
		flightAndHotelSearchResults.sortHotelResultByFourStarAndUp(driver);
		sleep(5);
		flightAndHotelSearchResults.verifyHotelFilterResults(driver);
		sleep(5);
		flightAndHotelSearchResults.getFirstFlightAndHotelResult(driver);
		sleep(5);
		flightAndHotelSearchResults.verifyTripDetailResults(driver);
	}
}
