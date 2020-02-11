package Client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import ArrayList.ArrayListImpl;
import Room.Room;

public class View {
   private Scanner scanner;
   
   public View(){
      scanner = new Scanner(System.in);
   }
   
   public void printMenu(){
      ArrayList<String> tasks = new ArrayList<String>();
      tasks.add("Print all available rooms");
      tasks.add("Print all booked rooms");
      tasks.add("Exit the program");
      String line = "+-----+---------------------------+";
      System.out.format(line+"\n");
      System.out.println("| THE DEER ALLEY HOTEL 2          |");
      System.out.format(line+"\n");
      String format = "| %-3s | %-25s |%n";
      for(int i = 0; i< tasks.size();i++){
         System.out.format(format,i+1+".",tasks.get(i));
      }
      System.out.format(line+"\n"); 
   }
   public void enterDate() {
      System.out.println("Enter a date");
   }
   public void enterStartDate() {
      System.out.println("Enter a start date (dd/mm/yyyy)");
   }
   public void enterEndDate() {
      System.out.println("Enter an end date (dd/mm/yyyy)");
   }

   public void updateViewForBooked(ArrayListImpl arrayList) {
      System.out.println("Booked rooms:");
      printRooms(arrayList);
   }
   public void updateViewForAvailable(ArrayListImpl list) {
      System.out.println("Available rooms:");
      printRooms(list);
   } 

   public void printRooms(ArrayListImpl arrayList){
      String[] text;
      String line = "+--------+-----------------+----------+---------+";
      System.out.format(line+"\n");
      System.out.format("| Number | Room type       | Capacity | Price   |\n");
      System.out.format(line+"\n");
      String format = "| %-6d | %-15s | %-8d | %-7.2f |%n";
      Iterator<Room> it = arrayList.iterator();
      while(it.hasNext()){
         text = it.next().toString().split(",");
         System.out.format(format, Integer.parseInt(text[0]),text[1],Integer.parseInt(text[2]),Double.parseDouble(text[3]));
      }

      System.out.format(line+"\n");
   }

   public void wrongDate(){
      System.out.println("Wrong format of date - (dd/mm/yyyy)");
   }
   public void wrongInput(){
      System.out.println("You cannot see rooms in selected time period");
   }
   public void dateValidator(){
      System.out.println("The date should be after today's date");
   }
   public void endBeforeStart(){
      System.out.println("End date should be after start date");       
   }
   public void outOfRange(){
      System.out.println("Write a number in range 1-3");
   }
   
   public void exitTheProgram() {
      System.out.println("Progam has been terminated");
   }
   public String stringInput(){
      return scanner.nextLine();
   }
}