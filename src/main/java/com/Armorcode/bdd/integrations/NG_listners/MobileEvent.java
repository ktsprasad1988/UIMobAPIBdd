package com.Armorcode.bdd.integrations.NG_listners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.Armorcode.bdd.integrations.common_utils.ConfigReader;
import com.Armorcode.bdd.integrations.common_utils.DriverFactory;
import com.Armorcode.bdd.integrations.report_utils.ReportManager;

public class MobileEvent implements ITestListener {

	DriverFactory driverFactory = DriverFactory.getInstance();
	private static final String KEY = "platform";
	private static final String KEY1 = "udid";
	private static final String KEY2 = "systemPort";
	private static final String KEY3 = "deviceName";
	private static final String KEY4 = "version";
	public static String Platform;
	public static String Udid;
	public static String SystemPort;
	public static String DeviceName;
	private static String Version;

	@Override
	public void onTestStart(ITestResult arg0) {
		System.out.println("+++++++++++++++++++onTestStart++++++++++++++++++");
		try {
			driverFactory.setMobileDriver(Platform, Udid, SystemPort, DeviceName,Version);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * try { ReportManager.logInfoMobile("Platform: " + Platform);
		 * ReportManager.logInfoMobile("UDID: " + Udid);
		 * ReportManager.logInfoMobile("Appium Port: " + SystemPort);
		 * ReportManager.logInfoMobile("Device Name: " + DeviceName);
		 * driverFactory.setMobileDriver(Platform, Udid, SystemPort, DeviceName); }
		 * catch (Exception e) { e.printStackTrace(); }
		 */
		

	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("Test Success: " + iTestResult.getMethod().getMethodName());
		ReportManager.logPassMobile("Test case passed");
		ReportManager.endCurrentTestMobile();

		driverFactory.getMobileDriver().quit();
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("Test Fail: " + iTestResult.getMethod().getMethodName());
		ReportManager.logFailMobile("Test case Fail");
		try {
			ReportManager.logMobileScreenshot();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ReportManager.logFailMobile(iTestResult.getThrowable().getMessage());
		ReportManager.endCurrentTestMobile();
		driverFactory.getMobileDriver().quit();
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
		Platform = arg0.getCurrentXmlTest().getParameter(KEY);
		Udid = arg0.getCurrentXmlTest().getParameter(KEY1);
		SystemPort = arg0.getCurrentXmlTest().getParameter(KEY2);
		DeviceName = arg0.getCurrentXmlTest().getParameter(KEY3);
		Version = arg0.getCurrentXmlTest().getParameter(KEY4);

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

}
