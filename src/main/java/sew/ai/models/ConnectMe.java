package sew.ai.models;

import java.util.Arrays;

public class ConnectMe {
    private String reason;
    private String subject;
    private String messageBody;
    private boolean isPreLogin;
    private String fromEMail;
    private String customerName;
    private String topicName;

    private String locationAddress;
    private ConnectMe[] connectMe;

    public ConnectMe() {
    }

    public ConnectMe(ConnectMe[] connectMe) {
        this.connectMe = connectMe;
    }


    public ConnectMe getConnectMeByTopicName(String topicName) {
        return Arrays.stream(connectMe).filter(contactus -> contactus.getTopicName()
                .equalsIgnoreCase(topicName)).findFirst().get();
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }


    public boolean getIsPreLogin() {
        return isPreLogin;
    }

    public void setIsPreLogin(boolean isPreLogin) {
        this.isPreLogin = isPreLogin;
    }


    public String getFromEMail() {
        return fromEMail;
    }

    public void setFromEMail(String fromEMail) {
        this.fromEMail = fromEMail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }
}
