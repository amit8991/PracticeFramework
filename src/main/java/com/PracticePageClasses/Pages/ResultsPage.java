package com.PracticePageClasses.Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.PracticePageClasses.Pages.Base.BasePage;

public class ResultsPage extends BasePage {

	public ResultsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public WebDriver driver;
	private String URL = "?query=";
	private String COURSES_LIST = "xpath=>//div[@class='course-listing']";

	public boolean isOpen() {
		return getURL().contains(URL);
	}

	public int coursesCount() {
		List<WebElement> coursesList= getElementList(COURSES_LIST, "Courses List");
		//List<WebElement> coursesList = driver.findElements(By.xpath(COURSES_LIST));
		return coursesList.size();
	}

	public boolean verifySearchResult() {
		boolean result = false;
		if (coursesCount() > 0) {
			result = true;
		}
		result = isOpen() && result;
		return result;
	}

	public boolean verifyFilterCourseCount(int expectedCount) {
		return coursesCount() == expectedCount;
	}
}
