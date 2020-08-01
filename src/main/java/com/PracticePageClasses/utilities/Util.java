package com.PracticePageClasses.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.google.common.collect.Ordering;

public class Util {

	public static void sleep(long msec, String info) {
		if (info != null) {
			System.out.println("Wait " + msec + " for " + info);
		}
		try {
			Thread.sleep(msec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void sleep(long msec) {
		sleep(msec, null);
	}

	public static int getRandomNumber(int min, int max) {
		int diff = max - min;
		return (int) (min + Math.random() * diff);
	}

	public static int getRandomNumber(int number) {
		return getRandomNumber(1, number);
	}

	public static String getRandomString(int length) {
		StringBuilder sbr = new StringBuilder();
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		for (int i = 0; i < length; i++) {
			int index = (int) (Math.random() * chars.length());
			sbr.append(chars.charAt(index));
		}
		String randomString = sbr.toString();
		System.out.println("Random String with length : : " + length + "is ::" + randomString);
		return randomString;
	}

	public static String getRandomString() {
		return getRandomString(10);
	}

	/***
	 * Get simple date as string in the specified format
	 * 
	 * @param format MMddyy MMddyyyy
	 * @return date as string type
	 */
	public static String getSimpleDateFormat(String format) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String formattedDate = formatter.format(date);
		System.out.println("Date with format :" + format + ":" + formattedDate);
		return formattedDate;
	}

	public static String getCurrentDateTime() {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String date = formatter.format(currentDate.getTime()).replace("/", "-");
		date = date.replace(":", "_");
		System.out.println("Date and Time " + date);
		return date;
	}

	public static boolean verifyTextContains(String actualText, String expText) {
		if (actualText.toLowerCase().contains(expText.toLowerCase())) {
			System.out.println("Actual Text From Web Application UI   --> : " + actualText);
			System.out.println("Expected Text From Web Application UI --> : " + expText);
			System.out.println("### Verification Contains !!!");
			return true;
		} else {
			System.out.println("Actual Text From Web Application UI   --> : " + actualText);
			System.out.println("Expected Text From Web Application UI --> : " + expText);
			System.out.println("### Verification DOES NOT Contains !!!");
			return false;
		}
	}

	public static boolean verifyTextMatch(String actualText, String expText) {
		if (actualText.toLowerCase().equals(expText.toLowerCase())) {
			System.out.println("Actual Text From Web Application UI   --> : " + actualText);
			System.out.println("Expected Text From Web Application UI --> : " + expText);
			System.out.println("### Verification Contains !!!");
			return true;
		} else {
			System.out.println("Actual Text From Web Application UI   --> : " + actualText);
			System.out.println("Expected Text From Web Application UI --> : " + expText);
			System.out.println("### Verification DOES NOT Contains !!!");
			return false;
		}
	}

	public static Boolean verifyListContains(List<String> actList, List<String> expList) {
		int expListSize = expList.size();
		for (int i = 0; i < expListSize; i++) {
			if (!(actList.contains(expList.get(i)))) {
				return false;
			}
		}
		System.out.println("Actual List contains Expected List");
		return true;
	}

	public static Boolean verifyListMatch(List<String> actList, List<String> expList) {
		boolean found = false;
		int actListSize = actList.size();
		int expListSize = expList.size();
		if (actListSize != expListSize) {
			return false;
		}
		for (int i = 0; i < actListSize; i++) {
			found = false;
			for (int j = 0; j < expListSize; j++) {
				if (verifyTextMatch(actList.get(i), expList.get(j))) {
					found = true;
					break;
				}
			}
		}
		if (found) {
			System.out.println("Actual List matches expected List");
			return true;
		} else {
			System.out.println("Actual List DOES NOT matches expected List");
			return false;
		}
	}

	public static Boolean verifyItemPresentInList(List<String> actList, String item) {
		int actListSize = actList.size();
		for (int i = 0; i < actListSize; i++) {
			if (!actList.contains(item)) {
				System.out.println("Item is NOT present in List !!!");
				return false;
			}
		}
		System.out.println("Item is present in List !!!");
		return true;
	}

	public static boolean isListAscendingOrder(List<Long> list) {
		boolean sorted = Ordering.natural().isOrdered(list);
		return sorted;
	}

	public static String getScreenshotName(String methodName, String browserName) {
		String localDateTime = getCurrentDateTime();
		StringBuilder name = new StringBuilder().append(browserName).append("_").append(methodName).append("_")
				.append(localDateTime).append(".png");
		return name.toString();

	}

	public static Boolean verifyListNotEmpty(List<WebElement> actualList) {
		int listSize = actualList.size();
		System.out.println("Size of list : " + listSize);
		if (listSize > 0) {
			System.out.println("List is not empty");
			return true;
		} else {
			System.out.println("List is empty");
			return false;
		}
	}

	public static String getReportName() {
		String localDateTime = getCurrentDateTime();
		StringBuilder name = new StringBuilder().append("AutomationReport_").append(localDateTime).append(".html");
		return name.toString();

	}

}
