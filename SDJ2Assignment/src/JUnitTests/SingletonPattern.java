package JUnitTests;

import static org.junit.Assert.*;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import org.junit.Test;

import Hotel.Implementation;

public class SingletonPattern
{
   @Test
   public void test() throws RemoteException,
   ClassNotFoundException, MalformedURLException,
   AlreadyBoundException
   {
      Implementation  impl1 = Implementation.getInstance();
      Implementation impl2 = Implementation.getInstance();
      assertEquals(impl1.hashCode(), impl2.hashCode());
      assertEquals(impl1, impl2);
   }
}
