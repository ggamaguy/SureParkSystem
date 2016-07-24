package com.surepark.cmu.domains;

import java.sql.Timestamp;

public class ReservationModel {

	private String reservationId;
	private String phoneNumber;
	private String email;
	private String parkingLotID;
    private int carSize;
    private Timestamp reservationTime;
    private Timestamp entranceTime;
    private Timestamp exitTime;
    private String cardNumber;
    private String cardExpirationMonth;
    private String cardExpirationYear;
    private String cardValidationCode;
    private String cardHolder;
    private String reservation;
    
    public ReservationModel(){}
	public ReservationModel(String reservationId, String phoneNumber, String email, String parkingLotID, int carSize,
			Timestamp reservationTime, Timestamp entranceTime, Timestamp exitTime, String cardNumber,
			String cardExpirationMonth, String cardExpirationYear, String cardValidationCode, String cardHolder,
			String reservation) {
		super();
		this.reservationId = reservationId;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.parkingLotID = parkingLotID;
		this.carSize = carSize;
		this.reservationTime = reservationTime;
		this.entranceTime = entranceTime;
		this.exitTime = exitTime;
		this.cardNumber = cardNumber;
		this.cardExpirationMonth = cardExpirationMonth;
		this.cardExpirationYear = cardExpirationYear;
		this.cardValidationCode = cardValidationCode;
		this.cardHolder = cardHolder;
		this.reservation = reservation;
	}
	public String getReservationId() {
		return reservationId;
	}
	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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

	public String getCardExpirationMonth() {
		return cardExpirationMonth;
	}
	public void setCardExpirationMonth(String cardExpirationMonth) {
		this.cardExpirationMonth = cardExpirationMonth;
	}
	public String getCardExpirationYear() {
		return cardExpirationYear;
	}
	public void setCardExpirationYear(String cardExpirationYear) {
		this.cardExpirationYear = cardExpirationYear;
	}
	public String getCardValidationCode() {
		return cardValidationCode;
	}
	public void setCardValidationCode(String cardValidationCode) {
		this.cardValidationCode = cardValidationCode;
	}
	public String getCardHolder() {
		return cardHolder;
	}
	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
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
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

}
