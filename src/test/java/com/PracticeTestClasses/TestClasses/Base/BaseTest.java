package com.PracticeTestClasses.TestClasses.Base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.PracticePageClasses.Pages.CategoryFilterPage;
import com.PracticePageClasses.Pages.LoginPage;
import com.PracticePageClasses.Pages.NavigationPage;
import com.PracticePageClasses.Pages.ResultsPage;
import com.PracticePageClasses.Pages.SearchBarPage;
import com.PracticePageClasses.utilities.Constants;

public class BaseTest {
	public WebDriver driver;
	protected String baseURL;
	protected LoginPage login;
	protected NavigationPage nav;
	protected SearchBarPage search;
	protected ResultsPage result;
	protected CategoryFilterPage category;

	@BeforeClass
	@Parameters({ "browser" })
	public void commonSetUp(String browser) {
		driver = WebDriverFactory.getInstance().getDriver(browser);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		baseURL = Constants.BASE_URL;
		driver.get(baseURL);
		nav = new NavigationPage(driver);
	}

	@BeforeMethod
	public void methodSetUp() {
		CheckPoint.clearHashMap();
	}

	@AfterClass
	public void tearDown() {
		WebDriverFactory.getInstance().quitDriver();
	}

}
