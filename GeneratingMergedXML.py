import os
import shutil
import sys
import time
from xml.dom import minidom


# 拷贝并分类.html/.xml/.log文件
def copyFiles(srcPath, format):
    num = 0
    folders_name = []
    files_name = []
    dirs_count = 0
    time_count = 0
    os.makedirs(dstPathHTML)
    print('Created the htmlreport folder!')
    os.makedirs(dstPathXML)
    print('Created the xmlreport folder!')
    os.makedirs(dstPathLOG)
    print('Created the log folder!')
    while dirs_count < int(sys.argv[1]):
        for dirs_name in os.listdir(srcPath):
            if dirs_name[0:2] == '20':
                if dirs_name not in folders_name:
                    folders_name.append(dirs_name)
                for root, _, files in os.walk(os.path.join(srcPath, dirs_name)):
                    for filename in files:
                        if os.path.splitext(filename)[1] == format[1]:
                            xmlfile = os.path.join(root, filename)
                            if xmlfile not in files_name:
                                files_name.append(xmlfile)
                                dirs_count += 1
                            if dirs_count == int(sys.argv[1]):
                                break
        
        # 没两分钟记一次，等待三小时！                    
        if time_count > 90:
            print('已等待三小时，强制收集报告信息！')
            break
        else:
            time_count += 1
            time.sleep(120)
            
        print(dirs_count)
        
    print(folders_name)
    
    print(files_name)

    while num < len(folders_name):
        for root, _, files in os.walk(os.path.join(srcPath, folders_name[num])):
            for filename in files:
                if os.path.splitext(filename)[1] == format[2]:
                    num += 1
                    filepath = os.path.join(root, filename)
                    print(filepath)
                    # if int(sys.argv[1]) == 1:
                    #     shutil.copy(filepath, os.path.join(dstPathLOG, "execution0.log"))
                    #     print('Copied execution.log successfully！')
                    # else:
                    #     shutil.copy(filepath, os.path.join(dstPathLOG, "execution" + str(num) + ".log"))
                    #     print('Copied execution' + str(num) + '.log successfully！')
                    shutil.copy(filepath, os.path.join(dstPathLOG, "execution" + str(num) + ".log"))
                    print('Copied execution' + str(num) + '.log successfully！')

                if os.path.splitext(filename)[1] == format[1]:
                    filepath = os.path.join(root, filename)
                    print(filepath)
                    # if int(sys.argv[1]) == 1:
                    #     shutil.copy(filepath, os.path.join(srcPath, "JUnit_Report.xml"))
                    #     print('Copied JUnit_Report.xml successfully！')
                    # else:
                    #     shutil.copy(filepath, os.path.join(dstPathXML, "JUnit_Report" + str(num) + ".xml"))
                    #     print('Copied JUnit_Report' + str(num) + '.xml successfully！')
                    shutil.copy(filepath, os.path.join(dstPathXML, "JUnit_Report" + str(num) + ".xml"))
                    print('Copied JUnit_Report' + str(num) + '.xml successfully！')

                if os.path.splitext(filename)[1] == format[0]:
                    filepath = os.path.join(root, filename)
                    print(filepath)
                    # if int(sys.argv[1]) == 1:
                    #     shutil.copy(filepath, os.path.join(srcPath, "TestReport.html"))
                    #     print('Copied TestReport.html successfully！')
                    # else:
                    #     shutil.copy(filepath, os.path.join(dstPathHTML, "TestReport" + str(num) + ".html"))
                    #     print('Copied TestReport' + str(num) + '.html successfully！')
                    shutil.copy(filepath, os.path.join(dstPathHTML, "TestReport" + str(num) + ".html"))
                    print('Copied TestReport' + str(num) + '.html successfully！')


# 合并多个XML文件
def mergeXMLFiles(logspath, outputfile):
    overwrite = []
    inputfiles = []
    for log in os.listdir(logspath):
        if log[0:9] == 'execution':
            if log not in inputfiles:
                inputfiles.append(os.path.join(logspath, log))
    for files in inputfiles:
        doc = minidom.parse(files)
        overwrite.append(doc.documentElement)
    impl = minidom.getDOMImplementation()
    dom = impl.createDocument(None, 'rill_merge_data', None)
    dom.actualEncoding = 'utf-8'
    root = dom.documentElement

    for text in overwrite:
        root.appendChild(text)
    if outputfile is None:
        return dom
    else:
        f = open(outputfile, 'wb')
        f.write(doc.toprettyxml(indent="", newl="", encoding="utf-8") + root.toprettyxml(indent="", newl="",
                                                                                         encoding="utf-8"))
        print("XML document merge completed!")
        f.close()
        return dom


# 过滤XML内容
def filterXML(outputfile):
    Dom = minidom.parse(outputfile)
    RootNode = Dom.documentElement
    count = 0
    for record in RootNode.getElementsByTagName("record"):
        TestSuiteNode = record.getElementsByTagName("method")
        count += 1
        if TestSuiteNode[0].childNodes[0].data == "startSuite" or TestSuiteNode[0].childNodes[0].data == "endSuite":
            cus_node = Dom.createElement('Qingbo')
            record.appendChild(cus_node)
            node_text = Dom.createTextNode("666")
            cus_node.appendChild(node_text)

            if count == 1 or count == len(RootNode.getElementsByTagName("record")):
                continue
            else:
                Qingbo_node = record.getElementsByTagName("Qingbo")
                Qingbo_node[0].childNodes[0].data = "888"

    print('Added some Qingbo elements！')

    for allRecords in RootNode.getElementsByTagName("log"):
        for record in allRecords.getElementsByTagName("record"):
            node = record.getElementsByTagName("Qingbo")
            if len(node) == 0:
                continue
            elif int(node[0].childNodes[0].data) == 888:
                allRecords.removeChild(record)

    print('Deleted some redundant elements！')

    # 更新报告名称
    for record in RootNode.getElementsByTagName("record"):
        TestSuiteNode = record.getElementsByTagName("method")
        reportname = record.getElementsByTagName("property")
        if TestSuiteNode[0].childNodes[0].data == "startSuite":
            if int(sys.argv[1]) == 1:
                reportname[0].childNodes[0].data = "Automation testing report"
            else:
                reportname[0].childNodes[0].data = "Agent automation testing report"
    print('Updated the test report name! ')

    with open(outputfile, 'w', encoding="UTF-8") as fh:
        Dom.writexml(fh, indent='', addindent="", newl='', encoding="UTF-8")


if __name__ == "__main__":
    #srcPath = r'/Users/qingbo/git/riil_katalon_automation_testing/Reports'
    #dstPathHTML = r'/Users/qingbo/git/riil_katalon_automation_testing/Reports/htmlreport'
    #dstPathXML = r'/Users/qingbo/git/riil_katalon_automation_testing/Reports/xmlreport'
    #dstPathLOG = r'/Users/qingbo/git/riil_katalon_automation_testing/Reports/log'
    #outputFile = '/Users/qingbo/git/riil_katalon_automation_testing/Reports/execution0.log'
    srcPath = r'C:\\Jenkins\\workspace\\Automation_testing\\Information_resources\\Katalon_auto\\Reports'
    dstPathHTML = r'C:\\Jenkins\\workspace\\Automation_testing\\Information_resources\\Katalon_auto\\Reports\\htmlreport'
    dstPathXML = r'C:\\Jenkins\\workspace\\Automation_testing\\Information_resources\\Katalon_auto\\Reports\\xmlreport'
    dstPathLOG = r'C:\\Jenkins\\workspace\\Automation_testing\\Information_resources\\Katalon_auto\\Reports\\log'
    outputFile = r'C:\\Jenkins\\workspace\\Automation_testing\\Information_resources\\Katalon_auto\\Reports\\execution0.log'
    copyFiles(srcPath, ['.html', '.xml', '.log'])
    mergeXMLFiles(dstPathLOG, outputFile)
    filterXML(outputFile)
