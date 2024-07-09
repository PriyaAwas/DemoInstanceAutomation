package demo.pageobjects;


import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import sew.ai.pageObjects.scp.HomePage;

public class PaymentLocationPage extends HomePage {
    private static final Logger log = LogManager.getLogger(PaymentLocationPage.class);

    public PaymentLocationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
@FindBy(css = "#preloginPaymentLocationModule")
	private WebElement payment_Location;
   
public void clickPaymentLocation() {

	click(payment_Location);
	log.info("Payment Location clicked successfully.");

}

@FindBy(xpath = "//*[@id=\"form1\"]/section[1]/div/div/div/h1/div")
private WebElement PaymentInformationlbl;

public boolean isPaymentLocationTitleVisible() {
	log.info("Payment location Page Title label visibility status : " + PaymentInformationlbl.isDisplayed());
	return PaymentInformationlbl.isDisplayed();
}
@FindBy(xpath = "//*[@id=\"PageTitle\"]")
private WebElement Paymentlctionnlbl;

public boolean isPaymentLocationHeaderVisible() {
	log.info("Payment location Page Title label visibility status : " + Paymentlctionnlbl.isDisplayed());
	return Paymentlctionnlbl.isDisplayed();
}

@FindBy(xpath = "/html/body/form/section[2]/div/div/div[1]/div[1]/div/div/div[3]/div[3]/div/div[1]/button")
private WebElement Satellite_btn;

public void clickSatellite() {

click(Satellite_btn);
log.info("Satellite btn clicked successfully.");

}
@FindBy(css = "[title=\"Show satellite imagery\"]")
private WebElement Satellite_;

public void clickSatellitebrn() {

click(Satellite_);
log.info("Satellite btn clicked successfully.");

}

@FindBy(xpath = "/html/body/form/section[2]/div/div/div[1]/div[1]/div/div/div[3]/div[3]/div/div[1]/button")
private WebElement Map_btn;

public void clickMap() {

click(Map_btn);
log.info("Map btn clicked successfully.");

}


@FindBy(css = "#ddlCity")
private WebElement Select_city;

public void clickSelectcity() {

click(Select_city);
log.info("Select City clicked successfully.");

}
public boolean isSelectcityVisible() {
	log.info("Payment location Page Title label visibility status : " + Select_city.isDisplayed());
	return Select_city.isDisplayed();}

@FindBy(css = "#tblSavedForms .dataTables_empty")
private WebElement td_NoMatchingResultFound;

@FindBy(css = "#ddlCity")
protected WebElement dropdown;

public String getSelectedValueInDropBox() {
	WebElement selectedOption = null;
	String selectedText = null;
	try {
		// WebElement element = driver.findElement(locator);
		if (dropdown.isDisplayed()) {
			Select selObj = new Select(dropdown);
			selectedOption = selObj.getFirstSelectedOption();
			selectedText = selectedOption.getText();
			log.info("User gets the selection as  " + selectedText + " from Dropdown");
		}
	} catch (Exception e) {
		e.printStackTrace();
		System.err.format("No Element Found to perform selection" + e);

	}
	return selectedText;
}

public boolean selectlstConnectMeOptions(String option) {
	boolean b = true;
	scrollToElement(dropdown);
	selectByVisibleText(dropdown, option);
	log.info("lstConnectMeOptions populated successfully.");
	return b;

}
@FindBy(xpath = "//div[@title='Anshika payment basic']/img[@alt='Banner' and @src='https://maps.gstatic.com/mapfiles/transparent.png']']")
private WebElement Locationpin;

public void clickPin() {
scrollToElement(Locationpin);
click(Locationpin);
log.info("Pin successfully.");

}

@FindBy(css = "#OuterHeader_homeAnchor")
private WebElement Homebtn;

public boolean isHomeiconVisible() {
	log.info("Payment location Page Title label visibility status : " + PaymentInformationlbl.isDisplayed());
	return Homebtn.isDisplayed();
}
@FindBy(css = "[class=\"iw-title\"]")
private WebElement Nametitle;

public boolean isNameVisible() {
	log.info("Payment location Page Title label visibility status : " + PaymentInformationlbl.isDisplayed());
	return Nametitle.isDisplayed();
}
@FindBy(xpath = "//*[@id=\"iw-container\"]/div[1]")
private WebElement Nametitles;

public boolean isNamesVisible() {
	log.info("Payment location Page Title label visibility status : " + Nametitles.isDisplayed());
	return Nametitles.isDisplayed();
}
@FindBy(xpath = "//*[@id=\"iw-container\"]/div[2]/b[1]")
private WebElement Address;

public boolean isAddressVisible() {
	log.info("Payment location Page Title label visibility status : " + PaymentInformationlbl.isDisplayed());
	return Address.isDisplayed();
}
@FindBy(xpath = "//*[@id=\"iw-container\"]/div[2]/b[1]")
private WebElement Addresscard;

public boolean isAddresVisible() {
	log.info("Payment location Page Title label visibility status : " + Addresscard.isDisplayed());
	return Address.isDisplayed();
}
@FindBy(xpath = "//*[@id=\"iw-container\"]/div[2]/b[2]")
private WebElement city;

public boolean isCityVisible() {
	log.info("Payment location Page Title label visibility status : " + city.isDisplayed());
	return city.isDisplayed();
}
@FindBy(xpath = "//*[@id=\"iw-container\"]/div[2]/b[2]")
private WebElement citycard;

public boolean isCitycardVisible() {
	log.info("Payment location Page Title label visibility status : " + citycard.isDisplayed());
	return citycard.isDisplayed();
}
@FindBy(css = "#point_0 > div > table > tbody > tr.blue > td > b")
private WebElement Listname;

public boolean isListnameVisible() {
	log.info("Payment location Page Title label visibility status : " + Listname.isDisplayed());
	return Listname.isDisplayed();
}
@FindBy(xpath = "//*[@id=\"point_0\"]/div/table/tbody/tr[3]/td")
private WebElement Listadd;

public boolean isListaddVisible() {
	log.info("Payment location Page Title label visibility status : " + Listadd.isDisplayed());
	return Listadd.isDisplayed();
}
@FindBy(xpath = "//*[@id=\"point_0\"]/div/table/tbody/tr[3]/td")
private WebElement Listaddress;

public boolean isListaddressVisible() {
	log.info("Payment location Page Title label visibility status : " + Listadd.isDisplayed());
	return Listadd.isDisplayed();
}
@FindBy(xpath = "//*[@id=\"point_0\"]/div/table/tbody/tr[4]/td")
private WebElement Listzip;

public boolean isListzipVisible() {
	log.info("Payment location Page Title label visibility status : " + Listadd.isDisplayed());
	return Listzip.isDisplayed();
}
@FindBy(xpath = "//*[@id=\"point_0\"]/div/table/tbody/tr[4]/td")
private WebElement ListZip;

public boolean isListZipVisible() {
	log.info("Payment location Page Title label visibility status : " + ListZip.isDisplayed());
	return ListZip.isDisplayed();
}


public void clickHomebtn() {

click(Homebtn);
log.info("Home Btn clicked successfully.");}

@FindBy(xpath = "//*[@id=\"paymentlocationprelogin_map_canvas\"]/div/div[3]/div[12]/div/div/div/button[1]")
private WebElement Zoominbtn;

public void clickZoomIn() {

click(Zoominbtn);
log.info("Zoom In button clicked successfully.");

}

@FindBy(css = "#module2 > a")
private WebElement Billingmodule;

public void clickBilling() {

click(Billingmodule);
log.info("Zoom In button clicked successfully.");

}
@FindBy(css = "#HeaderMenu_billList > li.icon_payment_location.list-group-item > a")
private WebElement Pmntlctn;

public void clickPaymntlctn() {

click(Pmntlctn);
log.info("Zoom In button clicked successfully.");

}


@FindBy(xpath = "//*[@id=\"paymentlocationprelogin_map_canvas\"]/div/div[3]/div[12]/div/div/div/button[2]")
private WebElement Zoomoutbtn;

public void clickZoomout() {

click(Zoomoutbtn);
log.info("Zoom out button clicked successfully.");

}

@FindBy(xpath = "/html/body/form/section[2]/div/div/div[1]/div[1]/div/div/div[3]/div[12]/div/button")
private WebElement Streetviewbtn;

public void clickStreetview() {

click(Streetviewbtn);
log.info("Street view button clicked successfully.");

}

@FindBy(xpath = "//*[@id=\"point_0\"]/div")
private WebElement Addoptn2;

public void clicklistLocation() {

click(Addoptn2);
log.info("Automation Payment Location clicked successfully.");

}


@FindBy(xpath = "//*[@id=\"point_0\"]/div")
protected WebElement Addoptn2Mapview;

public void AutomationPaymentLocationcontents() {

scrollToElement(Addoptn2Mapview);
log.info("Automation Payment Location contents srolled successfully.");

}
@FindBy(xpath = "//*[@id=\"paymentlocationprelogin_map_canvas\"]/div/div[3]/div[7]/button")
private WebElement Fullscreenout;

public void clickFullscreenout() {

click(Fullscreenout);
log.info("Full Screen out button clicked successfully.");

}


@FindBy(xpath = "//*[@id=\"paymentlocationprelogin_map_canvas\"]/div/div[3]/div[7]/button")
private WebElement Fullscreenbtn;

public void clickFullscreenbutton() {

click(Fullscreenbtn);
log.info("Full Screen button clicked successfully.");

}
@FindBy(css = "#toast-container > div > button")
private WebElement cancelbtn;

public void clickcancelButton() {

click(cancelbtn);
log.info("Full Screen button clicked successfully.");

}
@FindBy(css = "#paymentlocation_map_canvas > div > div.gm-style > div:nth-child(8) > button")
private WebElement FullScreenbtn;

public void clickFullscreenButton() {

click(FullScreenbtn);
log.info("Full Screen button clicked successfully.");

}
public void clickFullscreenOutButton() {

click(FullScreenbtn);
log.info("Full Screen button clicked successfully.");

}

@FindBy(css = "[id='txtLogin']")
private WebElement txt_username;

public void populateUserName(String userName) {
	log.info("Populating username {} :" + userName);
	sendKeys(txt_username, userName);
	log.info("Username populated successfully.");
}

@FindBy(css = "[id='txtpwd']")
private WebElement txt_password;

public void populatePassword(String password) {
	log.info("Populating password {} :" + password);
	sendKeys(txt_password, password);
	log.info("Password populated successfully.");
}

@FindBy(css = "#btnlogin")
	private WebElement btn_sign_in;
   
   public void clickSignInBtn() {
		log.info("Clicking the sign in button.");
		click(btn_sign_in);
		log.info("Sign in button clicked successfully.");
	}
   
   public Boolean isPaymentlctnPage(String url, String title) {
       Boolean isPaymentlctnPage = false;
       log.info("Checking that the current page is Payment Location page.");
       if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
    	   isPaymentlctnPage = true;
       log.info("The current page is Payment Location page {}: " + isPaymentlctnPage);
       return isPaymentlctnPage;
   }
       
       @FindBy(xpath = "//*[@id=\"module2\"]/a")
   	private WebElement Billingmdl;
      
      public void clickBillingmodule() {
   		log.info("Clicking the Billing dropdown button.");
   		click(Billingmdl);
   		log.info("Billing dropdown clicked successfully.");
   }
      @FindBy(xpath = "//*[@id=\"HeaderMenu_billList\"]/li[3]/a")
     	private WebElement Paymentlctnheader;
        
        public void clickPaymentlocation() {
     		log.info("Clicking the Payment Location.");
     		click(Paymentlctnheader);
     		log.info("Payment Location clicked successfully.");
     }
        
        @FindBy(xpath = "//*[@id=\"PageTitle\"]")
        private WebElement Paymentlctnlbl;

        public boolean isPaymentlocationTitleVisible() {
        	log.info("Payment location Page Title label visibility status : " + Paymentlctnlbl.isDisplayed());
        	return Paymentlctnlbl.isDisplayed();
        	
        }
        @FindBy(xpath = "/html/body/form/div[9]/div/div[3]/div[1]/div[1]/div[1]/div/div[3]/div[3]/div/div[2]/button")
    	private WebElement satellite_option;

    	public void clicksatellite() {

    	click(satellite_option);
    	log.info("Satellite btn clicked successfully.");

    }
        	
        	@FindBy(css ="[title='Show street map']")
        			
        		
        	private WebElement Map_option;

        	public void clickMaps() {

        	click(Map_option);
        	log.info("Map btn clicked successfully.");

        }
        	

        	@FindBy(xpath = "//*[@id=\"paymentlocation_map_canvas\"]/div/div[3]/div[12]/div/div/div/button[1]")
        	                 
        	private WebElement ZoomInbtn;

        	public void clickZoomin() {

        	click(ZoomInbtn);
        	log.info("Zoom In button clicked successfully.");

        	}

        	@FindBy(xpath = "//*[@id=\"paymentlocation_map_canvas\"]/div/div[3]/div[12]/div/div/div/button[2]")
        	private WebElement ZoomOutbtn;

        	public void clickZoomOut() {

        	click(ZoomOutbtn);
        	log.info("Zoom out button clicked successfully.");

        	}

        	@FindBy(xpath = "/html/body/form/div[9]/div/div[3]/div[1]/div[1]/div[1]/div/div[3]/div[12]/div/button")
        	private WebElement StreetViewbtn;

        	public void clickStreetView() {

        	click(StreetViewbtn);
        	log.info("Street view button clicked successfully.");

        	}

        	@FindBy(xpath = "//*[@id=\"point_0\"]/div")
        	private WebElement Addoptn;

        	public void clickAutomationPaymentlocation() {

        	scrollToElement(Addoptn2Mapview);
        	click(Addoptn);
        	log.info("Automation Payment Location clicked successfully.");

        	}

        	@FindBy(xpath = "//*[@id=\"iw-container\"]/div[2]")
        	protected WebElement Addoptn2MapView;

        	public void AutomationPaymentLocationContents() {

        	scrollToElement(Addoptn2MapView);
        	log.info("Automation Payment Location contents srolled successfully.");

        	}
        	

}






    