package prematest.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginScreen {

	WebDriver driver;

	public LoginScreen(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div//h4[@class='subheader']//em[1]")
	WebElement userNameText;

	@FindBy(xpath = "//div//h4[@class='subheader']//em[2]")
	WebElement passwordText;

	@FindBy(id = "username")
	WebElement userName;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement submit;

	public SuccessScreen Login() {
		
		String un = userNameText.getText();
		String pswd = passwordText.getText();

		userName.sendKeys(un);
		password.sendKeys(pswd);
		submit.click();
		SuccessScreen Home = new SuccessScreen(driver);
		return Home;

	}

}
