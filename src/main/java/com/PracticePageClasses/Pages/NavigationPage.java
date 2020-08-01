package com.PracticePageClasses.Pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.PracticePageClasses.Pages.Base.BasePage;
import com.PracticePageClasses.utilities.Util;

public class NavigationPage extends BasePage {

	public NavigationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		js = (JavascriptExecutor) driver;
	}

	public WebDriver driver;
	public JavascriptExecutor js;
	private String URL = "https://letskodeit.teachable.com/courses";
	private String ALL_COURSES_LINK = "xpath=>//a[contains(text(),'All Courses')]";
	private String MY_COURSES_LINK = "xpath=>//a[contains(text(),'My Courses')]";
	private String ACCOUNT_ICON = "class=>gravatar";
	private String LOGIN_LINK = "xpath=>//a[@href='/sign_in']";
	private String LOGOUT_LINK = "xpath=>//a[@href='/sign_out']";

	public LoginPage login() {
		elementClick(LOGIN_LINK, "Login Link");
		return new LoginPage(driver);
	}

	public void allCourses() {
		elementClick(ALL_COURSES_LINK, "All Courses Link");
	}

	public void myCourses() {
		elementClick(MY_COURSES_LINK, "My Courses Link");
	}

	public boolean isUserLoggedIn() {
		try {
			List<WebElement> accountImage = getElementList(ACCOUNT_ICON, "Account icon");
			return Util.verifyListNotEmpty(accountImage);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean verifyHeader() {
		WebElement link = getElement(ALL_COURSES_LINK, "All Courses Link");
		return Util.verifyTextContains(link.getText(), "All Courses");
	}

	public void logout() {
		elementClick(ACCOUNT_ICON, "User Account icon");
		WebElement logoutLink = waitForElement(LOGOUT_LINK, 10);
		javascriptClick(logoutLink, "Logout Link");

	}

	public boolean isOpen() {
		return URL.equalsIgnoreCase(driver.getCurrentUrl());
	}
}
