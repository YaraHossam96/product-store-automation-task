package pageObjects;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import helperFunctions.helperFunctions;

public class LandingPage extends helperFunctions {

	WebDriver driver;

	public LandingPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	String pageUrl = "https://www.demoblaze.com/";

	By landingPageLogoLocator = By.id("nava");

	By AllCategoriesLocator = By.xpath("//a[@id=\"cat\"]/following-sibling::a");
	@FindBy(xpath = "//a[@id=\"cat\"]/following-sibling::a")
	List<WebElement> AllCategories;

	By AllproductItemsLocator = By.className("card");
	@FindBy(className = "card")
	List<WebElement> AllproductItems;

	By itemCardLocator = By.xpath("//div[@class=\"card-block\"]");

	By itemNameLocator = By.xpath("h4");

	By itemPriceLocator = By.xpath("h5");

	By itemImgSrcLocator = By.xpath("preceding-sibling::a/img");

	public void navigateToLandingPage() {
		driver.get(pageUrl);
	}

	public void waitForPageToLoad() {
		waitForElementToDisplay(landingPageLogoLocator);
	}

	public List<WebElement> getAllCategories() {
		waitForElementsToDisplay(AllCategoriesLocator);
		return AllCategories;
	}

	public List<WebElement> getAllProductItems() {

		waitForElementToDisplay(AllproductItemsLocator);
		return AllproductItems;
	}

	public WebElement pickRandomItem() {

		Random random = new Random();
		int randomIndex = random.nextInt(AllproductItems.size());

		// returns item card info block
		return AllproductItems.get(randomIndex).findElement(itemCardLocator);

	}

	public String getRndItemName(WebElement item) {

		return item.findElement(itemNameLocator).getText();
	}

	public String getRndItemPrice(WebElement item) {
		return item.findElement(itemPriceLocator).getText().substring(1);
	}

	public String getRndItemImgSrc(WebElement item) {
		return item.findElement(itemImgSrcLocator).getAttribute("src");
	}

	public WebElement getClickableItemName(WebElement item) {
		return item.findElement(itemNameLocator);
	}

}
