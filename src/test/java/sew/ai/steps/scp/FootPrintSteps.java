package sew.ai.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.pageObjects.scp.FootPrintPage;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.PropertiesUtil;
import sew.ai.utils.RandomUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static sew.ai.helpers.reporters.ExtentLogger.logInfo;

public class FootPrintSteps extends FootPrintPage {
    private static final Logger log = LogManager.getLogger(FootPrintSteps.class);
    public static PropertiesUtil footPrintTextProp;
    public static List<String> expDropDownOptionList = new ArrayList<>();

    public FootPrintSteps(WebDriver driver) {
        super(driver);
        footPrintTextProp = new PropertiesUtil(
                FilePaths.SCP_TEXT_PROPERTIES + Constants.FOOT_PRINT_TXT_FILENAME);
    }

    public void verifyFootPrintPageObjects(SoftAssert softAssert) throws SQLException {
       
        //To verify the object visiblity displayed on the Footprints Page
        pause(2000);
        boolean listViewVisibleStatus=isListViewVisible();
        boolean dropDownvisibleStatus=isdropdownVisible();
        softAssert.assertTrue(dropDownvisibleStatus, "dropdown is not Visible");
       softAssert.assertTrue(isListViewVisible(), "LinkMap is not Visible");
        softAssert.assertTrue(isCurrentLocationlnkVisible(), "CurrentLocationlnk is not Visible");

        softAssert.assertTrue(isSearchBoxVisible(), "SearchBox is not Visible");
        softAssert.assertTrue(isSearchButtonVisible(), "SearchButton is not Visible");
        softAssert.assertTrue(isStreetMapBtnVisible(), "StreetMapBtn is not Visible");
        softAssert.assertTrue(isSatelliteMapBtnVisible(), "Satellite MapBtn  is not Visible");
        softAssert.assertTrue(isZoomInBtnVisible(), "ZoomInBtn is not Visible");
        softAssert.assertTrue(isZoomOutBtnVisible(), "ZoomOutBtn is not Visible");
        softAssert.assertTrue(isFullScreenBtnVisible(), "FullScreenBtn is not Visible");
        softAssert.assertTrue(isPegmanBtnVisible(), "Pegman Btn is not Visible");
        //Validation Attribute of all of the Objects present on UI
        softAssert.assertEquals(getFootPrintPageHeadingLabel(), footPrintTextProp.getPropValue("footPrintPageHeader"),
                "FootPrint Page Heading Label is not Matching.");
        softAssert.assertEquals(getBoxSearchAttribute(), footPrintTextProp.getPropValue("searchBoxAttribute"),
                "Search box Attribute is not Matching.");
        softAssert.assertEquals(getBtnSearchAttribute(), footPrintTextProp.getPropValue("searchBtnAttribute"),
                "Search box button Attribute is not Matching.");
        softAssert.assertEquals(getListViewAttribute(), footPrintTextProp.getPropValue("FootPrintListViewAttribute"),
                "List View Attribute is not Matching.");
        softAssert.assertEquals(getCurrentLocationlnkAttribute(), footPrintTextProp.getPropValue("currentLocatioBtnAttribute"),
                "Cuurent Location map Attribute is not Matching.");
        softAssert.assertEquals(getdropdownAttribute(), footPrintTextProp.getPropValue("dropdownAttribute"),
                "Drop Down box Attribute is not Matching.");
        softAssert.assertEquals(getStreetMapBtnAttribute(), footPrintTextProp.getPropValue("mapBtnAttribute"),
                "Srteet Map button  Attribute is not Matching.");
        softAssert.assertEquals(getSatelliteMapBtnAttribute(), footPrintTextProp.getPropValue("satelliteAttribute"),
                "Satellite Map button Attribute is not Matching.");
        softAssert.assertEquals(getZoomInBtnAttribute(), footPrintTextProp.getPropValue("zoomInAttribute"),
                "Zoom In Button Attribute is not Matching.");
        softAssert.assertEquals(getZoomOutBtnAttribute(), footPrintTextProp.getPropValue("zoomOutAttribute"),
                "Zoom out button Attribute is not Matching.");
        softAssert.assertEquals(getFullScreenBtnAttribute(), footPrintTextProp.getPropValue("toogleBtnAttribute"),
                "Full Screen Button Attribute is not Matching.");
        softAssert.assertEquals(getPegmanBtnAttribute(), footPrintTextProp.getPropValue("pegmanAttribute"),
                "Pegman button Attribute is not Matching.");
        //
        List<String> actDropDownOptionList = getAllDropDownOptionsInListBox();
        Collections.sort(actDropDownOptionList);
        String[] listDropDown = footPrintTextProp.getPropValue("listOfDropDown").split(",");
        for (int listDropDownCount = 0; listDropDownCount < listDropDown.length; listDropDownCount++) {
            String ListDropDown = listDropDown[listDropDownCount];
            expDropDownOptionList.add(ListDropDown);
        }
        Collections.sort(expDropDownOptionList);
        softAssert.assertEquals(actDropDownOptionList, expDropDownOptionList,"List of drop down is not matching with properties file text");
        //75354
        pause(200);
        clearBoxSearch();
        clickSearchButton();
        softAssert.assertEquals(getToastMessageLabel(), footPrintTextProp.getPropValue("searchToastMessage"),
                "Validatin msg is not coorect while searching with out input in search box");
        //75356
        pause(2000);
        clearBoxSearch();
        populateSearchBox(RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
        clickSearchButton();
        softAssert.assertEquals(getToastMessageLabel(), footPrintTextProp.getPropValue("searchToastMessageNoFP"),"not matching toster msg");
        //
        String selectFootprintType = actDropDownOptionList.get(0);
        selectDropDownByVisibleText(selectFootprintType);
        //clickListView();

        // Validate Label of the Page
        String str=getFootPrintPageHeadingLabel();
        softAssert.assertEquals(getFootPrintPageHeadingLabel(), footPrintTextProp.getPropValue("footPrintPageHeader"),
                "Foot print page header label is not Matching.");
        softAssert.assertEquals(getRefershBtnLabel(), footPrintTextProp.getPropValue("txtBtnRefresh"),
                "Refresh button label is not Matching.");
        //String sattliteLabel=getSatelliteMapBtnLabel();
        pause(3000);
        //softAssert.assertEquals(sattliteLabel, footPrintTextProp.getPropValue("txtBtnSatelliteMap"),
          //      "Satellite map button label is not Matching.");
        //75359-refresh map reset at default creteria
        List<String> options = getAllSelectDropDownOptionsInListBox();
        for (int dropdownCout = 1; dropdownCout < options.size(); dropdownCout++) {
            String selectedMap = options.get(dropdownCout);
            selectDropDownByVisibleText(selectedMap);
            clickRefreshbtn();
            softAssert.assertEquals(options.get(0), "All",
                    "After Refresh The selected map in drop down box is not set to Default setting");
        }
        //75367-- as per selected dropdown all list displayed in foorprint grid view
        for (int dropdownCout = 1; dropdownCout < options.size(); dropdownCout++) {
            String selectedMap = options.get(dropdownCout);
            selectDropDownByVisibleText(selectedMap);
            if(isListViewVisible()==true){
                clickListView();
            } else{
                clickListViewMap();
                clickListView();
            }
            List<WebElement> pagibatebtn=getPaginateBtnList();
            int noOfPage=pagibatebtn.size();
            pagibatebtn.get(noOfPage-1).click();
            pause(2000);
           List<WebElement> noOfFootPrintOnPage=getListFootPrint();
           int noOfFootPrintOnLastPage=noOfFootPrintOnPage.size();
           int totalNoOfFootPrint=((noOfPage-1)*10+noOfFootPrintOnLastPage);
            logInfo("Total no of Foot Print for "+selectedMap+"-- has been Calculated-->>"+totalNoOfFootPrint);
            int noOfRowData=0;
            pause(3000);
            ResultSet resultSet=DataBaseUtils.getResultSet(SQLQueries.getFootPrintData(dropdownCout));
            while(resultSet.next()){
                noOfRowData++;
            }
softAssert.assertEquals(totalNoOfFootPrint,noOfRowData,
        "Total no of Foot Print Data is Not Matching for Particular--"+selectedMap+"-- drop down > Foot Print from DB.");
            //clickListViewMap();
        }

        //75366-Application should display following message fields in Tabular format: Location type Name Address City
       List<WebElement> columnElement=getlstListViewFootPrintColumns();
        for(int columnElement1=0;columnElement1<columnElement.size()-1;columnElement1++){
           String columnName=columnElement.get(columnElement1).getText();
           if(columnElement1==0){softAssert.assertEquals(columnName,"LOCATION TYPE",
               "Location Type column name is not matching in list view type");}
            else if(columnElement1==1){softAssert.assertEquals(columnName,"NAME",
                   "Name column name is not matching in list view type");}
           else if(columnElement1==2){softAssert.assertEquals(columnName,"ADDRESS",
                   "Address column name is not matching in list view type");}
           else if (columnElement1==3){softAssert.assertEquals(columnName,"CITY",
                   "City column name is not matching in list view type");}
           else{log.info("The No of Column is rather than like Location, Name ,Address and City");}
        }
        //75352, 75353, , 75355
    }
    public void verifyPinedPopUpDetails(SoftAssert softAssert) throws Exception {
        
        List<String> category = getListOfAllCategories();
        //for(String cat :category){
        List<String> zipCode = getZipCodeForCategory(category.get(0));
        //for(int counter=0;counter<2;counter++){
        populateSearchBox(zipCode.get(0));
        List<String> options = getAllSelectDropDownOptionsInListBox();
        int i = 0;
        for (String st : options) {
            i++;
            if (i == 1)
                continue;
            System.out.println("Option==> " + st);
            selectDropDownByVisibleText(st);
            pause(3000);
            List<WebElement> fpOnMap = getFpOnMapElement();
            if (fpOnMap.size() > 1) {
                for (int j = 1; j <= fpOnMap.size(); j++) {
                    //pageUtil.scrollToElement(fpOnMap.get(j));
                    waitForElementToBeVisible(fpOnMap.get(j));
                    fpOnMap.get(j).click();
                    waitForFootPrintDetailsOnMap();
                    String footPrintDetails = getfootprintDetailsOnMapLabel();
                    String footPrintNameDetails=getfootprintNameDetailsOnMapLabel();
                    String footPrintName = getNameOfFootPrintForZip(zipCode.get(0)).get(j - 1);
                    String footPrintAddress = getAddressOfFootPrintForZip(zipCode.get(0)).get(j - 1);
                    //C75357,C75358
                    softAssert.assertEquals(footPrintNameDetails,st,"FootPrint name is not Matching on map which is selected on dropdown");
                    softAssert.assertEquals(footPrintDetails, footPrintName,
                            "Foot Print Details is not matching Foot Print Name");
                    softAssert.assertEquals(footPrintDetails, footPrintAddress,
                            "Foot Print Details is not matching with Foot Print Address");
                    //	pageUtil.explicitWaitToAvoidStaleElement(driver, footprintPage.getBtnCloseFootprint());
                    //pageUtil.check(footprintPage.getBtnCloseFootprint());
                    j++;
                    if (j == 2) {
                        break;
                    }
                }
            }
        }
    }
    public List<String> getListOfAllCategories() throws Exception {
        List<String> categoryList = new ArrayList<String>();
        String sSqlQuery = SQLQueries.getLocationId();
        ResultSet rs = DataBaseUtils.getResultSet(sSqlQuery);
        while (rs.next()) {
            String category = rs.getString(1).trim();
            categoryList.add(category);
        }
        return categoryList;
    }
    public List<String> getZipCodeForCategory(String category) throws Exception {
        List<String> zipList = new ArrayList<String>();
        String sSqlQuery = SQLQueries.getZipCodeForCategory(category);
        ResultSet rs = DataBaseUtils.getResultSet(sSqlQuery);
        while (rs.next()) {
            String zip = rs.getString(1).trim();
            zipList.add(zip);
        }
        return zipList;
    }
    public List<String> getNameOfFootPrintForZip(String zip) throws Exception {
        List<String> nameList = new ArrayList<String>();
        String sSqlQuery = SQLQueries.getNameOfFootPrintForZip(zip);
        ResultSet rs = DataBaseUtils.getResultSet(sSqlQuery);
        while (rs.next()) {
            String name = rs.getString(1).trim();
            nameList.add(name);
        }
        return nameList;
    }
    public List<String> getAddressOfFootPrintForZip(String zip) throws Exception {
        List<String> addressList = new ArrayList<String>();
        String sSqlQuery = SQLQueries.getAddressOfFootPrintForZip(zip);
        ResultSet rs = DataBaseUtils.getResultSet(sSqlQuery);
        while (rs.next()) {
            String address = rs.getString(1).trim();
            addressList.add(address);
        }
        return addressList;

    }
    public List<String> getListOfAllCategories(String catagory) throws SQLException {
        List<String> zipList = new ArrayList<String>();
        String sSqlQuery = SQLQueries.getZipCodeForCategory(catagory);
        ResultSet rs = DataBaseUtils.getResultSet(sSqlQuery);
        while (rs.next()) {
            String zip = rs.getString(1).trim();
            zipList.add(zip);
        }
        return zipList;
    }
}
