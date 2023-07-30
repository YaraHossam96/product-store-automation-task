package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import helperFunctions.helperFunctions;

public class ItemDetailsPage extends helperFunctions {

	WebDriver driver;
	WebDriverWait wait;

	public ItemDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By productDetailsTableLocator = By.cssSelector(".item");

	By addToCartBtnLocator = By.xpath("//*[contains(text(),'Add to cart')]");

	public void waitForPageToLoad() {
		waitForElementToDisplay(productDetailsTableLocator);
	}

	public WebElement getAddToCartBtn() {
		return driver.findElement(addToCartBtnLocator);
	}
}
