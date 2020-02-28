package com.riil.count

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
import internal.GlobalVariable
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

class ClickPage {
	@Keyword
	//点击页数(element:元素对象,返回值i：总共页数)
	public static int clickpage(TestObject element) {

		int i=1;

		while(WebUI.verifyElementVisible(element,FailureHandling.OPTIONAL))
		{
			WebUI.click(element);

			i++;
		}
		return i;
	}
	@Keyword
	//页面包含的数量(返回值j：包含数量)
	public int pagecontainnumber() {

		int j=1;

		String path="//"+"div[@class=\"alarm-body\"]/div[1]"

		WebDriver myDriver = DriverFactory.getWebDriver()

		try {
			while(myDriver.findElement(By.xpath(path)))
			{

				j++;

				path="//"+"div[@class=\"alarm-body\"]/div["+j+"]"

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		j=j-1;

		return j;
	}
}


