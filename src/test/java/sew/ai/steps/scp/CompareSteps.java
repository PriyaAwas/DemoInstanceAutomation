package sew.ai.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.pageObjects.scp.CompareMePage;
import sew.ai.utils.PropertiesUtil;

public class CompareSteps extends CompareMePage {
    private static final Logger log = LogManager.getLogger(CompareSteps.class);
    public static PropertiesUtil compareTextProp;

    public CompareSteps(WebDriver driver) {
        super(driver);
        compareTextProp = new PropertiesUtil(
                FilePaths.SCP_TEXT_PROPERTIES + Constants.COMPARE_TXT_FILENAME
        );
    }

    public Boolean isComparePage(String url, String title) {
        Boolean isComparePage = false;
        log.info("Checking that the current page is usage page.");
        if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
            isComparePage = true;
        log.info("The current page is usage page {}: " + isComparePage);
        return isComparePage;
    }
}
