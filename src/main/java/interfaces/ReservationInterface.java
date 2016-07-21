package interfaces;

import domains.ReservationModel;

public interface ReservationInterface {

	public void insertResv(ReservationModel rm);
	public void updateResv(ReservationModel rm);
	public void deleteResv(String key);
}
