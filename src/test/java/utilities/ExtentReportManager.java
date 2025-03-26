package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentReportManager implements ITestListener { // ✅ IMPLEMENT ITestListener

    public ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;
    
    String repName;

    @Override  // ✅ Required by ITestListener
    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // Timestamp
        repName = "Test-Report-" + timeStamp + ".html";

        // Ensure the reports folder exists before writing the report
        File reportsDir = new File(System.getProperty("user.dir") + "\\reports\\");
        if (!reportsDir.exists()) {
            reportsDir.mkdirs(); // Create reports folder if it doesn't exist
        }

        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\reports\\" + repName);

        // Check if extent-config.xml exists before loading it
        File configXml = new File(System.getProperty("user.dir") + "\\extent-config.xml");
        if (configXml.exists()) {
            try {
                sparkReporter.loadXMLConfig(configXml);
            } catch (IOException e) {
                System.err.println("Error loading extent-config.xml: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.err.println("Warning: extent-config.xml not found! Using default configuration.");
        }

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // System Information
        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub-Module", "Customer");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        // Report Configurations
        sparkReporter.config().setDocumentTitle("Opencart Automation Report");
        sparkReporter.config().setReportName("Opencart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);
    }


    @Override  // ✅ Required by ITestListener
    public void onTestSuccess(ITestResult result) {
        logger = extent.createTest(result.getTestClass().getName());
        logger.assignCategory(result.getMethod().getGroups());
        logger.log(Status.PASS, result.getName() + " got successfully executed");
    }

    @Override  // ✅ Required by ITestListener
    public void onTestFailure(ITestResult result) {
        logger = extent.createTest(result.getTestClass().getName());
        logger.assignCategory(result.getMethod().getGroups());
        logger.log(Status.FAIL, result.getName() + " got failed");
        logger.log(Status.INFO, result.getThrowable().getMessage());

        try {
            String imgPath = new BaseClass().captureScreen(result.getName());
            logger.addScreenCaptureFromPath(imgPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override  // ✅ Required by ITestListener
    public void onTestSkipped(ITestResult result) {
        logger = extent.createTest(result.getTestClass().getName());
        logger.assignCategory(result.getMethod().getGroups());
        logger.log(Status.SKIP, result.getName() + " got skipped");
        logger.log(Status.INFO, result.getThrowable().getMessage());
    }

    @Override  // ✅ Required by ITestListener
    public void onFinish(ITestContext testContext) {
        extent.flush();
        String pathOfExtentReport = System.getProperty("user.dir") + "/reports/" + repName;
        File extentReport = new File(pathOfExtentReport);
        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
