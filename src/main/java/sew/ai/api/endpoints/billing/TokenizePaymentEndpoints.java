package sew.ai.api.endpoints.billing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sew.ai.api.enums.HttpVerbs;
import sew.ai.api.enums.Routes;
import sew.ai.api.pojos.billing.applepay.TokenizePaymentApplePay;
import sew.ai.api.pojos.billing.paypal.TokenizePaymentBank;
import sew.ai.api.pojos.billing.paypal.TokenizePaymentCard;
import sew.ai.api.pojos.billing.paypal.TokenizePaymentWallet;
import sew.ai.api.pojos.billing.venmo.TokenizePaymentVenmo;
import sew.ai.api.utils.RestUtils;
import sew.ai.config.Configuration;
import sew.ai.models.*;

import java.util.Map;

public class TokenizePaymentEndpoints extends RestUtils {
    private static final Logger log = LogManager.getLogger(TokenizePaymentEndpoints.class);
    private static final String TOKENIZE_PAYMENT = "TokenizePayment";

    public void buildRequestSpecTokenizePaymentWithCard(Card card, TokenizePayment tokenizePayment, String resourcePathURI) {
        log.info("Initializing the add PayPal card API payload.");
        TokenizePaymentCard tokenizePaymentCard = new TokenizePaymentCard(
                Configuration.toString("userName"),
                card,
                tokenizePayment
        );
        log.info("Initializing the add PayPal card API request specification.");
        reqSpec = RestUtils.requestSpecification(
                resourcePathURI,
                tokenizePaymentCard
        );
    }

    public void hitTokenizePaymentWithCard() {
        String endPoint;
        Routes routes = Routes.valueOf(TOKENIZE_PAYMENT);
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

    public int getAPIResponseCode() {
        return response.getStatusCode();
    }

    public String getAPIResponseMessage() {
        return jsonPath.getString("Result").trim();
    }

    public String getTransactionRefNo() {
        return jsonPath.getString("content.txRefNum");
    }

    public String getPaymentCode() {
        return jsonPath.getString("content.respCode");
    }

    public Map<String, Object> getSuccessPaymentResponse() {
        Map<String, Object> addPayPalProfileResponse;
        addPayPalProfileResponse = jsonPath.getMap("content");
        return addPayPalProfileResponse;
    }

    public void buildRequestSpecTokenizePaymentWithBank(Bank bank, TokenizePayment tokenizePayment, String resourcePathURI) {
        log.info("Initializing the add PayPal card API payload.");
        TokenizePaymentBank tokenizePaymentBank = new TokenizePaymentBank(
                Configuration.toString("userName"),
                bank,
                tokenizePayment
        );
        log.info("Initializing the add PayPal card API request specification.");
        reqSpec = RestUtils.requestSpecification(
                resourcePathURI,
                tokenizePaymentBank
        );
    }

    public void hitTokenizePaymentWithBank() {
        String endPoint;
        Routes routes = Routes.valueOf(TOKENIZE_PAYMENT);
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

    public void buildRequestSpecTokenizePaymentWithWallet(PayPalWallet payPalWallet, TokenizePayment tokenizePayment,
                                                          String resourcePathURI) {
        log.info("Initializing the add PayPal card API payload.");
        TokenizePaymentWallet tokenizePaymentWallet = new TokenizePaymentWallet(
                Configuration.toString("userName"),
                payPalWallet,
                tokenizePayment
        );
        log.info("Initializing the add PayPal card API request specification.");
        reqSpec = RestUtils.requestSpecification(
                resourcePathURI,
                tokenizePaymentWallet
        );
    }

    public void hitTokenizePaymentWithWallet() {
        String endPoint;
        Routes routes = Routes.valueOf(TOKENIZE_PAYMENT);
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

    public void buildRequestSpecTokenizePaymentWithVenmoWallet(VenmoWallet venmoWallet, TokenizePayment tokenizePayment,
                                                               String resourcePathURI) {
        log.info("Initializing the add Venmo wallet API payload.");
        TokenizePaymentVenmo tokenizePaymentWallet = new TokenizePaymentVenmo(
                Configuration.toString("userName"),
                venmoWallet,
                tokenizePayment
        );
        log.info("Initializing the add Venmo wallet API request specification.");
        reqSpec = RestUtils.requestSpecification(
                resourcePathURI,
                tokenizePaymentWallet
        );
    }

    public void hitTokenizePaymentWithVenmoWallet() {
        String endPoint;
        Routes routes = Routes.valueOf(TOKENIZE_PAYMENT);
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

    public void buildRequestSpecTokenizePaymentWithApplePayWallet(AppleWallet appleWallet, TokenizePayment tokenizePayment,
                                                                  String resourcePathURI) {
        log.info("Initializing the add Venmo wallet API payload.");
        TokenizePaymentApplePay tokenizePaymentApplePay = new TokenizePaymentApplePay(
                Configuration.toString("userName"),
                appleWallet,
                tokenizePayment
        );
        log.info("Initializing the add Venmo wallet API request specification.");
        reqSpec = RestUtils.requestSpecification(
                resourcePathURI,
                tokenizePaymentApplePay
        );
    }

    public void hitTokenizePaymentWithApplePayWallet() {
        String endPoint;
        Routes routes = Routes.valueOf(TOKENIZE_PAYMENT);
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
}
