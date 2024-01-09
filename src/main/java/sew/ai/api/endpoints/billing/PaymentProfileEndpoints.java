package sew.ai.api.endpoints.billing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import sew.ai.api.enums.HttpVerbs;
import sew.ai.api.enums.Routes;
import sew.ai.api.pojos.billing.applepay.ApplePayPayload;
import sew.ai.api.pojos.billing.applepay.AppleTokenGraphQLQuery;
import sew.ai.api.pojos.billing.gpay.GPayWalletPayload;
import sew.ai.api.pojos.billing.paypal.PayPalBankPayload;
import sew.ai.api.pojos.billing.paypal.PayPalCardPayload;
import sew.ai.api.pojos.billing.paypal.PayPalWalletPayload;
import sew.ai.api.pojos.billing.venmo.VenmoPayload;
import sew.ai.api.utils.RestUtils;
import sew.ai.helpers.props.ResourcePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.*;
import sew.ai.utils.DataBaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class PaymentProfileEndpoints extends RestUtils {
    private static final Logger log = LogManager.getLogger(PaymentProfileEndpoints.class);
    public static String ADD_PAYPAL_CARD = "AddPayPalCardToProfile";
    public static String ADD_PAYPAL_BANK = "AddPayPalBankToProfile";
    public static String DELETE_PAYPAL_PROFILE = "DeletePayPalProfile";
    public static String ADD_WALLET = "AddWalletToProfile";
    public static String CREATE_APPLE_TOKEN = "CreateAppleToken";

    public void buildRequestSpecAddPaypalCardProfile(String userName, Card card, String resourcePathURI) {
        log.info("Initializing the add PayPal card API payload.");
        PayPalCardPayload payPalCardPayload = new PayPalCardPayload(userName, card);
        log.info("Initializing the add PayPal card API request specification.");
        reqSpec = RestUtils.requestSpecification(
                resourcePathURI,
                payPalCardPayload
        );
    }

    public void hitAddPayPalCardProfile() {
        String endPoint;
        Routes routes = Routes.valueOf(ADD_PAYPAL_CARD);
        endPoint = routes.getResource();
        log.info("Hit the add paypal card API with given request specification: " + reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
                HttpVerbs.POST.name());
        response = RestUtils.getResponse(
                reqSpec,
                HttpVerbs.POST.name(),
                endPoint
        );
        // Print the response
        response.prettyPrint();
        // Change the response to json path.
        jsonPath = RestUtils.rawToJson(response);
    }

    public Map<String, Object> getSuccessAddPayPalProfileResponse() {
        Map<String, Object> addPayPalProfileResponse;
        addPayPalProfileResponse = jsonPath.getMap("content");
        return addPayPalProfileResponse;
    }

    public void buildRequestSpecAddPaypalBankProfile(String userName, Bank bank, String resourcePathURI) {
        log.info("Initializing the add PayPal bank API payload.");
        PayPalBankPayload payPalBankPayload = new PayPalBankPayload(userName, bank);
        log.info("Initializing the add PayPal bank API request specification.");
        reqSpec = RestUtils.requestSpecification(
                resourcePathURI,
                payPalBankPayload
        );
    }

    public void hitAddPayPalBankProfile() {
        String endPoint;
        Routes routes = Routes.valueOf(ADD_PAYPAL_BANK);
        endPoint = routes.getResource();
        log.info("Hit the add paypal bank API with given request specification: " + reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
                HttpVerbs.POST.name());
        response = RestUtils.getResponse(
                reqSpec,
                HttpVerbs.POST.name(),
                endPoint
        );
        // Print the response
        response.prettyPrint();
        // Change the response to json path.
        jsonPath = RestUtils.rawToJson(response);
    }

    public int getAPIResponseCode() {
        return response.getStatusCode();
    }

    public String getAPIResponseMessage() {
        return jsonPath.getString("Result").trim();
    }

    public Map<String, String> getErrorDetailsForDuplicateCardProfiles() {
        Map<String, String> errorDetailsMap;
        errorDetailsMap = jsonPath.getMap("Error");
        return errorDetailsMap;
    }

    public void buildReqSpecForDeletePaymentProfile(String userId, String customerRefNum, String resourcePathURI) {
        // Initializing the path parameters map.
        log.info("Initializing the delete PayPal card path parameters.");
        TreeMap<String, Object> pathParams = new TreeMap<>();
        pathParams.put("customerRefNum", customerRefNum);
        pathParams.put("userId", userId);
        // Building request specification
        log.info("Initializing the delete PayPal card API request specification.");
        reqSpec = RestUtils.requestSpecification(
                pathParams,
                resourcePathURI
        );
    }

    public void hitDeletePaymentProfileAPI() {
        String endPoint;
        Routes routes = Routes.valueOf(DELETE_PAYPAL_PROFILE);
        endPoint = routes.getResource();
        log.info("Hit the add paypal card API with given request specification: " + reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
                HttpVerbs.DELETE.name());
        response = RestUtils.getResponse(
                reqSpec,
                HttpVerbs.DELETE.name(),
                endPoint
        );
        // Print the response
        response.prettyPrint();
        // Change the response to json path.
        jsonPath = RestUtils.rawToJson(response);
    }

    public Map<String, Object> getDeletePayPalProfileResponse() {
        Map<String, Object> deleteProfileResponse = new HashMap<>();
        deleteProfileResponse = jsonPath.getMap("content");
        return deleteProfileResponse;
    }

    public void deleteAllPaymentProfiles(User user) {
        ResultSet resultSet;
        String userId = user.getUserId();
        resultSet = DataBaseUtils.getResultSetPayPalDB(SQLQueries.getAllPaymentProfileDetails(userId));
        try {
            while (resultSet.next()) {
                // Build request specifications
                buildReqSpecForDeletePaymentProfile(
                        userId,
                        resultSet.getString("customerrefnum"),
                        ResourcePaths.PAYPAL_PATH_URI
                );
                // Hitting delete payment profile API
                hitDeletePaymentProfileAPI();
                // Verifying delete payment profile.
                Assert.assertEquals(getAPIResponseCode(), 200);
                Assert.assertEquals(getAPIResponseMessage(), "Successful");
                // Verifying the exact response message
                Assert.assertEquals(getDeletePayPalProfileResponse().get("responseText"),
                        "Profile Deleted Successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void buildRequestSpecAddPaypalWalletProfile(String userName, PayPalWallet payPalWallet,
                                                       String resourcePathURI) {
        log.info("Initializing the add PayPal card API payload.");
        PayPalWalletPayload payPalWalletPayload = new PayPalWalletPayload(userName, payPalWallet);
        log.info("Initializing the add PayPal card API request specification.");
        reqSpec = RestUtils.requestSpecification(
                resourcePathURI,
                payPalWalletPayload
        );
    }

    public void hitAddPayPalWalletProfile() {
        String endPoint;
        Routes routes = Routes.valueOf(ADD_WALLET);
        endPoint = routes.getResource();
        log.info("Hit the add paypal wallet API with given request specification: " + reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
                HttpVerbs.POST.name());
        response = RestUtils.getResponse(
                reqSpec,
                HttpVerbs.POST.name(),
                endPoint
        );
        // Print the response
        response.prettyPrint();
        // Change the response to json path.
        jsonPath = RestUtils.rawToJson(response);
    }

    public String getPaymentTokenGuid() {
        String paymentTokenGuid = null;
        paymentTokenGuid = jsonPath.getString("content.paymentTokenGuid");
        return paymentTokenGuid;
    }

    public String getEmailLinkedToWallet() {
        String walletEmail = null;
        walletEmail = jsonPath.getString("content.walletEmail");
        return walletEmail;
    }

    public void buildRequestSpecAddGPayWalletProfile(String userName, GPayWallet gPayWallet,
                                                     String resourcePathURI) {
        log.info("Initializing the add PayPal card API payload.");
        GPayWalletPayload gPayWalletPayload = new GPayWalletPayload(userName, gPayWallet);
        log.info("Initializing the add PayPal card API request specification.");
        reqSpec = RestUtils.requestSpecification(
                resourcePathURI,
                gPayWalletPayload
        );
    }

    public void hitAddGPayWalletProfile() {
        String endPoint;
        Routes routes = Routes.valueOf(ADD_WALLET);
        endPoint = routes.getResource();
        log.info("Hit the add gpay wallet API with given request specification: " + reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
                HttpVerbs.POST.name());
        response = RestUtils.getResponse(
                reqSpec,
                HttpVerbs.POST.name(),
                endPoint
        );
        // Print the response
        response.prettyPrint();
        // Change the response to json path.
        jsonPath = RestUtils.rawToJson(response);
    }

    public void buildRequestSpecAddVenmoWalletProfile(String userName, VenmoWallet venmoWallet,
                                                      String resourcePathURI) {
        log.info("Initializing the add PayPal card API payload.");
        VenmoPayload venmoWalletPayload = new VenmoPayload(userName, venmoWallet);
        log.info("Initializing the add PayPal card API request specification.");
        reqSpec = RestUtils.requestSpecification(
                resourcePathURI,
                venmoWalletPayload
        );
    }

    public void hitAddVenmoWalletProfile() {
        String endPoint;
        Routes routes = Routes.valueOf(ADD_WALLET);
        endPoint = routes.getResource();
        log.info("Hit the add Venmo wallet API with given request specification: " + reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
                HttpVerbs.POST.name());
        response = RestUtils.getResponse(
                reqSpec,
                HttpVerbs.POST.name(),
                endPoint
        );
        // Print the response
        response.prettyPrint();
        // Change the response to json path.
        jsonPath = RestUtils.rawToJson(response);
    }

    public void buildRequestSpecCreateAppleToken(AppleTokenGraphQLQuery appleTokenGraphQLQuery) {
        log.info("Creating headers for getting the apple token.");
        String authToken = "sandbox_ktkp4zdh_gmqpnqkcyh27vqd7";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + authToken);
        headers.put("Braintree-Version", "2019-01-01");
        log.info("Initializing the add PayPal card API request specification.");
        reqSpec = RestUtils.requestSpecification(
                "graphql",
                headers,
                appleTokenGraphQLQuery
        );
    }

    public void hitCreateAppleToken() {
        String endPoint;
        Routes routes = Routes.valueOf(CREATE_APPLE_TOKEN);
        endPoint = routes.getResource();
        log.info("Hit the create Apple token API with given request specification: " + reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
                HttpVerbs.POST.name());
        response = RestUtils.getResponse(
                reqSpec,
                HttpVerbs.POST.name(),
                endPoint
        );
        // Print the response
        response.prettyPrint();
        // Change the response to json path.
        jsonPath = RestUtils.rawToJson(response);
    }

    public String getAppleNonce() {
        String appleNonce = null;
        appleNonce = jsonPath.getString("data.tokenizeNetworkToken.paymentMethod.id");
        return appleNonce;
    }

    public String getUsageString() {
        String usage = null;
        usage = jsonPath.getString("data.tokenizeNetworkToken.paymentMethod.usage");
        return usage;
    }

    public void buildRequestSpecAddAppleWalletProfile(String userName, AppleWallet appleWallet,
                                                      String resourcePathURI) {
        log.info("Initializing the add PayPal card API payload.");
        ApplePayPayload appleWalletPayload = new ApplePayPayload(userName, appleWallet);
        log.info("Initializing the add PayPal card API request specification.");
        reqSpec = RestUtils.requestSpecification(
                resourcePathURI,
                appleWalletPayload
        );
    }

    public void hitAddAppleWalletProfile() {
        String endPoint;
        Routes routes = Routes.valueOf(ADD_WALLET);
        endPoint = routes.getResource();
        log.info("Hit the add Apple wallet API with given request specification: " + reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
                HttpVerbs.POST.name());
        response = RestUtils.getResponse(
                reqSpec,
                HttpVerbs.POST.name(),
                endPoint
        );
        // Print the response
        response.prettyPrint();
        // Change the response to json path.
        jsonPath = RestUtils.rawToJson(response);
    }

    public String getTokenForPayPalCard(User user, Card card) {
        String token;
        // Delete already added payment profiles first
        log.info("Deleting all the existing payment profiles.");
        deleteAllPaymentProfiles(user);
        // Creating request specification for adding payment profile.
        log.info("Building the add paypal card profile request specification with given card object.: " + card);
        // Build request specification for add PayPal card profile
        buildRequestSpecAddPaypalCardProfile(
                user.getUserName(),
                card,
                ResourcePaths.PAYPAL_PATH_URI
        );
        // Hit add PayPal card profile API.
        log.info("Hitting the add PayPal card profile API.");
        hitAddPayPalCardProfile();
        // Verify the status code
        log.info("Getting the API response code: " + getAPIResponseCode());
        Assert.assertEquals(getAPIResponseCode(), 200);
        // Verify the actual add card message
        Assert.assertEquals(getSuccessAddPayPalProfileResponse().get("responseText"),
                "APPROVED");
        // Init customer reference number for the added PayPal profile
        token = (String) getSuccessAddPayPalProfileResponse().get("customerRefNum");
        log.info("Paypal add card profile approved successfully with customer reference number: " + token);
        return token;
    }

    public String getTokenForPayPalBank(User user, Bank bank) {
        String token;
        // Delete already added payment profiles first
        log.info("Deleting all the existing payment profiles.");
        deleteAllPaymentProfiles(user);
        // Build request specification for add PayPal card profile
        buildRequestSpecAddPaypalBankProfile(
                user.getUserName(),
                bank,
                ResourcePaths.PAYPAL_PATH_URI
        );
        // Hit add PayPal bank profile API.
        log.info("Hitting the add PayPal bank profile API.");
        hitAddPayPalBankProfile();
        // Verify the status code
        log.info("Getting the API response code: " + getAPIResponseCode());
        Assert.assertEquals(getAPIResponseCode(), 200);
        // Verify the actual add bank success message
        Assert.assertEquals(getSuccessAddPayPalProfileResponse().get("responseText"),
                "APPROVED");
        // Init customer reference number for the added PayPal profile
        token = (String) getSuccessAddPayPalProfileResponse().get("customerRefNum");
        log.info("Paypal add card profile approved successfully with customer reference number: " + token);
        return token;
    }

    public String getTokenForPayPalWallet(User user, PayPalWallet payPalWallet) {
        String token;
        // Delete already added payment profiles first
        log.info("Deleting all the existing payment profiles.");
        deleteAllPaymentProfiles(user);
        // Build request specification for add PayPal card profile
        buildRequestSpecAddPaypalWalletProfile(
                user.getUserName(),
                payPalWallet,
                ResourcePaths.PAYPAL_PATH_URI
        );
        // Hit add PayPal bank profile API.
        log.info("Hitting the add PayPal bank profile API.");
        hitAddPayPalWalletProfile();
        // Verify the status code
        log.info("Getting the API response code: " + getAPIResponseCode());
        Assert.assertEquals(getAPIResponseCode(), 200);
        // Verify the actual add bank success message
        Assert.assertEquals(getSuccessAddPayPalProfileResponse().get("responseText"),
                "APPROVED");
        // Init customer reference number for the added PayPal profile
        token = (String) getSuccessAddPayPalProfileResponse().get("customerRefNum");
        log.info("Paypal add card profile approved successfully with customer reference number: " + token);
        return token;
    }

    public String getTokenForVenmoWallet(User user, VenmoWallet venmoWallet) {
        String token;
        // Delete already added payment profiles first
        log.info("Deleting all the existing payment profiles.");
        deleteAllPaymentProfiles(user);
        // Build request specification for add PayPal card profile
        buildRequestSpecAddVenmoWalletProfile(
                user.getUserName(),
                venmoWallet,
                ResourcePaths.PAYPAL_PATH_URI
        );
        // Hit add PayPal bank profile API.
        log.info("Hitting the add PayPal bank profile API.");
        hitAddVenmoWalletProfile();
        // Verify the status code
        log.info("Getting the API response code: " + getAPIResponseCode());
        Assert.assertEquals(getAPIResponseCode(), 200);
        // Verify the actual add bank success message
        Assert.assertEquals(getSuccessAddPayPalProfileResponse().get("responseText"),
                "APPROVED");
        // Init customer reference number for the added PayPal profile
        token = (String) getSuccessAddPayPalProfileResponse().get("customerRefNum");
        log.info("Paypal add card profile approved successfully with customer reference number: " + token);
        return token;
    }

    public String getTokenForAppleWallet(User user, AppleWallet appleWallet) {
        String token;
        // Delete already added payment profiles first
        log.info("Deleting all the existing payment profiles.");
        deleteAllPaymentProfiles(user);
        // Build request specification for add PayPal card profile
        buildRequestSpecAddAppleWalletProfile(
                user.getUserName(),
                appleWallet,
                ResourcePaths.PAYPAL_PATH_URI
        );
        // Hit add PayPal bank profile API.
        log.info("Hitting the add ApplePay wallet profile API.");
        hitAddAppleWalletProfile();
        // Verify the status code
        log.info("Getting the API response code: " + getAPIResponseCode());
        Assert.assertEquals(getAPIResponseCode(), 200);
        // Verify the actual add bank success message
        Assert.assertEquals(getSuccessAddPayPalProfileResponse().get("responseText"),
                "APPROVED");
        // Init customer reference number for the added PayPal profile
        token = (String) getSuccessAddPayPalProfileResponse().get("customerRefNum");
        log.info("Paypal add card profile approved successfully with customer reference number: " + token);
        return token;
    }
}
