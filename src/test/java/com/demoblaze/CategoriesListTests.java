package com.demoblaze;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;

public class CategoriesListTests {

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

	@Test
	public void checkCategoriesHasProductItems() throws InterruptedException {

		// get all landing page categories
		List<WebElement> categories = landingPage.getAllCategories();

		// loop on each category product items
		for (WebElement categoryName : categories) {
			categoryName.click();
			List<WebElement> productList = landingPage.getAllProductItems();

			// assert each category list has items
			Assert.assertNotEquals(productList.size(), 0);

		}
	}

	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		driver.quit();

	}
}