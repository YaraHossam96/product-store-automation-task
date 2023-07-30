package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import helperFunctions.helperFunctions;

public class RegisterationLoginPages extends helperFunctions {

	WebDriver driver;

	public RegisterationLoginPages(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "signin2")
	WebElement signUpBtn_Home;

	@FindBy(id = "login2")
	WebElement loginBtn_Home;

	By signUpUsernameLocator = By.id("sign-username");
	@FindBy(id = "sign-username")
	WebElement signUpUsernameTextBox;

	By loginUsernameLocator = By.id("loginusername");
	@FindBy(id = "loginusername")
	WebElement loginUsernameTextBox;

	@FindBy(id = "sign-password")
	WebElement signUpPasswordTextBox;

	@FindBy(id = "loginpassword")
	WebElement loginPasswordTextBox;

	By signUpBtn_modalLocator = By.xpath("//button[@onclick=\"register()\"]");
	@FindBy(xpath = "//button[@onclick=\"register()\"]")
	WebElement signUpBtn_modal;

	By loginBtn_modalLocator = By.xpath("//button[@onclick=\"logIn()\"]");
	@FindBy(xpath = "//button[@onclick=\"logIn()\"]")
	WebElement loginBtn_modal;

	By userProfileNameLocator = By.id("nameofuser");
	@FindBy(id = "nameofuser")
	WebElement userProfileName;

	public WebElement getSignUpBtn_Home() {
		return signUpBtn_Home;
	}

	public WebElement getLoginBtn_Home() {
		return loginBtn_Home;
	}

	public WebElement getSignUpUserNameTextBox() {
		waitForElementToDisplay(signUpUsernameLocator);
		return signUpUsernameTextBox;
	}

	public WebElement getLogintUserNameTextBox() {
		waitForElementToDisplay(loginUsernameLocator);
		return loginUsernameTextBox;
	}

	public WebElement getSignUpPasswordTextBox() {
		return signUpPasswordTextBox;
	}

	public WebElement getLoginPasswordTextBox() {
		return loginPasswordTextBox;
	}

	public WebElement getSignUpBtn_modal() {
		waitForElementToDisplay(signUpBtn_modalLocator);
		return signUpBtn_modal;
	}

	public WebElement getLoginBtn_modal() {
		waitForElementToDisplay(loginBtn_modalLocator);
		return loginBtn_modal;
	}

	public WebElement getUserProfileName() {
		waitForElementToDisplay(userProfileNameLocator);
		return userProfileName;
	}
}
