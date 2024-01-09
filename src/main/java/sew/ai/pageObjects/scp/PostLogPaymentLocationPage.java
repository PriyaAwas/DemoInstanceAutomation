package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PostLogPaymentLocationPage extends HomePage {
    private static final Logger log = LogManager.getLogger(PostLogPaymentLocationPage.class);

    public PostLogPaymentLocationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div[globalize='ML_BILLING_Navigation_PaymentLocationsMap']")
    private WebElement lblPaymentLocations;
    @FindBy(css = ".MessageContainer")
    private WebElement lstPaymentLocation;

    @FindBy(css = ".right_charging_map .outerprelogin_PinLabel")
    private WebElement txtPaymentLocation;
    @FindBy(css = ".MessageContainer:first-child .blue b")
    private WebElement lblPaymentLocationPinTitle;
    @FindBy(css = ".MessageContainer:first-child tr:nth-child(3) td")
    private WebElement lblPaymentLocationPinAddress;
    @FindBy(css = ".MessageContainer:first-child tr:nth-child(4) td")
    private WebElement lblPaymentLocationPinCity;

    @FindBy(css = ".gm-style-iw .iw-title")
    private WebElement lblPaymentLocationMapPinTitle;
    @FindBy(css = ".gm-style-iw .iw-content")
    private WebElement lblPaymentLocationMapPinContent;
    @FindBy(css = "select#ddlCity")
    private WebElement lstBoxCity;
    @FindBy(css = "#paymentlocation_map_canvas")
    private WebElement mapPaymentLoca;
    @FindBy(css = "#paymentlocationprelogin_map_canvas")
    private WebElement preLoginmapPaymentLocation;
    @FindBy(css = "tr.blue>td>b")
    private WebElement lblPLName;
    @FindBy (xpath= "//tr[@class ='blue']/following-sibling::tr[1]")
    private WebElement lblPLAddr;
    @FindBy (xpath= "//tr[@class ='blue']/following-sibling::tr[2]")
    private WebElement lblPLCity;
    @FindBy(css = "a#OuterHeader_homeAnchor")
    private WebElement btnHome;
    @FindBy(css = "div[role='button']>img")
    private WebElement PinO;

    @FindBy(css = ".labels")
    private WebElement PinOnMapPreLog;
    @FindBy(css = ".iw-content")
    private WebElement lblDetailsPinLocati;
}
