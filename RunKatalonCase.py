import cv2
import aircv as ac
from pymouse import PyMouse
from pykeyboard import *
import win32gui
import win32api
import win32con
import os
import time
from PIL import ImageGrab

TOTALPATH = 'E:\\Jenkins\\workspace\\smartbi_web_ui\\Screenshot\\run_katalon\\'

k = PyKeyboard()

def isFileExist():
    if not (os.path.exists(TOTALPATH+'ops_img\\FullScreen.png')):
        im = ImageGrab.grab()
        im.save(TOTALPATH+'ops_img\\FullScreen.png')

def img_match(scr,obj):
    time.sleep(2)
    point = []
    imsrc = ac.imread(scr)
    imobj = ac.imread(obj)
    pos = ac.find_template(imsrc, imobj)
    try:
         circle_center_pos = list(pos['result'])
    except:
        return False
    x = int(circle_center_pos[0])
    y = int(circle_center_pos[1])
    point.append(x)
    point.append(y)
    return point

def ops_elem(x,y):
    time.sleep(1)
    m = PyMouse()
    m.move(x, y)
    m.click(x, y)

def ops_handle(id):
    time.sleep(2)
    handle = int(str(id),16)
    left, top, right, bottom = win32gui.GetWindowRect(handle)
    win32api.SetCursorPos([left, top])
    win32api.mouse_event(win32con.MOUSEEVENTF_LEFTUP | win32con.MOUSEEVENTF_LEFTDOWN, 0, 0, 0, 0)
    k.tap_key(k.enter_key)
    k.tap_key(k.enter_key)

def input_str(str):
    time.sleep(2)
    k = PyKeyboard()
    k.type_string(str)

def ops_run_menu():
    #action
    time.sleep(1)
    p = img_match(TOTALPATH+'ops_img\\temp_action.png',TOTALPATH+'ops_img\\action.png')
    ops_elem(p[0],p[1])
    #run
    time.sleep(1)
    p = img_match(TOTALPATH+'ops_img\\temp_run.png',TOTALPATH+'ops_img\\run.png')
    ops_elem(p[0],p[1])
    #chrome
    time.sleep(1)
    p = img_match(TOTALPATH+'ops_img\\temp_chrome.png',TOTALPATH+'ops_img\\chrome.png')
    ops_elem(p[0],p[1])

def active_katalon():
    isFileExist()  
    #ops_handle('00091840')
    k.press_key(k.windows_l_key)
    k.tap_key('d')
    k.release_key(k.windows_l_key)
    hwnd = win32gui.FindWindow(u'SWT_Window0',None)
    win32gui.SetForegroundWindow(hwnd)
    im = ImageGrab.grab()
    im.save(TOTALPATH+'ops_img\\FullScreen.png')
    p = img_match(TOTALPATH+'ops_img\\FullScreen.png',TOTALPATH+'ops_img\\katalon.png')
    time.sleep(2)
    ops_elem(p[0],p[1])
def set_case(case):
    ops_handle('006F0370')
    ops_handle('00070B4C')
    input_str(case)
    ops_handle('006F0370')
    time.sleep(3)
    im = ImageGrab.grab()
    im.save(TOTALPATH+'ops_img\\temp_suite.png')
    p = img_match(TOTALPATH+'ops_img\\temp_suite.png',TOTALPATH+'suite_img\\'+case+'.png')
    if p == False:
        p = img_match(TOTALPATH + 'ops_img\\temp_suite.png', TOTALPATH + 'suite_img\\' + case + '1.png')
    ops_elem(p[0],p[1])
    k.tap_key(k.enter_key)
def run_ts_store():
    time.sleep(1)
    p = img_match(TOTALPATH+'ops_img\\temp_Execute.png',TOTALPATH+'ops_img\\Execute.png')
    ops_elem(p[0],p[1])


#运行操作
if __name__ == "__main__":
    #激活katalon
    active_katalon()
    #设定执行用例
    set_case(sys.argv[1])
    #执行运行动作
    # if (sys.argv[1] == 'TS_All'):
    #     run_ts_store()
    # else:
    ops_run_menu()

