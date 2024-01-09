package sew.ai.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.pageObjects.scp.PreLogEfficiencyPage;
import sew.ai.utils.PropertiesUtil;

public class PreLogEfficiencySteps extends PreLogEfficiencyPage {
    private static final Logger log = LogManager.getLogger(PreLogEfficiencyPage.class);
    public static PropertiesUtil efficiencyTxtProp;

    public PreLogEfficiencySteps(WebDriver driver) {
        super(driver);
        efficiencyTxtProp = new PropertiesUtil(
                FilePaths.SCP_TEXT_PROPERTIES + Constants.EFFICIENCY_TXT_FILENAME
        );
    }
}
