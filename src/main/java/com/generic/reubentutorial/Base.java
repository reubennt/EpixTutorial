package com.generic.reubentutorial;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Base {

	public WebDriver driver = null;

	@Parameters({ "useCloudEnv", "userName", "accessKey", "os", "browserName", "browserVersion", "url" })

	@BeforeMethod
	public void setUp(@Optional("false") boolean useCloudEnv, @Optional("rahmanww") String userName,
			@Optional("") String accessKey, @Optional("Windows 8") String os, @Optional("firefox") String browserName,
			@Optional("34") String browserVersion, @Optional("http://www.cnn.com") String url) throws IOException {
		BasicConfigurator.configure();
		if (useCloudEnv == true) {
			// run in cloud
			getCloudDriver(userName, accessKey, os, browserName, browserVersion);
			// logger.setLevel(Level.INFO);
			// logger.info("Test is running on Saucelabs");
		} else {
			// run in local
			getLocalDriver(browserName);
			// logger.info("Test is running on Local");
		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);
		driver.manage().window().maximize();

	}

	public WebDriver getLocalDriver(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Webdrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Webdrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "Generic/browser-driver/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		return driver;

	}

	public WebDriver getCloudDriver(String userName, String accessKey, String os, String browserName,
			String browserVersion) throws IOException {
		{

			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("platform", os);
			cap.setBrowserName(browserName);
			cap.setCapability("version", browserVersion);
			driver = new RemoteWebDriver(
					new URL("http://" + userName + ":" + accessKey + "@ondemand.saucelabs.com:80/wd/hub"), cap);
			return driver;
		}
	}

	@AfterMethod

	// close all processes and end test
	public void cleanUp() {
		driver.quit();
	}

	// click on a css element
	public void clickByCss(String locator) {
		driver.findElement(By.cssSelector(locator)).click();
	}

	// click on an xpath element
	public void clickByXpath(String locator) {
		driver.findElement(By.xpath(locator)).click();
	}

	// click on xpath element and selects "enter" keyboard command
	public void typeByXpathEnter(String locator, String value) {
		driver.findElement(By.xpath(locator)).sendKeys(value, Keys.ENTER);
	}

	// type value by Css
	public void typeByCss(String locator, String value) {
		driver.findElement(By.cssSelector(locator)).sendKeys(value);
	}

	// type value by Css and hit enter on keyboard
	public void typeByCssNEnter(String locator, String value) {
		driver.findElement(By.cssSelector(locator)).sendKeys(value, Keys.ENTER);
	}

	// type value by Id
	public void typeById(String locator, String value) {
		driver.findElement(By.id(locator)).sendKeys(value);
	}

	// Type value by Id and hit enter on keyboard
	public void typeByIdEnter(String locator, String value) {
		driver.findElement(By.id(locator)).sendKeys(value, Keys.ENTER);
	}

	// type value by Class
	public void typeByClass(String locator, String value) {
		driver.findElement(By.className(locator)).sendKeys(value);
	}

	// type value by xPath
	public void typeByXpath(String locator, String value) {
		driver.findElement(By.xpath(locator)).sendKeys(value);
	}
	
	public void takeEnterKeys(String locator) {
		driver.findElement(By.cssSelector(locator)).sendKeys(Keys.ENTER);
	}
	
	public void clearInputField(String locator) {
		driver.findElement(By.cssSelector(locator)).clear();
	}
	//Capture list of elements by ID
	public List<WebElement> getListOfWebElementsById(String locator) {
		List<WebElement> list = driver.findElements(By.id(locator));
		return list;
	}
	//Capture text from Elements by Css
	public List<String> getTextFromWebElements(String locator) {
		List<WebElement> element = driver.findElements(By.cssSelector(locator));
		List<String> text = new ArrayList<String>();
		for (WebElement web : element) {
			text.add(web.getText());
		}

		return text;
	}

	public List<WebElement> getListOfWebElementsByCss(String locator) {
		List<WebElement> list = driver.findElements(By.cssSelector(locator));
		return list;
	}

	public String getCurrentPageUrl() {
		String url = driver.getCurrentUrl();
		return url;
	}

	public void navigateBack() {
		driver.navigate().back();
	}

	public void navigateForward() {
		driver.navigate().forward();
	}

	public String getTextByCss(String locator) {
		String st = driver.findElement(By.cssSelector(locator)).getText();
		return st;
	}

	public String getTextByXpath(String locator) {
		String st = driver.findElement(By.xpath(locator)).getText();
		return st;
	}

	public String getTextById(String locator) {
		return driver.findElement(By.id(locator)).getText();
	}

	public String getTextByName(String locator) {
		String st = driver.findElement(By.name(locator)).getText();
		return st;
	}

	public List<String> getListOfString(List<WebElement> list) {
		List<String> items = new ArrayList<String>();
		for (WebElement element : list) {
			items.add(element.getText());
		}
		return items;
	}

	public void selectOptionByVisibleText(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	public void sleepFor(int sec) throws InterruptedException {
		Thread.sleep(sec * 1000);
	}

	public void mouseHoverByCSS(String locator) {
		try {
			WebElement element = driver.findElement(By.cssSelector(locator));
			Actions action = new Actions(driver);
			Actions hover = action.moveToElement(element);
		} catch (Exception ex) {
			System.out.println("First attempt has been done, This is second try");
			WebElement element = driver.findElement(By.cssSelector(locator));
			Actions action = new Actions(driver);
			action.moveToElement(element).perform();

		}

	}

	public void typeByName(String locator, String value) {
		driver.findElement(By.name(locator)).sendKeys(value);
	}

	public void clickByLink(String locator) {
		driver.findElement(By.linkText(locator)).click();
	}

	public void typeByLink(String locator, String value) {
		driver.findElement(By.linkText(locator)).sendKeys(value);
	}

	public void mouseHoverByXpath(String locator) {
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			Actions action = new Actions(driver);
			action.moveToElement(element).perform();
		} catch (Exception ex) {
			System.out.println("First attempt has been done, This is second try");
			WebElement element = driver.findElement(By.cssSelector(locator));
			Actions action = new Actions(driver);
			action.moveToElement(element).perform();
		}
	}

	// handling Alert
	public void okAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void cancelAlert() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	// iFrame Handle
	public void iframeHandle(WebElement element) {
		driver.switchTo().frame(element);
	}

	public void goBackToHomeWindow() {
		driver.switchTo().defaultContent();
	}

	// get Links
	public void getLinks(String locator) {
		driver.findElement(By.linkText(locator)).findElement(By.tagName("a")).getText();
	}

	// Taking Screen shots
	public void takeScreenShot() throws IOException {
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("screenShots.png"));
	}

	// Synchronization
	public void waitUntilClickAble(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitUntilVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitUntilSelectable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		boolean element = wait.until(ExpectedConditions.elementToBeSelected(locator));
	}

	public void upLoadFile(String locator, String path) {
		driver.findElement(By.cssSelector(locator)).sendKeys(path);
		/*
		 * path example to upload a file/image path=
		 * "C:\\Users\\rrt\\Pictures\\ds1.png";
		 */
	}

	public void clearInput(String locator) {
		driver.findElement(By.cssSelector(locator)).clear();
	}

	public void keysInput(String locator) {
		driver.findElement(By.cssSelector(locator)).sendKeys(Keys.ENTER);
	}

	public void typebyCss2(String Locator, String value) {
		driver.findElement(By.cssSelector(Locator)).sendKeys(value);
	}


	public void scrollToElementClick(String Locator) {
		WebElement element = driver.findElement(By.xpath(Locator));
		Actions action = new Actions(driver);
		action.dragAndDropBy(element, 100, 0).perform();

	}
	
	public void radioButtonxPathClick(String locator){
		WebElement amzRadioButton = driver.findElement(By.xpath(locator));
		amzRadioButton.click();
	}
}
