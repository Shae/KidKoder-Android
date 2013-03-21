package com.klusman.kidkoder;

public interface child {
	// set ID
	public boolean setChildID(String id);
	
	//set name
	public boolean setFirstName(String fName);
	
	//set name
	public boolean setLastName(String lName);
	
	//set bday
	public boolean setBDay(String bdate);
	
	//set gender
	public boolean setGender(String gender);
	
	//set enrolled
	public boolean setEnrolled(Boolean bool);
	
	//set allergies Ask
	public boolean setAllergiesBool(Boolean bool);
	
	//set allergies List if setAllergiesBool is true
	public boolean setAllergiesList(String allergies);
	
	//set emergency contact
	public boolean setEmergencyContact(String phNum);
	
	//set image
	//public boolean setImage(String path);
	
	public String getChildID();
	public String getFirstname();
	public String getLastName();
	public String getBDay();
	public String getGender();
	public Boolean getEnrolled();
	public Boolean getAllergiesBool();
	public String getAllergiesList();
	public String getEmergencyContact();
	
}
