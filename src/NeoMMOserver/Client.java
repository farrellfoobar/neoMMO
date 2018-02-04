package NeoMMOserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private ObjectInputStream input = null;
    private ObjectOutputStream output = null;
    private Method[] methods;
    serverPlayer player = new serverPlayer();
	public int hash;
	gameServer host;
	
	//multiple clients use the same socket so this socket is passed to the client;
	public Client(Socket socket, gameServer host) throws IOException
	{
		methods = serverPlayer.class.getDeclaredMethods();
		this.host = host;
		try
		{
			this.socket = socket;
			output = new ObjectOutputStream( socket.getOutputStream() );
			output.flush();
			input = new ObjectInputStream( socket.getInputStream() );
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
		String command = null;
		String[] methodArgs;
		String method;
		boolean isConnected = true;
		int i;
		
		while(isConnected) //client loop
		{
			
			try 
			{
				System.out.println("waiting for command!");
				command = input.readObject().toString();
				System.out.println("Got command from client: " + command);
			} catch (IOException e) 
			{
				//IOException here probably means the cliend disconnected
				isConnected = false;
				
				try
				{ 
					endConnection(); 
				}
				catch(Exception e1)
				{
					System.out.println("WARNING: Some resources may not have been freed.");
				};
				
				System.out.println("Here!");
				return;
			} catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
			
			methodArgs = command.substring(command.indexOf(",")+1, command.length() ).split(",");
			method = command.substring(0, command.indexOf(",") );
			//EX: if commands = "move,3,4" then method = "move", and methodArgs = ["3","4"]
			
			i = 0;
			while( !methods[i].getName().equals(method) )
				i++;
			
			try 
			{
				output.writeObject( methods[i].invoke(player, methodArgs) );	//call the method and send the client the result
			} 
			catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e) 
			{
				e.printStackTrace();
			}
			
		}//end client loop
		System.out.println("Here 2!;");
		return;
	}
	
	public void endConnection()
	{
		try 
		{
			if( output != null )
				this.output.close();
			
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
