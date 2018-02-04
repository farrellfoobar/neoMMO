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
	//Server Vars:
	public final int port = 9090;
	public final int maxPlayers = 10;
	public int currentPlayers = 0;
	ArrayList<Client> clients = new ArrayList<Client>(maxPlayers);
	static gameServer server;
	ServerSocket listener;
	public static int mapSize = 100;	
	public static gameMap map = new gameMap();
	//Time keeping Vars
	public final int milisPerRound = 10000;
	public final int tickPeriod = 10;
	public final int tickPeriodPerRound = milisPerRound/tickPeriod;
	int tickPeriods = 0;
	
public static void main(String[] args) throws IOException
{
	server = new gameServer();
}

public gameServer() throws IOException
{
	listener = new ServerSocket(port);
	listener.setSoTimeout(1000);

	Timer timer = new Timer();
	
	//this timer causes run() to run on a seperate thread once every tickPeriod milliseconds, but allows the constructor to continue execution
	//pruneClients is nonblocking so it can occur on the timer thread
	timer.schedule( new TimerTask()	
	{
		public void run()
		{
			tickPeriods++;
			pruneClients();
			
			if( tickPeriods >= tickPeriodPerRound)
				increment();
		}
	}, 100, 100);
	
	//acceptClients() is blocking so it runs on the constructor's thread
	while(true)
	{
		acceptClients();
	}
}

//This loop handles passive things that the client did not command, like timekeeping, bleeding etc
public void increment()
{
	System.out.println("Incrementing!");
	
	for( Client c : clients)
	{
		c.player.increment();
	}
}

public void pruneClients()
{
	//System.out.println("Updating Client Connections");
	for(Client c : clients)
	{
		System.out.println("testing alive");
		if( !c.isAlive() )
		{
			System.out.println("isntAlive");
			clients.remove(c);
			currentPlayers--;
			System.out.println("Player disconnected! at " + currentPlayers + "/" + maxPlayers);
		}
	}
}

//This method is blocking
public void acceptClients()
{
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
}
         
}
