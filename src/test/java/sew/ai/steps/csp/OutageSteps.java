package sew.ai.steps.csp;

import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.pageObjects.csp.OutagePage;
import sew.ai.utils.PropertiesUtil;

import java.util.List;

public class OutageSteps extends OutagePage {

    private static final Logger log = LogManager.getLogger(OutageSteps.class);
    public static PropertiesUtil outagesTextProp;

    public OutageSteps(WebDriver driver) {
        super(driver);
        outagesTextProp = new PropertiesUtil(FilePaths.CSP_TEXT_PROPERTIES + Constants.ADMIN_OUTAGES_TXT_FILENAME);
    }

    public void verifyObjectsAdminOutageTab(SoftAssert softAssert){

        // Verify admin outage page heading
        String expOutPageHeading = outagesTextProp.getPropValue("txtMsgOutagePageHeading");
        String actOutPageHeading = getLblOutagePageHeading();
        softAssert.assertEquals(actOutPageHeading, expOutPageHeading,"Admin outage page heading not matched.");

        // Verify export link
        softAssert.assertTrue(isLnkExportVisible(),"Export link was not present.");
        String actExpLnkTxt = getLblLnkExport();
        String expExpLnkTxt = outagesTextProp.getPropValue("txtLblExportLink");
        softAssert.assertEquals(actExpLnkTxt, expExpLnkTxt,"Admin outage export link text not matched.");

        // Verify add outage link
        String actAddOutLnkTxt = getLblLnkAddOutage();
        String expAddOutLnkTxt = outagesTextProp.getPropValue("txtLblAddOutageLink");
        softAssert.assertEquals(actAddOutLnkTxt, expAddOutLnkTxt,"Add outage link text not matched.");

        // Verify planned outage tab
        String actPlannedOutTabTxt = getLblTabPlannedOutage();
        String expPlannedOutTabTxt = outagesTextProp.getPropValue("txtLblPlannedOutageTab");
        softAssert.assertEquals(actPlannedOutTabTxt, expPlannedOutTabTxt,"Planned outage tab text not matched.");

        // Verify current outage tab
        String actCurrentOutTabTxt = getLblTabUnplannedOutage();
        String expCurrentOutTabTxt = outagesTextProp.getPropValue("txtLblUnplannedOutageTab");
        softAssert.assertEquals(actCurrentOutTabTxt, expCurrentOutTabTxt,
                "Unplanned outage tab text not matched.");

        // Verify total outage tab
        String actTotalOutTabTxt = getLblTabTotalOutage();
        String expTotalOutTabTxt = outagesTextProp.getPropValue("txtLblTotalOutageTab");
        softAssert.assertEquals(actTotalOutTabTxt, expTotalOutTabTxt,
                "Total outage tab text not matched.");

        // To verify the planned outage count label
        String actPlannedOutCount = getLblCountPlannedOutages();
        ExtentLogger.logInfo("Actual Planned outage on the UI is "+actPlannedOutCount);

        // To verify the Unplanned outage count label
        String actUnplannedOutCount = getLblCountUnplannedOutages();
        ExtentLogger.logInfo("Actual Unplanned outage on the UI is "+actUnplannedOutCount);

        // Verification of test case with id : C66131
        ExtentLogger.logInfo("C66131 - To verify that the count of planned " +
                "outage/current outage and total outage is correct on the header.");
        // To verify the total outage count label
        String actTotalOutCount = getLblCountTotalOutages();
        // To verify the actual total count is as per the cal: TO = PO + UPO
        String expTotalOutCount = String.valueOf(Integer.parseInt(actPlannedOutCount) + Integer
                .parseInt(actUnplannedOutCount));
        softAssert.assertEquals(actTotalOutCount, expTotalOutCount,
                "Total Outages Count on the header tabs not matched.");

        // To verify the grid view tabs
        softAssert.assertTrue(isLnkTabGridViewVisible(),"Grid view tab is not displaying.");
        String actTabLabel = getLblLnkTabGridView();
        String expTabLabel = outagesTextProp.getPropValue("txtLblGridViewTab");
        softAssert.assertEquals(actTabLabel, expTabLabel, "Grid view tab label not matched.");

        // To verify the map view tabs
        softAssert.assertTrue(isLnkTabMapViewVisible(),"Map view tab is not displaying.");
        actTabLabel = getLblLnkTabMapView();
        expTabLabel = outagesTextProp.getPropValue("txtLblMapViewTab");
        softAssert.assertEquals(actTabLabel, expTabLabel, "Map view tab label not matched.");

        // Verification of test case with id: C66133
        ExtentLogger.logInfo("C66133 - To verify that if user click on planned/current/outage" +
                " option on table it should load list of corresponding outages.");

        // To verify link filter planned outage visibility
        softAssert.assertTrue(isLnkFilterPlannedOutageVisible(),"Filter Planned outage link is not displayed.");
        String actLnkTxt = getLblLnkFilterPlannedOutage();
        String expLnkTxt = outagesTextProp.getPropValue("txtFilterPlannedOutageLnk");
        softAssert.assertEquals(actLnkTxt, expLnkTxt,"Filter planned outage link text not matched.");

        // To verify link filter current outage visibility
        softAssert.assertTrue(isLnkFilterCurrentOutageVisible(),"Filter Current outage link is not displayed.");
        actLnkTxt = getLblLnkFilterCurrentOutage();
        expLnkTxt = outagesTextProp.getPropValue("txtFilterCurrentOutageLnk");
        softAssert.assertEquals(actLnkTxt, expLnkTxt,"Filter current outage link text not matched.");

        // To verify link filter unplanned outage visibility
        softAssert.assertTrue(isLnkFilterUnplannedOutVisible(),"Filter Unplanned outage link is not displayed.");
        actLnkTxt = getLblLnkFilterUnplannedOut();
        expLnkTxt = outagesTextProp.getPropValue("txtFilterUnplannedOutLnk");
        softAssert.assertEquals(actLnkTxt, expLnkTxt,"Filter unplanned outage link text not matched.");

        // Getting the un-planned outage count from the outage view grid.
        clickLnkFilterUnplannedOut();
        waitForImgLoadingInvisibility();
        WebElement gridViewRowCont = getWebEleLblOutageGridRowWiseCellXp();
        List<WebElement> gridViewRowsList = gridViewRowCont.findElements(By.xpath("tr"));
        int unPlannedRowCount = getNoOfEntriesInGrid();
        // Getting the planned outage count from the outage view grid
        clickLnkFilterPlannedOutage();
        waitForImgLoadingInvisibility();
        gridViewRowCont = getWebEleLblOutageGridRowWiseCellXp();
        gridViewRowsList = gridViewRowCont.findElements(By.xpath("tr"));
        int plannedRowCount = getNoOfEntriesInGrid();
        // To verify the view all filter link
        softAssert.assertTrue(isLnkShowAllOutageVisible(),"View all filter is not displayed.");
        actLnkTxt = getLblLnkShowAllOutage();
        expLnkTxt = outagesTextProp.getPropValue("txtShowAllOutageLink");
        softAssert.assertEquals(actLnkTxt, expLnkTxt,"Filter view all outage link text not matched.");
        // Verification of test case with id: C66132
        ExtentLogger.logInfo("C66132 - To verify that the count of planned outage/current" +
                " outage and the total outage is correct in the outage list.");
        clickLnkShowAllOutage();
        waitForImgLoadingInvisibility();
        gridViewRowCont = getWebEleLblOutageGridRowWiseCellXp();
        gridViewRowsList = gridViewRowCont.findElements(By.xpath("tr"));
        int actTotalRowCount = getNoOfEntriesInGrid();
        int expTotalRowCount = unPlannedRowCount + plannedRowCount;
        softAssert.assertEquals(actTotalRowCount, expTotalRowCount,
                "Total row count in the outage grid are not equal to the planned outage plus unplanned outage.");
    }

    public void verifyOutageGridHeaderList(){
        List<String> actGridHeadersList =  getAllElementsTextInList(getLstWebEleOutageGridViewHeaders());
        List<String> expGridHeadersList = getExpectedElementsTextList(outagesTextProp.getPropValue("lblGridHeader"));
        ExtentLogger.logInfo("The Actual Grid Headers displayed on the UI is "+actGridHeadersList);
        ExtentLogger.logInfo("The Expected Grid Headers on the UI is "+expGridHeadersList);
        Assert.assertTrue(actGridHeadersList.containsAll(expGridHeadersList),"Outage Grid Headers displayed on the UI are not as Expected" +
                "The Expected Grid Headers are "+expGridHeadersList+ " The Actual Grid Headers are "+ actGridHeadersList);
    }

//    public void verifyOutageDetailsPopupPageObjects(SoftAssert softAssert){
//        List<WebElement> threeDotsRowList = getLstWebEleIcoThreeDotsGrid();
//        if (threeDotsRowList.size() > 0) {
//            // Select first outage to view outage details
//            clickLstWebEleIcoThreeDotsGrid(0);
//            pause(1000);
//            // Click outage details
//            clicklnkOutageDetailsForOpenMenu();
//            waitForImgLoadingInvisibility();
//            pageUtil.expWaitForEleVisibility(adminOutagePage.getLblOutageDetailsHeader());
//            // Verification of test case with id: C66135
//            Runner.test.log(Status.INFO, "C66136 - To verify that on selecting the outage  from table, " +
//                    "user can see details outage details, outage history and effected customer list.");
//            // Verify the UI on outage detail pop-up.
//            softAssert.assertTrue(pageUtil.isElementDisplayed(adminOutagePage.getLblOutageDetailsHeader()),
//                    "Outage details tab not displayed.");
//            String actTabLbl = pageUtil.getLocatorText(adminOutagePage.getLblOutageDetailsHeader());
//            String expTabLbl = txtPropJson.getStringJsonValue("txtLblOutageDetailsTab");
//            softAssert.assertEquals(actTabLbl, expTabLbl, "Outage details header text not matched.");
//            // Verifying the outage type grid label
//            softAssert.assertTrue(pageUtil.isElementDisplayed(adminOutagePage.getLblOutageTypeOutDetailsXp()),
//                    "Outage type label in grid is not displayed.");
//            String actGridLbl = pageUtil.getLocatorText(adminOutagePage.getLblOutageTypeOutDetailsXp());
//            String expGridLbl = txtPropJson.getStringJsonValue("txtLblOutageType");
//            softAssert.assertEquals(actGridLbl, expGridLbl, "Outage type label not matched.");
//            // Verify community affected.
//            softAssert.assertTrue(pageUtil.isElementDisplayed(adminOutagePage.getLblCommAffectedOutDetailsXp()),
//                    "Community affected label not displayed.");
//            actGridLbl = pageUtil.getLocatorText(adminOutagePage.getLblCommAffectedOutDetailsXp());
//            expGridLbl = txtPropJson.getStringJsonValue("txtLblCommunityAffected");
//            softAssert.assertEquals(actGridLbl, expGridLbl, "Community affected label not matched.");
//            // Verify customers affected.
//            softAssert.assertTrue(pageUtil.isElementDisplayed(adminOutagePage.getLblCustAffectedOutDetailsXp()),
//                    "Customers affected label not displayed.");
//            actGridLbl = pageUtil.getLocatorText(adminOutagePage.getLblCustAffectedOutDetailsXp());
//            expGridLbl = txtPropJson.getStringJsonValue("txtLblCustomersAffected");
//            softAssert.assertEquals(actGridLbl, expGridLbl, "Customers affected label not matched.");
//            // Verify start time.
//            softAssert.assertTrue(pageUtil.isElementDisplayed(adminOutagePage.getLblStartTimeOutDetailsXp()),
//                    "Start time label not displayed.");
//            actGridLbl = pageUtil.getLocatorText(adminOutagePage.getLblStartTimeOutDetailsXp());
//            expGridLbl = txtPropJson.getStringJsonValue("txtLblStartTime");
//            softAssert.assertEquals(actGridLbl, expGridLbl, "Start time label not matched.");
//            // Verify estimated restoration.
//            softAssert.assertTrue(pageUtil.isElementDisplayed(adminOutagePage.getLblEndTimeOutDetailsXp()),
//                    "estimated restoration label not displayed.");
//            actGridLbl = pageUtil.getLocatorText(adminOutagePage.getLblEndTimeOutDetailsXp());
//            expGridLbl = txtPropJson.getStringJsonValue("txtLblEstimatedRestoration");
//            softAssert.assertEquals(actGridLbl, expGridLbl, "Estimated restoration label not matched.");
//            // Verify outage status.
//            softAssert.assertTrue(pageUtil.isElementDisplayed(adminOutagePage.getLblOutStatusOutDetailsXp()),
//                    "Outage status label not displayed.");
//            actGridLbl = pageUtil.getLocatorText(adminOutagePage.getLblOutStatusOutDetailsXp());
//            expGridLbl = txtPropJson.getStringJsonValue("txtLblOutageStatus");
//            softAssert.assertEquals(actGridLbl, expGridLbl, "Outage status label not matched.");
//            // Verify outage cause.
//            softAssert.assertTrue(pageUtil.isElementDisplayed(adminOutagePage.getLblOutCauseOutDetailsXp()),
//                    "outage cause label not displayed.");
//            actGridLbl = pageUtil.getLocatorText(adminOutagePage.getLblOutCauseOutDetailsXp());
//            expGridLbl = txtPropJson.getStringJsonValue("txtLblOutageCause");
//            softAssert.assertEquals(actGridLbl, expGridLbl, "Outage cause label not matched.");
//            // Close outage details pop-up
//            pageUtil.clickElementUsingJsExecutor(adminOutagePage.getBtnCloseOutageDetails());
//            pageUtil.pause(4000);
//        }
//    }


    /****************************************************************************************************
     * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> REUSABLE METHODS <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< *
     *****************************************************************************************************/

    public Boolean isOutagesPage(String url, String title) {
        Boolean isOutagesPage = false;
        log.info("Checking that the current page is Outages page.");
        if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
            isOutagesPage = true;
        log.info("The current page is Outages page {}: " + isOutagesPage);
        return isOutagesPage;
    }
    public int getNoOfEntriesInGrid() {
        int count;
        String entries = getLblNoOfEntriesInGrid();
        entries = entries.substring(entries.indexOf("of") + 2, entries.indexOf("entries")).trim();
        count = Integer.parseInt(entries.replace(",",""));
        return count;
    }




}
