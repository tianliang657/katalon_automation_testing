import shutil
import os


def AddReportsFolder(path):
    if not os.path.exists(path):
        os.mkdir(path)
        print('Created the Agent Reports folder!')


def CopyReport(path, out):
    shutil.copytree(path, out)
    print('Copied the Agent Reports folder!')


def DelFolder(topath):
    shutil.rmtree(topath)
    print('Deleted the Agent Reports folder!')


if __name__ == "__main__":
    fromPath = r'C:\Jenkins\workspace\Automation_testing\Information_resources\Katalon_auto\Reports'
    toPath = r'C:\Jenkins\workspace\Automation_testing\Agent_testing\GeneratingHTMLReport\Reports'
    AddReportsFolder(toPath)
    DelFolder(toPath)
    CopyReport(fromPath, toPath)
