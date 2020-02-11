package Guest;

import java.io.Serializable;

public class Guest implements Serializable
{

   private static final long serialVersionUID = 1L;
   private String fName;
   private String ID;

   public Guest(String fName, String ID){
      this.fName=fName;
      this.ID=ID;
   }

   public String getfName(){
      return fName;
   }

   public String getID()
   {
      return ID;
   }

}
