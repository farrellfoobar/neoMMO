package NeoMMOserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.rmi.registry.*; 

public class gameServer extends UnicastRemoteObject implements serverInterface {

    private myObject obj = new myObject();
    
    public gameServer() throws RemoteException 
    {
    	//super(1099);
        super(1099);    // required to avoid the 'rmic' step, see below
    }

    public String getContent() 
    {
        return obj.getContent();
    }

    public static void main(String args[]) throws Exception 
    {
    	ServerSocket listener = new ServerSocket(9090);
        Scanner in = new Scanner(System.in);

        Socket socket = listener.accept();
        
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input =new BufferedReader(new InputStreamReader( socket.getInputStream() ));
        String scannerBuffer = "";
        String temp = "";
        String responce = null;
        
        while (true)
        {		/*
                while( in.hasNext() )
                {
                        out.println( in.next() );
                }
                */
    			responce = input.readLine();
    			if( responce != null)
    				 System.out.println( responce );
        }
    }
}