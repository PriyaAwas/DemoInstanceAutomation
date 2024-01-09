package sew.ai.pageObjects.csp;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sew.ai.pageObjects.scp.HomePage;

public class AdminUniversalSearchPage extends HomePage {

	private static final Logger log = LogManager.getLogger(AdminUniversalSearchPage.class);

	public AdminUniversalSearchPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[@class='fa fa-search']")
    private WebElement btnUniversalSearchIcon;
	//By btnUniversalSearchIcon = By.xpath(Utils.getRbPage().getString("btnUniversalSearchIconAusp"));
	public void explicitWaitForBtnUniversalSearchIcon() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.visibilityOf(btnUniversalSearchIcon));
		} catch (NoSuchElementException e) {
			log.error("No Element Found after wait" + e);
		}
	}
	public Boolean isBtnUniversalSearchIconVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(btnUniversalSearchIcon));
        return isElementVisible(btnUniversalSearchIcon);
    }
	public void clickBtnUniversalSearchIcon() {
        clickElementUsingJsExecutor(btnUniversalSearchIcon);
        waitForImgLoadingInvisibility();
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(xpath = "//div[@class='uni_src_left_box']/h3")
    private WebElement lblUniversalSearchHeader;
	//By lblUniversalSearchHeader = By.xpath(Utils.getRbPage().getString("lblUniversalSearchHeaderAusp"));
	public String getLblUniversalSearchHeaderLabel() {
        String label = getText(lblUniversalSearchHeader);
        log.info("BillTypeStatus Field Label {}: " + label);
        return label;
    }
	
	@FindBy(xpath = "//input[@id='txtcustomername']")
    private WebElement txtCustomerNameUniversalSearch;
	//By txtCustomerNameUniversalSearch = By.xpath(Utils.getRbPage().getString("txtCustomerNameUniversalSearchAusp"));
	public void explicitWaitForTxtCustomerNameUniversalSearch() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until(ExpectedConditions.visibilityOf(txtCustomerNameUniversalSearch));
		} catch (NoSuchElementException e) {
			log.error("No Element Found after wait" + e);
		}
	}
	public Boolean isTxtCustomerNameUniversalSearchVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtCustomerNameUniversalSearch));
        return isElementVisible(txtCustomerNameUniversalSearch);
    }
	public WebElement getTxtCustomerNameUniversalSearch() {
		return txtCustomerNameUniversalSearch;
	}
	
	@FindBy(xpath = "//input[@id='txtZipCode']")
    private WebElement txtZipUniversalSearch;
	//By txtZipUniversalSearch = By.xpath(Utils.getRbPage().getString("txtZipUniversalSearchAusp"));
	public Boolean isTxtZipUniversalSearchVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtZipUniversalSearch));
        return isElementVisible(txtZipUniversalSearch);
    }
	public WebElement getTxtZipUniversalSearch() {
		return txtZipUniversalSearch;
	}
	
	@FindBy(xpath = "//input[@id='txtAccountIDPopup']")
    private WebElement txtAccountIDUniversalSearch;
	//By txtAccountIDUniversalSearch = By.xpath(Utils.getRbPage().getString("txtAccountIDUniversalSearchAusp"));
	public Boolean isTxtAccountIDUniversalSearchVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtAccountIDUniversalSearch));
        return isElementVisible(txtAccountIDUniversalSearch);
    }
	public WebElement getTxtAccountIDUniversalSearch() {
		return txtAccountIDUniversalSearch;
	}
	
	@FindBy(xpath = "//input[@id='txtEmailPopup']")
    private WebElement txtEmailIDUniversalSearch;
	//By txtEmailIDUniversalSearch = By.xpath(Utils.getRbPage().getString("txtEmailIDUniversalSearchAusp"));
	public Boolean isTxtEmailIDUniversalSearchVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtEmailIDUniversalSearch));
        return isElementVisible(txtEmailIDUniversalSearch);
    }
	public WebElement getTxtEmailIDUniversalSearch() {
		return txtEmailIDUniversalSearch;
	}
	
	@FindBy(xpath = "//input[@id='txtMobilePopup']")
    private WebElement txtMobileUniversalSearch;
	//By txtMobileUniversalSearch = By.xpath(Utils.getRbPage().getString("txtMobileUniversalSearchAusp"));
	public Boolean isTxtMobileUniversalSearchVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtMobileUniversalSearch));
        return isElementVisible(txtMobileUniversalSearch);
    }
	public WebElement getTxtMobileUniversalSearch() {
		return txtMobileUniversalSearch;
	}
	
	@FindBy(xpath = "//input[@id='txtAddressPopup']")
    private WebElement txtAddressUniversalSearch;
	//By txtAddressUniversalSearch = By.xpath(Utils.getRbPage().getString("txtAddressUniversalSearchAusp"));
	public Boolean isTxtAddressUniversalSearchVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(txtAddressUniversalSearch));
        return isElementVisible(txtAddressUniversalSearch);
    }
	public WebElement getTxtAddressUniversalSearch() {
		return txtAddressUniversalSearch;
	}
	
	@FindBy(xpath = "//div[@class='button_uni']//*[text()='Submit']")
    private WebElement btnSubmitUniversalSearch;
	//By btnSubmitUniversalSearch = By.xpath(Utils.getRbPage().getString("btnSubmitUniversalSearchAusp"));
	public Boolean isBtnSubmitUniversalSearchVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(btnSubmitUniversalSearch));
        return isElementVisible(btnSubmitUniversalSearch);
    }
	public void clickBtnSubmitUniversalSearch() {
        click(btnSubmitUniversalSearch);
        waitForImgLoadingInvisibility();
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(xpath = "//div[@class='button_uni']//*[text()='Clear']")
    private WebElement btnClearUniversalSearch;
	//By btnClearUniversalSearch = By.xpath(Utils.getRbPage().getString("btnClearUniversalSearchAusp"));
	public Boolean isBtnClearUniversalSearchVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(btnClearUniversalSearch));
        return isElementVisible(btnClearUniversalSearch);
    }
	public void clickBtnClearUniversalSearch() {
        click(btnClearUniversalSearch);
        waitForImgLoadingInvisibility();
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBys(@FindBy(xpath = "//div[@class='dataTables_scrollHead']//tr//th"))
    private List<WebElement> listSearchGridResultHeaders;
	//By listSearchGridResultHeaders = By.xpath(Utils.getRbPage().getString("listSearchGridResultHeadersAusp"));
	public List<WebElement> getListSearchGridResultHeaders(){
		return listSearchGridResultHeaders;
	}
	
	@FindBy(xpath = "//button[@id='closepopup']")
    private WebElement btnCloseUniversalSearch;
	//By btnCloseUniversalSearch = By.xpath(Utils.getRbPage().getString("btnCloseUniversalSearchAusp"));
	public void clickBtnCloseUniversalSearch() {
        click(btnCloseUniversalSearch);
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(xpath = "//div[@class='uni_search_wrapper uni_top_space fade in']")
    private WebElement popUpUniversalPop;
	//By popUpUniversalPop = By.xpath(Utils.getRbPage().getString("popUpUniversalPopAusp"));
	
	@FindBys(@FindBy(xpath = "//table[@id='Search_report_Grid']//tbody//tr//td[1]"))
    private List<WebElement> lblFirstInGridTable;
	//By lblFirstInGridTable = By.xpath(Utils.getRbPage().getString("lblFirstInGridTableAusp"));
	public List<WebElement> getLblFirstInGridTable(){
		return lblFirstInGridTable;
	}
	
	@FindBys(@FindBy(xpath = "//table[@id='Search_report_Grid']//tbody//tr//td[2]"))
    private List<WebElement> lblSecondInGridTable;
	//By lblSecondInGridTable = By.xpath(Utils.getRbPage().getString("lblSecondInGridTableAusp"));
	public List<WebElement> getLblSecondInGridTable(){
		return lblSecondInGridTable;
	}
	
	@FindBys(@FindBy(xpath = "//table[@id='Search_report_Grid']//tbody//tr//td[3]"))
    private List<WebElement> lblThirdInGridTable;
	//By lblThirdInGridTable = By.xpath(Utils.getRbPage().getString("lblThirdInGridTableAusp"));
	public List<WebElement> getLblThirdInGridTable(){
		return lblThirdInGridTable;
	}
	
	@FindBys(@FindBy(xpath = "//table[@id='Search_report_Grid']//tbody//tr//td[4]"))
    private List<WebElement> lblFourthInGridTable;
	//By lblFourthInGridTable = By.xpath(Utils.getRbPage().getString("lblFourthInGridTableAusp"));
	public List<WebElement> getLblFourthInGridTable(){
		return lblFourthInGridTable;
	}
	
	@FindBys(@FindBy(xpath = "//table[@id='Search_report_Grid']//tbody//tr//td[5]"))
    private List<WebElement> lblFifthInGridTable;
	//By lblFifthInGridTable = By.xpath(Utils.getRbPage().getString("lblFifthInGridTableAusp"));
	public List<WebElement> getLblFifthInGridTable(){
		return lblFifthInGridTable;
	}
	
	@FindBys(@FindBy(xpath = "//table[@id='Search_report_Grid']//tbody//tr//td[6]"))
    private List<WebElement> lblSixthInGridTable;
	//By lblSixthInGridTable = By.xpath(Utils.getRbPage().getString("lblSixthInGridTableAusp"));
	public List<WebElement> getLblSixthInGridTable(){
		return lblSixthInGridTable;
	}
	
	@FindBy(xpath = "//table[@id='Search_report_Grid']//tbody//tr//td[1]//a")
    private WebElement lnkFirstInGridTable;
	//By lnkFirstInGridTable = By.xpath(Utils.getRbPage().getString("lnkFirstInGridTableAusp"));
	public WebElement getLnkFirstInGridTable() {
		return lnkFirstInGridTable;
	}
	public Boolean isLnkFirstInGridTableVisible() {
        log.info("Checking the visibility of Sign In Button on the page.");
        log.info("Sign In Button visibility status {}: " + isElementVisible(lnkFirstInGridTable));
        return isElementVisible(lnkFirstInGridTable);
    }
	public void clickLnkFirstInGridTable() {
        click(lnkFirstInGridTable);
        log.info("SignIn Button clicked successfully.");
    }
	
	@FindBy(xpath = "//h3[contains(text(),'Universal Search')]")
    private WebElement lblSearchReportGridInfo;
    //By lblSearchReportGridInfo = By.xpath(Utils.getRbPage().getString("lblSearchReportGridInfoAusp"));
	public String getLblSearchReportGridInfoLabel() {
		String sTitle = null;
		try {
			lblSearchReportGridInfo.isDisplayed();
			sTitle = lblSearchReportGridInfo.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}
    
    @FindBy(xpath = "//table[@id='Search_report_Grid']/tbody/tr/td")
    private WebElement lblNoDataFound;
    //By lblNoDataFound= By.xpath(Utils.getRbPage().getString("lblNoDataFoundAusp"));
    public String getLblNoDataFoundLabel() {
		String sTitle = null;
		try {
			lblNoDataFound.isDisplayed();
			sTitle = lblNoDataFound.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}
    
    @FindBy(xpath = "//div[@id='Search_report_Grid_info']")
    private WebElement lblPagination;
    //By lblPagination = By.xpath(Utils.getRbPage().getString("lblPaginationAusp"));
    public String getLblPaginationLabel() {
		String sTitle = null;
		try {
			lblPagination.isDisplayed();
			sTitle = lblPagination.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
			
		}
		return sTitle;

	}
    
    @FindBy(xpath = "//li[@id='Search_report_Grid_next']/a")
    private WebElement btnNext;
    //By btnNext = By.xpath(Utils.getRbPage().getString("btnNextAusp"));
    
    @FindBy(xpath = "//li[@id='Search_report_Grid_previous']/a")
    private WebElement btnPrevious;
    //By btnPrevious = By.xpath(Utils.getRbPage().getString("btnPreviousAusp"));
    
    @FindBy(css = ".paginate_button.active")
    private WebElement lblPageHigligted;
    //By lblPageHigligted = By.cssSelector(Utils.getRbPage().getString("lblPageHigligtedAusp"));
    
    
    public void enterTextValueCsr(WebElement locator, String expectedData) {
		try {
			WebElement element = locator;
			if (element.isDisplayed())
			// element.isDisplayed())
			{
				element.clear();
				if (expectedData.equals("NULL")) {
					element.sendKeys("");
				} else {
					element.sendKeys(expectedData);
					log.info("User enters " + expectedData + " in the " + element.getText() + " text box");
					
				}
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.err.format("No Element Found to enter text" + e);
			
		}
	}

}
