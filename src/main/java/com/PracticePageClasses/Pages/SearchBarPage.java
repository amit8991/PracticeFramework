package com.PracticePageClasses.Pages;

import org.openqa.selenium.WebDriver;

import com.PracticePageClasses.Pages.Base.BasePage;

public class SearchBarPage extends BasePage {

	public SearchBarPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public WebDriver driver;
	private String SEARCH_COURSE_FIELD = "id=>search-courses";
	private String SEARCH_COURSE_BUTTON = "id=>search-course-button";

	public ResultsPage course(String courseName) {
		sendData(SEARCH_COURSE_FIELD, courseName, "Search Course Field");
		elementClick(SEARCH_COURSE_BUTTON, "Search Course Button");
		
		return new ResultsPage(driver);
	}

}
