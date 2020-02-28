import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.navigateToUrl('http://172.17.207.163:81/workflow-v2/add-workflow')

CustomKeywords.'com.riil.optimage.DragElement.dragWorkflowElement'(findTestObject('Object Repository/Page_WorkflowManagement/Page_WorkflowConfiguration/icon_Start'),findTestObject('Object Repository/Page_WorkflowManagement/Page_WorkflowConfiguration/Canvas'))

CustomKeywords.'com.riil.optimage.DragElement.dragWorkflowElement'(findTestObject('Object Repository/Page_WorkflowManagement/Page_WorkflowConfiguration/icon_Extract'),findTestObject('Object Repository/Page_WorkflowManagement/Page_WorkflowConfiguration/Canvas'))

CustomKeywords.'com.riil.optimage.DragElement.dragWorkflowElement'(findTestObject('Object Repository/Page_WorkflowManagement/Page_WorkflowConfiguration/icon_Stop'),findTestObject('Object Repository/Page_WorkflowManagement/Page_WorkflowConfiguration/Canvas'))

CustomKeywords.'com.riil.optimage.DragElement.establishRelationship'('Start','Extract')

CustomKeywords.'com.riil.optimage.DragElement.establishRelationship'('Extract','Stop')

CustomKeywords.'com.riil.optimage.DragElement.establishRelationship'('Start','Stop')
