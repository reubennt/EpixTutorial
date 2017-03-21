package com.generic.reubentutorial;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePageFactory {
	
	WebDriver driver;
	
	@FindBy(linkText="Departments")
	WebElement departments;
	
	@FindBy(linkText="All Videos")
	WebElement allVideos;

	@FindBy(linkText="Originals")
	WebElement originals;
	
	@FindBy(xpath=".//*[@id='anonCarousel1']/ol/li[1]/div/a/img")
	WebElement grandTour;
	
	@FindBy(xpath=".//*[@id='nav-link-accountList']/span[1]")
	WebElement login;
	
	@FindBy(id="Sign in")
	WebElement signIn;

	public void clickLogin() {
		login.click();
		signIn.click();
	}
	
	public void loginInfoInput() {
		
	}
	
	public void clickDepartments() {
		departments.click();
	}
	
	public void clickAllVideos() {
		allVideos.click();
	}
	
	public void clickOriginals() {
		originals.click();
		
	}
	
	public void clickGrandTour() {
		grandTour.click();
	}
	
	
	public BasePageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
}
