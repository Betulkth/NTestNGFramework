package com.neotech.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.neotech.testbase.PageInitializer;

public class CommonMethods extends PageInitializer {

	// Common Ones:
//////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * This method clears a text box and send the text parameter to it.
	 * 
	 * @param element
	 * @param text
	 */
	public static void sendText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}

	/**
	 * This method pauses the execution for a certain amount of seconds.
	 * 
	 * @param seconds
	 */
	public static void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a wait object based on the driver and the
	 * EXPLICIT_WAIT_TIME.
	 * 
	 * @return
	 * 
	 */
	public static WebDriverWait getWaitObject() {

		return new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT_TIME));

	}

	/**
	 * This method creates a wait object based on the driver and the given amount of
	 * time.
	 * 
	 * @param secondsToWait
	 * @return
	 * 
	 */
	public static WebDriverWait getWaitObject(int secondsToWait) {

		return new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
	}

	/**
	 * This method will wait for the element to be clickable.
	 * 
	 * @param element
	 * @return
	 */
	public static WebElement waitForClickability(WebElement element) {
		return getWaitObject().until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method will wait for the element to be visible.
	 * 
	 * @param locator
	 * @return
	 */
	public static WebElement waitForVisibility(By locator) {
		return getWaitObject().until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static WebElement waitForVisibility(WebElement element) {
		return getWaitObject().until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This method will wait for clickability and then click on the passes element.
	 * This method not only clicks the web element also makes sure that the element
	 * is clickable
	 * 
	 * @param element
	 * 
	 */
	public static void click(WebElement element) {

		waitForClickability(element);
		element.click();
	}

	/**
	 * This method will take a screenshot and save it with the given fileName. 
	 * 
	 * @param fileName
	 */
	public static String takeScreenshot(String fileName) {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File screenShot = ts.getScreenshotAs(OutputType.FILE);

		String destination = Constants.SCREENSHOT_FILEPATH + fileName + getTimeStamp() + ".png";
		
		try {

			FileUtils.copyFile(screenShot, new File(destination));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to save the screenshot!!!");
		}
		
		return destination;
	}
	
	/**
	 * This method returns a timestamp as a String.
	 * 
	 * @return
	 */
	public static String getTimeStamp()
	{
		Date date = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		
		return sdf.format(date);
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////
//JavascriptExecutor:

	/**
	 * This method will cast the driver to a JavascriptExecutor object and return it
	 * 
	 * @return
	 */
	public static JavascriptExecutor getJSObject() {
		return (JavascriptExecutor) driver;
	}

	/**
	 * This method will click on element using JavascriptExecutor
	 * 
	 * @param element
	 */
	public static void jsClick(WebElement element) {
		getJSObject().executeScript("arguments[0].click()", element);
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////
//CheckBoxes and Dropdowns

	/**
	 * This method checks if the radio/checkbox is enabled, and then clicks on the
	 * element that have the attribute value as selectValue.
	 * 
	 * @param elementList
	 * @param selectValue
	 */
	public static void clickRadioOrCheckbox(List<WebElement> elementList, String selectValue) {
		for (WebElement el : elementList) {
			String elementValue = el.getAttribute("value").trim();

			if (elementValue.equals(selectValue) && el.isEnabled()) {
				el.click();
				break;
			}
		}
	}

	/**
	 * This method selects a drop down element based on visible text.
	 * 
	 * @param element
	 * @param visibleText
	 */
	public static void selectDropdown(WebElement element, String visibleText) {
		try {

			Select sl = new Select(element);
			sl.selectByVisibleText(visibleText);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method selects a drop down element based on index.
	 * 
	 * @param element
	 * @param index
	 */
	public static void selectDropdown(WebElement element, int index) {
		try {
			Select sl = new Select(element);
			sl.selectByIndex(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will select a date on a calendar whose elements are provided as
	 * the first parameter and select the date that is the second parameter
	 * 
	 * @param elements
	 * @param date
	 */
	public static void selectCalendarDate(List<WebElement> elements, String date) {

		for (WebElement day : elements) {

			if (day.getText().equals(date)) {

				if (day.isEnabled()) {

					click(day);
					break;

				} else {
					System.out.println("This day is not enabled!!!");
					break;
				}
			}
		}
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////
//Alerts:
	/**
	 * This method switches the focus of the driver to an Alert and accepts it if
	 * found. If not found, the NoAlertPresentException is handled.
	 * 
	 */
	public static void acceptAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (NoAlertPresentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method switches the focus of the driver to an Alert and dismisses it if
	 * found. If not found, the NoAlertPresentException is handled.
	 * 
	 */
	public static void dismissAlert() {

		try {

			Alert alert = driver.switchTo().alert();
			alert.dismiss();

		} catch (NoAlertPresentException e) {

			e.printStackTrace();

		}
	}

	/**
	 * This method will switch the focus of the driver to an alert and return its
	 * text. If alert it not found, the NoAlertPresentException is handled.
	 * 
	 * @return
	 * 
	 */
	public static String getAlertText() {

		String alertText = null;
		try {

			Alert alert = driver.switchTo().alert();
			alertText = alert.getText();

		} catch (NoAlertPresentException e) {

			e.printStackTrace();
		}

		return alertText;

	}

	/**
	 * This method will switch the focus of the driver to an alert and send text to
	 * it. If alert is not found, the NoAlertPresentException is handled.
	 * 
	 * @param text
	 * 
	 */
	public static void sendAlertText(String text) {

		try {

			Alert alert = driver.switchTo().alert();
			alert.sendKeys(text);

		} catch (NoAlertPresentException e) {

			e.printStackTrace();
		}
	}

	/**
	 * This method switches the focus of the driver to the child window.
	 * 
	 */
	public static void switchToChildWindow() {

		String mainWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {

			if (!mainWindow.equals(handle)) {

				driver.switchTo().window(handle);
			}
		}
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////
//Frames:

	/**
	 * This method switches to a frame using its name or id.
	 * 
	 * @param nameOrId
	 * 
	 */
	public static void switchToFrame(String nameOrId) {

		try {
			driver.switchTo().frame(nameOrId);

		} catch (NoSuchFrameException e) {

			e.printStackTrace();
		}

	}

	/**
	 * This method switches to a frame using its index.
	 * 
	 * @param index
	 * 
	 */
	public static void switchToFrame(int index) {

		try {

			driver.switchTo().frame(index);
		} catch (NoSuchFrameException e) {

			e.printStackTrace();
		}
	}

	/**
	 * This method switches to a frame using a WebElement.
	 * 
	 * @param element
	 * 
	 */
	public static void switchToFrame(WebElement element) {

		try {
			driver.switchTo().frame(element);

		} catch (NoSuchFrameException e) {

			e.printStackTrace();

		}
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////
//Keyboard Commends:

	/**
	 * This method hovers the mouse to the element
	 * 
	 * @param element
	 */
	public static void hoverTheMouse(WebElement element) {

		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	/**
	 * This method for right click
	 * 
	 */
	public static void rightClick() {

		Actions action = new Actions(driver);
		action.contextClick().perform();

	}

	/**
	 * This method for double click
	 * 
	 * @param element
	 */
	public static void doubleClick(WebElement element) {

		Actions action = new Actions(driver);
		action.moveToElement(element).doubleClick().perform();

	}

	/**
	 * This method drag the element to drop area
	 * 
	 * @param drag
	 * @param drop
	 */
	public static void dragAndDrop(WebElement drag, WebElement drop) {

		Actions action = new Actions(driver);
		action.clickAndHold(drag).moveToElement(drop).release().perform();

	}

	/**
	 * This method hovers the mouse to the element and click to it
	 * 
	 * @param element
	 */
	public static void hoverAndClick(WebElement element) {

		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();
	}

	/**
	 * This method scrolls the page until a specific element is in view
	 * 
	 * @param element
	 */
	public static void scrollToElement(WebElement element) {
		getJSObject().executeScript("arguments[0].scrollIntoView(true)", element);
	}

	/**
	 * This method scrolls the page down using the pixels parameter
	 * 
	 * @param pixels
	 */
	public static void scrollDown(int pixels) {
		getJSObject().executeScript("window.scrollBy(0, " + pixels + ")");
	}

	/**
	 * This method scrolls the page up using the pixels parameter
	 * 
	 * @param pixels
	 */
	public static void scrollUp(int pixels) {
		getJSObject().executeScript("window.scrollBy(0, -" + pixels + ")");
	}

}
