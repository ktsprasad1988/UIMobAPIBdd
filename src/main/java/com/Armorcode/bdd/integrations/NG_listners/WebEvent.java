package com.Armorcode.bdd.integrations.NG_listners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.Armorcode.bdd.integrations.common_utils.ConfigReader;
import com.Armorcode.bdd.integrations.common_utils.DriverFactory;
import com.Armorcode.bdd.integrations.report_utils.ReportManager;

public class WebEvent implements ITestListener {
	// private String str_BrowserType = System.getProperty("BROWSER");
	private String str_BrowserType = ConfigReader.getValue("Browser");
	DriverFactory driverFactory = DriverFactory.getInstance();
	public static String strBrowser;

	@Override
	public void onTestStart(ITestResult arg0) {
		System.out.println("+++++++++++++++++++++onTestStart++++++++++++++++++++");
			ReportManager.startTest(arg0.getMethod().getMethodName(),"WEB");
			System.out.println("Execution started @ " + strBrowser + " browser & for type : Web UI");
			try {
				driverFactory.setWebDriver(str_BrowserType.trim());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		
			driverFactory.getWebDriver().close();
		
		try {
			 Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im chrome.exe");
			 Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("+++++++++++++++++++++onTestFailure++++++++++++++++++++");
		ReportManager.logFail(iTestResult.getThrowable().toString());
		
			try {
				ReportManager.logScreenshot();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ReportManager.endCurrentTest();
			driverFactory.getWebDriver().close();
		
	

		try {
			Runtime.getRuntime().exec("taskkill /f /im chrome.exe");
			 Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext arg0) {

		strBrowser = arg0.getCurrentXmlTest().getParameter("browser");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

}
