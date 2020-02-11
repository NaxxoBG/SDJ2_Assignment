package Hotel;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import Server.Server;

public class MainMVC
{

   @SuppressWarnings("unused")
   public static void main(String[] args) throws ClassNotFoundException, RemoteException, MalformedURLException, AlreadyBoundException{
      Server server = Server.getInstance();
      Model model = new Model();
      View view = new View();
      Controller controller = new Controller(view, model);
   }
}
