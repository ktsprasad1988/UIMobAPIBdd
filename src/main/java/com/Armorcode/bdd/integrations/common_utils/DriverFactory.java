package com.Armorcode.bdd.integrations.common_utils;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	private static DriverFactory instance = null;
	ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	ThreadLocal<AppiumDriver<WebElement>> appiumDriver = new ThreadLocal<AppiumDriver<WebElement>>();
	ThreadLocal<WiniumDriver> windowDriver = new ThreadLocal<WiniumDriver>();

	private DriverFactory() {

	}

	public static DriverFactory getInstance() {
		if (instance == null) {
			instance = new DriverFactory();
		}
		return instance;
	}

	public final void setWebDriver(String browser) throws Exception {

		DesiredCapabilities caps = null;
		
		switch (browser) {

		case "Chrome":
			ChromeOptions chOptions = new ChromeOptions();
			Map<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("credentials_enable_service", false);
			chOptions.setExperimentalOption("prefs", chromePrefs);
			chOptions.addArguments("--no-sandbox");
			//chOptions.addArguments("--headless"); // !!!should be enabled for Jenkins
			chOptions.addArguments("--disable-dev-shm-usage"); // !!!should be enabled for Jenkins
			chOptions.addArguments("--window-size=1920x1080"); // !!!should be enabled for Jenkins
			chOptions.addArguments("--disable-plugins", "--disable-extensions", "--disable-popup-blocking");
			//to add personalise cookies
			chOptions.addArguments("user-data-dir=C:\\Users\\Sourav Sengupta\\AppData\\Local\\Google\\Chrome\\User Data");
			chOptions.addArguments("profile-directory=Profile 1");
			//chOptions.setCapability(ChromeOptions.CAPABILITY, chOptions);
			//chOptions.setCapability("applicationCacheEnabled", false);
			System.setProperty("webdriver.chrome.silentOutput", "true");
			WebDriverManager.chromedriver().setup();
			webDriver.set(new ChromeDriver(chOptions));
			getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			getWebDriver().manage().window().maximize();
			break;


		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			webDriver.set(new FirefoxDriver());
			getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			getWebDriver().manage().window().maximize();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			webDriver.set(new EdgeDriver());
			getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			getWebDriver().manage().window().maximize();
			break;

		case "ie":
			WebDriverManager.iedriver().setup();
			webDriver.set(new InternetExplorerDriver());
			getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			getWebDriver().manage().window().maximize();
			break;
			
		case "ChromeMobileMode":
			Map<String, String> mobileEmulation = new HashMap<String, String>();
			mobileEmulation.put("deviceName", ConfigReader.getValue("ChromeMobileModeDeviceName"));
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
			System.setProperty("webdriver.chrome.silentOutput", "true");
			WebDriverManager.chromedriver().setup();
			webDriver.set(new ChromeDriver(chromeOptions));
			getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			break;
		}
	}

	public final void setMobileDriverForAndroid(String platform, String udid, String systemPort, String deviceName) throws Exception {

		String[] platformInfo = platform.split(" ");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformInfo[0]);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformInfo[1]);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		capabilities.setCapability(MobileCapabilityType.UDID, udid);
		capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemPort);
		capabilities.setCapability(MobileCapabilityType.APP,
				System.getProperty("user.dir") + ConfigReader.getValue("AppLocation"));
		capabilities.setCapability(MobileCapabilityType.ORIENTATION, "PORTRAIT");
		capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
		appiumDriver.set(new AppiumDriver<WebElement>(new URL("http://0.0.0.0:"+systemPort+"/wd/hub"), capabilities));
		getMobileDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	
public final void setMobileDriver(String platform, String udid, String systemPort, String deviceName, String deviceVersion) throws Exception {

		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, deviceVersion);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		capabilities.setCapability(MobileCapabilityType.UDID, udid);
		capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
		capabilities.setCapability(MobileCapabilityType.SUPPORTS_LOCATION_CONTEXT, true);
		//capabilities.setCapability("useNewWDA", true);
		/*capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, deviceVersion);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
		capabilities.setCapability(MobileCapabilityType.UDID, udid);*/
		capabilities.setCapability("app", "Users/svc/Desktop/Decathlon/DC_Unified_ATM/src/test/resources/Mobile_apps/Android_APK/Scan_and_Go.app");
		URL url = new URL("http://0.0.0.0:4723/wd/hub");
		appiumDriver.set(new AppiumDriver<WebElement>(url, capabilities));
		//AppiumDriver<WebElement> driver = new AppiumDriver(url,capabilities);
		getMobileDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public final void setDesktopDriver() throws Exception {
		//AppProcess.startApplication("Winium.Desktop.Driver.exe", "./src/test/resources/Driver/Winium.Desktop.Driver.exe");
		DesktopOptions options = new DesktopOptions();
		options.setApplicationPath(ConfigReader.getValue("ReliancePOSPath1"));
		windowDriver.set(new WiniumDriver(new URL(ConfigReader.getValue("localhost")), options));

	}

	public WebDriver getWebDriver() {
		return webDriver.get();
	}

	public AppiumDriver<WebElement> getMobileDriver() {
		return appiumDriver.get();
	}

	public WiniumDriver getWindowDriver() {
		return windowDriver.get();
	}
}