package com.Armorcode.bdd.integrations.NG_listners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.Armorcode.bdd.integrations.report_utils.ReportManager;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;


public class APIEvent implements ITestListener {
	// private String str_BrowserType = System.getProperty("BROWSER");

	@Override
	public void onTestStart(ITestResult arg0) {
		System.out.println("+++++++++++++++++++++onTestStart++++++++++++++++++++");
		
		
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		try {
			// Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");
		//	Runtime.getRuntime().exec("taskkill /f /im chrome.exe");
			// Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("+++++++++++++++++++++onTestFailure++++++++++++++++++++");
		ReportManager.logFailAPI(iTestResult.getThrowable().toString());
		ReportManager.endCurrentAPITest();
		
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

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}
	
	

	    public static List<String> getParameterNames(Method method) {
	        Parameter[] parameters = method.getParameters();
	        List<String> parameterNames = new ArrayList<>();

	        for (Parameter parameter : parameters) {
	            if(!parameter.isNamePresent()) {
	                throw new IllegalArgumentException("Parameter names are not present!");
	            }

	            String parameterName = parameter.getName();
	            parameterNames.add(parameterName);
	        }

	        return parameterNames;
	    }

}
