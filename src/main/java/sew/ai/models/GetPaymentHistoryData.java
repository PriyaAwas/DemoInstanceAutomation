package sew.ai.models;;

public class GetPaymentHistoryData {
	
	String date;
	String paymentType;
	String channel;
	String status;
	String amount;
	
	public GetPaymentHistoryData(String date, String paymentType, String channel, String status, String amount) {
		
		this.date = date;
		this.paymentType = paymentType;
		this.channel = channel;
		this.status = status;
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}	

	public String getPaymentType() {
		return paymentType;
	}

	public String getChannel() {
		return channel;
	}

	public String getStatus() {
		return status;
	}

	public String getAmount() {
		return amount;
	}

	
	
	
	

	
}
