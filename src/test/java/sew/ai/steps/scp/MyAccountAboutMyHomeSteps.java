package sew.ai.steps.scp;

import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import sew.ai.config.SCPConfiguration;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.User;
import sew.ai.pageObjects.scp.MyAccountAboutMyHomePage;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.PropertiesUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MyAccountAboutMyHomeSteps extends MyAccountAboutMyHomePage {
    public static PropertiesUtil myaccountAboutMyHomeTextProp;

    public MyAccountAboutMyHomeSteps(WebDriver driver) {
        super(driver);
        myaccountAboutMyHomeTextProp = new PropertiesUtil(
                FilePaths.SCP_TEXT_PROPERTIES + Constants.MYACCOUNT_ABOUTMYHOME_TXT_FILENAME);
    }
    public void verifyAboutMyHomePageNavigation(SoftAssert softAssert) throws SQLException {
        //ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.getUserNameTempalateDetail("95"));
        //resultSet.next();
        //boolean userNameStatus = resultSet.getBoolean("value");
        //if (userNameStatus == true) {
            //Login into The Application and Navigate to My Profile Page
            LoginSteps loginSteps = new LoginSteps(driver);
            User user = SCPConfiguration.user;
            DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
            HomeSteps homeSteps = new HomeSteps(driver);
            homeSteps.navigateToAboutMyHomePage();

            ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getResidentialOwnerAccounts(user.getUserId()));
            if (rs.next()) {
                String resAcc = rs.getString("UtilityAccountNumber");
                // System.out.println("Residential====> "+resAcc);
                clickSelectAddressDropdownArrowBtn();
                List<WebElement> ls = getServiceAddDropdownList();
                for (WebElement el : ls) {
                    String addr =getServiceAddDropdownLabel(el);
                    // System.out.println("---->"+addr);
                    if (addr.contains(resAcc)) {
                        clickServiceAddDropdown(el);
                        pause(3000);
                        Assert.assertEquals(getAboutMyHomeLabel().toLowerCase(),myaccountAboutMyHomeTextProp.getPropValue("expectedAboutMyHomePageTitle").toLowerCase(),
                                "Abouy My Home Header is not matching");
                        Assert.assertTrue(isMyAccountAboutMyHomePage(myaccountAboutMyHomeTextProp.getPropValue("expectedAboutMyHomePageUrl"),
                                myaccountAboutMyHomeTextProp.getPropValue("expectedAboutMyHomePageTitle")));
                        break;
                    }
                }
            }

            ResultSet rs1 = DataBaseUtils.getResultSet(SQLQueries.getCommercialOwnerAccounts(user.getUserId()));
            if (rs1.next()) {
                String comAcc = rs1.getString("UtilityAccountNumber");
                // System.out.println("Commercial====> "+comAcc);
                clickSelectAddressDropdownArrowBtn();
                // pageUtil.click(myAccountProfilePage.getBtnSelectAddressDropdownArrowApp());
                List<WebElement> ls = getServiceAddDropdownList();
                for (WebElement el : ls) {
                    String addr = getServiceAddDropdownLabel(el);
                    // System.out.println("---->"+addr);
                    if (addr.contains(comAcc)) {
                        clickServiceAddDropdown(el);
                        pause(3000);
                        Assert.assertEquals(getAboutMyHomeLabel().toLowerCase(),myaccountAboutMyHomeTextProp.getPropValue("txtLblAboutMyBusinessPageHeadingAos").toLowerCase(),
                                "Abouy My Home Business Header is not matching");
                        Assert.assertTrue(isMyAccountAboutMyHomePage(myaccountAboutMyHomeTextProp.getPropValue("expectedAboutMyHomePageUrl"),
                                myaccountAboutMyHomeTextProp.getPropValue("expectedAboutMyHomePageTitle")));
                        break;
                    }

                }
            }

        }
    }
//}
