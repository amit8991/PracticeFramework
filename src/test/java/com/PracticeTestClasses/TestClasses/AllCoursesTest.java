package com.PracticeTestClasses.TestClasses;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.PracticePageClasses.Pages.CategoryFilterPage;
import com.PracticePageClasses.Pages.SearchBarPage;
import com.PracticePageClasses.utilities.Constants;
import com.PracticeTestClasses.TestClasses.Base.BaseTest;


public class AllCoursesTest extends BaseTest{


	@BeforeClass
	@Parameters({ "browser" })
	public void setUp() {
		login = nav.login();
		nav = login.signInWith(Constants.DEFAULT_USERNAME,Constants.DEFAULT_PASSWORD);
	}

	
	@Test
	public void verifySearchCourse() {
		nav.allCourses();
		search = new SearchBarPage(driver);
		result=search.course("rest api");
		boolean searchResult = result.verifySearchResult();
		Assert.assertTrue(searchResult);
	}
	
	 @Test
	    public void filterByCategory() {
	        nav.allCourses();
	        category = new CategoryFilterPage(driver);
	        result = category.select("Software IT");
	        int count = category.findCoursesCount("Software IT");
	        boolean filterResult = result.verifyFilterCourseCount(count);
	        Assert.assertTrue(filterResult);
	    }
}
