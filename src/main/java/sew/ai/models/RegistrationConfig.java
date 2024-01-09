package sew.ai.models;

/**
 * This class is created to bring the Registration Template Configuration Min, Max, 
 * Mandatory and CIS validation values from the Database
 *
 *@author Ranjit.Biswal
 */
public class RegistrationConfig {
	String fieldName;
	String fieldMinLength;
	String fieldMaxLength;
	String fieldMandatoryProperty;
	String fieldCisProperty;
	String fieldDisplay;
	String fieldType;
	
	public RegistrationConfig(String fieldDisplay, String fieldMinLength, String fieldMaxLength,
			String fieldMandatoryProperty, String fieldCisProperty, String fieldType) {
		this.fieldMinLength = fieldMinLength;
		this.fieldMaxLength = fieldMaxLength;
		this.fieldMandatoryProperty = fieldMandatoryProperty;
		this.fieldCisProperty = fieldCisProperty;
		this.fieldDisplay = fieldDisplay;
		this.fieldType = fieldType;
	}
	
	
	public RegistrationConfig(String fieldDisplay, String fieldName, String fieldMinLength, String fieldMaxLength,
			String fieldMandatoryProperty, String fieldCisProperty, String fieldType) {
		this.fieldDisplay = fieldDisplay;
		this.fieldName = fieldName;
		this.fieldMinLength = fieldMinLength;
		this.fieldMaxLength = fieldMaxLength;
		this.fieldMandatoryProperty = fieldMandatoryProperty;
		this.fieldCisProperty = fieldCisProperty;
		this.fieldType = fieldType;
	}
//	
//	public GetRegistrationConfig(String fieldDisplay, String fieldName, String fieldMinLength, String fieldMaxLength,
//		String fieldMandatoryProperty, String fieldCisProperty, String fieldType) {
//		this.fieldDisplay = fieldDisplay;
//		this.fieldName = fieldName;
//		this.fieldMinLength = fieldMinLength;
//		this.fieldMaxLength = fieldMaxLength;
//		this.fieldMandatoryProperty = fieldMandatoryProperty;
//		this.fieldCisProperty = fieldCisProperty;
//		this.fieldType = fieldType;
//	}
	
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public void setFieldDisplay(String fieldDisplay) {
		this.fieldDisplay = fieldDisplay;
	}

	public void setFieldMinLength(String fieldMinLength) {
		this.fieldMinLength = fieldMinLength;
	}

	public void setFieldMaxLength(String fieldMaxLength) {
		this.fieldMaxLength = fieldMaxLength;
	}

	public void setFieldMandatoryProperty(String fieldMandatoryProperty) {
		this.fieldMandatoryProperty = fieldMandatoryProperty;
	}

	public void setFieldCisProperty(String fieldCisProperty) {
		this.fieldCisProperty = fieldCisProperty;
	}
	
	public String getFieldDisplayStatus(){
		return fieldDisplay;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFieldMinLength() {
		return fieldMinLength;
	}

	public String getFieldMaxLength() {
		return fieldMaxLength;
	}

	public String getFieldMandatoryProperty() {
		return fieldMandatoryProperty;
	}

	public String getFieldCisProperty() {
		return fieldCisProperty;
	}
	
	public String getFieldType() {
		return fieldType;
	}
}
