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
	
	


}
