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

//循环添加多个标准
for(int i = 1 ; i<= Count ;i++)
{
	//打开添加标准弹窗
	WebUI.click(findTestObject('Object Repository/Page_StandardManagement/Page_ReferenceStandardManagement/btn_AddStandard'))
	
	//输入标准名称
	WebUI.setText(findTestObject('Object Repository/Page_StandardManagement/Page_ReferenceStandardManagement/Page_AddStandard/input_Name'), StandardName + i)
	
	//输入标准描述
	WebUI.setText(findTestObject('Object Repository/Page_StandardManagement/Page_ReferenceStandardManagement/Page_AddStandard/textarea_Description'), Description)
	
	//上传PDF文件
	for(int j = 1 ; j<= UploadNum ;j++)
	{	
		WebUI.uploadFile(findTestObject('Object Repository/Page_StandardManagement/Page_ReferenceStandardManagement/Page_AddStandard/input_UploadFiles'), 'E:\\Jenkins\\workspace\\smartbi_web_ui\\Data Files\\Markdown样例.pdf')
	}
	
	//点击确定
	WebUI.click(findTestObject('Object Repository/Page_StandardManagement/Page_ReferenceStandardManagement/Page_AddStandard/btn_OK'))
	
	//延时1秒
	WebUI.delay(1)
	
	//验证标准创建成功
	WebUI.verifyTextPresent('Test' + i, false)
}
