package com.demoblaze;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import helperFunctions.helperFunctions;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CartPage;
import pageObjects.ItemDetailsPage;
import pageObjects.LandingPage;

public class AddRemoveCartItemsTests {

	WebDriver driver;
	LandingPage landingPage;

	public AddRemoveCartItemsTests() {
	}

	public AddRemoveCartItemsTests(WebDriver driver) {
		this.driver = driver;
	}

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

	@Test(priority = 1)
	public void addRandomItemToCart() throws Exception {

		helperFunctions operations = new helperFunctions(driver);
		LandingPage landingPage = new LandingPage(driver);
		ItemDetailsPage itemDetails = new ItemDetailsPage(driver);
		CartPage cart = new CartPage(driver);

		// get all product items
		try {

			landingPage.getAllProductItems();

		}

		catch (Exception exception) {
			System.out.println("Homepage products failed to load");
		}

		// pick random item
		WebElement rndItem = landingPage.pickRandomItem();

		// extract item details
		String rndItemImgSrc = landingPage.getRndItemImgSrc(rndItem);
		String rndItemName = landingPage.getRndItemName(rndItem);
		String rndItemPrice = landingPage.getRndItemPrice(rndItem);

		// click on item card
		landingPage.getClickableItemName(rndItem).click();

		// add item to the cart
		itemDetails.waitForPageToLoad();
		itemDetails.getAddToCartBtn().click();

		// accept browser alert
		String actual_alertMsg = operations.getAlertText();
		operations.acceptAlert();

		String expected_alertMsg = "Product added";

		// validate alert message is as expected
		Assert.assertEquals(actual_alertMsg, expected_alertMsg);

		// open cart
		cart.getCartBtn().click();

		// get all cart items
		List<WebElement> cartItems = cart.getAllCartItems();

		// get added item row data
		List<WebElement> rndItemRowData = cart.getRndItemRowData(cartItems, rndItemName);

		// assert item data is correct
		Assert.assertEquals(rndItemImgSrc, rndItemRowData.get(0).findElement(By.xpath("./img")).getAttribute("src"));
		Assert.assertEquals(rndItemName, rndItemRowData.get(1).getText());
		Assert.assertEquals(rndItemPrice, rndItemRowData.get(2).getText());

		// assert total price
		Assert.assertEquals(rndItemPrice, cart.getTotalPrice());
	}

	@Test
	public void removeItemFromCart() throws Exception

	{

		CartPage cart = new CartPage(driver);

		// add random item to cart
		addRandomItemToCart();

		// get all cart items
		cart.getAllCartItems();

		// delete the item
		cart.getDeleteBtn().click();

		// assert cart is empty
		Assert.assertTrue(cart.checkCartIsEmpty());

	}

	@AfterMethod(alwaysRun = true)
	private void tearDown() {

		driver.quit();
	}
}
