package sew.ai.models;

public class CurrentOutage {
	
	String title;
	String outageDateTime;
	String estimatedRestoration;
	int customerAffected;
	String communityAffected;
	String reportInfo;
	String status;
	
	
	
	public CurrentOutage(String title, String outageDateTime, String estimatedRestoration, int customerAffected,
			String communityAffected, String reportInfo, String status) {
		
		this.title = title;
		this.outageDateTime = outageDateTime;
		this.estimatedRestoration = estimatedRestoration;
		this.customerAffected = customerAffected;
		this.communityAffected = communityAffected;
		this.reportInfo = reportInfo;
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public String getOutageDateTime() {
		return outageDateTime;
	}
	public String getEstimatedRestoration() {
		return estimatedRestoration;
	}
	public int getCustomerAffected() {
		return customerAffected;
	}
	public String getCommunityAffected() {
		return communityAffected;
	}
	public String getReportInfo() {
		return reportInfo;
	}
	public String getStatus() {
		return status;
	}
	
	

	

}
