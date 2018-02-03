package NeoMMOserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import NeoMMOshare.Tile;
import NeoMMOshare.gameMap;

/*
 * This class creates a server and deligates the acception of new connection to one thread while processing the clients' moves in another
 */

public class gameServer extends Thread
{
	public final int port = 9090;
	public final int maxPlayers = 10;
	public int currentPlayers = 0;
	public final int milisPerRound = 10000;
	ArrayList<Client> clients = new ArrayList<Client>(maxPlayers);
	static gameServer server;
	ServerSocket listener;
	public static int mapSize = 100;	
	public static gameMap map = new gameMap();

	
public static void main(String[] args) throws IOException
{
	server = new gameServer();
}

public gameServer() throws IOException
{
	listener = new ServerSocket(port);

	Timer timer = new Timer();
	
	start();
	timer.schedule( new TimerTask()
	{
		public void run()
		{
			increment();
		}
	}, milisPerRound, milisPerRound);
}

//This loop handles passive things that the client did not command, like timekeeping, bleeding etc

public void run()
{
	while(true)
	{
		updateClientConnections();
	}
}

public synchronized void increment()
{
	System.out.println("Incrementing!");
	
	for( Client c : clients)
	{
		c.player.increment();
	}
}

public synchronized void updateClientConnections()
{
	System.out.println("Updating Client Connections");
	if(currentPlayers < maxPlayers)
	{
		Client temp;
		try
		{
			temp = new Client( listener.accept(), this );
			clients.add( temp );	
			currentPlayers++;
			System.out.println("Player connected! at " + currentPlayers + "/"  + maxPlayers);
		}
		catch( SocketTimeoutException e){} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	for(Client c : clients)
	{
		if( !c.isAlive() )
		{
			clients.remove(c);
			currentPlayers--;
			System.out.println("Player disconnected! at " + currentPlayers + "/" + maxPlayers);
		}
	}
}
         
}
