package org.bp.com.uiautomation.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    int count = 0;
    int maxTry = 1;

    @Override
    public boolean retry(ITestResult arg0) {
        if (count < maxTry) {
            count++;
            return true;
        }
        return false;
    }

}
