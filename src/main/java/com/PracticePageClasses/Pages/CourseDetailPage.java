package com.PracticePageClasses.Pages;

import org.openqa.selenium.WebDriver;
import java.util.List;
import org.openqa.selenium.WebElement;

import com.PracticePageClasses.Pages.Base.BasePage;

/**
 * Page Object Model for Course Detail Page
 * Handles all operations on the individual course detail page
 */
public class CourseDetailPage extends BasePage {

	public CourseDetailPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public WebDriver driver;
	
	// Locators for Course Detail page
	private String COURSE_TITLE_HEADING = "xpath=>//h1[@class='course-title']";
	private String COURSE_DESCRIPTION = "xpath=>//div[@class='course-description']";
	private String COURSE_CONTENT_SECTION = "xpath=>//div[@class='course-content']";
	private String COURSE_MODULES = "xpath=>//div[@class='course-modules']";
	private String ENROLL_BUTTON = "xpath=>//button[contains(text(), 'Enroll')]";
	private String SECTIONS_LIST = "xpath=>//div[@class='course-sections']//div[@class='section']";

	/**
	 * Verify if Course Detail page is loaded
	 * @return true if page is loaded, false otherwise
	 */
	public boolean isCourseDetailPageLoaded() {
		try {
			WebElement courseTitle = getElement(COURSE_TITLE_HEADING, "Course Title");
			return courseTitle != null && courseTitle.isDisplayed();
		} catch (Exception e) {
			System.out.println("Course detail page not loaded: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Get the course title from the detail page
	 * @return course title text
	 */
	public String getCourseTitleText() {
		try {
			WebElement courseTitle = getElement(COURSE_TITLE_HEADING, "Course Title");
			return courseTitle != null ? courseTitle.getText() : "";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Check if course content/description is displayed
	 * @return true if content is displayed, false otherwise
	 */
	public boolean isCourseContentDisplayed() {
		try {
			WebElement courseContent = getElement(COURSE_CONTENT_SECTION, "Course Content");
			return courseContent != null && courseContent.isDisplayed();
		} catch (Exception e) {
			System.out.println("Course content not found: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Get course description text
	 * @return course description
	 */
	public String getCourseDescription() {
		try {
			WebElement description = getElement(COURSE_DESCRIPTION, "Course Description");
			return description != null ? description.getText() : "";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Get the number of sections/modules in the course
	 * @return number of sections
	 */
	public int getSectionsCount() {
		try {
			List<WebElement> sections = getElementList(SECTIONS_LIST, "Course Sections");
			return sections.size();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Click on Enroll button
	 * @return true if enrollment was successful, false otherwise
	 */
	public boolean clickEnrollButton() {
		try {
			elementClick(ENROLL_BUTTON, "Enroll Button");
			waitForPageLoad(2);
			return true;
		} catch (Exception e) {
			System.out.println("Error clicking Enroll button: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Get course modules/sections
	 * @return list of module elements
	 */
	public List<WebElement> getCourseModules() {
		try {
			return getElementList(COURSE_MODULES, "Course Modules");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
