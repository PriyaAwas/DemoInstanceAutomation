package sew.ai.models;

public class GetBillHistoryData {

	String statementDate;
	String billDueDate;
	String previousBalance;
	String paymentReceived;
	String currentCharges;
	String totalAmountDue;
	
	
	public GetBillHistoryData(String statementDate, String billDueDate, String previousBalance, String paymentReceived, 
			String currentCharges, String totalAmountDue) {

		this.statementDate = statementDate;
		this.billDueDate = billDueDate;

		this.previousBalance = previousBalance;
		this.paymentReceived = paymentReceived;
		this.currentCharges = currentCharges;
		this.totalAmountDue = totalAmountDue;

	}


	public String getStatementDate() {
		return statementDate;
	}


	public String getBillDueDate() {
		return billDueDate;
	}


	public String getPreviousBalance() {
		return previousBalance;
	}


	public String getPaymentReceived() {
		return paymentReceived;
	}


	public String getCurrentCharges() {
		return currentCharges;
	}


	public String getTotalAmountDue() {
		return totalAmountDue;
	}

	

}
