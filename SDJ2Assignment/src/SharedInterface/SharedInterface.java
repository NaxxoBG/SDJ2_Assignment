package SharedInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import ArrayList.ArrayListImpl;

public interface  SharedInterface <T> extends Remote
{
public ArrayListImpl getAvailableRooms(Date startDate, Date endDate) throws RemoteException;
public ArrayListImpl getBookedRooms(Date startDate, Date endDate) throws RemoteException;
}
