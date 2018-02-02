package NeoMMOserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * This class creates a server and deligates the acception of new connection to one thread while processing the clients' moves in another
 */

public class gameServer extends Thread
{
	public final int port = 9090;
	public final int maxPlayers = 10;
	public int currentPlayers = 0;
	ArrayList<Client> clients = new ArrayList<Client>(maxPlayers);
	
public static void main(String[] args) throws IOException
{
	gameServer server = new gameServer();
}

public gameServer() throws IOException
{
	ServerSocket listener = new ServerSocket(port);
	Socket socket;
	
	//start();
	
	while(true) //add/remove players loop
	{
		if(currentPlayers < maxPlayers)
		{
			clients.add( new Client( listener.accept() ) );
			currentPlayers++;
			System.out.println("Player connected! at " + currentPlayers + "/"  + maxPlayers);
		}
		
		for(Client c : clients)
			if( !c.isAlive() )
			{
				clients.remove(c);
				currentPlayers--;
				System.out.println("Player disconnected! at " + currentPlayers + "/" + maxPlayers);
			}
		
	}
}

//This loop handles passive things that the client did not command, like timekeeping, bleeding etc
@Override
public void run()
{
	for( Client c : clients )
	{
		
	}
}

         
}
