package NeoMMOserver;

import java.io.File;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.rmi.registry.*; 

public class gameServer extends UnicastRemoteObject implements RMIserverInterface {
    public static final String MESSAGE = "Is this actually working?";

    public gameServer() throws RemoteException 
    {
    	//super(1099);
        super(1099);    // required to avoid the 'rmic' step, see below
    }

    public String getMessage() {
        return MESSAGE;
    }

    public static void main(String args[]) throws Exception 
    {
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
    }
}