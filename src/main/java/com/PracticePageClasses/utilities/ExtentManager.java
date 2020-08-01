package com.PracticePageClasses.utilities;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;
	private static final Logger log = LogManager.getLogger(ExtentManager.class.getName());

	public static ExtentReports getInstance() {
		if (extent == null) {
			createInstance();
		}
		return extent;
	}

	public static synchronized ExtentReports createInstance() {
		String fileName = Util.getReportName();
		String reportsDirectory = Constants.REPORTS_DIRECTORY;
		new File(reportsDirectory).mkdirs();
		String path = reportsDirectory + fileName;
		log.info("*********** Report Path ***********");
		log.info(path);
		log.info("*********** Report Path ***********");
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle("Automation Run");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(fileName);
		htmlReporter.config().setAutoCreateRelativePathMedia(true);
		

		extent = new ExtentReports();
		extent.setSystemInfo("Organization", "Practice");
		extent.setSystemInfo("Automation Framework", "Selenium WebDriver");
		extent.attachReporter(htmlReporter);

		return extent;

	}

}
