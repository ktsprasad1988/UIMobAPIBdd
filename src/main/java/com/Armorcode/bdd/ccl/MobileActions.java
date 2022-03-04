package com.Armorcode.bdd.ccl;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Armorcode.bdd.integrations.common_utils.DriverFactory;
import com.Armorcode.bdd.integrations.report_utils.ReportManager;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;


public class MobileActions {

	Actions action = new Actions(DriverFactory.getInstance().getMobileDriver());

	/**
	 * =============================================================================
	 * Method: waitForVisible | Author: Rajesh Buddha | Date:16 Jan 2020 |
	 * Description: This method wait for element it will check every 5 sec its
	 * present or not until 60 sec | Parameters: locator | Return: element
	 * =============================================================================
	 */
	@SuppressWarnings("deprecation")
	public WebElement waitForVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getMobileDriver(), 100);
		wait.pollingEvery(5, TimeUnit.SECONDS);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}


	public void waitForElement(By locator, long time) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getMobileDriver(), time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * =============================================================================
	 * Method: Click | Author: Rajesh Buddha | Date:16 Jan 2020 | Description: This
	 * method click on element | Parameters: locator, info | Return: none
	 * =============================================================================
	 */
	public void Click(By locator, String info) {
		WebElement elm = waitForVisible(locator);
		elm.click();
		ReportManager.logInfo("Successfully clicked on - " + info);
		// LogClass.loginfo("Successfully clicked on -"+ info);
	}
	
	public void Click1(MobileElement webElement, String info) {
		//WebElement elm = waitForVisible(webElement);
		webElement.click();
		ReportManager.logPass("Successfully clicked on - " + info);
		//LogClass.loginfo("Successfully clicked on -"+ info);
	}
	
	public boolean isDisplayed(By locator, String info) {
		WebElement elm = waitForVisible(locator);
		boolean isPresent = elm.isDisplayed();
		if (isPresent) {
			ReportManager.logInfo("Successfully element displayed: " + info);
			System.out.println("Successfully element displayed: " + info);
		} else {
			ReportManager.logInfo("element not displayed: " + info);
			System.out.println("element not displayed: " + info);
		}
		return isPresent;
	}

	public void Send(String value) {

		Actions action = new Actions(DriverFactory.getInstance().getMobileDriver());
		action.sendKeys(value).perform();
	}
	
	

	/**
	 * =============================================================================
	 * Method: sendKeys | Author: Rajesh Buddha | Date:16 Jan 2020 | Description:
	 * This method enter text input text using element | Parameters: locator, text |
	 * Return: none
	 * =============================================================================
	 */
	public void sendKeys(By locator, String text) {
		WebElement elm = waitForVisible(locator);
		elm.sendKeys(text);
		ReportManager.logInfo("Successfully Entered text - " + text);
		System.out.println("Successfully Entered text - " + text);
	}

	/**
	 * =============================================================================
	 * Method: sendKeys | Author: Rajesh Buddha | Date:16 Jan 2020 | Description:
	 * This method enter text input text using element | Parameters: locator, text |
	 * Return: none
	 * =============================================================================
	 */
	public void sendKeys(By locator, String text, String info) {
		WebElement elm = waitForVisible(locator);
		elm.click();
		elm.sendKeys(text);
		ReportManager.logInfo(info + " <b style=\"color:green;\"> : " + text + "</b>");
		// LogClass.loginfo(info+" :"+text);
	}

	/**
	 * =============================================================================
	 * Method: clearAndSendKeys | Author: Rajesh Buddha | Date:16 Jan 2020 |
	 * Description: This method clear text in text box after that enter text using
	 * element | Parameters: locator, text | Return: none
	 * =============================================================================
	 */
	public void clearAndSendKeys(By locator, String text) {
		WebElement elm = waitForVisible(locator);
		elm.clear();
		elm.sendKeys(text);
		ReportManager.logInfo("Successfully Entered text -<b style=\"color:green;\"> " + text + "</b>");
		// LogClass.loginfo("Successfully Entered text - " + text);
	}

	/**
	 * =============================================================================
	 * Method: clearAndSendKeys | Author: Rajesh Buddha | Date:16 Jan 2020 |
	 * Description: This method clear text in text box after that enter text using
	 * element | Parameters: locator, text | Return: none
	 * =============================================================================
	 */
	public void clearAndSendKeys(By locator, String text, String info) {
		WebElement elm = waitForVisible(locator);
		elm.clear();
		elm.sendKeys(text);
		ReportManager.logInfo(info + "<b style=\"color:green;\"> :" + text + "</b>");
		// LogClass.loginfo(info+" : " + text);
	}

	/**
	 * =============================================================================
	 * Method: getText | Author: Rajesh Buddha | Date:16 Jan 2020 | Description:
	 * This method get the text of element | Parameters: locator | Return: elmText
	 * =============================================================================
	 */
	public String getText(By locator) {
		WebElement elm = waitForVisible(locator);
		String elmText = elm.getText();
		ReportManager.logInfo("Successfully get text - <b style=\"color:green;\">" + elmText + "</b>");
		System.out.println("Successfully get text - " + elmText);
		// LogClass.loginfo("Successfully get text - " + elmText);
		return elmText;
	}

	/**
	 * =============================================================================
	 * Method: getText | Author: Rajesh Buddha | Date:16 Jan 2020 | Description:
	 * This method get the text of element | Parameters: locator | Return: elmText
	 * =============================================================================
	 */
	public String getText(By locator, String info) {
		WebElement elm = waitForVisible(locator);
		String elmText = elm.getText();
		ReportManager.logInfo("" + info + "<b style=\"color:green;\"> :" + elmText + "</b>");
		// LogClass.loginfo(""+info+" : " + elmText);
		return elmText;
	}

	/**
	 * =============================================================================
	 * Method: swipeUp | Author: Rajesh Buddha | Date:16 Jan 2020 | Description:
	 * This method swipe up in mobile using touch action and enter int value number
	 * of times it should swipe| Parameters: howManySwipes | Return: none
	 * =============================================================================
	 */
	public void swipeUp(int howManySwipes) {
		Dimension size = DriverFactory.getInstance().getMobileDriver().manage().window().getSize();
		// calculate coordinates for vertical swipe
		int startY = (int) (size.height * 0.70);
		int endY = (int) (size.height * 0.30);
		int startX = (size.width / 2);
		try {
			for (int i = 1; i <= howManySwipes; i++) {
				new TouchAction(DriverFactory.getInstance().getMobileDriver())
						.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(startX, endY)).release()
						.perform();
				System.out.println("swipeUp");
			}
		} catch (Exception e) {
			// print error or something
		}
	}

	/**
	 * =============================================================================
	 * Method: swipeDown | Author: Rajesh Buddha | Date:16 Jan 2020 | Description:
	 * This method swipe down in mobile using touch action and enter int value
	 * number of times it should swipe| Parameters: howManySwipes | Return: none
	 * =============================================================================
	 */
	public void swipeDown(int howManySwipes) {
		// calculate coordinates for vertical swipe
		Dimension size = DriverFactory.getInstance().getMobileDriver().manage().window().getSize();
		int startY = (int) (size.height * 0.70);
		int endY = (int) (size.height * 0.30);
		int startX = (size.width / 2);
		try {
			for (int i = 1; i <= howManySwipes; i++) {
				new TouchAction(DriverFactory.getInstance().getMobileDriver())
						.longPress(PointOption.point(startX, endY)).moveTo(PointOption.point(startX, startY)).release()
						.perform();
				System.out.println("swipeDown");
			}
		} catch (Exception e) {
			// print error or something
		}
	}

	/**
	 * =============================================================================
	 * Method: swipeRighttoLeft | Author: Rajesh Buddha | Date:16 Jan 2020 |
	 * Description: This method swipe right to left in mobile using touch action and
	 * enter int value number of times it should swipe| Parameters: howManySwipes |
	 * Return: none
	 * =============================================================================
	 */
	public void swipeRighttoLeft(int howManySwipes) {
		Dimension size = DriverFactory.getInstance().getMobileDriver().manage().window().getSize();
		// calculate coordinates for horizontal swipe
		int startY = (int) (size.height / 2);
		int startX = (int) (size.width * 0.90);
		int endX = (int) (size.width * 0.10);
		try {
			for (int i = 1; i <= howManySwipes; i++) {
				new TouchAction(DriverFactory.getInstance().getMobileDriver())
						.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, startY)).release()
						.perform();
				System.out.println("swipeRighttoLeft");
			}
		} catch (Exception e) {
			// print error or something
		}
	}

	/**
	 * =============================================================================
	 * Method: swipeLefttoRight | Author: Rajesh Buddha | Date:16 Jan 2020 |
	 * Description: This method swipe left to right in mobile using touch action and
	 * enter int value number of times it should swipe| Parameters: howManySwipes |
	 * Return: none
	 * =============================================================================
	 */
	public void swipeLefttoRight(int howManySwipes) {
		Dimension size = DriverFactory.getInstance().getMobileDriver().manage().window().getSize();
		// calculate coordinates for horizontal swipe
		int startY = (int) (size.height / 2);
		int startX = (int) (size.width * 0.10);
		int endX = (int) (size.width * 0.90);
		try {
			for (int i = 1; i <= howManySwipes; i++) {
				new TouchAction(DriverFactory.getInstance().getMobileDriver())
						.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, startY)).release()
						.perform();
				System.out.println("swipeLefttoRight");
			}
		} catch (Exception e) {
			// print error or something
		}
	}

	/**
	 * =============================================================================
	 * Method: swipeUp_FindElementClick | Author: Rajesh Buddha | Date:16 Jan 2020 |
	 * Description: This method will swipe till element found and click| Parameters:
	 * howManySwipes, locator | Return: none
	 * =============================================================================
	 */
	public void swipeUp_FindElementClick(int howManySwipes, By locator) throws InterruptedException {
		Dimension size = DriverFactory.getInstance().getMobileDriver().manage().window().getSize();
		// calculate coordinates for vertical swipe
		int startY = (int) (size.height * 0.70);
		int endY = (int) (size.height * 0.30);
		int startX = (size.width / 2);
		Thread.sleep(3000);
		try {
			for (int i = 1; i <= howManySwipes; i++) {
				boolean isElmPresent = DriverFactory.getInstance().getMobileDriver().findElements(locator).size() > 0;
				if (isElmPresent) {
					DriverFactory.getInstance().getMobileDriver().findElement(locator).click();
					break;
				}
				new TouchAction(DriverFactory.getInstance().getMobileDriver())
						.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(startX, endY)).release()
						.perform();
				System.out.println("swipeUp");
			}
		} catch (Exception e) {
			// print error or something
		}
	}

	/**
	 * =============================================================================
	 * Method: pressKeyboardValues | Author: Rajesh Buddha | Date:16 Jan 2020 |
	 * Description: This method meant for static wait | Parameters: locator, text |
	 * Return: none
	 * =============================================================================
	 * 
	 * @throws InterruptedException
	 */
	public void pressKeyboardValues(Keys value) throws InterruptedException {
		action.sendKeys(value).build().perform();
		ReportManager.logInfo("Successfully performed keyboard action - <b style=\"color:green;\">" + value + "</b>");
		// LogClass.loginfo("Successfully performed keyboard action - " + value);
	}

	/**
	 * =============================================================================
	 * Method: rightToLeftSwipeUsingElement | Author: Rajesh Buddha | Date:16 Jan
	 * 2020 | Description: This method right To Left Swipe Using Element |
	 * Parameters: locator, text | Return: none
	 * =============================================================================
	 * 
	 * @param fromLocator
	 * @param toLocator
	 * 
	 * @throws InterruptedException
	 */
	public void rightToLeftSwipeUsingElement(By fromLocator, By toLocator) throws InterruptedException {
		List<WebElement> from_Location = DriverFactory.getInstance().getMobileDriver().findElements(fromLocator);
		List<WebElement> to_Location = DriverFactory.getInstance().getMobileDriver().findElements(toLocator);
		int startX = from_Location.get(0).getLocation().getX() + (from_Location.get(0).getSize().getWidth() / 2);
		int startY = from_Location.get(0).getLocation().getY() + (from_Location.get(0).getSize().getHeight() / 2);
		int endX = to_Location.get(0).getLocation().getX() + (to_Location.get(0).getSize().getWidth() / 2);
		int endY = to_Location.get(0).getLocation().getY() + (to_Location.get(0).getSize().getHeight() / 2);
		new TouchAction(DriverFactory.getInstance().getMobileDriver()).press(point(startX, startY))
				.waitAction(waitOptions(ofMillis(1000))).moveTo(point(endX, endY)).release().perform();

	}

	/**
	 * =============================================================================
	 * Method: rightToLeftSwipeUsingElement | Author: Rajesh Buddha | Date:16 Jan
	 * 2020 | Description: This method right To Left Swipe Using Element |
	 * Parameters: locator, text | Return: none
	 * =============================================================================
	 * 
	 * @param fromLocator
	 * @param toLocator
	 * 
	 * @throws InterruptedException
	 */
	public void rightToLeftSwipeUsingWebElement(WebElement fromLocator, WebElement toLocator)
			throws InterruptedException {
		System.out.println(" swip element");
		System.out.println("fromLocator" + fromLocator);
		System.out.println("to locator" + toLocator);

		WebElement from_Location = fromLocator;
		WebElement to_Location = toLocator;
		int startX = from_Location.getLocation().getX() + (from_Location.getSize().getWidth() / 2);
		int startY = from_Location.getLocation().getY() + (from_Location.getSize().getHeight() / 2);
		int endX = to_Location.getLocation().getX() + (to_Location.getSize().getWidth() / 2);
		int endY = to_Location.getLocation().getY() + (to_Location.getSize().getHeight() / 2);
		new TouchAction(DriverFactory.getInstance().getMobileDriver()).press(point(startX, startY))
				.waitAction(waitOptions(ofMillis(1000))).moveTo(point(endX, endY)).release().perform();

	}

	/**
	 * =============================================================================
	 * Method: getText | Author: Rajesh Buddha | Date:16 Jan 2020 | Description:
	 * This method get the text of element | Parameters: locator | Return: elmText
	 * =============================================================================
	 */
	public int getTextByInt(By locator) {
		WebElement elm = waitForVisible(locator);
		String elmText = elm.getText();
		int elmIntTxt = Integer.parseInt(elmText);
		ReportManager.logInfo("Successfully get Integer text - <b style=\"color:green;\">" + elmIntTxt + "</b>");
		System.out.println("Successfully get Integer text- " + elmIntTxt);
		return elmIntTxt;
	}

	/**
	 * =============================================================================
	 * Method: clickUsingCoordinates | Author: Rajesh Buddha | Date:16 Jan 2020 |
	 * Description: This method right To Left Swipe Using Element | Parameters:
	 * locator, text | Return: none
	 * =============================================================================
	 * 
	 * @param xcordinate
	 * @param ycordinate
	 * 
	 * @param fromLocator
	 * @param toLocator
	 * 
	 * @throws InterruptedException
	 */
	public void clickUsingCoordinates(int xcordinate, int ycordinate) throws InterruptedException {
		new TouchAction(DriverFactory.getInstance().getMobileDriver()).tap(PointOption.point(xcordinate, ycordinate))
				.release().perform();
	}

	/**
	 * =============================================================================
	 * Method: getElementSizeUsingFindElements | Author: Rajesh Buddha | Date:16 Jan
	 * 2020 | Description: This method returns size of elements by using
	 * findelements | Parameters: locator, text | Return: none
	 * =============================================================================
	 * 
	 * @param size
	 * @param locator
	 * @param xcordinate
	 * @param ycordinate
	 * 
	 * @param fromLocator
	 * @param toLocator
	 * 
	 * @throws InterruptedException
	 */
	public List<WebElement> getElementSizeUsingFindElements(By locator) throws InterruptedException {
		List<WebElement> lst_Elm = DriverFactory.getInstance().getMobileDriver().findElements(locator);
		ReportManager.logInfo("Successfully captured elemnt size is - " + lst_Elm.size());
		return lst_Elm;
	}
	
	/**
	 * =============================================================================
	 * Method: isAlertPresent | Author: Rajesh Buddha | Date:16 Jan
	 * 2020 | Description: This method returns true or false based on the alert 
	 * exists | Parameters: locator, text | Return: none
	 * =============================================================================
	 * 
	 * @param size
	 * @param locator
	 * 
	 * @throws InterruptedException
	 */
	public static boolean isAlertPresent(WebDriver driver)
	{
		try
		{
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException ex) {
			return false;
		}
	}

}
