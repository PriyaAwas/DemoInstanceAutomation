package sew.ai.models;

import java.util.Arrays;

public class Registration {

	String customerName;
	String utilityAccountNumber;
	String firstName;
	String lastName;
	String emailAddress;
	String userName;
	String password;
	String zipCode;
	String meterNumber;
	String ssn;
	String fidTin;
	String drivingLicenseNo;
	String contactType;
	String phoneNumber;
	String streetAddress;

	private Registration registration[];

	public Registration() {
	}

	public Registration(Registration[] registration) {
		this.registration = registration;
	}

	public Registration getRegBycustomerName(String customer) {
		return Arrays.stream(registration).filter(reg -> reg.getCustomerName()
				.equalsIgnoreCase(customer)).findFirst().get();
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getUtilityAccountNumber() {
		return utilityAccountNumber;
	}

	public void setUtilityAccountNumber(String utilityAccountNumber) {
		this.utilityAccountNumber = utilityAccountNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getMeterNumber() {
		return meterNumber;
	}

	public void setMeterNumber(String meterNumber) {
		this.meterNumber = meterNumber;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getFidTin() {
		return fidTin;
	}

	public void setFidTin(String fidTin) {
		this.fidTin = fidTin;
	}

	public String getDrivingLicenseNo() {
		return drivingLicenseNo;
	}

	public void setDrivingLicenseNo(String drivingLicenseNo) {
		this.drivingLicenseNo = drivingLicenseNo;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public Registration[] getRegistration() {
		return registration;
	}

	public void setRegistration(Registration[] registration) {
		this.registration = registration;
	}
}
