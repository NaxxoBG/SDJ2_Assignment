package Client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import ArrayList.ArrayListImpl;
import SharedInterface.SharedInterface;

public class ClientModel
{
   private ArrayListImpl availableRooms;
   private ArrayListImpl bookedRooms;
   
   private SharedInterface<Object> inter;
  
   @SuppressWarnings("unchecked")
   public ClientModel() throws RemoteException, MalformedURLException, NotBoundException{
      String name = "//localhost/Server";
      inter = (SharedInterface<Object>) Naming.lookup(name);
   }
   
   public void updateAvailable(Date startDate, Date endDate) throws RemoteException {
      availableRooms = inter.getAvailableRooms(startDate, endDate);
   }
   
   public void updateBooked(Date startDate, Date endDate) throws RemoteException {
      bookedRooms= inter.getBookedRooms(startDate, endDate);
   }
   
   public ArrayListImpl getAvailableRooms() {
      return availableRooms;
   }
   
   public ArrayListImpl getBookedRooms() {
      return bookedRooms;
   }
}