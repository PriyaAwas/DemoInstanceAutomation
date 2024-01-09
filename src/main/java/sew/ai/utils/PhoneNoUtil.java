package sew.ai.utils;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.Locale;

public class PhoneNoUtil {

    private static String formatted;

    public static String getPhoneNumberAsString(String phoneNo){
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(phoneNo, Locale.US.getCountry());
            formatted = phoneUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.NATIONAL);
        } catch (NumberParseException e) {
            e.printStackTrace();
        }
        return formatted;
    }
    
    public static String getPhoneNumberAsPlainNumbers(String phonenumber)
    {
    	PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
    	StringBuffer sb = new StringBuffer(phonenumber);
    	sb.deleteCharAt(0);
    	sb.deleteCharAt(3);
    	sb.deleteCharAt(3);
    	sb.deleteCharAt(6);
    	return sb.toString();  	
    }
    
    public static String convertStringToPhoneNumberFormat(String PhoneNo) {
		
    	String number = PhoneNo.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
    	System.out.println("Customer Number is "+number);
    	return number; 
    }
    
    public static String convertStringToPhoneNumberFormat1(String PhoneNo) {
		
    	String number = PhoneNo.replaceFirst("(\\d{4})(\\d{3})(\\d+)", "$1-$2-$3");
    	System.out.println("Customer Number is "+number);
    	return number; 
    }

    public static void main(String []args){
        String phoneNo = "1111118866";
        PhoneNoUtil phoneNoUtil = new PhoneNoUtil();
//        String formattedNo = phoneNoUtil.getPhoneNumberAsString(phoneNo);
//        System.out.println(formattedNo);
//    	System.out.println(getPhoneNumberAsPlainNumbers("(999) 999-9999"));
        convertStringToPhoneNumberFormat1(phoneNo);
    }

}
