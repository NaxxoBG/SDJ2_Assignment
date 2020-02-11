package Client;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MainMVC
{

   @SuppressWarnings("unused")
   public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
      View view  = new View();
      ClientModel client = new ClientModel();   
      Controller control = new Controller(view, client);
   }
}