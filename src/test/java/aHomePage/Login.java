package aHomePage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.generic.reubentutorial.Base;

import Util.Screenshots;

public class Login {

	// You can use either the methods created in the BasePageFactory or Base
	// classes
	// For this example I'll be using the Base class I created
	Base bs = new Base();
	String url = "www.amazon.com";
	Logger log;
	WebDriver driver;

	@BeforeMethod
	public void setUp() throws IOException {

		bs.setUp(false, null, null, null, "Chrome", null, "https://www.amazon.com/");

	}

	@Test
	public void HomePageLogin() throws InterruptedException {
		// click login button
		bs.clickByXpath(".//*[@id='nav-link-accountList']/span[1]");
		bs.sleepFor(3);
		// enter login credentials
		bs.typeByXpath(".//*[@id='ap_email']", "Epixtest@gmail.com");
		bs.sleepFor(3);
		// enter password and enter
		bs.typeByXpathEnter(".//*[@id='ap_password']", "invalidpassword");
		bs.sleepFor(3);

	}

	@AfterMethod
	public void tearDown(ITestResult testResult) throws Exception {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			Screenshots.takeScreenshot(driver, testResult.getName());
		}
		bs.cleanUp();

	}

}
