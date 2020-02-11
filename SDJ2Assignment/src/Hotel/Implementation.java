package Hotel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

import ArrayList.ArrayListImpl;
import Booking.Booking;
import Guest.Guest;
import Room.Room;
import Room.RoomFactory;
import SharedInterface.SharedInterface;

public class Implementation implements SharedInterface<Room>
{
   private ArrayList<Room> rooms;
   private RoomFactory roomFactory;
   private static Implementation implementation;

   private Implementation() throws ClassNotFoundException{
      rooms = new ArrayList<Room>();
      roomFactory = new RoomFactory();
      load();
   }
   public static Implementation getInstance() throws ClassNotFoundException{
      if(implementation == null) implementation=new Implementation();
      return implementation;
   }

   public boolean addBooking(Room room, Booking booking){
     if(room.availabilityChecker(booking.getStartDate(), booking.getEndDate()))
        {
        room.addBooking(booking);
        save();
        return true;
        }else 
       System.out.println("Cannot add a booking in given time period");
       return false;
   }

   private Booking createBooking(Room room, Date startDate, Date endDate, Guest guest){
      return new Booking(room, startDate, endDate, guest);
   }

   public boolean editBooking(Booking booking,Room room, Date startDate, Date endDate, Guest guest){
      Booking newBooking = createBooking(room, startDate, endDate, guest);
      room.deleteBooking(booking);
      if(room.availabilityChecker(newBooking.getStartDate(), newBooking.getEndDate())){
         room.editBooking(booking, newBooking);
         save();
         return true;
      }else 
      room.addBooking(booking);
      return false;

   }
   public Booking deleteBooking(Room room, Booking booking){
      room.deleteBooking(booking);
      save();
      return booking;
   }

   public ArrayListImpl getAvailableRooms(Date startDate, Date endDate){
      ArrayListImpl availableRooms = new ArrayListImpl();
      for(int i = 0; i<rooms.size();i++){
         if(( rooms.get(i)).availabilityChecker(startDate, endDate))
            availableRooms.add( rooms.get(i));
      }
      return availableRooms;
   }

   public ArrayListImpl getBookedRooms(Date startDate, Date endDate){
      ArrayListImpl bookedRooms = new ArrayListImpl();
      for(int i = 0; i<rooms.size();i++){
         if(!(  rooms.get(i)).availabilityChecker(startDate, endDate))
            bookedRooms.add(rooms.get(i));
      }
      return bookedRooms;
   }

   public ArrayList<Room> getRooms(){
      return rooms;
   }
   
   //*****************************************************************
   private  void save() {
      try {
         System.out.println("Saving");
         File file = new File("saveRooms.bin");
         FileOutputStream fos = new FileOutputStream(file);
         ObjectOutputStream out = new ObjectOutputStream(fos);
         out.writeObject(rooms);
         out.close();
      }
      catch (IOException e) {
      }
   }

   @SuppressWarnings("unchecked")
   private  void load() throws ClassNotFoundException {
      File file = new File("saveRooms.bin");
      if (file.exists()) {
         try {            
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fis);
            rooms= (ArrayList<Room>) in.readObject();
            in.close();
         } catch (IOException e) {
            System.out.println("Loading failed");
         }

      } else {
         rooms = roomFactory.createDefaultRooms();
         save();
      }  
   }
}
