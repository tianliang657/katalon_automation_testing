package com.riil.katalonreport

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
import com.kms.katalon.core.reporting.ReportUtil
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.logging.model.TestSuiteLogRecord
import internal.GlobalVariable

public class KatalonReport {

	@Keyword
	public void exportKatalonReports() {
		String reportFolder = "/Users/qingbo/git/riil_katalon_automation_testing/Reports";
		String logFiles = "/Users/qingbo/git/riil_katalon_automation_testing/Reports";
		File reportFolderFile = new File(reportFolder);
		TestSuiteLogRecord suiteLogEntity = ReportUtil.generate(logFiles);
		KeywordUtil.logInfo("Start generating HTML report folder at: " + reportFolder + "...");
		ReportUtil.writeHtmlReport(suiteLogEntity, reportFolderFile);
		KeywordUtil.logInfo("HTML report generated");
		KeywordUtil.logInfo("Start generating JUnit report folder at: " + reportFolder + "...");
		ReportUtil.writeJUnitReport(suiteLogEntity, reportFolderFile);
		KeywordUtil.logInfo("JUnit report generated");
	}
}
