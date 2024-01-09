package sew.ai.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.pageObjects.scp.BillDashboardPage;
import sew.ai.utils.PropertiesUtil;

public class BillDashboardSteps extends BillDashboardPage {
    private static final Logger log = LogManager.getLogger(BillDashboardSteps.class);
    public static PropertiesUtil billDashboardTextProp;

    public BillDashboardSteps() {
        super(driver);
        billDashboardTextProp = new PropertiesUtil(
                FilePaths.SCP_TEXT_PROPERTIES + Constants.BILL_DASHBOARD_TXT_FILENAME
        );
    }

    public Boolean isBillDashboardPage(String url, String title) {
        Boolean isBillDashboardPage = false;
        log.info("Checking that the current page is dashboard page.");
        if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
            isBillDashboardPage = true;
        log.info("The current page is dashboard page {}: " + isBillDashboardPage);
        return isBillDashboardPage;
    }
}
