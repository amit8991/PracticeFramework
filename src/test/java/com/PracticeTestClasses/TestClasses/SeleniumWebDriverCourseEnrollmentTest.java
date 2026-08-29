package com.PracticeTestClasses.TestClasses;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.PracticePageClasses.Pages.AllCoursesPage;
import com.PracticePageClasses.Pages.CourseDetailPage;
import com.PracticePageClasses.Pages.VideoCoursePage;
import com.PracticePageClasses.Pages.EnrollmentPage;
import com.PracticePageClasses.utilities.Constants;
import com.PracticeTestClasses.TestClasses.Base.BaseTest;
import com.PracticeTestClasses.TestClasses.Base.CheckPoint;

/**
 * Test class for Selenium WebDriver 4 With Java course enrollment workflow
 * Single comprehensive test that covers:
 * 1. Navigate to https://www.letskodeit.com/practice
 * 2. Click on sign and login into the website
 * 3. Click on All courses link
 * 4. Click on course Selenium WebDriver 4 With Java
 * 5. Click on video course and click Enroll now
 * 6. Get the total order summary amount
 */
public class SeleniumWebDriverCourseEnrollmentTest extends BaseTest {

	@BeforeClass
	public void setUp() {
		System.out.println("****** Selenium WebDriver Course Enrollment Test Setup ******");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("****** After Method ******");
		if (nav.isUserLoggedIn()) {
			nav.logout();
		}
	}

	/**
	 * Complete workflow test: Navigate, Login, Find Course, and Get Order Summary
	 */
	@Test
	public void testSeleniumWebDriverCourseEnrollmentWorkflow() {
		System.out.println("****** Test: Selenium WebDriver Course Enrollment Workflow ******");
		
		// Step 1: Navigate to practice website
		System.out.println("\n--- Step 1: Navigate to Practice Website ---");
		String currentUrl = driver.getCurrentUrl();
		boolean isOnPracticeSite = currentUrl.contains("letskodeit.com/practice");
		
		CheckPoint.mark("step1-navigate-practice-site", isOnPracticeSite, 
				"Successfully navigated to practice website: " + currentUrl);
		
		// Step 2: Login to the website
		System.out.println("\n--- Step 2: Login to Website ---");
		login = nav.login();
		nav = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
		
		boolean headerResult = nav.verifyHeader();
		CheckPoint.mark("step2-header-verification", headerResult, 
				"Header verified after login");
		
		boolean isLoggedIn = nav.isUserLoggedIn();
		CheckPoint.mark("step2-login-verification", isLoggedIn, 
				"User logged in successfully with credentials: " + Constants.DEFAULT_USERNAME);
		
		// Step 3: Navigate to All Courses
		System.out.println("\n--- Step 3: Navigate to All Courses ---");
		AllCoursesPage allCoursesPage = nav.allCourses();
		
		boolean isOnAllCoursesPage = allCoursesPage.isAllCoursesPageLoaded();
		CheckPoint.mark("step3-all-courses-page-load", isOnAllCoursesPage, 
				"All Courses page loaded successfully");
		
		boolean coursesDisplayed = allCoursesPage.areCoursesDisplayed();
		CheckPoint.mark("step3-courses-displayed", coursesDisplayed, 
				"Courses are displayed on All Courses page");
		
		// Step 4: Click on Selenium WebDriver 4 With Java course
		System.out.println("\n--- Step 4: Click on Selenium WebDriver 4 With Java Course ---");
		CourseDetailPage courseDetailPage = allCoursesPage.clickOnCourse("Selenium WebDriver 4 With Java");
		
		boolean isOnCourseDetailPage = courseDetailPage.isCourseDetailPageLoaded();
		CheckPoint.mark("step4-course-detail-page-load", isOnCourseDetailPage, 
				"Selenium WebDriver course detail page loaded successfully");
		
		String courseTitle = courseDetailPage.getCourseTitleText();
		boolean isCourseCorrect = courseTitle.contains("Selenium WebDriver") || 
								  courseTitle.contains("WebDriver");
		CheckPoint.mark("step4-course-title-verification", isCourseCorrect, 
				"Course title verified: " + courseTitle);
		
		boolean isCourseContentDisplayed = courseDetailPage.isCourseContentDisplayed();
		CheckPoint.mark("step4-course-content-displayed", isCourseContentDisplayed, 
				"Course content is displayed");
		
		// Step 5: Click on video course and Enroll Now
		System.out.println("\n--- Step 5: Click on Video Course and Enroll Now ---");
		VideoCoursePage videoCoursePage = new VideoCoursePage(driver);
		
		boolean isVideoPageLoaded = videoCoursePage.isVideoCoursesPageLoaded();
		CheckPoint.mark("step5-video-page-load", isVideoPageLoaded, 
				"Video course page loaded");
		
		// Click on first video
		videoCoursePage.clickOnFirstVideo();
		boolean isVideoPlayerDisplayed = videoCoursePage.isVideoPlayerDisplayed();
		CheckPoint.mark("step5-video-player-display", isVideoPlayerDisplayed, 
				"Video player is displayed");
		
		String videoTitle = videoCoursePage.getVideoTitle();
		System.out.println("Video Title: " + videoTitle);
		CheckPoint.mark("step5-video-title-retrieved", !videoTitle.isEmpty(), 
				"Video title retrieved: " + videoTitle);
		
		// Check if Enroll Now button is visible
		boolean isEnrollButtonVisible = videoCoursePage.isEnrollNowButtonDisplayed();
		CheckPoint.mark("step5-enroll-button-visibility", isEnrollButtonVisible, 
				"Enroll Now button is visible on video course page");
		
		// Click Enroll Now button
		EnrollmentPage enrollmentPage = videoCoursePage.clickEnrollNowButton();
		boolean isEnrollmentPageLoaded = enrollmentPage.isEnrollmentPageLoaded();
		CheckPoint.mark("step5-enrollment-page-load", isEnrollmentPageLoaded, 
				"Enrollment/Checkout page loaded successfully");
		
		// Step 6: Get the total order summary amount
		System.out.println("\n--- Step 6: Get Total Order Summary Amount ---");
		
		// Verify enrollment page elements
		boolean isTotalAmountDisplayed = enrollmentPage.isTotalAmountDisplayed();
		CheckPoint.mark("step6-total-amount-displayed", isTotalAmountDisplayed, 
				"Total order amount is displayed on the page");
		
		// Get the total order amount
		String totalOrderAmount = enrollmentPage.getTotalOrderAmount();
		System.out.println("Total Order Amount Retrieved: " + totalOrderAmount);
		
		CheckPoint.mark("step6-total-amount-retrieval", !totalOrderAmount.isEmpty(), 
				"Total order amount retrieved successfully: " + totalOrderAmount);
		
		// Extract numeric value from the amount string
		double amountValue = enrollmentPage.extractAmountValue(totalOrderAmount);
		System.out.println("Extracted Amount Value: " + amountValue);
		
		CheckPoint.mark("step6-amount-extraction", amountValue > 0, 
				"Amount value extracted: " + amountValue);
		
		// Get complete order summary details
		String orderSummaryDetails = enrollmentPage.getOrderSummaryDetails();
		System.out.println("\nComplete Order Summary Details:\n" + orderSummaryDetails);
		
		CheckPoint.mark("step6-order-summary-details", !orderSummaryDetails.isEmpty(), 
				"Order summary details retrieved successfully");
		
		// Final verification - Mark as complete
		CheckPoint.markFinal("complete-workflow", 
				isOnPracticeSite && isLoggedIn && isOnAllCoursesPage && isOnCourseDetailPage && 
				isEnrollmentPageLoaded && isTotalAmountDisplayed && !totalOrderAmount.isEmpty(),
				"Complete Workflow Executed Successfully!\n" +
				"1. ✓ Navigated to practice website\n" +
				"2. ✓ Logged in successfully\n" +
				"3. ✓ Opened All Courses\n" +
				"4. ✓ Clicked on Selenium WebDriver 4 With Java course\n" +
				"5. ✓ Clicked on video and Enroll Now\n" +
				"6. ✓ Retrieved Total Order Amount: " + totalOrderAmount);
	}
}
