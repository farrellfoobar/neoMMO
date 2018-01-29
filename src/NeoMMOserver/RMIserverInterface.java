package NeoMMOserver;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIserverInterface extends Remote 
{
    public String getMessage() throws RemoteException;
}