package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import helperFunctions.helperFunctions;

public class CartPage extends helperFunctions {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By cartBtnLocator = By.id("cartur");

	@FindBy(id = "cartur")
	WebElement cartBtn;

	By cartContentLocator = By.cssSelector("#tbodyid tr");
	@FindBy(css = "#tbodyid tr")
	List<WebElement> cartItems;

	By cartItemDataColsLocator = By.tagName("td");

	@FindBy(xpath = "//td/a[contains(text(), \"Delete\")]")
	WebElement deleteBtn;

	By totalPriceLocator = By.id("totalp");
	@FindBy(id = "totalp")
	WebElement totalPrice;

	@FindBy(xpath = "//button[contains(text(), \"Place Order\")]")
	WebElement placeOrderBtn;

	public WebElement getCartBtn() {
		waitForElementToDisplay(cartBtnLocator);
		return cartBtn;
	}

	public WebElement getDeleteBtn() {
		return deleteBtn;
	}

	public WebElement getPlaceOrderBtn() {
		return placeOrderBtn;
	}

	public String getTotalPrice() {
		return totalPrice.getText();
	}

	public List<WebElement> getAllCartItems() {
		waitForElementsToDisplay(cartContentLocator);
		return cartItems;
	}

	public boolean checkCartIsEmpty() throws Exception {

		if (getAllCartItems() != null) {
			return true;
		} else {
			throw new Exception("Item is not removed!");
		}

	}

	public List<WebElement> getRndItemRowData(List<WebElement> cartItems, String rndItemName) throws Exception {
		for (WebElement item : cartItems) {
			if (item.getText().contains(rndItemName)) {
				List<WebElement> cartItemDataCols = item.findElements(cartItemDataColsLocator);
				return cartItemDataCols;
			}

			else {
				throw new Exception("Item was not found in the cart!");
			}

		}

		return null;
	}

}
