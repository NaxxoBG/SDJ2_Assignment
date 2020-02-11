package Room;

public class TwinRoom extends Room
{
   private static final long serialVersionUID = 1L;

   public TwinRoom(int roomNumber) {
      super(roomNumber);
      super.price = 220;
      super.capacity = 2;
   }

}
