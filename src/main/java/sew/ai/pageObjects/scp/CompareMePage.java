package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CompareMePage extends HomePage {
    private static final Logger log = LogManager.getLogger(CompareMePage.class);

    public CompareMePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
