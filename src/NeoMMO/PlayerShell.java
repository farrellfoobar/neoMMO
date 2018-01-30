package NeoMMO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import NeoMMOshare.PlayerInterface;

public class PlayerShell implements PlayerInterface
{
    private Socket serverSocket;
    private BufferedReader input;
    private PrintWriter output;
    
	public PlayerShell(String ip, int port) throws UnknownHostException, IOException
	{
		Socket serverSocket  = new Socket(ip, port);
		BufferedReader input = new BufferedReader(new InputStreamReader( serverSocket.getInputStream() ));
		PrintWriter output = new PrintWriter(serverSocket.getOutputStream(), true);
		
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
                	move(x, y);
                         break;
                case "getPosition":  getPosition();
                         break;
                default: System.out.println( "Not a valid move" );
                         break;
            }
        		output.println( in.next() );
            }
        	
        	responce = input.readLine();
        	if( responce != null)
        		System.out.println( responce );
            
        }
	}
	
	@Override
	public boolean move(String x, String y) 
	{
		output.write("move," + x + "," + y);
		return false;
	}

	@Override
	public int[] getPosition() 
	{
		// TODO Auto-generated method stub
		return null;
	}

}