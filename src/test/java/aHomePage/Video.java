package aHomePage;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.generic.reubentutorial.Base;

import Util.Screenshots;

public class Video {

	WebDriver driver;
	Base bs = new Base();

	@BeforeMethod
	public void setUp() throws IOException {
		bs.setUp(false, null, null, null, "Chrome", null, "https://www.amazon.com/");

	}
	
	@Test
	
	public void elementScrollexample() throws InterruptedException {
	bs.clickByXpath(".//*[@id='nav-link-shopall']/span[2]");
	bs.waitUntilClickAble(By.xpath(".//*[@id='a-page']/div[1]/div/div[1]/div[1]/div/a[1]"));
	bs.clickByXpath(".//*[@id='a-page']/div[1]/div/div[1]/div[1]/div/a[1]");
	bs.sleepFor(2);
//	bs.infiniteScroll();
	bs.scrollToElementClick(".//*[@id='content']/div[6]/div/div[1]/h2/a");
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
