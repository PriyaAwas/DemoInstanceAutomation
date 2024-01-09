package sew.ai.api.pojos.dashboard;

import sew.ai.models.User;

public class UsageBillGenerationPayload {
	private int AccountNumber;
	private int UserId;
	private String Type;
	private String Mode;
	private String MeterNumber;
	private String UsageType;
	private String LanguageCode;
	private boolean IsEnterpriseUser;
	private String UtilityAccountNumber;

	public UsageBillGenerationPayload(User user) {
		this.AccountNumber = Integer.parseInt(user.getAccountNumber());
		this.UserId = Integer.parseInt(user.getUserId());
		this.Type = "D";
		this.Mode = "M";
		this.MeterNumber = "";
		this.UsageType = "E,G,PV,W";
		this.LanguageCode = "EN";
		this.IsEnterpriseUser = false;
		this.UtilityAccountNumber = user.getDefaultUtilityAccNum();
	}

	public int getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		AccountNumber = accountNumber;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getMode() {
		return Mode;
	}

	public void setMode(String mode) {
		Mode = mode;
	}

	public String getMeterNumber() {
		return MeterNumber;
	}

	public void setMeterNumber(String meterNumber) {
		MeterNumber = meterNumber;
	}

	public String getUsageType() {
		return UsageType;
	}

	public void setUsageType(String usageType) {
		UsageType = usageType;
	}

	public String getLanguageCode() {
		return LanguageCode;
	}

	public void setLanguageCode(String languageCode) {
		LanguageCode = languageCode;
	}

	public boolean isIsEnterpriseUser() {
		return IsEnterpriseUser;
	}

	public void setIsEnterpriseUser(boolean isEnterpriseUser) {
		IsEnterpriseUser = isEnterpriseUser;
	}

	public String getUtilityAccountNumber() {
		return UtilityAccountNumber;
	}

	public void setUtilityAccountNumber(String utilityAccountNumber) {
		UtilityAccountNumber = utilityAccountNumber;
	}
}
