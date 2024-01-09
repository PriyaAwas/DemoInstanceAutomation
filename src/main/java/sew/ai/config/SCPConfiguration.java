package sew.ai.config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import sew.ai.api.endpoints.auth.LoginEndpoint;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.Customer;
import sew.ai.models.Meter;
import sew.ai.models.RegistrationMailsConfig;
import sew.ai.models.User;
import sew.ai.models.UserAccount;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.RandomUtil;

public class SCPConfiguration {

    public static User user;
    public static UserAccount[] userAccounts;
    public static Meter[] meters;
    public static String loginToken;
    public static String jwtToken;
    public static Customer customer;
    public static ResultSet resultSet;
    String utilityAccountNumber, zipCode, usernameTaken, mobilePhone, drivingLicense,
    meterNo, streetAddress, ssnNo, fidTin, addressType,customerID;

    public static void initUserConfiguration() {
        user = new User(
                Configuration.toString("userName"),
                Configuration.toString("password")
        );
        initJwtToken();
        user.setJwtToken(jwtToken);
    }

    public static void initLoginToken() {
        LoginEndpoint loginEndpoint = new LoginEndpoint();
        loginToken = loginEndpoint.getLoginToken(user.getUserName(), user.getPassword());
        user.setLoginToken(loginToken);
    }

    public static void initJwtToken() {
        LoginEndpoint loginEndpoint = new LoginEndpoint();
        jwtToken = loginEndpoint.getJwtToken(user.getUserName(), user.getPassword());
        user.setJwtToken(jwtToken);
    }

    public static UserAccount initUserAccountConfiguration() {
        UserAccount userAcc = new UserAccount(
                Configuration.toString("userName")
        );
        userAccounts = userAcc.getUserAccounts();
        return new UserAccount(userAccounts);
    }

    public static Meter initMeterConfiguration(String accountNumber) {
        Meter meter = new Meter(accountNumber);
        meters = meter.getMeters();
        return new Meter(meters);
    }

    public static void setDefaultUtilityAccount() {
        try {
            String setDefaultAccount = SQLQueries.setDefaultAccount(
                    Configuration.toString("userName"),
                    user.getDefaultUtilityAccNum()
            );
            DataBaseUtils.updateSqlComm(setDefaultAccount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Customer initCustomerConfig(String customerName,String customerType) throws SQLException {
    	customer = ModelsConfiguration.readCustomerConfig().getCustomerByName(customerName);
    	
    	ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.getRegData(customerType,"3"));
		while (resultSet.next()) {
			
			customer.setUtilityAccountNumber(resultSet.getString("utilityaccountnumber"));
			customer.setZipCode(resultSet.getString("zipcode"));
			customer.setEmailAddress(customer.getEmailAddress()+RandomUtil.generateInteger()+"@mailinator.com");
			customer.setFirstName(customer.getFirstName()+RandomUtil.generateInteger());
			customer.setLastName(customer.getLastName()+RandomUtil.generateInteger());
			customer.setUserName(customer.getUserName()+RandomUtil.generateInteger()+"111144");
		 }
    	return customer;
    }



/**
     * Initialize CIS data before registration.
     *
     * @param accountNumber
     */
    public  static void initCisDataBeforeActivation(String accountNumber) {
        try {
        	String emailAddOld=null, phoneOld=null, isPaperlessOld = null;
            ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries
                    .getCISDataFromCustomerInfo(accountNumber));
            resultSet.next();
            emailAddOld = resultSet.getString("EmailID");
            phoneOld = resultSet.getString("MobilePhone");
            resultSet = DataBaseUtils.getResultSet(SQLQueries
                    .getCISDataFromAccountTable(accountNumber));
            resultSet.next();
            isPaperlessOld = resultSet.getString("Paperless");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String,RegistrationMailsConfig> getRegistrationMailsConfig(String emailId) {
        Map<String, RegistrationMailsConfig> contAccountNotify = new HashMap<>();
        RegistrationMailsConfig getRegMailsConfig;
        String query = SQLQueries.getRegistrationEmailContent(emailId);
        try {
            resultSet = DataBaseUtils.getResultSet(query);
            while (resultSet.next()) {
                getRegMailsConfig = new RegistrationMailsConfig(
				/*
				 * resultSet.getString("CustomerName"), resultSet.getString("EmailId"),
				 * resultSet.getString("Subject"), resultSet.getString("EmailMsg"),
				 * resultSet.getString("IsEmailSent")
				 */
                		 // resultSet.getString("CustomerName"),
                          resultSet.getString("EmailId"),
                          resultSet.getString("Subject"),
                          resultSet.getString("Message"),
                          resultSet.getString("IsNotify")
                );
                contAccountNotify.put(resultSet.getString("Subject"), getRegMailsConfig);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contAccountNotify;
    }
    
    public static Map<String, RegistrationMailsConfig> getRegistrationMailsConfig(String emailId,String emailSubject) {
        Map<String, RegistrationMailsConfig> contAccountNotify = new HashMap<>();
        RegistrationMailsConfig getRegMailsConfig;
        String query = SQLQueries.getRegistrationEmailContent(emailId,emailSubject);
        try {
            resultSet = DataBaseUtils.getResultSet(query);
            while (resultSet.next()) {
                getRegMailsConfig = new  RegistrationMailsConfig(
				/*
				 * resultSet.getString("CustomerName"), resultSet.getString("EmailId"),
				 * resultSet.getString("Subject"), resultSet.getString("EmailMsg"),
				 * resultSet.getString("IsEmailSent")
				 */
                		 // resultSet.getString("CustomerName"),
                          resultSet.getString("EmailId"),
                          resultSet.getString("Subject"),
                          resultSet.getString("Message"),
                          resultSet.getString("IsNotify")
                );
                contAccountNotify.put(resultSet.getString("Subject"), getRegMailsConfig);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contAccountNotify;
    }
    
}
