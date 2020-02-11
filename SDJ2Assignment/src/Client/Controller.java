package Client;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller
{
   private View view;
   private  ClientModel model;
   private String delimiters =  "\\D+";


   public Controller(View view, ClientModel client) throws RemoteException {
      this.view = view;
      this.model = client;
      menuChoice();
   }

   public void menuChoice() throws RemoteException {
      while (true) {
         view.printMenu();
         String choice = view.stringInput();
         switch (choice) {
            case "1":
               availableRooms();
               break;
            case "2":
               bookedRooms();
               break;
            case "3":
               exitProgram();
               break;
            default:
              view.outOfRange();
         }
      }
   }

   public void availableRooms() throws RemoteException {
      Date[] dates = createDates();
      while(!orderDates(dates[0], dates[1])){
         view.endBeforeStart();
         dates = createDates();
      };
      model.updateAvailable(dates[0], dates[1]);
      view.updateViewForAvailable(model.getAvailableRooms());
   }

   public void bookedRooms() throws RemoteException {
      Date[] dates = createDates();
      while(!orderDates(dates[0], dates[1])){
         view.endBeforeStart();
         dates = createDates();
      }
      model.updateBooked(dates[0], dates[1]);
      view.updateViewForBooked(model.getBookedRooms());
   }

   public void exitProgram() {
      view.exitTheProgram();
      System.exit(0);
   }
   /******************************************************************/
   private Date[] createDates(){
      Date[] dates = new Date[2];
      view.enterStartDate();
      dates[0]= createDate();
      view.enterEndDate();
      dates[1] = createDate();
      return dates;
   }
   
   private Date createDate(){
      String regex = "^(\\D*?)(?:(?:0?)[1-9]|[123][01]|[12][1-9])\\D+(?:(?:(?:0?)[1-9])|(?:[1][012]))\\D+(:?[2][0][12](?:(?:0)|(?:[6-9])))$";;
      Pattern pattern = Pattern.compile(regex);
      String date = view.stringInput();
      Matcher matcher = pattern.matcher(date); 
      while (true) {
      try{
            while (!matcher.matches()) {
            view.wrongDate();
            date = view.stringInput();
            matcher = pattern.matcher(date);
          
         }
            String[] array = date.split(delimiters);
            Calendar cal = Calendar.getInstance();
            cal.set(Integer.parseInt(array[2]), Integer.parseInt(array[1])-1, Integer.parseInt(array[0]));
            if (validator(cal.getTime()))
               return cal.getTime();
            else  {
         
            view.wrongInput();
            date = view.stringInput();
            matcher = pattern.matcher(date);
            } 
         } 
      catch (Exception e)
      {
         view.wrongDate();
         date = view.stringInput();
         matcher = pattern.matcher(date);
      }
      }
         
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

   private boolean orderDates(Date startDate, Date endDate){
      if(endDate.before(startDate))return false;
      else return true;
   }
}