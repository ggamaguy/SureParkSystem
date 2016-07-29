package com.surepark.cmu.domains;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;

public class CustomerMongoModel {
	
	@Id
	private String id;
	
	private int reservationId;
	private String phoneNumber;
	private String parkingLotID;
	private String email;
    private int carSize;
    private String reservationTime;
    private String entranceTime;
    private String exitTime;
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
	private String paymentFee;
	
	public CustomerMongoModel()
	{}
	
	
	
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
	public String getReservationTime() {
		return reservationTime;
	}
	public void setReservationTime(String reservationTime) {
		this.reservationTime = reservationTime;
	}
	public String getEntranceTime() {
		return entranceTime;
	}
	public void setEntranceTime(String entranceTime) {
		this.entranceTime = entranceTime;
	}
	public String getExitTime() {
		return exitTime;
	}
	public void setExitTime(String exitTime) {
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


	public int getReservationId() {
		return reservationId;
	}


	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}



	public String getPaymentFee() {
		return paymentFee;
	}



	public void setPaymentFee(String paymentFee) {
		this.paymentFee = paymentFee;
	}
	
	
	

}
