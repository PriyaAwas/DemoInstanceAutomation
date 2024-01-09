package alectra.pageObjects;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.pageObjects.scp.HomePage;

public class ToNeethuPage extends HomePage {
    private static final Logger log = LogManager.getLogger(ToNeethuPage.class);
    private static int EXPLICIT_WAIT = 5;

    public ToNeethuPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    
    public void waitForPageToLoad() {
		pause(1000);
		ExpectedCondition<Boolean> pageLoadCondition = driver -> ((JavascriptExecutor) driver)
				.executeScript("return document.readyState").equals("complete");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		wait.until(pageLoadCondition);
		pause(500);
	}
    
    
    public static void waitForElementToBeVisible(WebElement element) {
		log.info("Waiting for element to be visible for " + EXPLICIT_WAIT + " seconds.");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("Element is visible on the page.");
	}
    
    public static void clickElementUsingJsExecutor(WebElement element) {
		log.info("Preparing for element to click.");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Element is clicked successfully.");
	}
    
    public static boolean isElementVisible(WebElement element) {
		boolean isVisible = false;
		if (element.isDisplayed()) {
			isVisible = true;
			log.info("Element {} : " + element + " is visible on the page.");
		}
		return isVisible;
	}
    
    public static void scrollDown() {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("window.scrollBy(0,1000)");
	}
    
    
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[3]/section/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/table/thead/tr/th[1]/span")
	private WebElement bill_date_button;
    
    public Boolean isBillDateVisible() {
		log.info("Checking the visibility of Bill Date.");
		log.info("Company logo visibility status {}: " + isElementVisible(bill_date_button));
		return isElementVisible(bill_date_button);
	}
    
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[3]/section/div[2]/div/div/div/div[2]/div[2]/div/div/div[1]/table/thead/tr/th[1]/span")
	private WebElement transaction_date_button;
    
    public Boolean isTransactionDateVisible() {
		log.info("Checking the visibility of Bill Date.");
		log.info("Company logo visibility status {}: " + isElementVisible(transaction_date_button));
		return isElementVisible(transaction_date_button);
	}
                     
   
	@FindBy(xpath = "//*[@id=\"root\"]/div/nav/div/div/ul[1]/li[2]/div/div/div/ul/li[6]/a")
	private WebElement greenButtonOption;

	public void clickOnGreenButtonOption() {
		waitForElementToBeVisible(greenButtonOption);
		clickElementUsingJsExecutor(greenButtonOption);
		log.info("Clicked green button option");
			}
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/nav/div/div/ul[1]/li[2]/div/div/div/ul/li[6]/a")
	private WebElement paymentInfoOption;

	public void clickOnPaymentInfoOption() {
		waitForElementToBeVisible(paymentInfoOption);
		clickElementUsingJsExecutor(paymentInfoOption);
		log.info("Clicked payment info option");
			}
	
	@FindBy(xpath = "//*[@id=\"dtAccount\"]")
	private WebElement accountHeader;

	public void clickOnAccountHeader() {
		waitForElementToBeVisible(accountHeader);
		clickElementUsingJsExecutor(accountHeader);
		log.info("Clicked on account");
	}
	
	@FindBy(xpath = "//*[@id=\"mui-component-select-graphUnitDropdown\"]")
	private WebElement KwhUnitDropdown;

	public void clickKwhUnitDropdown() {
		click(KwhUnitDropdown);
		log.info("Clicked on Kwh Unit Dropdown");
	}
	
	@FindBy(xpath = "//*[@id=\"menu-graphUnitDropdown\"]/div[3]/ul/li[1]")
	private WebElement DollarUnit;

	public void clickDollarUnit() {
		click(DollarUnit);
		log.info("Clicked on Dollar Unit Dropdown");
	}
	
	@FindBy(xpath = "//*[@id=\"chart-tab-3\"]")
	private WebElement WaterOption;

	public void clickWaterOption() {
		click(WaterOption);
		log.info("Clicked on Dollar Unit Dropdown");
	}
	
	@FindBy(xpath = "//*[@id=\"mui-component-select-graphUnitDropdown\"]")
	private WebElement M3Unit;

	public void clickM3UnitDropdown() {
		click(M3Unit);
		log.info("Clicked on Dollar Unit Dropdown");
	}
	
	@FindBy(xpath = "//*[@id=\"chart-tab-1\"]")
	private WebElement DerOption;

	public void clickDerOption() {
		click(DerOption);
		log.info("Clicked on Der option");
	}
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[3]/section/div[2]/div[2]/div[1]/div/div[2]/div[4]/div[1]/a[1]")
	private WebElement RatesButton;

	public void clickRatesButton() {
		click(RatesButton);
		log.info("Clicked on Rates option");
	}
	
	@FindBy(xpath = "/html/body/div[6]/div[3]/div/div[2]/div/div/div/buttongroups/label[2]/div")
	private WebElement TieredButton;

	public void clickTieredButton() {
		waitForElementToBeVisible(TieredButton);
		clickElementUsingJsExecutor(TieredButton);
		log.info("Clicked on usage overview");
	}
	
	@FindBy(xpath = "/html/body/div[6]/div[3]/div/div[2]/div/div/div/buttongroups/label[3]/div")
	private WebElement ULOButton;

	public void clickULOButton() {
		waitForElementToBeVisible(ULOButton);
		clickElementUsingJsExecutor(ULOButton);
		log.info("Clicked on usage overview");
	}
	
	@FindBy(css = ".highcharts-container  > svg > text.highcharts-title > tspan")
	private WebElement ratesText;
	
	public String getRatesText() {
		String graphHeader = getText(ratesText);
		log.info("Page header label is {} " + graphHeader);
		ExtentLogger.logInfo("Page header label is {} " + graphHeader);
		return graphHeader;
	}
	
	public String getURLforGreenButton() {
		String URL = driver.getCurrentUrl();
		log.info("Page URL is {} " + URL);
		ExtentLogger.logInfo("Page header label is {} " + URL);
		return URL;
	}
	
	public String getURLforPaymentInfo() {
		String URL = driver.getCurrentUrl();
		log.info("Page URL is {} " + URL);
		ExtentLogger.logInfo("Page header label is {} " + URL);
		return URL;
	}
	
	@FindBy(xpath = "/html/body/div[6]/div[3]/div/div[2]/div/div/div/div[2]/ul/li[1]")
	private WebElement tieredText;
	
	public String getTieredText() {
		String graphHeader = getText(tieredText);
		log.info("Page header label is {} " + graphHeader);
		ExtentLogger.logInfo("Page header label is {} " + graphHeader);
		return graphHeader;
	}
	
	@FindBy(css = "#root > div > div.pagewrapper > section > div.MuiGrid-root.pageheading-wrapper > div > div > div:nth-child(1) > div > h1")
	private WebElement greenText;
	
	public String getGreenText() {
		String titleHeader = getText(greenText);
		log.info("Page header label is {} " + titleHeader);
		ExtentLogger.logInfo("Page header label is {} " + titleHeader);
		return titleHeader;
	}
	
	@FindBy(css = "#\\38 58 > span.MuiIconButton-label")
	private WebElement DOTButton;

	public void clickDOTButton() {
		waitForElementToBeVisible(DOTButton);
		clickElementUsingJsExecutor(DOTButton);
		log.info("Clicked on three dots");
	}
	
	@FindBy(css = "body > div.MuiPopover-root > div.MuiPaper-root.MuiMenu-paper.LongMenuWrapper.MuiPopover-paper.MuiPaper-rounded > ul > li:nth-child(3)")
	private WebElement SetAsDefaultButton;

	public void clickSetAsDefaultButton() {
		waitForElementToBeVisible(SetAsDefaultButton);
		clickElementUsingJsExecutor(SetAsDefaultButton);
		log.info("Clicked on Set As Default");
	}
	
	@FindBy(css = "body > div.MuiDialog-root.small-dialog-box > div.MuiDialog-container.MuiDialog-scrollPaper > div > div.MuiDialogActions-root.jss134.FormButtonsArea.MuiDialogActions-spacing > button.MuiButtonBase-root.MuiButton-root.MuiButton-contained.MuiButton-containedPrimary > span.MuiButton-label")
	private WebElement yesBoxText;
	
	public String getYesBoxText() {
		String titleHeader = getText(yesBoxText);
		log.info("Page header label is {} " + titleHeader);
		ExtentLogger.logInfo("Page header label is {} " + titleHeader);
		return titleHeader;
	}
	
	@FindBy(css = "body > div.MuiDialog-root.small-dialog-box > div.MuiDialog-container.MuiDialog-scrollPaper > div > div.MuiDialogActions-root.jss134.FormButtonsArea.MuiDialogActions-spacing > button.MuiButtonBase-root.MuiButton-root.MuiButton-contained.MuiButton-containedPrimary > span.MuiButton-label")
	private WebElement ClickOnYesButton;

	public void clickYesButton() {
		waitForElementToBeVisible(ClickOnYesButton);
		clickElementUsingJsExecutor(ClickOnYesButton);
		log.info("Clicked on Yes");
	}
	
	@FindBy(css = "body > div:nth-child(13) > div > div")
	private WebElement toastText;
	
	public String getToastText() {
		String titleHeader = getText(toastText);
		log.info("Page header label is {} " + titleHeader);
		ExtentLogger.logInfo("Page header label is {} " + titleHeader);
		return titleHeader;
	}
	
	
	
	
	
	
	
	
	

//    @FindBy(css = ".icon_bell_icon")
//    private WebElement iconSetBillAlerts;
//    
//    @FindBy(xpath = "//table[@id='tblBillingHistory']/tbody/tr/td[1]")
//    private WebElement lblBillDateValue;
    
    
    
    
}
