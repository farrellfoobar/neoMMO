package NeoMMO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import NeoMMOshare.PlayerInterface;

public class clientPlayer implements PlayerInterface
{
    private Socket serverSocket;
    private BufferedReader input;
    private PrintWriter output;
    
	public clientPlayer(String ip, int port) throws UnknownHostException, IOException
	{
		serverSocket  = new Socket(ip, port);
		input = new BufferedReader(new InputStreamReader( serverSocket.getInputStream() ));
		output = new PrintWriter(serverSocket.getOutputStream(), true);
		
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
		System.out.println("move," + x + "," + y);
		boolean out = false;
		try {
			output.write("move," + x + "," + y);
			System.out.println("Here1");
			String in = input.readLine();
			System.out.println("Here2");
			out = Boolean.parseBoolean(in);
			System.out.println("Here3");
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(out);
		return out;
	}

}
