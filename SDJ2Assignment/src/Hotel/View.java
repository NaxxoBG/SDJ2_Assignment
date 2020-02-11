package Hotel;

import java.util.ArrayList;
import java.util.Iterator;
import ArrayList.ArrayListImpl;
import Booking.Booking;
import Room.Room;

public class View
{

   public void greetClient() {
      System.out.println("Welcome to THE DEER ALLEY HOTEL 2");
   }
   public void addBooking(){
      System.out.println("Adding new booking");
   }
   public void editBooking(){
      System.out.println("Edit booking");
   }
   public void deleteBooking(){
      System.out.println("Delete booking");
   }
   public void selectBooking(){
      System.out.println("Select booking");
   }
   public void selectRoom(){
      System.out.println("Select room number");
   }

   public void enterStartDate() {
      System.out.println("Enter a start date (dd/mm/yyyy)");
   }
   public void enterEndDate() {
      System.out.println("Enter an end date (dd/mm/yyyy)");
   }
   public void addName(){
      System.out.println("Enter guests full name");
   }
   public void enterId(){
      System.out.println("Enter guests ID");
   }

   public void exitTheProgram() {
      System.out.println("Progam has been terminated");
   }

   public void printRooms(ArrayListImpl list){
      String[] text;
      String line = "+--------+-----------------+----------+---------+";

      System.out.format(line+"\n");
      System.out.format("| Number | Room type       | Capacity | Price   |\n");
      System.out.format(line+"\n");
      String format = "| %-6d | %-15s | %-8d | %-7.2f |%n";
      Iterator<Room> it = list.iterator();
      while(it.hasNext()){
         text = it.next().toString().split(",");
         System.out.format(format, Integer.parseInt(text[0]),text[1],Integer.parseInt(text[2]),Double.parseDouble(text[3]));
      }
      System.out.format(line+"\n");
   }
   public void printRooms(ArrayList<Room> list){
      String[] text;
      String line = "+--------+-----------------+----------+---------+";

      System.out.format(line+"\n");
      System.out.format("| Number | Room type       | Capacity | Price   |\n");
      System.out.format(line+"\n");
      String format = "| %-6d | %-15s | %-8d | %-7.2f |%n";
      Iterator<Room> it = list.iterator();
      while(it.hasNext()){
         text = it.next().toString().split(",");
         System.out.format(format, Integer.parseInt(text[0]),text[1],Integer.parseInt(text[2]),Double.parseDouble(text[3]));
      }
      System.out.format(line+"\n");
   }
   public void printBookings(ArrayList<Booking> list){
      String[] text;
      String line = "+-----+------------+------------+----------------------+---------------+";
      System.out.format(line+"\n");
      System.out.format("| Pos | Start date | End date   | Guest Name           | Guest ID      |\n");
      System.out.format(line+"\n");
      String format = "| %-3d | %-10s | %-10s | %-20s | %-13s |%n";
      for(int i = 0; i< list.size();i++){
         text = list.get(i).toString().split(",");
         System.out.format(format, i+1,text[0],text[1],text[2],text[3]);
      }
      System.out.format(line+"\n"); 
   }
   public void printMenu(){
      ArrayList<String> tasks = new ArrayList<String>();
      tasks.add("Add new booking"); tasks.add("Edit booking");
      tasks.add("Delete booking");
      tasks.add("Available rooms");
      tasks.add("Booked rooms");
      tasks.add("Exit");
      String line = "+-----+-----------------+";
      System.out.format(line+"\n");
      System.out.format("| Pos | Task            |\n");
      System.out.format(line+"\n");
      String format = "| %-3s | %-15s |%n";
      for(int i = 0; i< tasks.size();i++){
         System.out.format(format,i+1+".",tasks.get(i));
      }
      System.out.format(line+"\n"); 
   }

   public void successfullAdd()
   {
      System.out.println("Booking have been added successfully");  
   }
   public void wrongDate(){
      System.out.println("Wrong format of date - (dd/mm/yyyy)");
   }
   public void wrongInput()
   {
      System.out.println("You cannot book the room in selected time period");
   }
   public void wrongChoice(){
      System.out.println("Wrong input");
   }
   public void endBeforeStart()
   {
      System.out.println("End date should be after start date");
   }
   public void outOfRange()
   {
      System.out.println("Write a number in the range 1-6");
   }
   public void noBookings()
   {
      System.out.println("There are no booking for selected room");   
   }
}
