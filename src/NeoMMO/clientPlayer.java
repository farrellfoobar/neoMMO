package NeoMMO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import NeoMMOshare.Tile;
import NeoMMOshare.gameMap;

public class clientPlayer
{
    private Socket serverSocket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private gameMap view;
    
	public clientPlayer(String ip, int port) throws UnknownHostException, IOException, InterruptedException, ClassNotFoundException
	{
		serverSocket  = new Socket(ip, port);
		input = new ObjectInputStream( serverSocket.getInputStream() );
		output = new ObjectOutputStream( serverSocket.getOutputStream() );
		output.flush();
		
		output.writeObject("getView");
		view = (gameMap) input.readObject();
		
		Scanner in = new Scanner(System.in);
		
		String responce = null;
        while (true)
        {
        	
        	while( in.hasNext() )
            {
                switch ( in.nextLine() ) //once a UI is developed the UI will make these calls
                {
                case "move":  
                	System.out.println("enter your desired x");
                	String x = in.nextLine();
                	System.out.println("Enter your desired y");
                	String y = in.nextLine();
                	System.out.println( move(x, y) );
                         break;
                default: System.out.println( "Not a valid move" );
                         break;
                }
        		output.writeObject( in.next() );
            }
        	
        	try {
				responce = input.readObject().toString();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
        	if( responce != null)
        		System.out.println( responce );
            
        }
	}
	
	public Boolean move(String x, String y) 
	{
		System.out.println("move," + x + "," + y);
		Boolean out = false;
		try {
			output.writeObject("move," + x + "," + y);
			gameMap viewIn = ( (gameMap) input.readObject() );
			gameClient.view = viewIn;
		} catch (IOException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		System.out.println("Move request was successful?: " + out);
		return out;
	}

}
