#!/usr/bin/python
# -*- coding: UTF-8 -*-

import xml.etree.ElementTree as ET
import os
import sys
import time


list_tests = []
list_failures = []
list_error = []
s = ['<testsuites', '<property', 'type="FAILED"', 'type="PASSED"', '</properties>\n', '</testsuite>', '<testsuite',
     '<properties>',  '</testsuites>','?xml']

def file_list(dirname, ext='.csv'):
    """获取目录下所有特定后缀的文件
    @param dirname: str 目录的完整路径
    @param ext: str 后缀名, 以点号开头
    @return: list(str) 所有子文件名(不包含路径)组成的列表
    """
    return list(filter(
        lambda filename: os.path.splitext(filename)[1] == ext,
        os.listdir(dirname)))

def get_data(path):
    list = file_list(path,'.xml')
    for f in list:
        try:
            tree = ET.parse(path+'\\'+f)
            root = tree.getroot()
        except Exception as e:  # 捕获除与程序退出sys.exit()相关之外的所有异常
            print("parse test.xml fail!")
            sys.exit()
        list_tests.append(root.attrib['tests'])
        list_failures.append(root.attrib['failures'])
        list_error.append(root.attrib['errors'])

def get_file_body(path):
    list = file_list(path, '.xml')
    flag = 0
    tmpf = path+'\\First.xml'  #首个文件
    tmp = path+'\\tmpTotal.xml'
    tmpe = path + '\\End.xml'
    total =  path + '\\Junit_Report.xml'
    i = len(list)
    print('xml文件个数=',i)
    if i > 2:
        flag = 10   #标志位只有1个xml文件
        list_middle = list[1:-1]
        with open(path+'\\'+ list[0], encoding='UTF-8') as f:
            lines = f.readlines()
            curr = lines[:-2]
        f = open(tmpf, 'w', encoding='UTF-8')
        f.writelines(curr)
        f.close()
        time.sleep(3)
        # print(path+'\\'+ list[-1])
        with open(path+'\\'+ list[-1], encoding='UTF-8') as g:
            lines = g.readlines()
            curr = lines[19:]
        g = open(tmpe, 'w', encoding='UTF-8')
        g.writelines(curr)
        g.close()

    elif i == 2:
        list_2file= list
    elif i == 1:
        list_1file = list
    else:
        print('没有xml文件')
        return

    #截取中间文件
    if flag == 10:
        for f in list_middle:
            with open(path + '\\' + f, encoding='UTF-8') as file_write_obj:
                lines = file_write_obj.readlines()
                curr = lines[19:-2]
            file_write_obj = open(tmp, 'w+', encoding='UTF-8')
            file_write_obj.writelines(curr)
            file_write_obj.close()

    #三个文件连接
    if i>2:
        filenames = ['First.xml', 'tmpTotal.xml','End.xml']

        ff = open(total, 'w', encoding='UTF-8')
        for f in filenames:
            with open(path + '\\' + f, encoding='UTF-8') as hh:
                lines = hh.readlines()
                ff.writelines(lines)
        ff.close()
    elif i ==2:
        print('2')
    else:
        print('1')


def add_data(path,t,f,errors):
    try:
        tree = ET.parse(path + '\\Junit_Report.xml')
        root = tree.getroot()
    except Exception as e:  # 捕获除与程序退出sys.exit()相关之外的所有异常
        print("parse test.xml fail!")
        sys.exit()

    root.attrib['tests'] = str(t)
    root.attrib['failures'] = str(f)
    root.attrib['errors'] = str(errors)
    for document in root:
        document.set('tests',str(t))
        document.set('failures', str(f))
        document.set('errors', str(errors))
    tree.write(path + '\\Junit_Report.xml', encoding='utf-8', xml_declaration=True)



if __name__ == "__main__":
    path = u'C:\\Jenkins\\workspace\\Automation_testing\\Agent_testing\\Reports\\xmlreport'
    flist = ['First.xml','tmpTotal.xml','End.xml','Junit_Report.xml']
    for f in flist:
        if os.path.exists(path+'\\'+f):
            os.remove(path+'\\'+f)
    get_data(path)
    tests,failures,errors = 0,0,0
    for i in range(len(list_tests)):
        tests = tests + int(list_tests[i])
    for j in range(len(list_failures)):
        failures = failures + int(list_failures[j])
    for j in range(len(list_error)):
        errors = errors + int(list_error[j])
    print(tests,failures,errors)
    #
    get_file_body(path)
    add_data(path,tests,failures,errors)
    flist = ['First.xml', 'tmpTotal.xml', 'End.xml']
    for f in flist:
        if os.path.exists(path + '\\' + f):
            os.remove(path + '\\' + f)

