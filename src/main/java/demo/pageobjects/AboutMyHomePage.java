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

	@FindBy(xpath = "//*[contains(@tcollectionid,'1825')]")
	private WebElement lblHowManyBathroomsField;

	public boolean isHowManyBathroomsFieldVisible() {
		log.info("Checking that How many bathrooms do you have field is visible on the About My Home page."
				+ lblHowManyBathroomsField.isDisplayed());
		return isElementVisible(lblHowManyBathroomsField);

	}

	@FindBy(xpath = "//*[contains(@tcollectionid,'1826')]")
	private WebElement lblHowManyHighEfficiencyAppsField;

	public boolean isHowManyHighEfficiencyAppsFieldVisible() {
		log.info(
				"Checking that How many high efficiency appliances do you have field is visible on the About My Home page."
						+ lblHowManyHighEfficiencyAppsField.isDisplayed());
		return isElementVisible(lblHowManyHighEfficiencyAppsField);

	}

	@FindBy(xpath = "//*[contains(@tcollectionid,'1827')]")
	private WebElement lblLotSizeField;

	public boolean isLotSizeFieldVisible() {
		log.info("Checking that What is your lot size field is visible on the About My Home page."
				+ lblLotSizeField.isDisplayed());
		return isElementVisible(lblLotSizeField);

	}

	@FindBy(xpath = "//*[contains(@tcollectionid,'1828')]")
	private WebElement lblLandscapeAreaField;

	public boolean isLandscapeAreaFieldVisible() {
		log.info("Checking that Does your home has a landscape area field is visible on the About My Home page."
				+ lblLandscapeAreaField.isDisplayed());
		return isElementVisible(lblLandscapeAreaField);

	}

	@FindBy(xpath = "//*[contains(@tcollectionid,'1829')]")
	private WebElement lblSizeOfSpecialLandscapeField;

	public boolean isSizeOfSpecialLandscapeFieldVisible() {
		log.info("Checking that What is the size of special landscape area field is visible on the About My Home page."
				+ lblSizeOfSpecialLandscapeField.isDisplayed());
		return isElementVisible(lblSizeOfSpecialLandscapeField);

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

}