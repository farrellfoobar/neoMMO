package NeoMMOserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/*
 * Clients exist on a thread that reads in and store a client's commands. The server's core loop reads 
 * in a command from a client's BufferedReader, input, and acts on it.
 */

public class Client extends Thread
{
	private Socket socket = null;
    private BufferedReader input = null;
    private PrintWriter output = null;
    private Method[] methods;
    Player player = new Player();
	public int hash;
	//public player; //ingame representation of client
	
	//multiple clients use the same socket so this socket is passed to the client;
	public Client(Socket socket) throws IOException
	{
		methods = Player.class.getDeclaredMethods();
		
		try
		{
			this.socket = socket;
	        input = new BufferedReader(new InputStreamReader( socket.getInputStream() ));	//input is how the client listens for command from the real client
	        output = new PrintWriter(socket.getOutputStream(), true);						//output is how the client responds to the real client
		}
		catch(Exception e)
		{
			e.printStackTrace();
			endConnection();
		}
		//No two users will have the same ip + port, but to avoid worrying about leaking someone's ip when using their client's hash, I hash their ip+port with String.hashCode()
		this.hash = ( (socket.getInetAddress().toString() + socket.getPort() ).replace(".", "") ).hashCode();
		start();
	}
	
	@Override
	public void run()
	{
		System.out.println("Client Thread Started!");
		String command;
		String[] methodArgs;
		String method;
		try 
		{
			System.out.println("Waiting for command");
			command = input.readLine();
			System.out.println("got command");
		} catch (IOException e) 
		{
			e.printStackTrace();
			try{ endConnection(); }catch(Exception e1){};
			return;
		}
		
		methodArgs = command.substring(command.indexOf(",")+1, command.length() ).split(",");
		method = command.substring(0, command.indexOf(",") );
		//EX: if commands = "move,3,4" then method = "move", and methodArgs = ["3","4"]
		
		for( int i = 0; i < methods.length; i++ )
		{

			if( methods[i].getName().equals(method) )
			{
				try 
				{
					Object b = methods[i].invoke(player, methodArgs);
					System.out.println( "method out: " + b );
					output.println( b.toString() );	//call the method and 
					System.out.println("Client asked to: " + methods[i].getName() );
				} 
				catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
				output.println("false");
		}
		
		//Do something with command
	}
	
	public void endConnection()
	{
		if( output != null )
			this.output.close();
		try 
		{
			if(input != null)
				this.input.close();
			
			if(socket != null)
				this.socket.close();
			
		} catch (IOException e) 
		{
			System.out.println( "***IMPORTANT: Some resources may not have been closed." );
			e.printStackTrace();
		}
		
	}
	
	@Override 
	public boolean equals(Object obj)
	{
		return this.hashCode() == obj.hashCode();
	}
	
	@Override
	public int hashCode()
	{
		return hash;
	}
}
