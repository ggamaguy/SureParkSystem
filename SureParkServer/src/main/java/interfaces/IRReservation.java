package interfaces;

import domains.ReservationModel;

public interface IRReservation {

	public void insertResv(ReservationModel rm);
	public void updateResv(ReservationModel rm);
	public void deleteResv(String key);
}
