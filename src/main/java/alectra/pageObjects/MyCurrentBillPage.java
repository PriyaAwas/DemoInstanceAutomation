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

public class MyCurrentBillPage extends HomePage {
    private static final Logger log = LogManager.getLogger(MyCurrentBillPage.class);
    private static int EXPLICIT_WAIT = 5;

    public MyCurrentBillPage(WebDriver driver) {
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
    
    @FindBy(xpath = "//*[@id=\"dtBilling\"]")
	private WebElement billingDropdown;
    
	public void clickBillingDropdown() {
		waitForElementToBeVisible(billingDropdown);
		clickElementUsingJsExecutor(billingDropdown);
		log.info("Clicked billing module from Header");
	}
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/nav/div/div/ul[1]/li[3]/div/div/div/ul/li[1]/a")
	private WebElement myCurrentBillOption;

	public void clickOnMyCurrentBill() {
		waitForElementToBeVisible(myCurrentBillOption);
		clickElementUsingJsExecutor(myCurrentBillOption);
		log.info("Clicked My Current Bill option from billing Header");
			}
	

//    @FindBy(css = ".icon_bell_icon")
//    private WebElement iconSetBillAlerts;
//    
//    @FindBy(xpath = "//table[@id='tblBillingHistory']/tbody/tr/td[1]")
//    private WebElement lblBillDateValue;
    
    
    
    
}
