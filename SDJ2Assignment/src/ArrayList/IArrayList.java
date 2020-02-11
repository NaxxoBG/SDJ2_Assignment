package ArrayList;

import Room.Room;

public interface IArrayList
{
   public Room get(int pos);

   public void set(int pos, Room val);

   public void add(Room value);

   public void remove(int pos);

   public int size();

   public void clear();

   public boolean isEmpty();

   public void print();
}