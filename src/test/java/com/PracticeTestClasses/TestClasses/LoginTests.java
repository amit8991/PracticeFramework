package com.PracticeTestClasses.TestClasses;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.PracticePageClasses.utilities.Constants;
import com.PracticeTestClasses.TestClasses.Base.BaseTest;
import com.PracticeTestClasses.TestClasses.Base.CheckPoint;

public class LoginTests extends BaseTest {

	@BeforeClass
	public void setUp() {

	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("****** After Method ******");
		if (nav.isUserLoggedIn()) {
			nav.logout();
			nav.login();
		}
	}

	@Test
	public void testLogin() {
		login = nav.login();
		nav = login.signInWith(Constants.DEFAULT_USERNAME,Constants.DEFAULT_PASSWORD);
		boolean headerResult = nav.verifyHeader();
		// Assert.assertTrue(headerResult);
		CheckPoint.mark("test-01", headerResult, "Header Verification");
		boolean result = nav.isUserLoggedIn();
		// Assert.assertTrue(result);
		CheckPoint.markFinal("test-01", result, "Login Verification");
	}

	@Test(priority = 1, enabled = false)
	public void testInvalidLogin() {
		login = nav.login();
		nav = login.signInWith(Constants.DEFAULT_USERNAME,Constants.DEFAULT_PASSWORD);
		boolean result = nav.isUserLoggedIn();
		Assert.assertFalse(result);

	}
}
