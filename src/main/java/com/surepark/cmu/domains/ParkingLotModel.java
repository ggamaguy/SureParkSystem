package com.surepark.cmu.domains;

public class ParkingLotModel {
	
	private String parkingLotID;
	private String parkingLotName;
	private String parkingLotLocation; 
	private String parkingLotAdress;
	private String parkingLotStartTime;
	private String parkingLotEndTime;
	private int parkingLotMaximumCapacity;
	private String ownerID;
	private int parkingLotGracePeriod;
	private int parkingLotPreResvationPeriod;
	
	public ParkingLotModel()
	{
		
	}
	
	
	public ParkingLotModel(String parkingLotID, String parkingLotName, String parkingLotLocation,
			String parkingLotAdress, String parkingLotStartTime, String parkingLotEndTime,
			int parkingLotMaximumCapacity, String ownerID, int parkingLotGracePeriod,
			int parkingLotPreResvationPeriod) {
		super();
		this.parkingLotID = parkingLotID;
		this.parkingLotName = parkingLotName;
		this.parkingLotLocation = parkingLotLocation;
		this.parkingLotAdress = parkingLotAdress;
		this.parkingLotStartTime = parkingLotStartTime;
		this.parkingLotEndTime = parkingLotEndTime;
		this.parkingLotMaximumCapacity = parkingLotMaximumCapacity;
		this.ownerID = ownerID;
		this.parkingLotGracePeriod = parkingLotGracePeriod;
		this.parkingLotPreResvationPeriod = parkingLotPreResvationPeriod;
	}
	
	


	private String parkingLotID; 
	private String parkingLotName; 
	private String parkingLotLocationLongitude;
	private String parkingLotLocationLatitude;
	private String parkingLotAdress;
	private String parkingLotStartTime; 
	private String parkingLotEndTime;
	private String parkingLotMaximumCapacity; 
	private String ownerID;
	private String parkingLotGracePeriod; 
	private String parkingLotPreResvationPeriod;
	
	public String getParkingLotID() {
		return parkingLotID;
	}
	public void setParkingLotID(String parkingLotID) {
		this.parkingLotID = parkingLotID;
	}
	public String getParkingLotName() {
		return parkingLotName;
	}
	public void setParkingLotName(String parkingLotName) {
		this.parkingLotName = parkingLotName;
	}
	public String getParkingLotLocationLongitude() {
		return parkingLotLocationLongitude;
	}
	public void setParkingLotLocationLongitude(String parkingLotLocationLongitude) {
		this.parkingLotLocationLongitude = parkingLotLocationLongitude;
	}
	public String getParkingLotLocationLatitude() {
		return parkingLotLocationLatitude;
	}
	public void setParkingLotLocationLatitude(String parkingLotLocationLatitude) {
		this.parkingLotLocationLatitude = parkingLotLocationLatitude;
	}
	public String getParkingLotAdress() {
		return parkingLotAdress;
	}
	public void setParkingLotAdress(String parkingLotAdress) {
		this.parkingLotAdress = parkingLotAdress;
	}
	public String getParkingLotStartTime() {
		return parkingLotStartTime;
	}
	public void setParkingLotStartTime(String parkingLotStartTime) {
		this.parkingLotStartTime = parkingLotStartTime;
	}
	public String getParkingLotEndTime() {
		return parkingLotEndTime;
	}
	public void setParkingLotEndTime(String parkingLotEndTime) {
		this.parkingLotEndTime = parkingLotEndTime;
	}
	public String getParkingLotMaximumCapacity() {
		return parkingLotMaximumCapacity;
	}
	public void setParkingLotMaximumCapacity(String parkingLotMaximumCapacity) {
		this.parkingLotMaximumCapacity = parkingLotMaximumCapacity;
	}
	public String getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}
	public String getParkingLotGracePeriod() {
		return parkingLotGracePeriod;
	}
	public void setParkingLotGracePeriod(String parkingLotGracePeriod) {
		this.parkingLotGracePeriod = parkingLotGracePeriod;
	}
	public String getParkingLotPreResvationPeriod() {
		return parkingLotPreResvationPeriod;
	}
	public void setParkingLotPreResvationPeriod(String parkingLotPreResvationPeriod) {
		this.parkingLotPreResvationPeriod = parkingLotPreResvationPeriod;
	}
}
