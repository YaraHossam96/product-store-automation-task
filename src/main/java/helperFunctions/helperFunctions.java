package helperFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class helperFunctions {
	
	WebDriver driver;
	
	public helperFunctions(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElementToDisplay (By FindBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	
	
	public void waitForElementsToDisplay (By FindBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(FindBy));
	}


	public void waitForAlertToDisplay ()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public String getAlertText ()
	{
		waitForAlertToDisplay();
		return driver.switchTo().alert().getText();
	}
	
	
	public void acceptAlert ()
	{
		waitForAlertToDisplay();
		driver.switchTo().alert().accept();
	}
	
	
}
