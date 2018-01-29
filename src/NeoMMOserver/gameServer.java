package NeoMMOserver;

import java.io.File;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*; 

public class gameServer extends UnicastRemoteObject implements RMIserverInterface {
    public static final String MESSAGE = "Hello World";

    public gameServer() throws RemoteException 
    {
    	//super(1099);
        super(1099);    // required to avoid the 'rmic' step, see below
    }

    public String getMessage() {
        return MESSAGE;
    }

    public static void main(String args[]) throws Exception {
        System.out.println("RMI server started");

        try { //special exception handler for registry creation
            LocateRegistry.createRegistry(1099); 
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }
        
        gameServer obj = new gameServer();
        
        // Bind this object instance to the name "RmiServer"
        Naming.rebind("/atlasNetwork.dynu.net/RmiServer", obj);
        
        //System.out.println("PeerServer bound in registry");
    }
}