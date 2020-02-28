package com.riil.optimage

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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
import internal.GlobalVariable
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.awt.event.KeyEvent;



public class OptImage {
	@Keyword
	//在页面里点击某个小的图片
	public static void custClickImage(String target) {
		String scr = null;

		try {
			SearchPixelPosition searchPixelPosition = new SearchPixelPosition();
			ResultBean result = searchPixelPosition.getAllRGB(target);
			if (result == null) {
				System.out.println("no find!");
				return;
			}

			System.out.print(result.x+" "+result.y+" "+result.width+" "+ result.height+"\n");
			Robot robot = null;
			try {
				robot = new Robot();
			} catch (AWTException e) {
			}
			Thread.currentThread().sleep(1000);
			robot.mouseMove((int)(result.x+result.width/2), (int)(result.y+result.height/2));
			Thread.currentThread().sleep(1000);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.setAutoDelay(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Keyword
	//在页面里右键点击某个小的图片
	public static void custRightClickImage(String target) {
		String scr = null;

		try {
			SearchPixelPosition searchPixelPosition = new SearchPixelPosition();
			ResultBean result = searchPixelPosition.getAllRGB(target);
			if (result == null) {
				System.out.println("no find!");
				return;
			}

			System.out.print(result.x+" "+result.y+" "+result.width+" "+ result.height+"\n");
			Robot robot = null;
			try {
				robot = new Robot();
			} catch (AWTException e) {
			}
			Thread.currentThread().sleep(1000);
			robot.mouseMove((int)(result.x+result.width/2), (int)(result.y+result.height/2));
			Thread.currentThread().sleep(1000);
			robot.mousePress(InputEvent.BUTTON3_MASK);
			robot.mouseRelease(InputEvent.BUTTON3_MASK);
			robot.setAutoDelay(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Keyword
	//在页面里双击某个小的图片
	public static void custDoubleClickImage(String target) {
		String scr = null;

		try {
			SearchPixelPosition searchPixelPosition = new SearchPixelPosition();
			ResultBean result = searchPixelPosition.getAllRGB(target);
			if (result == null) {
				System.out.println("no find!");
				return;
			}

			System.out.print(result.x+" "+result.y+" "+result.width+" "+ result.height+"\n");
			Robot robot = null;
			try {
				robot = new Robot();
			} catch (AWTException e) {
			}
			Thread.currentThread().sleep(1000);
			robot.mouseMove((int)(result.x+result.width/2), (int)(result.y+result.height/2));
			Thread.currentThread().sleep(1000);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.setAutoDelay(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Keyword
	//页面里包含某个小图片
	public static boolean custContainImage(String target) {
		String scr = null;

		try {
			SearchPixelPosition searchPixelPosition = new SearchPixelPosition();
			ResultBean result = searchPixelPosition.getAllRGB(target);
			if (result == null) {
				System.out.println("no find!");
				return false;
			}
			else{
				System.out.println("find!");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Keyword
	//页面里拖动趋势图
	public static void custDropAsImageOffset(String target,int x,int y) {
		try {
			SearchPixelPosition searchPixelPosition = new SearchPixelPosition();
			ResultBean result = searchPixelPosition.getAllRGB(target);
			if (result == null) {
				System.out.println("no find!");
				return;
			}

			System.out.print(result.x+" "+result.y+" "+result.width+" "+ result.height+"\n");
			Robot robot = null;
			try {
				robot = new Robot();
			} catch (AWTException e) {
			}
			Thread.currentThread().sleep(1000);
			robot.mouseMove((int)(result.x+result.width/2), (int)(result.y+result.height/2-y));
			Thread.currentThread().sleep(1000);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.setAutoDelay(2000);
			robot.mouseMove((int)(result.x+result.width/2+x), (int)(result.y+result.height/2-y));
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.setAutoDelay(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Keyword
	//在页面里点击某个小的图片
	public static void custClickImageOffset(String target,int o) {
		String scr = null;

		try {
			SearchPixelPosition searchPixelPosition = new SearchPixelPosition();
			ResultBean result = searchPixelPosition.getAllRGB(target);
			if (result == null) {
				System.out.println("no find!");
				return;
			}

			System.out.print(result.x+" "+result.y+" "+result.width+" "+ result.height+"\n");
			Robot robot = null;
			try {
				robot = new Robot();
			} catch (AWTException e) {
			}
			Thread.currentThread().sleep(1000);
			robot.mouseMove((int)(result.x+result.width/2+o), (int)(result.y+result.height/2));
			Thread.currentThread().sleep(1000);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.setAutoDelay(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
