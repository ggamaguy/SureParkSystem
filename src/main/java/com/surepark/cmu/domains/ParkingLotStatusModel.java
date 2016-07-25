package com.surepark.cmu.domains;

public class ParkingLotStatusModel {
	
	private String parkingLotID;
	private int avaliableParkingSpot;
	private String parkingLotState;
	
	public ParkingLotStatusModel()
	{
		
	}
	
	public ParkingLotStatusModel(String parkingLotID, int avaliableParkingSpot, String parkingLotState) {
		super();
		this.parkingLotID = parkingLotID;
		this.avaliableParkingSpot = avaliableParkingSpot;
		this.parkingLotState = parkingLotState;
	}

	public String getParkingLotID() {
		return parkingLotID;
	}

	public void setParkingLotID(String parkingLotID) {
		this.parkingLotID = parkingLotID;
	}

	public int getAvaliableParkingSpot() {
		return avaliableParkingSpot;
	}

	public void setAvaliableParkingSpot(int avaliableParkingSpot) {
		this.avaliableParkingSpot = avaliableParkingSpot;
	}

	public String getParkingLotState() {
		return parkingLotState;
	}

	public void setParkingLotState(String parkingLotState) {
		this.parkingLotState = parkingLotState;
	}
	
	
	


}
