package com.PracticeTestClasses.TestClasses.Base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import com.PracticePageClasses.utilities.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {

	private static final WebDriverFactory instance = new WebDriverFactory();

	private WebDriverFactory() {

	}

	public static WebDriverFactory getInstance() {
		return instance;
	}

	private static ThreadLocal<WebDriver> threadedDriver = new ThreadLocal<WebDriver>();
	private static ThreadLocal<String> threadedBrowser = new ThreadLocal<String>();

	public WebDriver getDriver(String browser) {
		WebDriver driver = null;
		setDriver(browser);
		threadedBrowser.set(browser);
		if (threadedDriver.get() == null) {
			try {
				if (browser.equalsIgnoreCase(Constants.FIREFOX)) {
					FirefoxOptions ffOptions = setFFOptions();
					driver = new FirefoxDriver(ffOptions);
					threadedDriver.set(driver);
				}
				if (browser.equalsIgnoreCase(Constants.CHROME)) {
					ChromeOptions chromeOptions = setChromeOptions();
					driver = new ChromeDriver(chromeOptions);
					threadedDriver.set(driver);
				}
				if (browser.equalsIgnoreCase(Constants.IE)) {
					InternetExplorerOptions ieOptions = setIEOptions();
					driver = new InternetExplorerDriver(ieOptions);
					threadedDriver.set(driver);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			threadedDriver.get().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			threadedDriver.get().manage().window().maximize();
		}
		return threadedDriver.get();
	}

	public void quitDriver() {
		threadedDriver.get().quit();
		threadedDriver.set(null);
	}

	private void setDriver(String browser) {
		String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
		System.out.println("OS Name from system property :: " + os);

		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
		} else if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
		} else if (browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
		} else {
			System.out.println("Browser type not supported");
		}
	}

	private ChromeOptions setChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		return options;
	}

	private FirefoxOptions setFFOptions() {
		FirefoxOptions options = new FirefoxOptions();
		options.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
		return options;
	}

	private InternetExplorerOptions setIEOptions() {
		InternetExplorerOptions options = new InternetExplorerOptions();
		options.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
		options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
		options.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		return options;
	}

	public String getBrowser() {

		return threadedBrowser.get();
	}
}
