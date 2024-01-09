package alectra.pageObjects;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sew.ai.pageObjects.scp.HomePage;

public class BillingHistoryPage extends HomePage {
    private static final Logger log = LogManager.getLogger(BillingHistoryPage.class);
    private static int EXPLICIT_WAIT = 5;

    public BillingHistoryPage(WebDriver driver) {
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
    
    public Boolean isBillDateVisible() {
		log.info("Checking the visibility of Bill Date.");
		log.info("Company logo visibility status {}: " + isElementVisible(bill_date_button));
		return isElementVisible(bill_date_button);
	}
    
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[3]/section/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/table/thead/tr/th[1]/span")
	private WebElement bill_date_button;
    
    public Boolean isTransactionDateVisible() {
		log.info("Checking the visibility of Bill Date.");
		log.info("Company logo visibility status {}: " + isElementVisible(transaction_date_button));
		return isElementVisible(transaction_date_button);
	}
                     
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[3]/section/div[2]/div/div/div/div[2]/div[2]/div/div/div[1]/table/thead/tr/th[1]/span")
	private WebElement transaction_date_button;

    @FindBy(xpath = "//*[@id=\"dtBilling\"]")
	private WebElement billingDropdown;
    
	public void clickBillingDropdown() {
		waitForElementToBeVisible(billingDropdown);
		clickElementUsingJsExecutor(billingDropdown);
		log.info("Clicked billing module from Header");
	}
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/nav/div/div/ul[1]/li[3]/div/div/div/ul/li[4]/a")
	private WebElement billAndPayHistoryoption;

	public void clickOnBillAndPayHistory() {
		waitForElementToBeVisible(billAndPayHistoryoption);
		clickElementUsingJsExecutor(billAndPayHistoryoption);
		log.info("Clicked billing Queries option from billing Header");
			}
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[3]/section/div[2]/div/div/div/div[1]/header/div/div[2]/div/button[2]")
	private WebElement paymentHistoryoption;

	public void clickOnPaymentHistory() {
		waitForElementToBeVisible(paymentHistoryoption);
		clickElementUsingJsExecutor(paymentHistoryoption);
		log.info("Clicked billing Queries option from billing Header");
			}

//    @FindBy(css = ".icon_bell_icon")
//    private WebElement iconSetBillAlerts;
//    
//    @FindBy(xpath = "//table[@id='tblBillingHistory']/tbody/tr/td[1]")
//    private WebElement lblBillDateValue;
    
    
    
    
}
