package NeoMMOserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class gameServer {

public static void main(String[] args) throws IOException
{
	ServerSocket listener = new ServerSocket(9090);
	Scanner in = new Scanner(System.in);

    Socket socket = listener.accept();
    BufferedReader input = new BufferedReader(new InputStreamReader( socket.getInputStream() ));
    PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

    String scannerBuffer = "";
    String temp = "";
    String responce = null;
    
    while (true)
    {
    	while( in.hasNext() )
        {
    		output.println( in.next() );
        }
    	
    	responce = input.readLine();
    	if( responce != null)
    		System.out.println( responce );
        
    }
}         
         
}
