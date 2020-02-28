package com.riil.optimage

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
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.awt.event.KeyEvent;

public class DragElement {
	@Keyword
	//拖动工作流图标
	public static void dragWorkflowElement(TestObject sourceObject,TestObject targetObject) {
		//拖动结点到Canvas
		WebDriver myDriver = DriverFactory.getWebDriver();
		WebElement source_element = WebUI.findWebElement(sourceObject);
		WebElement target_element = WebUI.findWebElement(targetObject);
		println("source locale: "+ source_element.getLocation());
		println("target locale: "+ target_element.getLocation());
		Actions builder = new Actions(myDriver);
		builder.moveToElement(source_element).clickAndHold(source_element).moveByOffset(200, 10).release().perform();
		builder.click(target_element).perform();
		
	}

	@Keyword
	//建立关联
	public static void establishRelationship(String from, String to) {
		
		String imgPath = "C:\\Users\\Administrator\\git\\weishao_katalon_automation_testing\\Screenshot\\Page_MasterDataPlatform\\" ;
		ResultBean fullSource;
		ResultBean fullTarget;
		ResultBean source;
		ResultBean target;
		Robot robot = new Robot();
		SearchPixelPosition searchPixelPosition = new SearchPixelPosition();
		WebDriver myDriver = DriverFactory.getWebDriver();
		Actions builder = new Actions(myDriver);
		Thread.sleep(2000);
		//获取源结点坐标值
		source = searchPixelPosition.getAllRGB(imgPath + from + ".png");
		if (source == null) {
			println("No find source image!");
			return;
		}else{
			robot.mouseMove((int)(source.x + source.width/2), (int)(source.y + source.height/2));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			Thread.sleep(2000);
			fullSource = searchPixelPosition.getAllRGB(imgPath + from + "01.png");
			if(fullSource == null){
				println("No find full source image!");
				return;
			}else{
				println("full source image: 横坐标：  " + fullSource.x+" 纵坐标： "+fullSource.y+" 宽度： "+fullSource.width+" 高度："+ fullSource.height+"\n");
				//取消Node结点高亮
				robot.mouseMove((int)(fullSource.x - fullSource.width/2), (int)(fullSource.y));
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);

			}
		}
		
		//获取目标结点坐标值
		Thread.sleep(2000);
		target = searchPixelPosition.getAllRGB(imgPath + to + ".png");
		if (target == null) {
			println("No find target image!");
			return;
		}else{
			robot.mouseMove((int)(target.x + target.width/2), (int)(target.y + target.height/2));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			Thread.sleep(2000);
			fullTarget = searchPixelPosition.getAllRGB(imgPath + to + "01.png");
			if(fullTarget == null){
				println("No find full target image!");
				return;
			}else{
				println("full target image: 横坐标：  " + fullTarget.x+" 纵坐标： "+ fullTarget.y + " 宽度： " + fullTarget.width + " 高度："+ fullTarget.height+"\n");
				//取消Node结点高亮
				robot.mouseMove((int)(fullTarget.x - fullTarget.width/2), (int)(fullTarget.y));
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
			}
		}
		
		//目标结点设为高亮
		robot.mouseMove((int)(target.x + target.width/2), (int)(target.y + target.height/2));
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		
		//建立连接  --- 源结点左侧连接目标结点左侧
		Thread.sleep(2000);
		robot.mouseMove((int)(source.x), (int)(source.y));
		println("源结点左侧坐标： " + (int)(fullSource.x + 8)+" "+ (int)(fullSource.y + fullSource.height/2));
		Thread.sleep(1000);
		robot.mouseMove((int)(fullSource.x + 8), (int)(fullSource.y + fullSource.height/2));	
		robot.mousePress(InputEvent.BUTTON1_MASK);
		println("目标结点左侧坐标： " + (int)(fullTarget.x + 8) +" "+ (int)(fullTarget.y + fullTarget.height/2));		
		Thread.sleep(1000);
		robot.mouseMove((int)(fullTarget.x + 8), (int)(fullTarget.y + fullTarget.height/2));
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		Thread.sleep(1000);
		
		//取消目标结点高亮
		robot.mouseMove((int)(fullTarget.x - fullTarget.width/2), (int)(fullTarget.y));
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		
	}
}
