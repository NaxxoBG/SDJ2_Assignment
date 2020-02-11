package Booking;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import Guest.Guest;
import Room.Room;

public class Booking implements Serializable, Comparable<Booking>
{
   private static final long serialVersionUID = 1L;
   private Date startDate;
   private Date endDate;
   private Room room;
   private Guest guest;

   public Booking(Room room, Date startDate, Date endDate, Guest guest){
      this.room = room;
      this.startDate=startDate;
      this.endDate = endDate;
      this.guest=guest;
   }

   public Date getStartDate(){
      return startDate;
   }
   
   public Date getEndDate(){
      return endDate;
   }

   public int compareTo(Booking o) {
      return this.startDate.compareTo(o.startDate);
   }

   public String toString() {
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      return dateFormat.format(startDate) + "," + dateFormat.format(endDate) + "," + guest.getfName()  + "," + guest.getID();
   }
   public Room getRoom(){
      return room;
      
   }
}