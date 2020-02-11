package Room;

public class KingRoom extends Room
{ 
   private static final long serialVersionUID = 1L;

   public KingRoom(int roomNumber) {
      super(roomNumber);
      super.price = 170;
      super.capacity = 2;
   }
}
