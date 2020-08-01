package com.PracticePageClasses.Pages;

public class PracticePage {

	public static void main(String[] args) {

		String locator = "xpath=>//a[@href='/sign_in']";
		String locatorType = locator.split("=>")[0];
		locator = locator.split("=>")[1];
		System.out.println(locator);
		System.out.println(locatorType);
	}

}
