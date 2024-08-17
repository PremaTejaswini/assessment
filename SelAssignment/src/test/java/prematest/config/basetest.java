package prematest.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import prematest.pageobjects.LandingPage;

public class basetest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\prematest\\resources\\GlobalData.properties");
		

		prop.load(fis);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();

			driver.manage().window().maximize();
			return driver;

		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.out.println("firefox reached");
			WebDriverManager.firefoxdriver().setup();
			WebDriver driver = new FirefoxDriver();

			driver.manage().window().maximize();
			return driver;
		} else {

			WebDriverManager.edgedriver().setup();
			WebDriver driver = new EdgeDriver();
			return driver;

		}

	}

	public String Screenshot(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File source = ts.getScreenshotAs(OutputType.FILE);

		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");

		FileUtils.copyFile(source, file);

		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";

	}

	public List<HashMap<String, String>> getJsonData(String filepath) throws IOException {

		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	@BeforeMethod(alwaysRun = true)

	public void launchApplication() throws IOException {

		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
//		return landingPage;

	}

	@AfterMethod(alwaysRun = true)

	public void closeApplication() {

		driver.quit();

	}

}
