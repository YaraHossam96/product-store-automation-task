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

public class LoginTests {
	WebDriver driver;
	RegisterationLoginPages loginPage;
	helperFunctions operations;
	LandingPage landingPage;

	@BeforeMethod(alwaysRun = true)
	private void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		loginPage = new RegisterationLoginPages(driver);
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

	@Parameters({ "username", "password" })
	@Test
	public void loginExistingUser(String username, String password) {

		// click Login and fill user data
		loginPage.getLoginBtn_Home().click();
		loginPage.getLogintUserNameTextBox().sendKeys(username);
		loginPage.getLoginPasswordTextBox().sendKeys(password);
		loginPage.getLoginBtn_modal().click();

		// assert profile name exists and correct
		Assert.assertTrue(loginPage.getUserProfileName() != null);
		Assert.assertEquals(loginPage.getUserProfileName().getText(), "Welcome " + username);

	}

	@Parameters({ "username", "password", "expectedAlertMsg" })
	@Test
	public void loginNonRegistered_incorrectPassword(String username, String password, String expectedAlertMsg) {

		// click login and fill user data
		loginPage.getLoginBtn_Home().click();
		loginPage.getLogintUserNameTextBox().sendKeys(username);
		loginPage.getLoginPasswordTextBox().sendKeys(password);
		loginPage.getLoginBtn_modal().click();

		String actual_alertMsg = operations.getAlertText();
		operations.acceptAlert();

		// assert displayed alert message
		Assert.assertEquals(actual_alertMsg, expectedAlertMsg);
	}

	@Parameters({ "expectedAlertMsg" })
	@Test
	public void incompleteLoginData(String expectedAlertMsg) {

		// login without filling user data
		loginPage.getLoginBtn_Home().click();
		loginPage.getLoginBtn_modal().click();

		String actual_alertMsg = operations.getAlertText();
		operations.acceptAlert();

		// assert displayed alert message
		Assert.assertEquals(actual_alertMsg, expectedAlertMsg);
	}

	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		driver.quit();
	}

}
