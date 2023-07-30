package com.demoblaze;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import helperFunctions.helperFunctions;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;
import pageObjects.RegisterationLoginPages;

public class RegistrationTests {
	WebDriver driver;
	RegisterationLoginPages registerPage;
	helperFunctions operations;
	LandingPage landingPage;

	@BeforeMethod(alwaysRun = true)
	private void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		registerPage = new RegisterationLoginPages(driver);
		operations = new helperFunctions(driver);
		landingPage = new LandingPage(driver);

		try {
			landingPage.navigateToLandingPage();
			landingPage.waitForPageToLoad();
			driver.manage().window().maximize();
			System.out.println("Homepage is opened");
		}

		catch (Exception exception) {
			System.out.println("Website failed to load");
		}
	}

	@Parameters({ "username", "password", "expectedAlertMsg" })
	@Test (priority = 1)
	public void registerUserTests(String username, String password, String expectedAlertMsg) {

		// click signUp and fill user data
		registerPage.getSignUpBtn_Home().click();
		registerPage.getSignUpUserNameTextBox().sendKeys(username);
		registerPage.getSignUpPasswordTextBox().sendKeys(password);
		registerPage.getSignUpBtn_modal().click();

		// assert displayed alert message
		String actual_alertMsg = operations.getAlertText();
		operations.acceptAlert();

		Assert.assertEquals(actual_alertMsg, expectedAlertMsg);

	}

	@Test
	public void incompleteRegistrationTest() {

		// click signUp and register with no data
		registerPage.getSignUpBtn_Home().click();
		registerPage.getSignUpBtn_modal().click();

		// assert displayed alert message
		String actual_alertMsg = operations.getAlertText();
		operations.acceptAlert();

		String expected_alertMsg = "Please fill out Username and Password.";

		Assert.assertEquals(actual_alertMsg, expected_alertMsg);

	}

	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		driver.quit();
	}

}
