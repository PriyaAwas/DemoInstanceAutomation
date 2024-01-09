package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FootPrintPage extends HomePage {

    private static final Logger log = LogManager.getLogger(FootPrintPage.class);

    public FootPrintPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public boolean isFootPrintPage(String url, String title){
        boolean isFootPrintPage=false;
        log.info("Checking the current page is Foot Print page.");
        if(getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().contains(title.toLowerCase()))
            isFootPrintPage=true;
        log.info("The Current page is Foot Page Status:"+ isFootPrintPage);
        return isFootPrintPage;
    }
    @FindBy(css = ".MuiGrid-root #PageTitle")
    private WebElement lblFootprintPageHeading;
    public boolean isFootprintPageHeadingVisible(){
        log.info("Featching the Visibility of Foot print Page Heading :"+lblFootprintPageHeading.isDisplayed());
        return isElementVisible(lblFootprintPageHeading);
    }
    public String getFootPrintPageHeadingLabel(){
        log.info("Featching the label of Foot Print Header Label:");
        String label=getText(lblFootprintPageHeading);
        log.info("Foot Print Header Label is :"+label);
        return label;
    }
    @FindBy(css = "#GIStxtGoogleSearch")
    private WebElement txtBoxSearch;
    public void clickBoxSearch(){
        click(txtBoxSearch);
    }
    public void clearBoxSearch(){
        clear(txtBoxSearch);
    }
    public String getBoxSearchAttribute(){
        log.info("Fetching the Attribute in Google Search Box....");
        String attribute=getAttribute(txtBoxSearch,"validatemessage");
        log.info("Attribute in Google Search Box is"+attribute);
        return attribute;
    }
    public void populateSearchBox(String searchname){
        sendKeys(txtBoxSearch,searchname);
        log.info("Search by text box is successfully populated.");
    }
    public boolean isSearchBoxVisible(){
        log.info("Featching the Visibility of Search Box:"+txtBoxSearch.isDisplayed());
        return isElementVisible(txtBoxSearch);
    }

    @FindBy(css = "a.GISsearch_ico")
    private WebElement btnSearch;
    public String getBtnSearchAttribute(){
        log.info("Fetching the Attribute in Button Search link....");
        String attribute=getAttribute(btnSearch,"title");
        log.info("Attribute in Button Search Box is"+attribute);
        return attribute;
    }
    public void clickSearchButton(){
        log.info("process to click search button on Search by CityName/ZipCode.");
        click(btnSearch);
    }
    public boolean isSearchButtonVisible(){
        log.info("Featching the Visibility of Search Button:"+btnSearch.isDisplayed());
        return isElementVisible(btnSearch);
    }


    @FindBy(css = "button[Title='Show street map']")
    private WebElement lnkMap;
    public boolean isLinkMapVisible(){
        log.info("Featching the Visibility of Link Map:"+lnkMap.isDisplayed());
        return isElementVisible(lnkMap);
    }
    public String getLnkMapLabel(){
        String label=getText(lnkMap);
        return label;
    }

    @FindBy(css = "[id='Text']")
    private WebElement lnkListView;
    public String getListViewAttribute(){
        log.info("Fetching the Attribute in List View..");
        String attribute=getAttribute(lnkListView,"title");
        log.info("Attribute in List View is"+attribute);
        return attribute;
    }
    public void clickListView(){
        log.info("process to click List View .");
        click(lnkListView);
    }
    public boolean isListViewVisible(){
        log.info("Featching the Visibility of List View:"+lnkListView.isDisplayed());
        //waitForElementToBeVisible(lnkListView);
        return isElementVisible(lnkListView);
    }

    @FindBy(css="#Map .material-icons")
    private WebElement lnkListViewMap;
    public void clickListViewMap(){
        log.info("process to click List View Map .");
        click(lnkListViewMap);
    }
    public boolean isListViewMapVisible(){
        log.info("Featching the Visibility of List View Map:"+lnkListViewMap.isDisplayed());
        waitForElementToBeVisible(lnkListViewMap);
        return isElementVisible(lnkListViewMap);
    }

    //#ContentPlaceHolder1_Span1
    //Replace Locator .crnt_loca by .crnt_loca span
    @FindBy(css = ".crnt_loca span")
    private WebElement lnkCurrentLocation;
    public String getCurrentLocationlnkAttribute(){
        log.info("Fetching the Attribute of Current Location..");
        String attribute=getAttribute(lnkCurrentLocation,"alt");
        log.info("Attribute of Current Location is"+attribute);
        return attribute;
    }
    public void clickCurrentLocationlnk(){
        log.info("process to click Current Location link .");
        click(lnkCurrentLocation);
    }
    public boolean isCurrentLocationlnkVisible(){
        log.info("Featching the Visibility of Current Location:"+lnkCurrentLocation.isDisplayed());
        return isElementVisible(lnkCurrentLocation);
    }
    @FindBy(css = "[id='selFootPrint'] option")
    private WebElement lstFootPrintCategories;
    @FindBy(css = "select#selFootPrint")
    private WebElement dropdownCategory;
    public List<String> getAllSelectDropDownOptionsInListBox(){
        List<String> options=getAllSelectOptionsInListBox(dropdownCategory);
        return options;
    }
    public void selectDropDownByVisibleText(String text){
        selectByVisibleText(dropdownCategory,text);
    }
    public String getdropdownAttribute(){
        log.info("Fetching the Attribute of dropdown..");
        String attribute=getAttribute(dropdownCategory,"title");
        log.info("Attribute of dropdown is"+attribute);
        return attribute;
    }
    public void clickdropdown(){
        log.info("process to click drop down.");
        click(dropdownCategory);
    }
    public boolean isdropdownVisible(){
        log.info("Featching the Visibility of dropdown:"+dropdownCategory.isDisplayed());
        waitForElementToBeVisible(dropdownCategory);
        return isElementVisible(dropdownCategory);
    }
    public List<String> getAllDropDownOptionsInListBox(){
        List<String> optionList=getAllOptionsInListBox(dropdownCategory);
        return optionList;
    }

    @Override
    public List<String> getAllSelectOptionsInListBox(WebElement element) {
        return super.getAllSelectOptionsInListBox(element);
    }

    @FindBy(css = "#a[id=btnRef]")
    private WebElement btnRefresh;
    public void clickRefreshbtn(){
        log.info("process to click Refresh Button.");
        click(btnRefresh);
    }
    public boolean isRefreshbtnVisible(){
        log.info("Featching the Visibility of Refresh Button:"+btnRefresh.isDisplayed());
        return isElementVisible(btnRefresh);
    }
    @FindBy(css = "#btnRef span")
    private WebElement btnRefresh1;
    public String getRefershBtnLabel(){
        log.info("Fetching the label of Refresh button.");
        String label=getText(btnRefresh1);
        return label;
    }


    @FindBy(css = "button[Title = 'Show street map']")
    private WebElement btnStreetMap;
    public String getStreetMapBtnAttribute(){
        log.info("Fetching the Attribute of Street Map button..");
        String attribute=getAttribute(btnStreetMap,"aria-label");
        log.info("Attribute of Street Map button is"+attribute);
        return attribute;
    }
    public void clickStreetMapbtn(){
        log.info("process to click Street Map button  .");
        click(btnStreetMap);
    }
    public boolean isStreetMapBtnVisible(){
        log.info("Featching the Visibility of StreetMap btn"+btnStreetMap.isDisplayed());
        return isElementVisible(btnStreetMap);
    }
    public String getStreetMapLabel(){
        String label= getText(btnStreetMap);
        return label;
    }

    @FindBy(css = "button[Title = 'Show satellite imagery']")
    private WebElement btnSatelliteMap;
    public String getSatelliteMapBtnAttribute(){
        log.info("Fetching the Attribute of Satellite Map  button..");
        String attribute=getAttribute(btnSatelliteMap,"aria-label");
        log.info("Attribute of SatelliteMap Btn is"+attribute);
        return attribute;
    }
    public void clickSatelliteMapBtn(){
        log.info("process to click Satellite Map Btn  .");
        click(btnSatelliteMap);
    }
    public boolean isSatelliteMapBtnVisible(){
        log.info("Featching the Visibility of Satellite Map Btn"+btnSatelliteMap.isDisplayed());
        return isElementVisible(btnSatelliteMap);
    }
    public String getSatelliteMapBtnLabel(){
        String label= getText(btnSatelliteMap);
        return label;
    }
    @FindBy(css = "button[Title = 'Zoom in']")
    private WebElement btnZoomIn;
    public String getZoomInBtnAttribute(){
        log.info("Fetching the Attribute of ZoomIn  button..");
        String attribute=getAttribute(btnZoomIn,"title");
        log.info("Attribute of ZoomIn Btn is"+attribute);
        return attribute;
    }
    public void clickZoomInBtn(){
        log.info("process to click ZoomIn Btn  .");
        click(btnZoomIn);
    }
    public boolean isZoomInBtnVisible(){
        log.info("Featching the Visibility of ZoomIn Btn"+btnZoomIn.isDisplayed());
        return isElementVisible(btnZoomIn);
    }
    @FindBy(css = "button[Title = 'Zoom out']")
    private WebElement btnZoomOut;
    public String getZoomOutBtnAttribute(){
        log.info("Fetching the Attribute of ZoomOut  button..");
        String attribute=getAttribute(btnZoomOut,"title");
        log.info("Attribute of ZoomOut Btn is"+attribute);
        return attribute;
    }
    public void clickZoomOutBtn(){
        log.info("process to click ZoomOut Btn  .");
        click(btnZoomOut);
    }
    public boolean isZoomOutBtnVisible(){
        log.info("Featching the Visibility of ZoomOut Btn"+btnZoomOut.isDisplayed());
        return isElementVisible(btnZoomOut);
    }
    @FindBy(css = "button[Title='Toggle fullscreen view']")
    private WebElement btnFullScreen;
    public String getFullScreenBtnAttribute(){
        log.info("Fetching the Attribute of FullScreen  button..");
        String attribute=getAttribute(btnFullScreen,"aria-label");
        log.info("Attribute of FullScreen Btn is"+attribute);
        return attribute;
    }
    public void clickFullScreenBtn(){
        log.info("process to click FullScreen Btn  .");
        click(btnFullScreen);
    }
    public boolean isFullScreenBtnVisible(){
        log.info("Featching the Visibility of FullScreen Btn"+btnFullScreen.isDisplayed());
        return isElementVisible(btnFullScreen);
    }

    @FindBy(css = "div.gm-svpc")
    private WebElement btnPegman;
    public String getPegmanBtnAttribute(){
        log.info("Fetching the Attribute of Pegman  button..");
        String attribute=getAttribute(btnPegman,"title");
        log.info("Attribute of Pegman Btn is"+attribute);
        return attribute;
    }
    public void clickPegmanBtn(){
        log.info("process to click Pegman Btn  .");
        click(btnPegman);
    }
    public boolean isPegmanBtnVisible(){
        log.info("Featching the Visibility of Pegman Btn"+btnPegman.isDisplayed());
        return isElementVisible(btnPegman);
    }
    @FindBys(@FindBy(css="div[role='button'] img"))
    private List<WebElement> fpOnMap;
    public List<WebElement> getFpOnMapElement(){
        return fpOnMap;
    }
    @FindBy(css="iw-container")
    private WebElement footPrintTypeDetailsOnMap;
    public String getfootPrintTypeDetailsOnMapLabel(){
       String label= getText(footPrintTypeDetailsOnMap);
       return label;
    }
    @FindBy(css=".iw-title")
    private WebElement footprintNameDetailsOnMap;
    public String getfootprintNameDetailsOnMapLabel(){
        String label= getText(footprintNameDetailsOnMap);
        return label;
    }
    @FindBy(css = ".iw-content")
    private WebElement footprintDetailsOnMap;
    public String getfootprintDetailsOnMapLabel(){
        String label= getText(footprintDetailsOnMap);
        return label;
    }
    public void waitForFootPrintDetailsOnMap(){
        waitForElementToBeVisible(footprintDetailsOnMap);
        log.info("Foot Print Details on Map elemtment is successfully visible");
    }
    @Override
    public void navigateToUrl(String activationUrl) {
        super.navigateToUrl(activationUrl);
    }

    @FindBys(@FindBy(css = "[role='row'] th"))
    private List<WebElement> lstListViewFootPrintColumns;
    public List<WebElement> getlstListViewFootPrintColumns(){return lstListViewFootPrintColumns;}

    @FindBys(@FindBy (css="#tblFootPrint [data-toggle='collapse']"))
    private List<WebElement> listFootPrint;
    public List<WebElement> getListFootPrint(){return listFootPrint;}

   @FindBys(@FindBy(css="span .paginate_button"))
   private List<WebElement> paginateBtn;
   public List<WebElement> getPaginateBtnList(){
       log.info("Return no of page on map foot print view");
       return paginateBtn;
   }
    public void clickPaginatePageNoBtn() {
        click(btnNext);
        log.info("Page no Button from paginate list clicked successfully {}.");
    }

    @FindBy(css = ".paginate_button.next")
    private WebElement btnNext;
    public void clickPaginateNextBtn() {
        click(btnNext);
        log.info("Paginate Next Button clicked successfully {}.");
    }
    @FindBy(css = ".paginate_button.previous")
    private WebElement btnPrev;
    public void clickPaginatePrevBtn() {
        click(btnNext);
        log.info("Paginate Previous Button clicked successfully {}.");
    }
    @FindBy(css = "#pagerjqxgrid>div>div:nth-of-type(3)")
    private WebElement lblItemCountPerPage;
    @FindBy(css = "#pagerjqxgrid>div>div:nth-of-type(4)")
    private WebElement btnRowNumbers;
    @FindBy(css = ".dataTables_info")
    private WebElement lblShowRows;
    @FindBy(css = "#pagerjqxgrid>div>div:nth-of-type(6)")
    private WebElement lblPageNum;
    @FindBy(css = "#pagerjqxgrid>div>div:nth-of-type(7)")
    private WebElement lblGoForPage;


    @FindBy (xpath="//img[contains(@src, 'images/Footprint')])[2]")
    private WebElement btnFirstFootprint;
    @FindBy(css = ".gm-style-iw.gm-style-iw-c>button")
    private WebElement btnCloseFootprint;



    @FindBy(css = "[id='footprint_map_canvas_graphics_layer'] image")
    private WebElement lstFootPrintCategoriesMapImage;
    @FindBy(css = "img[src='images/Footprint/pev_green.png']")
    private WebElement imgFootprintOnMap;
    @FindBy(css = ".gis-footprint-area li:nth-child(2) img")
    private WebElement lnkCatergoryImage;


    @FindBy(css = "#contenttablejqxgrid>div>div:first-child>div")
    private WebElement lblLocationTypeColumns;
    @FindBy(css = "#tblFootPrint tbody tr td[id*='tbltdBillingHistoryAmount']")
    private WebElement lblNameColumns;
    @FindBy(css = "#contenttablejqxgrid>div>div:nth-of-type(3)>div")
    private WebElement lblAddressColumns;
    @FindBy(css = "#contenttablejqxgrid>div>div:nth-of-type(4)>div")
    private WebElement lblCityColumns;
    @FindBy (css=".toast-message")
    private WebElement toastMessage;
    public String getToastMessageLabel(){
        String label=getText(toastMessage);
        return label;
    }


}
