package sew.ai.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.pageObjects.scp.BillPaymentPage;
import sew.ai.utils.PropertiesUtil;

public class BillPaymentSteps extends BillPaymentPage {
    private static final Logger log = LogManager.getLogger(BillPaymentSteps.class);
    public static PropertiesUtil paymentTextProp;

    public BillPaymentSteps(WebDriver driver) {
        super(driver);
        paymentTextProp = new PropertiesUtil(
                FilePaths.SCP_TEXT_PROPERTIES + Constants.PAYMENT_TXT_FILENAME
        );
    }

    public Boolean isPaymentPage(String url, String title) {
        Boolean isPaymentPage = false;
        log.info("Checking that the current page is dashboard page.");
        if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
            isPaymentPage = true;
        log.info("The current page is dashboard page {}: " + isPaymentPage);
        return isPaymentPage;
    }
}
