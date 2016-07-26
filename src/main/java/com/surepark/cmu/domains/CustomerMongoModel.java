package com.surepark.cmu.domains;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;

public class CustomerMongoModel {
	
	@Id
	private String id;
	private String phoneNumber;
	private String parkingLotID;
    private int carSize;
    private Timestamp reservationTime;
    private Timestamp entranceTime;
    private Timestamp exitTime;
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
	
	public CustomerMongoModel()
	{}
	
	
	public CustomerMongoModel(String id, String phoneNumber, String parkingLotID, int carSize,
			Timestamp reservationTime, Timestamp entranceTime, Timestamp exitTime, String parkingLotName,
			String parkingLotLocationLongitude, String parkingLotLocationLatitude, String parkingLotAdress,
			String parkingLotStartTime, String parkingLotEndTime, String parkingLotMaximumCapacity, String ownerID,
			String parkingLotGracePeriod, String parkingLotPreResvationPeriod) {
		super();
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.parkingLotID = parkingLotID;
		this.carSize = carSize;
		this.reservationTime = reservationTime;
		this.entranceTime = entranceTime;
		this.exitTime = exitTime;
		this.parkingLotName = parkingLotName;
		this.parkingLotLocationLongitude = parkingLotLocationLongitude;
		this.parkingLotLocationLatitude = parkingLotLocationLatitude;
		this.parkingLotAdress = parkingLotAdress;
		this.parkingLotStartTime = parkingLotStartTime;
		this.parkingLotEndTime = parkingLotEndTime;
		this.parkingLotMaximumCapacity = parkingLotMaximumCapacity;
		this.ownerID = ownerID;
		this.parkingLotGracePeriod = parkingLotGracePeriod;
		this.parkingLotPreResvationPeriod = parkingLotPreResvationPeriod;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getParkingLotID() {
		return parkingLotID;
	}
	public void setParkingLotID(String parkingLotID) {
		this.parkingLotID = parkingLotID;
	}
	public int getCarSize() {
		return carSize;
	}
	public void setCarSize(int carSize) {
		this.carSize = carSize;
	}
	public Timestamp getReservationTime() {
		return reservationTime;
	}
	public void setReservationTime(Timestamp reservationTime) {
		this.reservationTime = reservationTime;
	}
	public Timestamp getEntranceTime() {
		return entranceTime;
	}
	public void setEntranceTime(Timestamp entranceTime) {
		this.entranceTime = entranceTime;
	}
	public Timestamp getExitTime() {
		return exitTime;
	}
	public void setExitTime(Timestamp exitTime) {
		this.exitTime = exitTime;
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
