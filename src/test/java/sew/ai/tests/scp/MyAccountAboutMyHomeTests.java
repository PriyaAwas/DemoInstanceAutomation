package sew.ai.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.testrail.TestRail;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.MyAccountAboutMyHomeSteps;

import java.sql.SQLException;

public class MyAccountAboutMyHomeTests extends TestRunner {
    private static final Logger log = LogManager.getLogger(MyAccountAboutMyHomeTests.class);

    private MyAccountAboutMyHomeSteps myAccountAboutMyHomeSteps;
    @TestRail(testCaseId = {74560, 74561, 74577, 74578,107346})
    @FrameworkAnnotations(author = {"Gaurav Saxena"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify Navigation of About My Home Page")
    public void verifyAboutMyHomePageNavigation() throws SQLException {
        log.info("To verify Navigation of About My Home Page. " +
                "74560, 74561, 74577, 74578,107346");
        SoftAssert softAssert = new SoftAssert();

        myAccountAboutMyHomeSteps=new MyAccountAboutMyHomeSteps(driver);
        myAccountAboutMyHomeSteps.verifyAboutMyHomePageNavigation(softAssert);


    }
}
