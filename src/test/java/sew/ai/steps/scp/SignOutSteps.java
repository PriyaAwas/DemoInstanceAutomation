package sew.ai.steps.scp;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.pageObjects.scp.SignOutPage;
import sew.ai.utils.PropertiesUtil;

public class SignOutSteps extends SignOutPage {
    public static PropertiesUtil signOutTextProp;

    public SignOutSteps(WebDriver driver) {
        super(driver);
        signOutTextProp = new PropertiesUtil(
                FilePaths.SCP_TEXT_PROPERTIES + Constants.SIGNOUT_TXT_FILENAME
        );
    }

    public void moveToLoginPageAgain(SoftAssert softAssert) {
        Assert.assertTrue(
                isSignOutPage(
                    signOutTextProp.getPropValue("signOutPageUrl"),
                    signOutTextProp.getPropValue("signOutPageTitle")
                )
        );
        waitForSignOutSuccessLbl();
        softAssert.assertEquals(getSignOutSuccessfullyLbl(), signOutTextProp.getPropValue("lblYouHaveSignedOut"),
                "You have signed out successfully message not matched.");
        clickSignInAgainLnk();
    }
}
