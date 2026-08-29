package com.PracticePageClasses.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.PracticePageClasses.Pages.Base.BasePage;
import com.PracticePageClasses.utilities.Util;

/**
 * Page Object Model for Enrollment/Checkout Page
 * Handles all operations on the enrollment and order summary page
 */
public class EnrollmentPage extends BasePage {

	public EnrollmentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public WebDriver driver;
	
	// Locators for Enrollment/Checkout page
	private String ORDER_SUMMARY_CONTAINER = "xpath=>//div[@class='order-summary']";
	private String ORDER_TOTAL_AMOUNT = "xpath=>//div[@class='order-summary']//span[@class='total-amount']";
	private String TOTAL_PRICE = "xpath=>//div[contains(@class, 'total')]//span[@class='price']";
	private String ORDER_AMOUNT_TEXT = "xpath=>//div[@class='order-summary']//div[@class='total-section']//span";
	private String COURSE_PRICE = "xpath=>//div[@class='course-price']//span[@class='price']";
	private String ENROLL_CONFIRM_BUTTON = "xpath=>//button[contains(text(), 'Enroll')]";
	private String PROCEED_BUTTON = "xpath=>//button[contains(text(), 'Proceed')]";
	private String CONFIRM_BUTTON = "xpath=>//button[contains(text(), 'Confirm')]";

	/**
	 * Verify if Enrollment page is loaded
	 * @return true if page is loaded, false otherwise
	 */
	public boolean isEnrollmentPageLoaded() {
		try {
			WebElement orderSummary = getElement(ORDER_SUMMARY_CONTAINER, "Order Summary Container");
			return orderSummary != null && orderSummary.isDisplayed();
		} catch (Exception e) {
			System.out.println("Enrollment page not loaded: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Get the total order summary amount
	 * @return total amount as string
	 */
	public String getTotalOrderAmount() {
		try {
			// Try to get the total amount from the order summary
			WebElement totalAmount = getElement(ORDER_TOTAL_AMOUNT, "Total Order Amount");
			if (totalAmount != null) {
				return totalAmount.getText();
			}
			
			// Alternative locator
			totalAmount = getElement(TOTAL_PRICE, "Total Price");
			if (totalAmount != null) {
				return totalAmount.getText();
			}
			
			// Another alternative
			totalAmount = getElement(COURSE_PRICE, "Course Price");
			if (totalAmount != null) {
				return totalAmount.getText();
			}
			
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Get the complete order summary text
	 * @return order summary details
	 */
	public String getOrderSummaryDetails() {
		try {
			WebElement orderSummary = getElement(ORDER_SUMMARY_CONTAINER, "Order Summary Container");
			if (orderSummary != null) {
				return orderSummary.getText();
			}
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Check if total amount is present
	 * @return true if total amount is displayed, false otherwise
	 */
	public boolean isTotalAmountDisplayed() {
		try {
			WebElement totalAmount = getElement(ORDER_TOTAL_AMOUNT, "Total Order Amount");
			if (totalAmount != null && totalAmount.isDisplayed()) {
				return true;
			}
			
			totalAmount = getElement(TOTAL_PRICE, "Total Price");
			if (totalAmount != null && totalAmount.isDisplayed()) {
				return true;
			}
			
			return false;
		} catch (Exception e) {
			System.out.println("Total amount not displayed: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Click Enroll button to confirm enrollment
	 * @return true if enrollment was successful, false otherwise
	 */
	public boolean clickEnrollConfirmButton() {
		try {
			elementClick(ENROLL_CONFIRM_BUTTON, "Enroll Confirm Button");
			Util.waitForPageLoad(3);
			return true;
		} catch (Exception e) {
			System.out.println("Error clicking Enroll confirm button: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Click Proceed button
	 * @return true if button was clicked, false otherwise
	 */
	public boolean clickProceedButton() {
		try {
			elementClick(PROCEED_BUTTON, "Proceed Button");
			Util.waitForPageLoad(2);
			return true;
		} catch (Exception e) {
			System.out.println("Error clicking Proceed button: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Click Confirm button
	 * @return true if button was clicked, false otherwise
	 */
	public boolean clickConfirmButton() {
		try {
			elementClick(CONFIRM_BUTTON, "Confirm Button");
			Util.waitForPageLoad(2);
			return true;
		} catch (Exception e) {
			System.out.println("Error clicking Confirm button: " + e.getMessage());
			return false;
		}
	}
}
