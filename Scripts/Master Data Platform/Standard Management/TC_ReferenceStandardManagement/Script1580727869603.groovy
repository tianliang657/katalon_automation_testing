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

/**
 * 参考标准管理
 *
 */

//打开参考标准
WebUI.navigateToUrl('http://172.17.207.171:81/standard/reference-standard')

////TC:验证添加超过20个标准，列表翻页不出现

//添加多个标准（Count：重复次数，UploadNum：上传附件数量，StandardName：标准名称，Description：描述内容）
WebUI.callTestCase(findTestCase('Common Test Cases/TC_AddSomeStandard'), [('Count') : 21, ('UploadNum') : 1, ('StandardName') : 'Test'
	, ('Description') : '这是一个参考标准'], FailureHandling.STOP_ON_FAILURE)

//鼠标悬浮到第一个标准
CustomKeywords.'com.riil.pageElement.MouseOver.mouseOver'('td', 'Test1')

//验证第一个标准显示
WebUI.verifyTextPresent('Test1', false)



////TC:验证标准文件中展开/收起功能

//添加一个标准且上传三个PDF附件
WebUI.callTestCase(findTestCase('Common Test Cases/TC_AddSomeStandard'), [('Count') : 1, ('UploadNum') : 3, ('StandardName') : '标准测试'
	, ('Description') : '测试展开收起功能'], FailureHandling.STOP_ON_FAILURE)

//鼠标悬浮到最新创建的标准
CustomKeywords.'com.riil.pageElement.MouseOver.mouseOver'('td', '标准测试')

//点击“展开”链接
WebUI.click(findTestObject('Object Repository/Page_StandardManagement/Page_ReferenceStandardManagement/txt_Expand'))

//验证“收起”链接出现
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_StandardManagement/Page_ReferenceStandardManagement/txt_Collapse'), 1)

//点击“收起”链接
WebUI.click(findTestObject('Object Repository/Page_StandardManagement/Page_ReferenceStandardManagement/txt_Collapse'))

//验证“展开”链接出现
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_StandardManagement/Page_ReferenceStandardManagement/txt_Expand'), 1)



