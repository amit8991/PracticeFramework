package com.PracticePageClasses.Pages;

import org.openqa.selenium.WebDriver;
import java.util.List;
import org.openqa.selenium.WebElement;

import com.PracticePageClasses.Pages.Base.BasePage;
import com.PracticePageClasses.utilities.Util;

/**
 * Page Object Model for All Courses Page
 * Handles all operations on the All Courses page
 */
public class AllCoursesPage extends BasePage {

	public AllCoursesPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public WebDriver driver;
	
	// Locators for All Courses page
	private String PAGE_TITLE = "xpath=>//h1[contains(text(), 'All Courses')]";
	private String COURSES_CONTAINER = "xpath=>//div[@class='courses-container']";
	private String COURSE_ITEM = "xpath=>//div[contains(@class, 'course-item')]";
	private String COURSE_TITLE = "xpath=>//h3[@class='course-title']";
	
	/**
	 * Verify if All Courses page is loaded
	 * @return true if page is loaded, false otherwise
	 */
	public boolean isAllCoursesPageLoaded() {
		try {
			WebElement pageTitle = getElement(PAGE_TITLE, "All Courses Page Title");
			return pageTitle != null && pageTitle.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Check if courses are displayed on the page
	 * @return true if courses are displayed, false otherwise
	 */
	public boolean areCoursesDisplayed() {
		try {
			List<WebElement> courses = getElementList(COURSE_ITEM, "Course Items");
			return Util.verifyListNotEmpty(courses);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Click on a specific course by name
	 * @param courseName - Name of the course to click
	 * @return CourseDetailPage object
	 */
	public CourseDetailPage clickOnCourse(String courseName) {
		try {
			// Construct dynamic XPath to find course by name
			String courseXPath = "xpath=>//div[contains(@class, 'course-item')]//h3[contains(text(), '" + courseName + "')]/ancestor::div[@class='course-item']";
			
			WebElement courseElement = getElement(courseXPath, "Course: " + courseName);
			if (courseElement != null) {
				elementClick(courseXPath, "Course: " + courseName);
				waitForPageLoad(3);
			}
		} catch (Exception e) {
			System.out.println("Error clicking on course: " + courseName);
			e.printStackTrace();
		}
		
		return new CourseDetailPage(driver);
	}

	/**
	 * Get the number of courses displayed on the page
	 * @return number of courses
	 */
	public int getCoursesCount() {
		try {
			List<WebElement> courses = getElementList(COURSE_ITEM, "Course Items");
			return courses.size();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Wait for page to load (utility method)
	 * @param seconds - seconds to wait
	 */
	private void waitForPageLoad(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
