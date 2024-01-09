package sew.ai.helpers.reporters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.props.FilePaths;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class ExtentReport {

    private ExtentReport() {
    }

    private static ExtentReports extent;

    public static void initReports() {
        if (Objects.isNull(extent)) {
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter(FilePaths.EXTENT_REPORT_PATH);
            extent.attachReporter(spark);
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("SCM Test Automation Execution Report");
            spark.config().setReportName("10S Automation Execution Report");
        }
    }

    public static void flushReports() {
        if (Objects.nonNull(extent)) {
            extent.flush();
        }
        ExtentManager.unload();
        try {
            Desktop.getDesktop().browse(new File(FilePaths.EXTENT_REPORT_PATH).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createTest(String testcaseName) {
        ExtentTest test = extent.createTest(testcaseName);
        ExtentManager.setExtentTest(test);
    }

    public static void addAuthors(String[] authors) {
        for (String author : authors)
            ExtentManager.getExtentTest().assignAuthor(author);
    }

    public static void addCategories(CategoryType[] categories) {
        for (CategoryType category : categories)
            ExtentManager.getExtentTest().assignCategory(category.toString());
    }
}
