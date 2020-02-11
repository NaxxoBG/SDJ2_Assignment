package Room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import Booking.Booking;

public abstract class Room implements Serializable
{
   private static final long serialVersionUID = 1L;
   private int roomNumber;
   protected int capacity;
   protected int price;
   private ArrayList<Booking> bookings;

   protected Room(int roomNumber) {
      bookings = new ArrayList<Booking>();
      this.roomNumber = roomNumber;
   }

   public ArrayList<Booking> getBookings() {
      return bookings;
   }

   public void addBooking(Booking booking) {
      bookings.add(booking);
      sortBookings(bookings);
   }
   
   public void editBooking(Booking removeBooking,Booking editedBooking){
      deleteBooking(removeBooking);
      addBooking(editedBooking);
   }

   public void deleteBooking(Booking booking){
      bookings.remove(booking);
   }

   public boolean availabilityChecker(Date startDate, Date endDate){   
      return  dayChecker(bookings, startDate, endDate); 
   }
   
   private boolean dayChecker(ArrayList<Booking> list, Date startDate, Date endDate){
      if(list.size()==0)return true;
      else{
         for(int i =0; i<list.size();i++){
            if (startDate.compareTo(bookings.get(i).getStartDate())<=0 && endDate.compareTo(bookings.get(i).getEndDate())>=0)
               return false;
            else if(startDate.compareTo(bookings.get(i).getStartDate())>=0 && endDate.compareTo(bookings.get(i).getEndDate())<=0)
               return false;
            else if((startDate.compareTo(bookings.get(i).getStartDate())>=0 && startDate.compareTo(bookings.get(i).getEndDate())<=0) && endDate.compareTo(bookings.get(i).getEndDate())>=0)
               return false;
            else if( startDate.compareTo(bookings.get(i).getStartDate())<=0 && (endDate.compareTo(bookings.get(i).getStartDate())>=0 && endDate.compareTo(bookings.get(i).getEndDate())<=0))
               return false;
         }
         return true;
      }
   }

   private String getType(){
     return getClass().getName().substring(5);
   }
   
   public String toString(){
      return roomNumber+","+getType()+","+capacity+","+price;
   }
   
   /*Sort arrayList of bookings in class Room by their startDate*/
   public void sortBookings(ArrayList<Booking> list){
      Comp c = new Comp();
      list.sort(c.getComparator());
   }

   //Private class for comparator - HAVE to be Serializable
   private class Comp implements Serializable {
      private static final long serialVersionUID = 1L;
      public  Comparator<Booking> comparator = new Comparator<Booking>() {
         @Override
         public int compare(Booking o1, Booking o2) {
            return o1.compareTo(o2);
         }
      };

      public Comparator<Booking> getComparator(){
         return comparator;
      }
   }
}