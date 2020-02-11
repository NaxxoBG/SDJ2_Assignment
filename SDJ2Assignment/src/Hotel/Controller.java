package Hotel;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Booking.Booking;
import Guest.Guest;
import Room.Room;

public class Controller
{
   private View view;
   private Model model;
   private String delimiters = "\\D+";

   public Controller(View view, Model model) throws RemoteException {
      this.view = view;
      this.model = model;
      menuChoice();
   }

   public void menuChoice() throws RemoteException {
      while (true) {
         view.printMenu();
         String choice = model.stringInput();
         switch (choice) {
            case "1":
               if(addNewBooking())view.successfullAdd();
               else view.wrongInput();
               break;
            case "2":
               if(editBooking()) view.successfullAdd();
               else view.wrongInput();
               break;
            case "3":
               deleteBooking();
               break;
            case "4":
               availableRooms();
               break;
            case "5":
               bookedRooms();
               break;
            case "6":
               exitProgram();
               break;
            default:
               view.outOfRange();
               break;
         }
      }
   }
   public boolean addNewBooking(){
      view.addBooking();
      Room room = selectRoom();
      Date[] dates = createDates();
      Guest guest = createGuest();
      return model.getImplementation().addBooking(room, new Booking(room, dates[0], dates[1], guest));
   }

   public boolean editBooking(){
      view.editBooking();
      Room room = selectRoom();
      view.selectBooking();
      Booking booking = selectBooking(room);
      if(booking==(null))return false;
      Date[] dates = createDates();
      Guest guest = createGuest();
      return model.getImplementation().editBooking(booking, room, dates[0], dates[1], guest);
   }
   public Booking deleteBooking(){
      view.deleteBooking();
      Room room = selectRoom();
      view.selectBooking();
      Booking booking = selectBooking(room);
      if(booking==(null))return null;
      return model.getImplementation().deleteBooking(room, booking);
   }
   public void availableRooms() throws RemoteException {
      Date[] dates = createDates();
      view.printRooms( model.getImplementation().getAvailableRooms(dates[0], dates[1]));
   }
   public void bookedRooms() throws RemoteException {
      Date[] dates = createDates();
      view.printRooms( model.getImplementation().getBookedRooms(dates[0], dates[1]));
   }
   public void exitProgram() {
      view.exitTheProgram();
      System.exit(0);
   }
   
   /*************************************************************/
   private Room selectRoom(){
      view.printRooms(model.getImplementation().getRooms());
      view.selectRoom();
      while(true){
         try{
            int number =(Integer.parseInt(model.stringInput()));
            return model.getImplementation().getRooms().get(number-1);
            }
         catch (Exception e){
           view.wrongChoice();
         }
      }
   }
   private Booking selectBooking(Room room){
      view.printBookings(room.getBookings());
      if(room.getBookings().size()==0){view.noBookings();return null;}
      view.selectBooking();
      while(true){
         try{
            int number =Integer.parseInt( model.stringInput());
            return room.getBookings().get(number-1);
            }
         catch (Exception e){
           view.wrongChoice();
         }
      }
   }
   private Date[] createDates(){
      Date[] dates = new Date[2];
      view.enterStartDate();
      dates[0]= createDate();
      view.enterEndDate();
      dates[1] = createDate();
      while(!orderDates(dates[0], dates[1])){
         view.endBeforeStart();
         dates = createDates();
      }
      return dates;
   }
   private Date createDate(){
      String regex = "^(\\D*?)(?:(?:0?)[1-9]|[123][01]|[12][1-9])\\D+(?:(?:(?:0?)[1-9])|(?:[1][012]))\\D+(:?[2][0][12](?:(?:0)|(?:[6-9])))$";;
      Pattern pattern = Pattern.compile(regex);
      String date = model.stringInput();
      Matcher matcher = pattern.matcher(date);   
      while (true) { 
         while (!matcher.matches()) {
         view.wrongDate();
         date = model.stringInput();
         matcher = pattern.matcher(date);
         }
         String[] array = date.split(delimiters);
         Calendar cal = Calendar.getInstance();
         cal.set(Integer.parseInt(array[2]), Integer.parseInt(array[1])-1, Integer.parseInt(array[0]));
         if (validator(cal.getTime()))
            return cal.getTime();
            view.wrongInput();
            date = model.stringInput();}   
      
      }
   
   private boolean validator(Date date){
      Date today = Calendar.getInstance().getTime();  
      if(date.before(today) && !sameDay(date))
         return false;
      else 
         return true;
   }
   
   private  boolean sameDay(Date date){
      Calendar cal1= Calendar.getInstance();
      Calendar cal2= Calendar.getInstance();
      cal2.setTime(date); 
      boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
            cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);     
      return sameDay; 
   }
   
   private Guest createGuest(){
      view.addName();
      String fName = model.stringInput();
      view.enterId();
      String id = model.stringInput();
      return new Guest(fName, id);
   }
   private boolean orderDates(Date startDate, Date endDate){
      if(endDate.before(startDate))return false;
      else return true;
   }
}
