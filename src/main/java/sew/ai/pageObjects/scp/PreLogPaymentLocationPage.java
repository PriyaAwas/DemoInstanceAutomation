package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PreLogPaymentLocationPage extends HomePage {
    private static final Logger log = LogManager.getLogger(PreLogPaymentLocationPage.class);

    public PreLogPaymentLocationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".MessageContainer")
    private WebElement lstPaymentLocation;

    public boolean isbtnSetBudgetVisible() {
    	log.info("lst Payment Location Status :" + lstPaymentLocation.isDisplayed());
    	return lstPaymentLocation.isDisplayed();
   }
    
//    @FindBy(css = ".inner_mid_section .map_canvas .gm-style .gmnoprint img[src*=" pin"]")
//    private WebElement lnkPaymentLocationPin;
//    
//    public boolean islnkPaymentLocationPinVisible() {
//    	log.info("lst Payment Location Status :" + lnkPaymentLocationPin.isDisplayed());
//    	return lnkPaymentLocationPin.isDisplayed();
//   }
    
    @FindBy(css = ".right_charging_map .outerprelogin_PinLabel")
    private WebElement txtPaymentLocation;
    
    public boolean istxtPaymentLocationVisible() {
    	log.info("lst Payment Location Status :" + txtPaymentLocation.isDisplayed());
    	return txtPaymentLocation.isDisplayed();
   }
       
    @FindBy(css = ".MessageContainer:first-child .blue b")
    private WebElement lblPaymentLocationPinTitle;
    
    public boolean islblPaymentLocationPinTitleVisible() {
    	log.info("lst Payment Location Status :" + lblPaymentLocationPinTitle.isDisplayed());
    	return lblPaymentLocationPinTitle.isDisplayed();
   }
    
    
    @FindBy(css = ".MessageContainer:first-child tr:nth-child(3) td")
    private WebElement lblPaymentLocationPinAddress;
    
    public boolean islblPaymentLocationPinAddressVisible() {
    	log.info("lbl Payment Location Pin Address Status :" + lblPaymentLocationPinAddress.isDisplayed());
    	return lblPaymentLocationPinAddress.isDisplayed();
   }
    
    
    @FindBy(css = ".MessageContainer:first-child tr:nth-child(4) td")
    private WebElement lblPaymentLocationPinCity;
    
    public boolean islblPaymentLocationPinCityVisible() {
    	log.info("lbl Payment Location Pin City Status :" + lblPaymentLocationPinCity.isDisplayed());
    	return lblPaymentLocationPinCity.isDisplayed();
   }
    
    
    @FindBy(css = ".gm-style-iw .iw-title")
    private WebElement lblPaymentLocationMapPinTitle;
    
    public boolean islblPaymentLocationMapPinTitleVisible() {
    	log.info("lbl Payment Location Map Pin Title Status :" + lblPaymentLocationMapPinTitle.isDisplayed());
    	return lblPaymentLocationMapPinTitle.isDisplayed();
   }
    
    @FindBy(css = ".gm-style-iw .iw-content")
    private WebElement lblPaymentLocationMapPinContent;
    
    public boolean islblPaymentLocationMapPinContentVisible() {
    	log.info("lbl Payment Location Map Pin Content Status :" + lblPaymentLocationMapPinContent.isDisplayed());
    	return lblPaymentLocationMapPinContent.isDisplayed();
   }
    
    
    @FindBy(css = "#LeftPanel")
    private WebElement LblNoPaymentLoca;
    
    public boolean isLblNoPaymentLocaVisible() {
    	log.info("Lbl No Payment Location Status :" + LblNoPaymentLoca.isDisplayed());
    	return LblNoPaymentLoca.isDisplayed();
   }
    
}
