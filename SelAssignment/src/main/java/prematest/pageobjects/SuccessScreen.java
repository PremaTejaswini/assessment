package prematest.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SuccessScreen {

	WebDriver driver;

	public SuccessScreen(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "flash")
	WebElement successMessage;

	@FindBy(xpath = "//div[@class='example']//a")
	WebElement logOut;


	public String extractsuccessMessage() {
		
		String validationMessage = successMessage.getText();
		String[] arr = validationMessage.split("!");
		System.out.println(arr[0]);
		return arr[0];

	}

	public void logOut() {

		logOut.click();
	}

}
