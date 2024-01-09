package sew.ai.models;

public class RegistrationMailsConfig {

    private String customerName;
    private String emailId;
    private String subject;
    private String emailMsg;
    private String isEmailSent;

    public RegistrationMailsConfig(String customerName, String emailId, String subject, String emailMsg, String isEmailSent) {
        this.customerName = customerName;
        this.emailId = emailId;
        this.subject = subject;
        this.emailMsg = emailMsg;
        this.isEmailSent = isEmailSent;
    }
    public RegistrationMailsConfig( String emailId, String subject, String emailMsg, String isEmailSent) {
        this.customerName = customerName;
        this.emailId = emailId;
        this.subject = subject;
        this.emailMsg = emailMsg;
        this.isEmailSent = isEmailSent;
    }


    public String getCustomerName() {
        return customerName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getSubject() {
        return subject;
    }

    public String getEmailMsg() {
        return emailMsg;
    }

    public String getIsEmailSent() {
        return isEmailSent;
    }
}
