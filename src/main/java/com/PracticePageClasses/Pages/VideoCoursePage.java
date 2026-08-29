package com.PracticePageClasses.Pages;

import org.openqa.selenium.WebDriver;
import java.util.List;
import org.openqa.selenium.WebElement;

import com.PracticePageClasses.Pages.Base.BasePage;
import com.PracticePageClasses.utilities.Util;

/**
 * Page Object Model for Video Course Page
 * Handles all operations on the video course page
 */
public class VideoCoursePage extends BasePage {

	public VideoCoursePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public WebDriver driver;
	
	// Locators for Video Course page
	private String COURSE_VIDEO_CONTAINER = "xpath=>//div[@class='video-container']";
	private String FIRST_VIDEO = "xpath=>//div[@class='video-list']//div[@class='video-item'][1]";
	private String VIDEO_TITLE = "xpath=>//h2[@class='video-title']";
	private String ENROLL_NOW_BUTTON = "xpath=>//button[contains(text(), 'Enroll Now')]";
	private String ENROLL_BUTTON_ALT = "xpath=>//button[contains(text(), 'Enroll')]";
	private String VIDEO_PLAYER = "xpath=>//div[@class='video-player']";

	/**
	 * Verify if Video Course page is loaded
	 * @return true if page is loaded, false otherwise
	 */
	public boolean isVideoCoursesPageLoaded() {
		try {
			WebElement courseVideo = getElement(COURSE_VIDEO_CONTAINER, "Video Course Container");
			return courseVideo != null && courseVideo.isDisplayed();
		} catch (Exception e) {
			System.out.println("Video course page not loaded: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Click on the first video course
	 * @return VideoCoursePage object
	 */
	public VideoCoursePage clickOnFirstVideo() {
		try {
			elementClick(FIRST_VIDEO, "First Video Course");
			Util.waitForPageLoad(3);
		} catch (Exception e) {
			System.out.println("Error clicking on first video: " + e.getMessage());
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * Get the video title
	 * @return video title text
	 */
	public String getVideoTitle() {
		try {
			WebElement videoTitle = getElement(VIDEO_TITLE, "Video Title");
			return videoTitle != null ? videoTitle.getText() : "";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Check if video player is displayed
	 * @return true if video player is displayed, false otherwise
	 */
	public boolean isVideoPlayerDisplayed() {
		try {
			WebElement player = getElement(VIDEO_PLAYER, "Video Player");
			return player != null && player.isDisplayed();
		} catch (Exception e) {
			System.out.println("Video player not displayed: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Click on Enroll Now button
	 * @return EnrollmentPage object
	 */
	public EnrollmentPage clickEnrollNowButton() {
		try {
			// Try the primary button first
			try {
				elementClick(ENROLL_NOW_BUTTON, "Enroll Now Button");
			} catch (Exception e) {
				// If primary button not found, try alternative button
				elementClick(ENROLL_BUTTON_ALT, "Enroll Button");
			}
			Util.waitForPageLoad(3);
		} catch (Exception e) {
			System.out.println("Error clicking Enroll Now button: " + e.getMessage());
			e.printStackTrace();
		}
		
		return new EnrollmentPage(driver);
	}

	/**
	 * Check if Enroll Now button is displayed
	 * @return true if button is displayed, false otherwise
	 */
	public boolean isEnrollNowButtonDisplayed() {
		try {
			WebElement button = getElement(ENROLL_NOW_BUTTON, "Enroll Now Button");
			if (button != null && button.isDisplayed()) {
				return true;
			}
			button = getElement(ENROLL_BUTTON_ALT, "Enroll Button");
			return button != null && button.isDisplayed();
		} catch (Exception e) {
			System.out.println("Enroll button not displayed: " + e.getMessage());
			return false;
		}
	}
}
