package sew.ai.api.endpoints.billing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sew.ai.api.enums.HttpVerbs;
import sew.ai.api.enums.Routes;
import sew.ai.api.pojos.billing.applepay.OneTimeApplePay;
import sew.ai.api.pojos.billing.paypal.OneTimePaymentBank;
import sew.ai.api.pojos.billing.paypal.OneTimePaymentCard;
import sew.ai.api.pojos.billing.paypal.OneTimePaypalWallet;
import sew.ai.api.pojos.billing.venmo.OneTimePaymentVenmo;
import sew.ai.api.utils.RestUtils;
import sew.ai.config.Configuration;
import sew.ai.models.*;

import java.util.Map;

public class OneTimePaymentEndpoints extends RestUtils {
    private static final Logger log = LogManager.getLogger(OneTimePaymentEndpoints.class);
    private static final String ONE_TIME_PAYMENT_CARD = "OneTimePayByCard";
    private static final String ONE_TIME_PAYMENT_BANK = "OneTimePayByBank";
    private static final String ONE_TIME_PAYMENT_WALLET = "OneTimePayByWallet";

    public void buildRequestSpecOneTimePaymentWithCard(Card card, OneTimePayment oneTimePayment, String resourcePathURI) {
        log.info("Initializing the add PayPal card API payload.");
        OneTimePaymentCard oneTimePaymentCard = new OneTimePaymentCard(
                Configuration.toString("userName"),
                card,
                oneTimePayment
        );
        log.info("Initializing the add PayPal card API request specification.");
        reqSpec = RestUtils.requestSpecification(
                resourcePathURI,
                oneTimePaymentCard
        );
    }

    public void hitOneTimePaymentWithCard() {
        String endPoint;
        Routes routes = Routes.valueOf(ONE_TIME_PAYMENT_CARD);
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

    public Map<String, Object> getSuccessPaymentResponse() {
        Map<String, Object> addPayPalProfileResponse;
        addPayPalProfileResponse = jsonPath.getMap("content");
        return addPayPalProfileResponse;
    }

    public String getPaymentCode() {
        return jsonPath.getString("content.respCode");
    }

    public String getPaymentAmount() {
        return jsonPath.getString("content.paymentAmount");
    }

    public String getConvenienceFee() {
        return jsonPath.getString("content.convenienceFee");
    }

    public String getOrderId() {
        return jsonPath.getString("content.orderId");
    }

    public String getTransactionRefNo() {
        return jsonPath.getString("content.txRefNum");
    }

    public void buildRequestSpecOneTimePaymentWithBank(Bank bank, OneTimePayment oneTimePayment, String resourcePathURI) {
        log.info("Initializing the one time payment with PayPal bank API payload.");
        OneTimePaymentBank oneTimePaymentBank = new OneTimePaymentBank(
                Configuration.toString("userName"),
                bank,
                oneTimePayment
        );
        log.info("Initializing the one time payment with bank API request specification.");
        reqSpec = RestUtils.requestSpecification(
                resourcePathURI,
                oneTimePaymentBank
        );
    }

    public void hitOneTimePaymentWithBank() {
        String endPoint;
        Routes routes = Routes.valueOf(ONE_TIME_PAYMENT_BANK);
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

    public void buildRequestSpecOneTimePaymentWithPaypalWallet(PayPalWallet payPalWallet, OneTimePayment oneTimePayment,
                                                               String resourcePathURI) {
        log.info("Initializing the add PayPal card API payload.");
        OneTimePaypalWallet oneTimePaypalWallet = new OneTimePaypalWallet(
                Configuration.toString("userName"),
                payPalWallet,
                oneTimePayment
        );
        log.info("Initializing the add PayPal card API request specification.");
        reqSpec = RestUtils.requestSpecification(
                resourcePathURI,
                oneTimePaypalWallet
        );
    }

    public void hitOneTimePaymentWithWallet() {
        String endPoint;
        Routes routes = Routes.valueOf(ONE_TIME_PAYMENT_WALLET);
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

    public void buildRequestSpecOneTimePaymentWithVenmoWallet(VenmoWallet venmoWallet, OneTimePayment oneTimePayment,
                                                              String resourcePathURI) {
        log.info("Initializing the add PayPal card API payload.");
        OneTimePaymentVenmo oneTimePaypalVenmo = new OneTimePaymentVenmo(
                Configuration.toString("userName"),
                venmoWallet,
                oneTimePayment
        );
        log.info("Initializing the add PayPal card API request specification.");
        reqSpec = RestUtils.requestSpecification(
                resourcePathURI,
                oneTimePaypalVenmo
        );
    }

    public void buildRequestSpecOneTimePaymentWithAppleWallet(AppleWallet appleWallet, OneTimePayment oneTimePayment,
                                                              String resourcePathURI) {
        log.info("Initializing the add PayPal card API payload.");
        OneTimeApplePay oneTimePayApplePay = new OneTimeApplePay(
                Configuration.toString("userName"),
                appleWallet,
                oneTimePayment
        );
        log.info("Initializing the add PayPal card API request specification.");
        reqSpec = RestUtils.requestSpecification(
                resourcePathURI,
                oneTimePayApplePay
        );
    }
}
