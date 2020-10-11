package com.StepReader;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ExcelReader.ReadExcelFile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;

public class StepDefinations {
	HashMap<String, String> repository = new HashMap<String, String>();

	public StepDefinations() {
		ReadExcelFile excel = new ReadExcelFile();
		repository = excel.getXpathMap("src/main/resources/Repository.xls");
	}

	public boolean launchApp(AppiumDriver<MobileElement> driver) {
		boolean result = false;
		//System.out.println("App Installed "+driver.isAppInstalled("com.coalshastralive.android.app"));
		if (driver.isAppInstalled("com.coalshastralive.android.app")) {
			result = driver.queryAppState("com.coalshastralive.android.app").toString()
					.equalsIgnoreCase("RUNNING_IN_FOREGROUND");
		}

		return result;
	}

	public HashMap<String, Boolean> ValidatePresenceOfElements(AppiumDriver<MobileElement> driver,
			HashMap<Integer, String> map) {
		HashMap<String, Boolean> result = new HashMap<String, Boolean>();
		boolean res;
		WebDriverWait wait = new WebDriverWait(driver,5);
		for (int index = 1; index <= map.size(); index++) {
			res = false;
			try {
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(repository.get(map.get(index)))));
				res = true;

			} catch (Exception ex) {
				System.out.println("Exception >> " + ex.getMessage());
				res = false;
			}
			result.put(map.get(index), res);
		}
		return result;
	}

	public boolean TapOnElement(AppiumDriver<MobileElement> driver, String elementToBeClicked) {
		boolean result = false;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(repository.get(elementToBeClicked))));
			Thread.sleep(250);
			driver.findElement(By.xpath(repository.get(elementToBeClicked))).click();
			result = true;
		} catch (Exception ex) {
			System.out.println("Exception >> " + ex.getMessage());
		}
		return result;
	}

	public boolean enterDataInTextField(AppiumDriver<MobileElement> driver, String textField, String data) {
		boolean result = false;
		WebDriverWait wait = new WebDriverWait(driver, 3);
		try {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(repository.get(textField))));
			Thread.sleep(250);
			driver.findElement(By.xpath(repository.get(textField))).click();
			wait.until(waitForQwertyPadToAppear());
			driver.findElement(By.xpath(repository.get(textField))).clear();
			driver.findElement(By.xpath(repository.get(textField))).sendKeys(data);
			driver.hideKeyboard();
			wait.until(waitForQwertyPadToHide());
			result = true;
		} catch (Exception ex) {
			System.out.println("Exception >> " + ex.getMessage());
		}
		return result;
	}

	ExpectedCondition<Boolean> waitForQwertyPadToAppear() {
		return new ExpectedCondition<Boolean>() {

			@SuppressWarnings("unchecked")
			public Boolean apply(WebDriver driver) {
				boolean result = false;
				result = ((AndroidDriver<MobileElement>) driver).isKeyboardShown();
				return result;
			}
		};
	}

	ExpectedCondition<Boolean> waitForQwertyPadToHide() {
		return new ExpectedCondition<Boolean>() {

			@SuppressWarnings("unchecked")
			public Boolean apply(WebDriver driver) {
				boolean result = false;

				result = !((AndroidDriver<MobileElement>) driver).isKeyboardShown();
				return result;
			}
		};
	}

}
