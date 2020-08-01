package com.PracticePageClasses.Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.PracticePageClasses.Pages.Base.BasePage;

public class CategoryFilterPage extends BasePage {

	public CategoryFilterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		js = (JavascriptExecutor) driver;
	}

	public WebDriver driver;
	public JavascriptExecutor js;
	private String CATEGORY_DROPDOWN = "xpath=>//div[contains(@class,'course-filter')][1]//button";
	private String CATEGORY_OPTION = "xpath=>//a[@href='/courses/category/%s']";

	public void clickCategoryDropdown() {
		elementClick(CATEGORY_DROPDOWN, "Category dropdown");
	}

	public WebElement findCategory(String categoryName) {
		clickCategoryDropdown();
		WebElement categoryOption = waitForElementToBeClickable(String.format(CATEGORY_OPTION, categoryName), 15);
		return categoryOption;
	}

	public ResultsPage select(String categoryName) {
		WebElement categoryOption = findCategory(categoryName);
		javascriptClick(categoryOption, "Category Option");
		waitForElement(CATEGORY_OPTION, 3);
		return new ResultsPage(driver);
	}

	public int findCoursesCount(String categoryName) {
		WebElement categoryOption = findCategory(categoryName);
		String categoryText = categoryOption.getText();

		String[] arrayTemp = categoryText.split("\\(");
		String courseCountString = arrayTemp[1].split("\\)")[0];
		int courseCount = Integer.parseInt(courseCountString);

		clickCategoryDropdown();
		return courseCount;

	}
}
