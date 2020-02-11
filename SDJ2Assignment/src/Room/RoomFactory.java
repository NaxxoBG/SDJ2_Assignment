package Room;

import java.util.ArrayList;

public class RoomFactory
{
   public Room getRoom(String type, int roomNumber) {
      switch (type) {
         case "SingleRoom":
            return new SingleRoom(roomNumber);
         case "KingRoom":
            return new KingRoom(roomNumber);
         case "TwinRoom":
            return new TwinRoom(roomNumber);
         default:return null;
      }
   }

   public Room getRoom(String type, int roomNumber, int capacity) {
      return new Suite(roomNumber, capacity);
   }

   public  ArrayList<Room> createDefaultRooms() {
      ArrayList<Room> rooms = new ArrayList<Room>();

      for (int i = 1; i < 4; i++) {
         rooms.add(getRoom("SingleRoom", i));
      }

      for (int i = 4; i < 16; i++) {
         rooms.add(getRoom("KingRoom", i));
      }

      for (int i = 16; i < 22; i++) {
         rooms.add(getRoom("TwinRoom", i));
      }

      for (int i = 22; i < 24; i++) {
         rooms.add(getRoom("SuiteRoom", i, 2));
      }

      rooms.add(getRoom("SuiteRoom", 24, 3));

      return rooms;
   }
}