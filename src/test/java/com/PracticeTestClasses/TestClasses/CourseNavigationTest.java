package com.PracticeTestClasses.TestClasses;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.PracticePageClasses.Pages.AllCoursesPage;
import com.PracticePageClasses.Pages.CourseDetailPage;
import com.PracticePageClasses.utilities.Constants;
import com.PracticeTestClasses.TestClasses.Base.BaseTest;
import com.PracticeTestClasses.TestClasses.Base.CheckPoint;

/**
 * Test class for navigating to Complete Test Automation Bundle course
 * Workflow:
 * 1. Navigate to https://www.letskodeit.com/practice
 * 2. Click on sign and login into the website
 * 3. Click on All courses link
 * 4. Click on course Complete Test Automation Bundle
 */
public class CourseNavigationTest extends BaseTest {

	@BeforeClass
	public void setUp() {
		System.out.println("****** Test Setup ******");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("****** After Method ******");
		if (nav.isUserLoggedIn()) {
			nav.logout();
			nav.login();
		}
	}

	/**
	 * Test: Navigate to practice website and verify landing page
	 */
	@Test(priority = 0)
	public void testNavigateToPracticeSite() {
		System.out.println("****** Test: Navigate to Practice Site ******");
		
		// Verify the page title or current URL
		String currentUrl = driver.getCurrentUrl();
		boolean isOnPracticeSite = currentUrl.contains("letskodeit.com/practice");
		
		CheckPoint.mark("navigate-practice-site", isOnPracticeSite, 
				"Successfully navigated to practice website");
		CheckPoint.markFinal("navigate-practice-site", isOnPracticeSite, 
				"Landing page verification");
	}

	/**
	 * Test: Login into the website
	 */
	@Test(priority = 1)
	public void testLoginToPracticeWebsite() {
		System.out.println("****** Test: Login to Practice Website ******");
		
		// Click on login and sign in
		login = nav.login();
		nav = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
		
		// Verify login was successful
		boolean headerResult = nav.verifyHeader();
		CheckPoint.mark("login-verification", headerResult, 
				"Header verification after login");
		
		boolean isLoggedIn = nav.isUserLoggedIn();
		CheckPoint.markFinal("login-verification", isLoggedIn, 
				"User login verification");
	}

	/**
	 * Test: Navigate to All Courses page
	 */
	@Test(priority = 2, dependsOnMethods = { "testLoginToPracticeWebsite" })
	public void testNavigateToAllCourses() {
		System.out.println("****** Test: Navigate to All Courses ******");
		
		// First ensure user is logged in
		login = nav.login();
		nav = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
		
		// Click on All Courses link
		AllCoursesPage allCoursesPage = nav.allCourses();
		
		// Verify we are on All Courses page
		boolean isOnAllCoursesPage = allCoursesPage.isAllCoursesPageLoaded();
		CheckPoint.mark("all-courses-navigation", isOnAllCoursesPage, 
				"All Courses page loaded successfully");
		
		boolean coursesDisplayed = allCoursesPage.areCoursesDisplayed();
		CheckPoint.markFinal("all-courses-navigation", coursesDisplayed, 
				"Courses are displayed on All Courses page");
	}

	/**
	 * Test: Navigate to Complete Test Automation Bundle course
	 */
	@Test(priority = 3, dependsOnMethods = { "testNavigateToAllCourses" })
	public void testNavigateToCompleteTestAutomationBundleCourse() {
		System.out.println("****** Test: Navigate to Complete Test Automation Bundle Course ******");
		
		// Ensure user is logged in
		login = nav.login();
		nav = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
		
		// Navigate to All Courses
		AllCoursesPage allCoursesPage = nav.allCourses();
		
		// Search or find the Complete Test Automation Bundle course
		CourseDetailPage courseDetailPage = allCoursesPage.clickOnCourse("Complete Test Automation Bundle");
		
		// Verify we are on the course detail page
		boolean isOnCourseDetailPage = courseDetailPage.isCourseDetailPageLoaded();
		CheckPoint.mark("course-detail-navigation", isOnCourseDetailPage, 
				"Course detail page loaded successfully");
		
		// Verify the course title matches
		String courseTitle = courseDetailPage.getCourseTitleText();
		boolean isCourseCorrect = courseTitle.contains("Complete Test Automation Bundle") || 
								  courseTitle.contains("Automation Bundle");
		CheckPoint.mark("course-title-verification", isCourseCorrect, 
				"Course title verified: " + courseTitle);
		
		// Verify course content is displayed
		boolean isCourseContentDisplayed = courseDetailPage.isCourseContentDisplayed();
		CheckPoint.markFinal("course-navigation-complete", isCourseContentDisplayed, 
				"Course content is displayed");
	}

	/**
	 * Test: Complete workflow - Login and navigate to Complete Test Automation Bundle
	 */
	@Test(priority = 4, dependsOnMethods = { "testNavigateToCompleteTestAutomationBundleCourse" })
	public void testCompleteWorkflow() {
		System.out.println("****** Test: Complete Workflow - Login and Navigate to Course ******");
		
		// Step 1: Verify we are on the practice site (should be already there from setUp)
		String currentUrl = driver.getCurrentUrl();
		boolean isOnPracticeSite = currentUrl.contains("letskodeit.com");
		CheckPoint.mark("workflow-site-verification", isOnPracticeSite, 
				"On LetsKodeIT website");
		
		// Step 2: Login
		login = nav.login();
		nav = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
		boolean isLoggedIn = nav.isUserLoggedIn();
		CheckPoint.mark("workflow-login", isLoggedIn, 
				"User logged in successfully");
		
		// Step 3: Navigate to All Courses
		AllCoursesPage allCoursesPage = nav.allCourses();
		boolean isOnAllCourses = allCoursesPage.isAllCoursesPageLoaded();
		CheckPoint.mark("workflow-all-courses", isOnAllCourses, 
				"All Courses page opened");
		
		// Step 4: Navigate to Complete Test Automation Bundle course
		CourseDetailPage courseDetailPage = allCoursesPage.clickOnCourse("Complete Test Automation Bundle");
		boolean isOnCourseDetail = courseDetailPage.isCourseDetailPageLoaded();
		
		CheckPoint.markFinal("workflow-complete", isOnCourseDetail, 
				"Complete workflow executed successfully - Course page loaded");
	}
}
