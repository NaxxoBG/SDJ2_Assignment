package ArrayList;

import java.io.Serializable;
import java.util.Iterator;

import Room.Room;

public class ArrayListImpl  implements IArrayList, Iterable<Room>, Serializable
{
   private static final long serialVersionUID = 1L;
   private Room[] array;

   public ArrayListImpl(Room[] integers) {
      this.array = integers;
   }
   public ArrayListImpl(){
      array= new Room[0];
   }

   public Room get(int pos) {
      if (pos > array.length) {
         System.out.println("The position is out of bounds.");
      } else {
         return array[pos];
      }
      return null;
   }

   public void set(int pos, Room val) {
      array[--pos] = val;
   }

   public void add(Room value) {
      Room[] help = new Room[array.length+1];

      for (int i = 0; i < array.length; i++) {
         help[i] = array[i];
      }
      help[array.length] = value;

      array = help;
   }

   public void remove(int pos) {
      Room[] help = new Room[array.length-1];
      boolean add = false;

      for (int i = 0; i < array.length; i++) {
         if (i == (pos-1)) {
            add = true;
            continue;
         }
         if(!add) {
            help[i] = array[i];
         } else {
            help[i-1] = array[i];
         }
      }
      array = help;
   }

   public int size() {
      return array.length;
   }

   public void clear() {
      //Arrays.fill(integers,0);
      Room[] newArray = new Room[0];
      array = newArray;
   }

   public boolean isEmpty() {
      return array.length == 0;   
   }

   @Override
   public void print() {
      if (array.length == 0) {
         System.out.println("\nPRINT| The arraylist is empty");
      } else {
         System.out.print("\nPRINT| -> ");
         for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "| ");
         }
      }
      
   }
   @Override
   public Iterator<Room> iterator()
   {
      Iterator<Room> it = (Iterator<Room>) new Iterator<Room>()
      {    
         int i = 0;
         @Override
         public boolean hasNext()
         {
            if(i<array.length)return true;
            else return false;
         }

         @Override
         public Room next()
         {
            return array[i++];
         }};
      
      return  it;
   }

}