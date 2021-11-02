package staff;

import hotel.BookingDetail;
import hotel.BookingManager;
import hotel.IBookingManager;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.util.Set;

public class BookingClient extends AbstractScriptedSimpleTest {

	private IBookingManager bm = null;

	public static void main(String[] args) throws Exception {
		BookingClient client = new BookingClient();
		client.run();
	}

	/***************
	 * CONSTRUCTOR *
	 ***************/
	public BookingClient() {
		try {
            System.setProperty("java.rmi.server.hostname","localhost");
            // Lookup bookingManager object from RMI registry
            Registry registry = LocateRegistry.getRegistry("localhost",1099);
			this.bm = (IBookingManager)registry.lookup("IBookingManager");

		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	// use bm remote object to implement the following method
	@Override
	public boolean isRoomAvailable(Integer roomNumber, LocalDate date) throws RemoteException {
		//Implement this method
        return bm.isRoomAvailable(roomNumber,date);
	}

	@Override
	public void addBooking(BookingDetail bookingDetail) throws RemoteException {
		//Implement this method
        bm.addBooking(bookingDetail);
	}

	@Override
	public Set<Integer> getAvailableRooms(LocalDate date) throws RemoteException {
		//Implement this method
		return bm.getAvailableRooms(date);
	}

	@Override
	public Set<Integer> getAllRooms() throws RemoteException {
		return bm.getAllRooms();
	}
}
