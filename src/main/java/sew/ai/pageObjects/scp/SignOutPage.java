package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignOutPage extends HomePage {
    private static final Logger log = LogManager.getLogger(SignOutPage.class);

    public SignOutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".banner_logged_out h1")
    private WebElement lbl_you_signed_out;

    public String getSignOutSuccessfullyLbl() {
        return getText(lbl_you_signed_out);
    }

    public void waitForSignOutSuccessLbl() {
        waitForElementToBeVisible(lbl_you_signed_out);
    }

    @FindBy(css = "[globalize='ML_signout_txt_signin']")
    private WebElement lnk_sign_in_again;

    public void clickSignInAgainLnk() {
        click(lnk_sign_in_again);
        log.info("Sign in again link clicked.");
    }

    @FindBy(css = "a[globalize=ML_signout_txt_signin]")
    private WebElement btn_sign_in_again;

    public void clickSignInAgainBtn() {
        click(btn_sign_in_again);
        log.info("Sign in again button clicked.");
    }

    public Boolean isSignOutPage(String url, String title) {
        Boolean isSignOutPage = false;
        log.info("Checking that the current page is login page.");
        if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
            isSignOutPage = true;
        log.info("The current page is login page {}: " + isSignOutPage);
        return isSignOutPage;
    }
}
