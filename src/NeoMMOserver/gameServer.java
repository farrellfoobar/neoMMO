package NeoMMOserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*; 

//This server/client setup uses RMI: Remote Method Invocation so that the client can simply call methods on the Server's object.
//This means that there is only one canonical version of every object, hosted on the server, modified by RMI calls made by the client.
//The outline of an RMI server and client on which this class is based exists here: https://en.wikipedia.org/wiki/Java_remote_method_invocation 
public class gameServer extends UnicastRemoteObject implements RMIserverInterface
{

    public gameServer() throws RemoteException 
    {
        super(0);
    }
	
	public static void main(String[] args) throws IOException 
	{	
        System.out.println("RMI server started");

        try { //special exception handler for registry creation
            LocateRegistry.createRegistry(9090); 
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }
           
        //Instantiate RmiServer

        gameServer obj = new gameServer();

        // Bind this object instance to the name "RmiServer"
        Naming.rebind("RmiServer", obj);
        System.out.println("PeerServer bound in registry");
		/*
		ServerSocket listener = new ServerSocket(9090);
		Scanner in = new Scanner(System.in);
	 
		Socket socket = listener.accept();
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	 
		String scannerBuffer = "";
		String temp = "";
	 
		while (true)
		{
			while( in.hasNext() )
			{
				out.println( in.next() );
			}
		}
		*/
	}
	
	public String getMessage()
	{
		return "Trying Linux.";
	}
         
}
