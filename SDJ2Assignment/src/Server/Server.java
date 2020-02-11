package Server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import Hotel.Implementation;
import SharedInterface.SharedInterface;

@SuppressWarnings("serial")
public class Server extends UnicastRemoteObject
{
   private static Server server=null;
   private Implementation impl;
   
   @SuppressWarnings("rawtypes")
   private Server() throws RemoteException, ClassNotFoundException, MalformedURLException, AlreadyBoundException {
      super();
      impl = Implementation.getInstance();
      SharedInterface inter = (SharedInterface) UnicastRemoteObject.exportObject(impl, 0);
      LocateRegistry.createRegistry(1099);
      Naming.bind("rmi://localhost:1099/Server", inter);    
   }
   
   public static  Server getInstance() throws RemoteException, ClassNotFoundException, MalformedURLException, AlreadyBoundException{
      if(server==null)server = new Server();
      return server;
   }
}