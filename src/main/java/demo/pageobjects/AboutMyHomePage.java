package demo.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import sew.ai.pageObjects.scp.HomePage;

import java.util.List;

import static sew.ai.helpers.reporters.ExtentLogger.logInfo;

public class AboutMyHomePage extends HomePage {
	protected static final Logger log = LogManager.getLogger(AboutMyHomePage.class);

	public AboutMyHomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

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

	@FindBy(css = "h1#PageTitle")
	private WebElement aboutMyHome;

	public String getAboutMyHomeLabel() {
		String label = getText(aboutMyHome);
		return label;
	}

	@FindBy(xpath = " //input[@inputtype=\"Account\"] ")
	private WebElement lblAccountNumbField;

	public boolean isAccountNumberVisible() {
		log.info("Checking that Account Number is visible on the About My Home page."
				+ lblAccountNumbField.isDisplayed());
		return isElementVisible(lblAccountNumbField);
	}

	@FindBy(xpath = "//*[contains(@tcollectionid,'1859')]")
	private WebElement lblHomeTypeField;

	public boolean isHomeTypeFieldVisible() {
		log.info("Checking that What type of home do you live in field is visible on the About My Home page."
				+ lblHomeTypeField.isDisplayed());
		return isElementVisible(lblHomeTypeField);

	}

	public void chooseSelectOption() {
		log.info("Choosing 'Select' option in the home type dropdown.");

		if (isElementVisible(lblHomeTypeField)) {
			Select dropdown = new Select(lblHomeTypeField);
			dropdown.selectByVisibleText("Select");
			log.info("'Select' option has been successfully chosen.");
		} else {
			log.error("Home type dropdown is not visible. Cannot choose 'Select' option.");
		}
	}

	public void clickHomeTypeField() {
		click(lblHomeTypeField);
		log.info("Home Type Field has been successfully clicked.");

	}

	@FindBy(xpath = "//*[contains(@tcollectionid,'1860')]")
	private WebElement lblHowManyPplField;

	public boolean isHowManyPeopleFieldVisible() {
		log.info(
				"Checking that How many people are there in your house hold field is visible on the About My Home page."
						+ lblHowManyPplField.isDisplayed());
		return isElementVisible(lblHowManyPplField);

	}

	@FindBy(css = "input[globalize='ML_DynamicForm_DynamicKey_185']")
	private WebElement lblHaveSolarPanelsField;

	public boolean isHaveSolarPanelsVisible() {
		log.info("Checking that Do you have solar panels field is visible on the About My Home page."
				+ lblHaveSolarPanelsField.isDisplayed());
		return isElementVisible(lblHaveSolarPanelsField);

	}

	public void enterDataInSolarPanelField(String datafield) {
		sendKeys(lblHaveSolarPanelsField, datafield);
		log.info("Entering data in the do you have solar panels field.");
	}
	
	public String getTxtBoxSolarPanelMaxLength() {
        String maxLen = getAttribute(lblHaveSolarPanelsField, "maxlength");
        log.info("Max length of Solar Panel field is {} " + maxLen);
        return maxLen;
    }
	
	public String getTxtBoxSolarPanelInputMode() {
        String inputMode = getAttribute(lblHaveSolarPanelsField, "inputmode");
        log.info("Input mode of Solar Panel field is {} " + inputMode);
        return inputMode;
    }

	@FindBy(css = "#formfieldlist > div:nth-child(4) > div > a")
	private WebElement iconHaveSolarPanelsField;

	public boolean isIconSolarPanelsVisible() {
		log.info("Checking that Do you have solar panels info icon is visible on the About My Home page."
				+ iconHaveSolarPanelsField.isDisplayed());
		return isElementVisible(iconHaveSolarPanelsField);

	}

	public void clickIconSolarPanels() {
		click(iconHaveSolarPanelsField);
		log.info("Do you have solar panels info icon has been successfully clicked.");

	}
	
	public String getSolarPanelInfoIcon() {
		log.info("Fetching the info icon details on Solar Panels field");
		String tooltip = iconHaveSolarPanelsField.getAttribute("aria-label");
		return tooltip;
	}

	@FindBy(xpath = "//*[contains(@tcollectionid,'1821')]")
	private WebElement lblYourHomeSizeField;

	public boolean isYourHomeSizeVisible() {
		log.info("Checking that What is your home size field is visible on the About My Home page."
				+ lblYourHomeSizeField.isDisplayed());
		return isElementVisible(lblYourHomeSizeField);

	}

	public void enterDataInHomeSizeField(String datafield) {
		sendKeys(lblYourHomeSizeField, datafield);
		log.info("Entering data in the what is your home size field.");

	}
	
	public String getTxtBoxYourHomeSizeMaxLength() {
        String maxLen = getAttribute(lblYourHomeSizeField, "maxlength");
        log.info("Max length of Your Home Size field is {} " + maxLen);
        return maxLen;
    }
	
	public String getTxtBoxYourHomeSizeInputMode() {
        String inputMode = getAttribute(lblYourHomeSizeField, "inputmode");
        log.info("Input mode of Your Home Size field is {} " + inputMode);
        return inputMode;
    }
	
	@FindBy(css = "#formfieldlist > div:nth-child(5) > div > a")
	private WebElement iconHomeSizeField;

	public boolean isIconHomeSizeVisible() {
		log.info("Checking that Home size info icon is visible on the About My Home page."
				+ iconHomeSizeField.isDisplayed());
		return isElementVisible(iconHomeSizeField);

	}

	public void clickIconHomeSize() {
		click(iconHomeSizeField);
		log.info("Home size info icon has been successfully clicked.");

	}
	
	public String getHomeSizeInfoIcon() {
		log.info("Fetching the info icon details on Home Size field");
		String tooltip = iconHomeSizeField.getAttribute("aria-label");
		return tooltip;
	}

	@FindBy(xpath = "//*[contains(@tcollectionid,'1822')]")
	private WebElement lblHowManyFloorsField;

	public boolean isHowManyFloorsFieldVisible() {
		log.info("Checking that How many floors does your home have field is visible on the About My Home page."
				+ lblHowManyFloorsField.isDisplayed());
		return isElementVisible(lblHowManyFloorsField);

	}

	public void enterDataInFloorsField(String datafield) {
		sendKeys(lblYourHomeSizeField, datafield);
		log.info("Entering data in the how many floors does your home have field.");

	}
	
	public String getTxtBoxHowManyFloorsMaxLength() {
        String maxLen = getAttribute(lblHowManyFloorsField, "maxlength");
        log.info("Max length of How Many Floor field is {} " + maxLen);
        return maxLen;
    }
	
	public String getTxtBoxHowManyFloorsInputMode() {
        String inputMode = getAttribute(lblHowManyFloorsField, "inputmode");
        log.info("Input mode of How Many Floor field is {} " + inputMode);
        return inputMode;
    }

	@FindBy(xpath = "//*[contains(@tcollectionid,'1823')]")
	private WebElement lblOwnAnEVToggle;

	public boolean isDoYouOwnEVToggleVisible() {
		log.info("Checking that Do you own an electric vehicle toggle is visible on the About My Home page."
				+ lblOwnAnEVToggle.isDisplayed());
		return isElementVisible(lblOwnAnEVToggle);

	}

	@FindBy(xpath = "//*[contains(@tcollectionid,'1824')]")
	private WebElement lblYearOfHouseBuildField;

	public boolean isYearOfHouseBuildFieldVisible() {
		log.info("Checking that In which year was your house build field is visible on the About My Home page."
				+ lblYearOfHouseBuildField.isDisplayed());
		return isElementVisible(lblYearOfHouseBuildField);

	}
	
	public void enterDataInYrOfBuiltField(String datafield) {
		sendKeys(lblYearOfHouseBuildField, datafield);
		log.info("Entering data in the house build year field.");

	}
	
	public String getTxtBoxHouseBuildYrMaxLength() {
        String maxLen = getAttribute(lblYearOfHouseBuildField, "maxlength");
        log.info("Max length of House Build Year field is {} " + maxLen);
        return maxLen;
    }
	
	public String getTxtBoxHouseBuildYrInputMode() {
        String inputMode = getAttribute(lblYearOfHouseBuildField, "inputmode");
        log.info("Input mode of House Build Year field is {} " + inputMode);
        return inputMode;
    }

	@FindBy(xpath = "//*[contains(@tcollectionid,'1825')]")
	private WebElement lblHowManyBathroomsField;

	public boolean isHowManyBathroomsFieldVisible() {
		log.info("Checking that How many bathrooms do you have field is visible on the About My Home page."
				+ lblHowManyBathroomsField.isDisplayed());
		return isElementVisible(lblHowManyBathroomsField);

	}
	
	public void enterDataInBathroomNoField(String datafield) {
		sendKeys(lblHowManyBathroomsField, datafield);
		log.info("Entering data in the no of bathrooms field.");

	}
	
	public String getTxtBoxHowManyBathMaxLength() {
        String maxLen = getAttribute(lblHowManyBathroomsField, "maxlength");
        log.info("Max length of How Many Bathroom field is {} " + maxLen);
        return maxLen;
    }
	
	public String getTxtBoxHowManyBathInputMode() {
        String inputMode = getAttribute(lblHowManyBathroomsField, "inputmode");
        log.info("Input mode of How Many Bathroom field is {} " + inputMode);
        return inputMode;
    }

	@FindBy(xpath = "//*[contains(@tcollectionid,'1826')]")
	private WebElement lblHowManyHighEfficiencyAppsField;

	public boolean isHowManyHighEfficiencyAppsFieldVisible() {
		log.info(
				"Checking that How many high efficiency appliances do you have field is visible on the About My Home page."
						+ lblHowManyHighEfficiencyAppsField.isDisplayed());
		return isElementVisible(lblHowManyHighEfficiencyAppsField);

	}
	
	public void enterDataInHighEfficiencyField(String datafield) {
		sendKeys(lblHowManyHighEfficiencyAppsField, datafield);
		log.info("Entering data in the high efficiency apps field.");

	}
	
	public String getTxtBoxHowManyHighEfficiencyMaxLength() {
        String maxLen = getAttribute(lblHowManyHighEfficiencyAppsField, "maxlength");
        log.info("Max length of How many high efficiency field is {} " + maxLen);
        return maxLen;
    }
	
	public String getTxtBoxHowManyHighEfficiencyInputMode() {
        String inputMode = getAttribute(lblHowManyHighEfficiencyAppsField, "inputmode");
        log.info("Input mode of How many high efficiency field is {} " + inputMode);
        return inputMode;
    }
	
	@FindBy(css = "#formfieldlist > div:nth-child(10) > div > a")
	private WebElement iconHaveHighEfficiencyField;

	public boolean isIconHighEfficiencyVisible() {
		log.info("Checking that Do you have high efficiency info icon is visible on the About My Home page."
				+ iconHaveSolarPanelsField.isDisplayed());
		scrollToElement(iconHaveHighEfficiencyField);
		return isElementVisible(iconHaveHighEfficiencyField);

	}
	
	public void clickIconHighEfficiency() {
		click(iconHaveHighEfficiencyField);
		log.info("High efficiency info icon has been successfully clicked.");

	}
	
	public String getHighEfficiencyInfoIcon() {
		log.info("Fetching the info icon details on High Efficiency field");
		String tooltip = iconHaveHighEfficiencyField.getAttribute("aria-label");
		return tooltip;
	}

	@FindBy(xpath = "//*[contains(@tcollectionid,'1827')]")
	private WebElement lblLotSizeField;

	public boolean isLotSizeFieldVisible() {
		log.info("Checking that What is your lot size field is visible on the About My Home page."
				+ lblLotSizeField.isDisplayed());
		return isElementVisible(lblLotSizeField);

	}
	
	public void enterDataInLotSizeField(String datafield) {
		sendKeys(lblLotSizeField, datafield);
		log.info("Entering data in the lot size field.");

	}
	public String getTxtBoxLotSizeMaxLength() {
        String maxLen = getAttribute(lblLotSizeField, "maxlength");
        log.info("Max length of Lot Size field is {} " + maxLen);
        return maxLen;
    }
	
	public String getTxtBoxLotSizeInputMode() {
        String inputMode = getAttribute(lblLotSizeField, "inputmode");
        log.info("Input mode of Lot Size field is {} " + inputMode);
        return inputMode;
    }
	
	@FindBy(css = "#formfieldlist > div:nth-child(11) > div > a")
	private WebElement iconLotSizeField;

	public boolean isIconLotSizeVisible() {
		log.info("Checking that Do you have lot size info icon is visible on the About My Home page."
				+ iconLotSizeField.isDisplayed());
		scrollToElement(iconLotSizeField);
		return isElementVisible(iconLotSizeField);

	}
	
	public void clickIconLotSize() {
		click(iconLotSizeField);
		log.info("Lot Size info icon has been successfully clicked.");

	}
	
	public String getLotSizeInfoIcon() {
		log.info("Fetching the info icon details on Lot Size field");
		String tooltip = iconLotSizeField.getAttribute("aria-label");
		return tooltip;
	}

	@FindBy(xpath = "//*[contains(@tcollectionid,'1828')]")
	private WebElement lblLandscapeAreaField;

	public boolean isLandscapeAreaFieldVisible() {
		log.info("Checking that Does your home has a landscape area field is visible on the About My Home page."
				+ lblLandscapeAreaField.isDisplayed());
		return isElementVisible(lblLandscapeAreaField);

	}
	
	public void enterDataInLandscapeAreaField(String datafield) {
		sendKeys(lblLandscapeAreaField, datafield);
		log.info("Entering data in the landscape area field.");

	}
	public String getTxtBoxLandscapeAreaMaxLength() {
        String maxLen = getAttribute(lblLandscapeAreaField, "maxlength");
        log.info("Max length of Landscape Area field is {} " + maxLen);
        return maxLen;
    }
	
	public String getTxtBoxLandscapeAreaInputMode() {
        String inputMode = getAttribute(lblLandscapeAreaField, "inputmode");
        log.info("Input mode of Landscape Area field is {} " + inputMode);
        return inputMode;
    }
	
	@FindBy(css = "#formfieldlist > div:nth-child(12) > div > a")
	private WebElement iconLandscapeAreaField;

	public boolean isIconLandscapeAreaVisible() {
		log.info("Checking that Do you have landscape area info icon is visible on the About My Home page."
				+ iconLandscapeAreaField.isDisplayed());
		scrollToElement(iconLandscapeAreaField);
		return isElementVisible(iconLandscapeAreaField);

	}
	
	public void clickIconLandscapeArea() {
		click(iconLandscapeAreaField);
		log.info("Landscape area info icon has been successfully clicked.");

	}
	
	public String getLandscapeAreaInfoIcon() {
		log.info("Fetching the info icon details on Landscape Area field");
		String tooltip = iconLandscapeAreaField.getAttribute("aria-label");
		return tooltip;
	}

	@FindBy(xpath = "//*[contains(@tcollectionid,'1829')]")
	private WebElement lblSizeOfSpecialLandscapeField;

	public boolean isSizeOfSpecialLandscapeFieldVisible() {
		log.info("Checking that What is the size of special landscape area field is visible on the About My Home page."
				+ lblSizeOfSpecialLandscapeField.isDisplayed());
		return isElementVisible(lblSizeOfSpecialLandscapeField);

	}
	
	public void enterDataInLandscapeSizeField(String datafield) {
		sendKeys(lblSizeOfSpecialLandscapeField, datafield);
		log.info("Entering data in the landscape size field.");

	}
	public String getTxtBoxLandscapeSizeMaxLength() {
        String maxLen = getAttribute(lblSizeOfSpecialLandscapeField, "maxlength");
        log.info("Max length of Landscape Size field is {} " + maxLen);
        return maxLen;
    }
	
	public String getTxtBoxLandscapeSizeInputMode() {
        String inputMode = getAttribute(lblSizeOfSpecialLandscapeField, "inputmode");
        log.info("Input mode of Landscape Size field is {} " + inputMode);
        return inputMode;
    }
	
	@FindBy(css = "#formfieldlist > div:nth-child(13) > div > a")
	private WebElement iconLandscapeSizeField;

	public boolean isIconLandscapeSizeVisible() {
		log.info("Checking that Do you have landscape size info icon is visible on the About My Home page."
				+ iconLandscapeSizeField.isDisplayed());
		scrollToElement(iconLandscapeSizeField);
		return isElementVisible(iconLandscapeSizeField);

	}
	
	public void clickIconLandscapeSize() {
		click(iconLandscapeSizeField);
		log.info("Landscape Size info icon has been successfully clicked.");

	}
	
	public String getLandscapeSizeInfoIcon() {
		log.info("Fetching the info icon details on Landscape Size field");
		String tooltip = iconLandscapeSizeField.getAttribute("aria-label");
		return tooltip;
	}

	@FindBy(xpath = "//*[contains(@tcollectionid,'1853')]")
	private WebElement lblSwimmingPoolToggle;

	public boolean isSwimmingPoolToggleVisible() {
		log.info("Checking that Do you have a swimming pool toggle is visible on the About My Home page."
				+ lblSwimmingPoolToggle.isDisplayed());
		return isElementVisible(lblSwimmingPoolToggle);

	}

	@FindBy(xpath = "//*[contains(@id,'titleBanner')]")
	private WebElement lblAboutMyHomeBanner;

	public boolean isAboutMyHomeBannerVisible() {
		log.info("Checking that AboutMyHome Banner is visible on the About My Home page."
				+ lblAboutMyHomeBanner.isDisplayed());
		return isElementVisible(lblAboutMyHomeBanner);

	}

	@FindBy(xpath = "//*[contains(@class,'contact_wrp_sidebar')]")
	private WebElement lblContactUsBanner;

	public boolean isContactUsBannerVisible() {
		log.info("Checking that ContactUs Banner is visible on the About My Home page."
				+ lblContactUsBanner.isDisplayed());
		return isElementVisible(lblContactUsBanner);

	}

	@FindBy(xpath = "//*[@id=\"btnSaveHomeInfo\"]")
	private WebElement lblSubmitBttn;

	public void clickAboutMyHomeFormSubmitbtn() {
		click(lblSubmitBttn);
		log.info("About My Home form has been successfully submitted");

	}

	@FindBy(xpath = " //*[@id=\"toast-container\"]/div/div ")
	private WebElement lblSubmitForm;

	public String getSubmitFormPopUpLabel() {
		log.info("Fetching the successful form submission lable on pop up");
		String label = getText(lblSubmitForm);
		return label;

	}

	@FindBy(xpath = "//*[@id=\"4f9f1c42-d461-4212-837b-f920b3a10eba\"]/option[1]")
	private WebElement lblSelectOptn;

	public void clickSelectOption() {
		click(lblSelectOptn);
		log.info("Choosing select option for home type field");

	}

	public boolean isMyAccountAboutMyHomePage(String url, String title) {
		boolean isMyAccountAboutMyHomePage = false;
		log.info("Checking that the current page is MyAccount About My HomePage.");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isMyAccountAboutMyHomePage = true;
		log.info("The current page is My Profile {}: " + isMyAccountAboutMyHomePage);
		return isMyAccountAboutMyHomePage;
	}
	
	@FindBy(xpath = "//*[@id='toast-container']/div")
	private WebElement lblZeroValSub;

	public String getZeroValPopUpLabel() {
		log.info("Fetching the validation message when customer enters all 0 in fields");
		String label = getText(lblZeroValSub);
		return label;

	}
	
	@FindBy(xpath = "//*[@id=\"toast-container\"]/div/div")
	private WebElement lbl1900ValSub;

	public String get1900PopUpLabel() {
		log.info("Fetching the validation message when customer enters less than 1900 in fields");
		String label = getText(lbl1900ValSub);
		return label;

	}

}