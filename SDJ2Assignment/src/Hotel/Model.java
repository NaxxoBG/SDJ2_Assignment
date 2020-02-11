package Hotel;

import java.util.Scanner;

public class Model
{
   private Scanner scan;
   private Implementation impl;

   public Model() throws ClassNotFoundException{
      scan = new Scanner(System.in);
      impl = Implementation.getInstance();
   }

   public Implementation getImplementation(){
      return impl;
   }

   public String stringInput(){
      return scan.nextLine();
   }

   

}
