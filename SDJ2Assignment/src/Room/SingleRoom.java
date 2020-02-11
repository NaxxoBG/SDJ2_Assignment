package Room;

public class SingleRoom extends Room
{
   private static final long serialVersionUID = 1L;

   public SingleRoom(int roomNumber) {
      super(roomNumber);
      super.price = 110;
      super.capacity = 1;
   }
}