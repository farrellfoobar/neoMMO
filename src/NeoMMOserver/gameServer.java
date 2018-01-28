package NeoMMOserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class gameServer 
{

	public static void main(String[] args) throws IOException
	{
		ServerSocket listener = new ServerSocket(9090);
		Scanner in = new Scanner(System.in);
	 
		Socket socket = listener.accept();
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	 
		String scannerBuffer = "";
		String temp = "";
	 
		while (true)
		{
			while( in.hasNext() )
			{
				out.println( in.next() );
			}
		}
	}         
         
}
