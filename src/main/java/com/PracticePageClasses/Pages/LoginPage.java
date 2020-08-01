package com.PracticePageClasses.Pages;

import org.openqa.selenium.WebDriver;

import com.PracticePageClasses.Pages.Base.BasePage;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public WebDriver driver;

	private String EMAIL_FIELD = "id=>user_email";
	private String PASSWORD_FIELD = "id=>user_password";
	private String LOG_IN_BUTTON = "name=>commit";

	public NavigationPage signInWith(String email, String password) {
		sendData(EMAIL_FIELD, email, "Email field");
		sendData(PASSWORD_FIELD, password, "Password field");
		elementClick(LOG_IN_BUTTON, "Login Button");

		return new NavigationPage(driver);
	}
}
