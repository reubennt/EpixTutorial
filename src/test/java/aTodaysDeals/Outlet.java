package aTodaysDeals;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.generic.reubentutorial.Base;

import Util.Screenshots;

public class Outlet {

	WebDriver driver;
	Base bs = new Base();

	@BeforeMethod
	public void setUp() throws IOException {
		bs.setUp(false, null, null, null, "Firefox", null, "https://www.amazon.com/");

	}

	@Test
	public void checkBoxTest() throws InterruptedException {
		bs.clickByXpath(".//*[@id='nav-xshop']/a[2]");
		bs.sleepFor(2);
		bs.clickByXpath(".//*[@id='nav-subnav']/a[4]/span");
		bs.sleepFor(2);
		bs.radioButtonxPathClick(".//*[@id='widgetFilters']/div[1]/div/span[1]/div/label/input");
		bs.sleepFor(2);

	}

	@AfterMethod
	public void tearDown(ITestResult testResult) throws Exception {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			Screenshots.takeScreenshot(driver, testResult.getName());
		}
		bs.cleanUp();
	}
}
