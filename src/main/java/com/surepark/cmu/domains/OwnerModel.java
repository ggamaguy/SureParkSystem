package com.surepark.cmu.domains;

public class OwnerModel {
	
	private String ownerID;
	private String ownerName;
	private String ownerPassword;
	private String ownerEmail;
	private String ownerPhoneNumber;
	private String ownerTwofactorPassword;
	private String owner1stLoginTry;
	private String owner2ndLoginTry;
	private String ownerAccountAvailable;
	


	public String getOwnerAccountAvailable() {
		return ownerAccountAvailable;
	}


	public void setOwnerAccountAvailable(String ownerAccountAvailable) {
		this.ownerAccountAvailable = ownerAccountAvailable;
	}


	public OwnerModel()
	{
		
	}
	
	
	public OwnerModel(String ownerID, String ownerName, String ownerPassword, String ownerEmail,
			String ownerPhoneNumber, String ownerTwofactorPassword) {
		super();
		this.ownerID = ownerID;
		this.ownerName = ownerName;
		this.ownerPassword = ownerPassword;
		this.ownerEmail = ownerEmail;
		this.ownerPhoneNumber = ownerPhoneNumber;
		this.ownerTwofactorPassword = ownerTwofactorPassword;
	}
	
	public OwnerModel(OwnerModel ownerModel)
	{
		this.ownerID = ownerModel.getOwnerID();
		this.ownerName = ownerModel.getOwnerName();
		this.ownerPassword = ownerModel.getOwnerPassword();
		this.ownerEmail = ownerModel.getOwnerEmail();
		this.ownerPhoneNumber = ownerModel.getOwnerPhoneNumber();
		this.ownerTwofactorPassword = ownerModel.getOwnerTwofactorPassword();
	}
	public String getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getOwnerPassword() {
		return ownerPassword;
	}
	public void setOwnerPassword(String ownerPassword) {
		this.ownerPassword = ownerPassword;
	}
	public String getOwnerEmail() {
		return ownerEmail;
	}
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	public String getOwnerPhoneNumber() {
		return ownerPhoneNumber;
	}
	public void setOwnerPhoneNumber(String ownerPhoneNumber) {
		this.ownerPhoneNumber = ownerPhoneNumber;
	}
	public String getOwnerTwofactorPassword() {
		return ownerTwofactorPassword;
	}
	public void setOwnerTwofactorPassword(String ownerTwofactorPassword) {
		this.ownerTwofactorPassword = ownerTwofactorPassword;
	}
	@Override
	public String toString() {
		return "OwnerModel [ownerID=" + ownerID + ", ownerName=" + ownerName + ", ownerPassword=" + ownerPassword
				+ ", ownerEmail=" + ownerEmail + ", ownerPhoneNumber=" + ownerPhoneNumber + ", ownerTwofactorPassword="
				+ ownerTwofactorPassword + "]";
	}
	public String getOwner1stLoginTry() {
		return owner1stLoginTry;
	}
	public void setOwner1stLoginTry(String owner1stLoginTry) {
		this.owner1stLoginTry = owner1stLoginTry;
	}
	public String getOwner2ndLoginTry() {
		return owner2ndLoginTry;
	}
	public void setOwner2ndLoginTry(String owner2ndLoginTry) {
		this.owner2ndLoginTry = owner2ndLoginTry;
	}
}
