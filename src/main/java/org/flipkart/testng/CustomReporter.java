package org.flipkart.testng;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import org.flipkart.domain.RestApiTest;
import org.testng.*;
import org.testng.xml.XmlSuite;

public class CustomReporter implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        ExtentReports extent = new ExtentReports();
//        System.out.println(outputDirectory);
        // create HtmlReporter
        System.out.println(outputDirectory);
        ExtentHtmlReporter  htmlReporter = new ExtentHtmlReporter(outputDirectory + "/ExtentReport.html");
        extent.attachReporter(htmlReporter);

        // Loop through all the suites
        for (ISuite suite : suites) {
            // Get all the suite results
            Map<String, ISuiteResult> suiteResults = suite.getResults();

            // Loop through all the suite results
            for (ISuiteResult suiteResult : suiteResults.values()) {
                // Get the test context
                ITestContext testContext = suiteResult.getTestContext();
                List<ITestResult> allResults = new ArrayList<>();
                allResults.addAll(testContext.getPassedTests().getAllResults());
                allResults.addAll(testContext.getFailedTests().getAllResults());
                allResults.addAll(testContext.getSkippedTests().getAllResults());
                for (ITestResult result : allResults) {
                    // Get the DataProvider parameters
                    Object[] params = result.getParameters();
                    RestApiTest restApiTest = (RestApiTest) params[0];
                    String test_name = restApiTest.getTest_case_name();
//                    String test_name = "service : " + restApiTest.getService() + "\n"+ "name : "+ restApiTest.getTest_case_name();
//                    String testNameWithParams = result.getMethod().getMethodName() + "[" + restApiTest.getService() + "]";
                    ExtentTest test = extent.createTest(test_name);
                    test.assignCategory(restApiTest.getService());
//                    test.assignCategory(testContext.getName());
//                    System.out.println(testContext.getName());
                    switch (result.getStatus()) {
                        case ITestResult.SUCCESS:
                            test.pass("Test passed");
                            break;
                        case ITestResult.FAILURE:
                            test.fail(result.getThrowable().getMessage());
                            break;
                        case ITestResult.SKIP:
                            test.skip(result.getThrowable());
                            break;
                        default:
                            test.skip("Test skipped");
                            break;
                    }
                    for (String log : Reporter.getOutput(result)) {
                        test.info(log);
                    }
                }
            }
        }
        extent.flush();
    }
}

