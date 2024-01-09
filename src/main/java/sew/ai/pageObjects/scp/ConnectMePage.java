package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ConnectMePage extends HomePage {
    private static final Logger log = LogManager.getLogger(ConnectMePage.class);

    public ConnectMePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
