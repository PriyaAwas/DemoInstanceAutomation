package sew.ai.models;

import java.util.Arrays;

public class GuestUser {
	
	
	String guestRole;
	String guestName;	
	String guestEmail;
	String gustAccessPeriod;
	
	private GuestUser[] guestUser;
	
	GuestUser(){
		
	}
	
	public GuestUser(GuestUser[] guestUser) {
        this.guestUser = guestUser;
    }
	
	
	public GuestUser getGuestUserRole(String guestRole) {
        return Arrays.stream(guestUser).filter(guestUser -> guestUser.getGuestRole()
                .equalsIgnoreCase(guestRole)).findFirst().get();
    }
	
	
	public String getGuestName() {
		return guestName;
	}
	
	public GuestUser getGuestUserName(String guestName) {
        return Arrays.stream(guestUser).filter(bank -> bank.getGuestName()
                .equalsIgnoreCase(guestName)).findFirst().get();
    }


	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}


	public String getGuestRole() {
		return guestRole;
	}


	public void setGuestRole(String guestRole) {
		this.guestRole = guestRole;
	}


	public String getGuestEmail() {
		return guestEmail;
	}


	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}


	public String getGustAccessPeriod() {
		return gustAccessPeriod;
	}


	public void setGustAccessPeriod(String gustAccessPeriod) {
		this.gustAccessPeriod = gustAccessPeriod;
	}


	public GuestUser[] getGuestUser() {
		return guestUser;
	}


	public void setGuestUser(GuestUser[] guestUser) {
		this.guestUser = guestUser;
	}

}
