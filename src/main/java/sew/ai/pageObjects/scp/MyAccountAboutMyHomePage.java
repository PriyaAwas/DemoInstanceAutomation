package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static sew.ai.helpers.reporters.ExtentLogger.logInfo;

public class MyAccountAboutMyHomePage extends HomePage {
    private static final Logger log = LogManager.getLogger(MyAccountAboutMyHomePage.class);

    public MyAccountAboutMyHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public boolean isMyAccountAboutMyHomePage(String url,String title){
        boolean isMyAccountAboutMyHomePage=false;
        log.info("Checking that the current page is MyAccount About My HomePage.");
        if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
            isMyAccountAboutMyHomePage = true;
        log.info("The current page is My Profile {}: " + isMyAccountAboutMyHomePage);
        return isMyAccountAboutMyHomePage;
    }

    @FindBy(css = " #dLabel")
    private WebElement btnSelectAddressDropdownArrow;

    public void clickSelectAddressDropdownArrowBtn() {
        logInfo("clicking the SelectAddressDropdownArrow button");
        click(btnSelectAddressDropdownArrow);
    }

    @FindBys(@FindBy(css = ".dropdown.open #UlddlAddress li a"))
    private List<WebElement> lstServiceAddDropdown;

    public List<WebElement> getServiceAddDropdownList() {
        List<WebElement> ls = lstServiceAddDropdown;
        return ls;
    }

    public String getServiceAddDropdownLabel(WebElement e) {
        String label = getText(e);
        return label;
    }

    public void clickServiceAddDropdown(WebElement e) {
        logInfo("Service Add Drop down is going to be clicked...");
        click(e);
    }
   @FindBy(css="h1#PageTitle")
        private WebElement aboutMyHome;
    public String getAboutMyHomeLabel(){
       String label=getText(aboutMyHome);
       return label;
    }
}
