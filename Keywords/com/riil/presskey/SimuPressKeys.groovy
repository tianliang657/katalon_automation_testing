package com.riil.presskey
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.*;


class devFuction {

	@Keyword
	//在任意位置点击右键
	def	moniRightClickMouse(){
		Robot robot = new Robot();
		robot.mousePress(InputEvent.BUTTON3_MASK);//按下鼠标左键
		Thread.sleep(50);
		//模拟鼠标松开左键
		robot.mouseRelease(InputEvent.BUTTON3_MASK);//释放鼠标左键

	}
	
	//在任意位置点击左键
	@Keyword
	def	moniLeftClickMouse(){
		Robot robot = new Robot();
		robot.mousePress(InputEvent.BUTTON1_MASK);//按下鼠标左键
		Thread.sleep(50);
		//模拟鼠标松开左键
		robot.mouseRelease(InputEvent.BUTTON1_MASK);//释放鼠标左键

	}
	
	//模拟Ctrl+C
	@Keyword
	def	moniCtrlKey(String key){
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(key);
		robot.keyRelease(key);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	@Keyword
	//在任意位置按回车键
	def	pressEnter(){
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(50);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	//指定位置按鼠标左键
	@Keyword
	def	mouseMoveAndPressLeftKey(int x,int y){
		Robot robot = new Robot();
		robot.mouseMove(x,y)
		Thread.sleep(50);
		robot.mousePress(InputEvent.BUTTON1_MASK);//按下鼠标左键
		Thread.sleep(500);
		//模拟鼠标松开左键
		robot.mouseRelease(InputEvent.BUTTON1_MASK);//释放鼠标左键
		Thread.sleep(50);
		robot.mousePress(InputEvent.BUTTON1_MASK);//按下鼠标左键
		Thread.sleep(500);
		//模拟鼠标松开左键
		robot.mouseRelease(InputEvent.BUTTON1_MASK);//释放鼠标左键
	}
}