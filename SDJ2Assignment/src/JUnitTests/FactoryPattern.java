package JUnitTests;

import static org.junit.Assert.*;
import org.junit.Test;
import Room.KingRoom;
import Room.Room;
import Room.RoomFactory;
import Room.SingleRoom;
import Room.Suite;
import Room.TwinRoom;

public class FactoryPattern
{
   RoomFactory factory = new RoomFactory();
   Room room;
   
   @Test
   public void test1(){
     room = factory.getRoom("SingleRoom", 10);   
     assertEquals(true, room instanceof Room);
     assertEquals(true, room instanceof SingleRoom);
     assertEquals(false, room instanceof KingRoom);
     assertEquals(false, room instanceof TwinRoom);
     assertEquals(false, room instanceof Suite);  
   }  
   @Test
   public void test2(){
     room = factory.getRoom("KingRoom", 10);    
     assertEquals(true, room instanceof Room);
     assertEquals(false, room instanceof SingleRoom);
     assertEquals(true, room instanceof KingRoom);
     assertEquals(false, room instanceof TwinRoom);
     assertEquals(false, room instanceof Suite);  
   }  
   @Test
   public void test3(){
     room = factory.getRoom("TwinRoom", 10); 
     assertEquals(true, room instanceof Room);
     assertEquals(false, room instanceof SingleRoom);
     assertEquals(false, room instanceof KingRoom);
     assertEquals(true, room instanceof TwinRoom);
     assertEquals(false, room instanceof Suite);  
   } 
   
   
   
   @Test
   public void test4(){
     room = factory.getRoom("Suite", 10,2);   
     assertEquals(true, room instanceof Room);
     assertEquals(false, room instanceof SingleRoom);
     assertEquals(false, room instanceof KingRoom);
     assertEquals(false, room instanceof TwinRoom);
     assertEquals(true, room instanceof Suite);  
   }
}
