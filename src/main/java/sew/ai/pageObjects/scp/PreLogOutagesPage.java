package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PreLogOutagesPage extends HomePage {
    private static final Logger log = LogManager.getLogger(PreLogOutagesPage.class);

    public PreLogOutagesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#PageTitle")
    private WebElement PageHeader;

    public String getOutagesHeader() {
        log.info("Fetching the Outages page header.");
        String label = getText(PageHeader);
        log.info("Login page header is {}: " + label);
        return label;
    }
    
    public boolean isOutagesVisible() {
    	log.info("Outages Page header label visibility Status :" + PageHeader.isDisplayed());
    	return PageHeader.isDisplayed();
   }
    
    @FindBy(css = "a[title='Report Outage'] span:nth-child(3)")
    private WebElement ReportOutage;

    public String getReportOutage() {
        log.info("Fetching the Report Outage");
        String label = getText(ReportOutage);
        log.info("Report Outage is {}: " + label);
        return label;
    }
    
    public boolean isReportOutageVisible() {
    	log.info("Report Outage visibility Status :" + ReportOutage.isDisplayed());
    	return ReportOutage.isDisplayed();
   }
    
    @FindBy(css = "#btnReportOutage")
    private WebElement ReportOutagePost;

    public String getReportOutagePost() {
        log.info("Fetching the Report Outage Post");
        String label = getText(ReportOutagePost);
        log.info("Report Outage Post is {}: " + label);
        return label;
    }
    
    public boolean isReportOutagePostVisible() {
    	log.info("ReportOutagePrOsp :" + ReportOutagePost.isDisplayed());
    	return ReportOutagePost.isDisplayed();
   }
    
    @FindBy(css = ".manage_out_alert")
    private WebElement Notifications;

    public String getNotifications() {
        log.info("Fetching the Report Outage Post");
        String label = getText(Notifications);
        log.info("Report Outage Post is {}: " + label);
        return label;
    }
    
    public boolean isNotificationsVisible() {
    	log.info("Report Outage Post visibility Status :" + Notifications.isDisplayed());
    	return Notifications.isDisplayed();
   }
    
    @FindBy(css = ".crPrOutage .tbCurrent")
    private WebElement CurrentOutage;

    public String getCurrentOutage() {
        log.info("Fetching the Report Outage Post");
        String label = getText(CurrentOutage);
        log.info("Report Outage Post is {}: " + label);
        return label;
    }
    
    public boolean isCurrentOutageVisible() {
    	log.info("Report Outage Post visibility Status :" + CurrentOutage.isDisplayed());
    	return CurrentOutage.isDisplayed();
   }
    
    @FindBy(css = ".crPrOutage .tbCurrent")
    private WebElement CurrentOutagePost;

    public String getCurrentOutagePost() {
        log.info("Fetching the Current Outage Post");
        String label = getText(CurrentOutagePost);
        log.info("Current Outage Post is {}: " + label);
        return label;
    }
    
    public boolean isCurrentOutagePostVisible() {
    	log.info("Current Outage Post visibility Status :" + CurrentOutagePost.isDisplayed());
    	return CurrentOutagePost.isDisplayed();
   }
    
    @FindBy(css = ".crPrOutage .tbPlanned")
    private WebElement PlannedOutage;

    public String getPlannedOutage() {
        log.info("Fetching the Planned Outage");
        String label = getText(PlannedOutage);
        log.info("Planned Outage Post is {}: " + label);
        return label;
    }
    
    public boolean isPlannedOutageVisible() {
    	log.info("Planned Outage visibility Status :" + PlannedOutage.isDisplayed());
    	return PlannedOutage.isDisplayed();
   }
    
    @FindBy(css = ".crPrOutage .tbPlanned")
    private WebElement PlannedOutagePost;

    public String getPlannedOutagePost() {
        log.info("Fetching the Planned Outage");
        String label = getText(PlannedOutage);
        log.info("Planned Outage Post is {}: " + label);
        return label;
    }
    
    public boolean isPlannedOutagePostVisible() {
    	log.info("Planned Outage visibility Status :" + PlannedOutage.isDisplayed());
    	return PlannedOutage.isDisplayed();
   }
    
    @FindBy(css = "#LeftPanel >  div:nth-child(1) > table:nth-child(4) td strong")
    private WebElement lblInfoOutageDetailsLb;

    public String getlblInfoOutageDetails() {
        log.info("Fetching the Info Outage Detail");
        String label = getText(lblInfoOutageDetailsLb);
        log.info("Info Outage Detail is {}: " + label);
        return label;
    }
    
    public boolean isInfoOutageDetailsLbVisible() {
    	log.info("Info Outage Detail visibility Status :" + lblInfoOutageDetailsLb.isDisplayed());
    	return lblInfoOutageDetailsLb.isDisplayed();
   }
    
    @FindBy(css = "#LeftPanel >  div:nth-child(1) > table:nth-child(4) td")
    private WebElement lblInfoOutageDetailsVal;

    public String getInfoOutageDetailsVal() {
        log.info("Fetching the Info Outage Details Val");
        String label = getText(lblInfoOutageDetailsVal);
        log.info("Info Outage Detail is {}: " + label);
        return label;
    }
    
    public boolean isInfoOutageDetailsValVisible() {
    	log.info("Info Outage Detail visibility Status :" + lblInfoOutageDetailsVal.isDisplayed());
    	return lblInfoOutageDetailsVal.isDisplayed();
    
    	
    }
    
    @FindBy(css = "//*[@id='LeftPanel']/div[1]/table[2]/tbody/tr[4]/td")
    private WebElement lblCustomersAffFirst;

    public String getCustomersAffFirst() {
        log.info("Fetching the Info Customers Aff First");
        String label = getText(lblCustomersAffFirst);
        log.info("Customers Aff First is {}: " + label);
        return label;
    }
    
    public boolean isCustomersAffFirstVisible() {
    	log.info("Info Customers Aff First visibility Status :" + lblCustomersAffFirst.isDisplayed());
    	return lblCustomersAffFirst.isDisplayed();
    
    }
        
    @FindBy(css = ".toast.toast-error")
    private WebElement lblMsgSearchErr;

    public String getlblMsgSearchErr() {
        log.info("Fetching the lbl Msg Search Error");
        String label = getText(lblMsgSearchErr);
        log.info("lbl Msg Search Error is {}: " + label);
        return label;
    }
    
    public boolean islblMsgSearchErrVisible() {
    	log.info("the lbl Msg Search Error visibility Status :" + lblMsgSearchErr.isDisplayed());
    	return lblMsgSearchErr.isDisplayed();
    
    }
    
    @FindBy(css = "div#iw-container")
    private WebElement lblOutageMapContent;

    public String getlblOutageMapContent() {
        log.info("Fetching the lbl Outage Map Content");
        String label = getText(lblOutageMapContent);
        log.info("lbl Msg Search Error is {}: " + label);
        return label;
    }
    
    public boolean islblOutageMapContentVisible() {
    	log.info("the lbl Msg Search Error visibility Status :" + lblOutageMapContent.isDisplayed());
    	return lblOutageMapContent.isDisplayed();
    
    }
    
    @FindBy(css = "td.blue")
    private WebElement lblOutageTitle;

    public String getlblOutageTitle() {
        log.info("Fetching the lbl Outage Title");
        String label = getText(lblOutageTitle);
        log.info("lbl Outage Title is {}: " + label);
        return label;
    }
    
    public boolean islblOutageTitleVisible() {
    	log.info("the lbl  Outage Title visibility Status :" + lblOutageTitle.isDisplayed());
    	return lblOutageTitle.isDisplayed();
    
    }  
    
    @FindBy(css = ".MessageContainer:first-child .border td")
    private WebElement lblOutageReportInfo;

    public String getlblOutageReportInfo() {
        log.info("Fetching the lbl Outage Report Info");
        String label = getText(lblOutageReportInfo);
        log.info("lbl Outage Report Info is {}: " + label);
        return label;
    }
    
    public boolean islblOutageReportInfoVisible() {
    	log.info("lbl Outage Report Info visibility Status :" + lblOutageReportInfo.isDisplayed());
    	return lblOutageReportInfo.isDisplayed();
    
    } 	
    
    @FindBy(css = ".MessageContainer:first-child tr:nth-child(2) td")
    private WebElement lblOutageDateTime;

    public String getlblOutageDateTime() {
        log.info("Fetching the lbl Outage Date Time");
        String label = getText(lblOutageDateTime);
        log.info("Outage Date Time is {}: " + label);
        return label;
    }
    
    public boolean islblOutageDateTimeVisible() {
    	log.info("Outage Date Time  visibility Status :" + lblOutageDateTime.isDisplayed());
    	return lblOutageDateTime.isDisplayed();
    
    } 	
    
    @FindBy(css = ".MessageContainer:first-child tr:nth-child(3) td")
    private WebElement lblOutageRestDateTime;

    public String getlblOutageRestDateTime() {
        log.info("Fetching the Outage Rest Date Time");
        String label = getText(lblOutageRestDateTime);
        log.info("Outage Rest Date Time is {}: " + label);
        return label;
    }
    
    public boolean islblOutageRestDateTimeVisible() {
    	log.info("Outage Rest Date Time visibility Status :" + lblOutageRestDateTime.isDisplayed());
    	return lblOutageRestDateTime.isDisplayed();
    
    } 	
    
    @FindBy(css = ".MessageContainer:first-child tr:nth-child(4) td")
    private WebElement lblOutageCustomerAffCount;

    public String getlblOutageCustomerAffCount() {
        log.info("Fetching the Outage Rest Date Time");
        String label = getText(lblOutageRestDateTime);
        log.info("Outage Rest Date Time is {}: " + label);
        return label;
    }
    
    public boolean islblOutageCustomerAffCountVisible() {
    	log.info("Outage Rest Date Time visibility Status :" + lblOutageRestDateTime.isDisplayed());
    	return lblOutageRestDateTime.isDisplayed();
    
    } 	
    
    @FindBy(css = ".MessageContainer:first-child tr:nth-child(5) td")
    private WebElement lblOutageCommunityAff;

    public String getlblOutageCommunityAff() {
        log.info("Fetching the lbl Outage Community Aff");
        String label = getText(lblOutageCommunityAff);
        log.info("lbl Outage Community Aff is {}: " + label);
        return label;
    }
    
    public boolean islblOutageCommunityAffVisible() {
    	log.info("lbl Outage Community Aff visibility Status :" + lblOutageCommunityAff.isDisplayed());
    	return lblOutageCommunityAff.isDisplayed();
    
    } 	
    
    @FindBy(css = ".iw-content")
    private WebElement MapPinContent;

    public String getMapPinContent() {
        log.info("Fetching the lbl Map Pin Content");
        String label = getText(MapPinContent);
        log.info("Map Pin Content  is {}: " + label);
        return label;
    }
    
    public boolean isMapPinContentVisible() {
    	log.info("lbl Outage Community Aff visibility Status :" + MapPinContent.isDisplayed());
    	return MapPinContent.isDisplayed();
    
    } 	
}
