package prematest.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	WebDriver driver;

	public LandingPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='content']//ul//li[21]//a")
	WebElement formAuthentication;

	public LoginScreen navigation() {

		formAuthentication.click();
		LoginScreen login = new LoginScreen(driver);
		return login;
	}

	public void goTo() {

		driver.get("https://the-internet.herokuapp.com/");

	}


}
