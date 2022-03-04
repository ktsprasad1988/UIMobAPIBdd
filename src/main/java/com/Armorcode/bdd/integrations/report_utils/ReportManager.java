package com.Armorcode.bdd.integrations.report_utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.Armorcode.bdd.integrations.common_utils.DriverFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportManager {

           public static ExtentHtmlReporter htmlReporter;
           public static ExtentReports extent;
           public static Map<Long, ExtentTest> testMap = new HashMap<>();
           public static Map<String, ExtentTest> extentMap = new HashMap<>();
           public static void startReport() {

                      if (htmlReporter == null) {
                                 String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
                                 String dateStamp = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
                                 htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Execution_Reports/Web_Reports/Armorcode.html");
                                                       //+ dateStamp + "/" + "Armorcode-" + timeStamp + ".html");
                                 
                                // htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +
                                		 
                                 // "/Reports/WebReports/Decathlon.html");
                                 // Create an object of Extent Reports
                                 extent = new ExtentReports();
                                 extent.attachReporter(htmlReporter);
                                 extent.setSystemInfo("Host Name", "Armorcode");
                                 extent.setSystemInfo("Environment", "Production");
                                 extent.setSystemInfo("User Name", "Kriss");
                                 System.out.println();
                                 htmlReporter.config().setDocumentTitle("Armorcode Application");
                                 // Name of the report
                                 htmlReporter.config().setReportName("Armorcode Application");
                                 htmlReporter.config().enableTimeline(true);
                                 // htmlReporter.config().setAutoCreateRelativePathMedia(true);
                                 // Dark Theme
                                 htmlReporter.config().setTheme(Theme.STANDARD);

                      }

           }
           public static void startTest(String testName, String categories) {
                      if(extentMap.containsKey(testName)) {
                                 extent.removeTest(extentMap.get(testName));
                                 testName = "Rerun - "+testName;
                      }
                      ExtentTest test = extent.createTest(testName);
                      test.assignCategory(categories);
                      testMap.put(Thread.currentThread().getId(), test);
                      extentMap.put(testName, test);
           }

           /*public static void startTest(String testName, String categories) {

                      ExtentTest test = extent.createTest(testName);
                      test.assignCategory(categories);
                      testMap.put(Thread.currentThread().getId(), test);

           }
*/
           /**
           * =============================================================================
           * Method: logScreenShot | Author: Rajesh Buddha | Date:30 Jan 2020 |
           * Description: This method log take screenshot | Parameters: message | Return:
           * none
           * =============================================================================
           * 
            * @param driver
           * @throws IOException
           */
           public static void logScreenshot() throws IOException {
                      // getCurrentTest().addScreenCaptureFromBase64String(ScreenshotUtil.takeScreenshot(DriverFactory.getInstance().getWebDriver()));
                      MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromBase64String(
                                            ScreenshotUtil.takeScreenshot(DriverFactory.getInstance().getWebDriver())).build();
                      getCurrentTest().fail("", mediaModel);

           }

           public static void logPass(String message) {
                      getCurrentTest().log(Status.PASS, message);

           }

           public static void logFail(String message){
                      getCurrentTest().log(Status.FAIL, message);
           }

           public static void logInfo(String message) {
                      getCurrentTest().log(Status.INFO, message);

           }

           public static void endCurrentTest() {
                      getCurrentTest().getExtent().flush();

                      testMap.remove(Thread.currentThread().getId());
           }

           public static ExtentTest getCurrentTest() {
                      return testMap.get(Thread.currentThread().getId());

           }

           public static void endReport() {

                      extent.flush();
           }

           public static ExtentHtmlReporter htmlReporterMobile;
           public static ExtentReports extentMobile;
           public static Map<Long, ExtentTest> testMapMobile = new HashMap<>();

           public static void startReportMobile() {
                      if (htmlReporter == null) {
                                 htmlReporter = new ExtentHtmlReporter(
                                                       System.getProperty("user.dir") + "/reports/MobileReports/Armorcode.html");
                                 // Create an object of Extent Reports
                                 extent = new ExtentReports();
                                 extent.attachReporter(htmlReporter);
                                 extent.setSystemInfo("Host Name", "Armorcode");
                                 extent.setSystemInfo("Environment", "Production");
                                 extent.setSystemInfo("User Name", "Kriss");
                                 htmlReporter.config().setDocumentTitle("Armorcode Application");
                                 // Name of the report
                                 htmlReporter.config().setReportName("Armorcode Application");
                                 htmlReporter.config().enableTimeline(true);
                                 // htmlReporter.config().setAutoCreateRelativePathMedia(true);
                                 // Dark Theme
                                 htmlReporter.config().setTheme(Theme.STANDARD);

                      }

           }

           public static void startTestMobile(String testName, String categories) {
                      if(extentMap.containsKey(testName)) {
                                 extent.removeTest(extentMap.get(testName));
                                 testName = "Rerun - "+testName;
                      }
                      ExtentTest test = extent.createTest(testName);
                      test.assignCategory(categories);
                      //if(!testMap.containsKey(Thread.currentThread().getId())) 
                      {
                                 testMap.put(Thread.currentThread().getId(), test);
                                 extentMap.put(testName, test);
                      }

           }

           public static void logPassMobile(String message) {
                      getCurrentTest().log(Status.PASS, message);

           }

           /**
           * =============================================================================
           * Method: logScreenShot | Author: Rajesh Buddha | Date:30 Jan 2020 |
           * Description: This method log take screenshot | Parameters: message | Return:
           * none
           * =============================================================================
           * 
            * @param driver
           * @throws IOException
           */
           public static void logMobileScreenshot() throws IOException {
                      MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromBase64String(
                                            ScreenshotUtil.takeScreenshot(DriverFactory.getInstance().getMobileDriver())).build();
                      getCurrentTest().fail("", mediaModel);

           }

           public static void logFailMobile(String message) {
                      getCurrentTest().log(Status.FAIL, message);

           }

           public static void logInfoMobile(String message) {
                      getCurrentTest().log(Status.INFO, message);

           }

           public static void endCurrentTestMobile() {
                      getCurrentTest().getExtent().flush();

                      testMap.remove(Thread.currentThread().getId());
           }

           public static ExtentTest getCurrentTestMobile() {
                      return testMap.get(Thread.currentThread().getId());

           }

           public static void endReportMobile() {

                      extent.flush();
           }

           /**
           * =============================================================================
           * Method: Api Report | Author: Govindh | Date:11 Aug 2020 | Parameters: message
           * | Return: none
           * =============================================================================
           * 
            * @param driver
           * @throws IOException
           */

           public static ExtentHtmlReporter htmlReporterAPI;
           public static ExtentReports extentAPI;
           public static Map<Long, ExtentTest> testMapAPI = new HashMap<>();
           public static ExtentTest testSuiteAPI;
           public static Map<String, ExtentTest> extentMapAPI = new HashMap<>();

           public static void startApiReport() {

                      if (htmlReporterAPI == null) {
                                 String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
                                 String dateStamp = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
                                 htmlReporterAPI = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Execution_Reports/API_Reports/ArmorCodeAPI.html");
                                 extentAPI = new ExtentReports();
                                 extentAPI.attachReporter(htmlReporterAPI);
                                 extentAPI.setSystemInfo("Host Name", "Armorcode");
                                 extentAPI.setSystemInfo("Environment", "Production");
                                 extentAPI.setSystemInfo("User Name", "Kriss");
                                 System.out.println();
                                 htmlReporterAPI.config().setDocumentTitle("Armorcode Application");
                                 // Name of the report
                                 htmlReporterAPI.config().setReportName("Armorcode Application");
                                 htmlReporterAPI.config().enableTimeline(true);
                                 // Dark Theme
                                 htmlReporterAPI.config().setTheme(Theme.STANDARD);

                      }
           }

           public static void startTestAPI(String apiTestSuiteName, String categories) {
                      if(extentMapAPI.containsKey(apiTestSuiteName)) {
                                 extentAPI.removeTest(extentMapAPI.get(apiTestSuiteName));
                                 apiTestSuiteName = "Rerun - "+apiTestSuiteName;
                      }
                      testSuiteAPI = extentAPI.createTest(apiTestSuiteName);
                      testSuiteAPI.assignCategory(categories);
                      testMapAPI.put(Thread.currentThread().getId(), testSuiteAPI);

           }

           public static void createAPI_Node(String api_scenarioName) {

                      testSuiteAPI.createNode(api_scenarioName);

           }

           public static void logPassAPI(String message) {
                      getCurrentAPITest().log(Status.PASS, message);

           }

           public static void logFailAPI(String message) {
                      getCurrentAPITest().log(Status.FAIL, message);

           }

           public static void logInfoAPI(String message) {
                      getCurrentAPITest().log(Status.INFO, message);

           }

           public static void endCurrentAPITest() {
                      getCurrentAPITest().getExtent().flush();

                      testMapAPI.remove(Thread.currentThread().getId());
           }

           public static ExtentTest getCurrentAPITest() {
                      return testMapAPI.get(Thread.currentThread().getId());

           }

           public static void endAPIReport() {

                      extentAPI.flush();
           }

}
