package com.demoblaze;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import helperFunctions.helperFunctions;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.LandingPage;

public class CheckoutTests {

	WebDriver driver;
	LandingPage landingPage;

	@BeforeMethod(alwaysRun = true)
	private void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

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

	@Parameters({ "name", "country", "city", "creditcard", "month", "year" })
	@Test
	public void checkoutRandomItem(String name, String country, String city, String creditcard, String month,
			String year) throws Exception {

		AddRemoveCartItemsTests tests = new AddRemoveCartItemsTests(driver);
		CheckoutPage checkout = new CheckoutPage(driver);
		CartPage cart = new CartPage(driver);

		// add item to cart and place order
		tests.addRandomItemToCart();
		cart.getPlaceOrderBtn().click();

		// fill user data
		checkout.getNameTextBox().sendKeys(name);
		checkout.getCountryTextBox().sendKeys(country);
		checkout.getCityTextBox().sendKeys(city);
		checkout.getCreditCardTextBox().sendKeys(creditcard);
		checkout.getMonthTextBox().sendKeys(month);
		checkout.getYearTextBox().sendKeys(year);

		// confirm purchase
		checkout.getPurchaseBtn().click();

		// get displayed purchase user data
		List<String> purchaseData = checkout.getPurchaseData();
		String expectedMsg = "Thank you for your purchase!";

		// assert on successful purchase data
		Assert.assertEquals(checkout.getSuccessfulPurchaseMsgr().getText(), expectedMsg);
		Assert.assertEquals(purchaseData.get(1), "Amount: " + cart.getTotalPrice() + " USD");
		Assert.assertEquals(purchaseData.get(2), "Card Number: " + creditcard);
		Assert.assertEquals(purchaseData.get(3), "Name: " + name);

		// click OK to close modal
		checkout.getOkBtn().click();
	}

	@Test
	public void checkoutWithMissingData() throws Exception {
		AddRemoveCartItemsTests tests = new AddRemoveCartItemsTests(driver);
		CheckoutPage checkout = new CheckoutPage(driver);
		helperFunctions operations = new helperFunctions(driver);
		CartPage cart = new CartPage(driver);

		// add item to cart and place order
		tests.addRandomItemToCart();
		cart.getPlaceOrderBtn().click();

		// confirm purchase without filling user data
		checkout.getPurchaseBtn().click();

		// assert on displayed alert
		String expectedAlertMsg = "Please fill out Name and Creditcard.";
		Assert.assertEquals(operations.getAlertText(), expectedAlertMsg);

		// accept alert
		operations.acceptAlert();

	}

	@AfterMethod(alwaysRun = true)
	private void tearDown() {

		driver.quit();
	}
}
