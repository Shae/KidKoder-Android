package com.klusman.kidkoder;

public class Kid implements child {
	//byte[] _photo;
	String first;
	String last;
	String bdate;
	String gender;
	Boolean enrolled;
	Boolean allergies;
	Boolean _inOut;
	String allergiesList;
	String emergencyNum;
	String ID;
	
	public Kid( String id, String fName, String lName ,String bdate, String gender, Boolean enrolled, Boolean allergies, String allergiesList, String phNum, Boolean inOut){
		// byte[] photo,  // pull out
		//setPhoto(photo);
		setChildID(id);
		setFirstName(fName);
		setLastName(lName);
		setBDay(bdate);
		setGender(gender);
		setAllergiesBool(enrolled);
		setAllergiesList(allergiesList);
		setEmergencyContact(phNum);
		setInOut(inOut);
		
	}
	
//	@Override
//	public boolean setPhoto(byte[] photo) {
//		this._photo = photo;
//		return false;
//	}
	
	@Override
	public boolean setChildID(String id) {
		this.ID = id;
		return true;
	}
	
	@Override
	public boolean setFirstName(String fName) {
		this.first = fName;
		return true;
	}

	@Override
	public boolean setLastName(String lName) {
		this.last = lName;
		return true;
	}

	@Override
	public boolean setBDay(String bdate) {
		this.bdate = bdate;
		return true;
	}

	@Override
	public boolean setGender(String gender) {
		this.gender = gender;
		return true;
	}

	@Override
	public boolean setEnrolled(Boolean bool) {
		this.enrolled = bool;
		return true;
	}

	@Override
	public boolean setAllergiesBool(Boolean bool) {
		this.allergies = bool;
		return true;
	}

	@Override
	public boolean setAllergiesList(String allergies) {
		this.allergiesList = allergies;
		return true;
	}

	@Override
	public boolean setEmergencyContact(String phNum) {
		this.emergencyNum = phNum;
		return true;
	}
	
	@Override
	public boolean setInOut(Boolean inOut){
		this._inOut = inOut;
		return true;
	}
	
	
	
	@Override
	public String getChildID() {
		// TODO Auto-generated method stub
		return this.ID;
	}
	
	@Override
	public String getFirstname() {
		// TODO Auto-generated method stub
		return this.first;
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return this.last;
	}

	@Override
	public String getBDay() {
		// TODO Auto-generated method stub
		return this.bdate;
	}

	@Override
	public String getGender() {
		// TODO Auto-generated method stub
		return this.gender;
	}

	@Override
	public Boolean getEnrolled() {
		// TODO Auto-generated method stub
		return this.enrolled;
	}

	@Override
	public Boolean getAllergiesBool() {
		// TODO Auto-generated method stub
		return this.allergies;
	}

	@Override
	public String getAllergiesList() {
		// TODO Auto-generated method stub
		return this.allergiesList;
	}

	@Override
	public String getEmergencyContact() {
		// TODO Auto-generated method stub
		return this.emergencyNum;
	}

	@Override
	public boolean getInOut() {
		// TODO Auto-generated method stub
		return this._inOut;
	}


//	@Override
//	public byte[] getPhoto() {
//		// TODO Auto-generated method stub
//		return this._photo;
//	}




}
