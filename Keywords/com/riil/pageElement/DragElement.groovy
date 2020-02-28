package com.riil.pageElement

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import internal.GlobalVariable
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import java.awt.Robot;
import java.awt.event.InputEvent;

public class DragElement {
	@Keyword
	//拖动页面元素
	public static void dragElement(TestObject sourceObject,TestObject targetObject) {
		WebDriver myDriver = DriverFactory.getWebDriver();
		WebElement source_element = WebUI.findWebElement(sourceObject);
		WebElement target_element = WebUI.findWebElement(targetObject);
		println("test locale:"+ source_element.getLocation() );
		println("test locale:"+ target_element.getLocation() );
		Robot robot = new Robot();
		robot.mouseMove(target_element.getLocation().getX()+200, target_element.getLocation().getY()+200)
		Thread.sleep(2000);
		Actions builder = new Actions(myDriver);
		builder.moveToElement(source_element).clickAndHold(source_element);
		Thread.sleep(2000);
		builder.moveToElement(target_element).release().perform();
	}
}
