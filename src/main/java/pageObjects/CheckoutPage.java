package pageObjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import helperFunctions.helperFunctions;

public class CheckoutPage extends helperFunctions {

	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	

	By nameTextBoxLocator = By.id("name");
	
	@FindBy (id = "name")
	WebElement nameTextBox;
	
	@FindBy (id = "country")
	WebElement countryTextBox;
	
	@FindBy (id = "city")
	WebElement cityTextBox;
	
	@FindBy (id = "card")
	WebElement cardTextBox;
	
	@FindBy (id = "month")
	WebElement monthTextBox;
	
	@FindBy (id = "year")
	WebElement yearTextBox;
	
	By purchaseBtnLocator = By.xpath("//button[contains(text(), \"Purchase\")]");
	@FindBy (xpath = "//button[contains(text(), \"Purchase\")]")
	WebElement purchaseBtn;
	
	By purchaseCompleteLocator = By.xpath("//h2[contains(text(), \"Thank you for your purchase!\")]");
			
	@FindBy (xpath = "//h2[contains(text(), \"Thank you for your purchase!\")]")
	WebElement purchaseComplete;
	
	@FindBy (css = "p.lead")
	WebElement purchaseDataBlock;
	
	@FindBy (xpath = "//button[contains(text(), \"OK\")]")
	WebElement okBtn;
	
	
	public WebElement getNameTextBox()
	{
		waitForElementToDisplay(nameTextBoxLocator);
		return nameTextBox;
	}
	
	public WebElement getCountryTextBox()
	{
		return countryTextBox;
	}
	
	
	public WebElement getCityTextBox()
	{
		return cityTextBox;
	}
	
	public WebElement getCreditCardTextBox()
	{
		return cardTextBox;
	}
	
	public WebElement getMonthTextBox()
	{
		return monthTextBox;
	}
	
	public WebElement getYearTextBox()
	{
		return yearTextBox;
	}
	
	public WebElement getPurchaseBtn()
	{
		waitForElementToDisplay(purchaseBtnLocator);
		return purchaseBtn;
	}

	public WebElement getSuccessfulPurchaseMsgr()
	
	{
		waitForElementToDisplay(purchaseCompleteLocator);
		return purchaseComplete;
	}
	
	public WebElement getOkBtn()
	
	{
		return okBtn;
	}
	
	public WebElement getPurchaseDataBlock ()
	{
		return purchaseDataBlock;
	}
	
	public List <String> getPurchaseData ()
	{
		String purchaseDataBlockText = getPurchaseDataBlock().getAttribute("innerText");
		List <String> purchaseDataTextLines = Arrays.asList(purchaseDataBlockText.split("\\n"));
		return purchaseDataTextLines;
	}
}
