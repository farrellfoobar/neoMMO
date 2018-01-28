package NeoMMO;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class gameClient 
{
	static final String ip = "atlasNetwork.dynu.net";
	static final int port = 9090;
	
	public static void main(String[] args) throws IOException 
    {
		JFrame frame = new JFrame("NeoMMO");
		gameMap map = new gameMap(9, 5);
		frame.setPreferredSize( new Dimension(500, 500) );
		map.setPreferredSize( new Dimension(400, 400) );
		
		frame.add(map, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		
		try{
	        Socket serverSocket  = new Socket(ip, port);
	        BufferedReader input = new BufferedReader(new InputStreamReader( serverSocket.getInputStream() ));
	        PrintWriter output = new PrintWriter(serverSocket.getOutputStream(), true);
	    	Scanner in = new Scanner(System.in);
	        
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
		catch(java.net.ConnectException e)
		{
			System.out.println( "Could not connect to server. Is it up?" );
		}
        

    }
	
}

class gameMap extends JPanel
{
	
	static final String containerName = "NeoMMO";
	static final String[] images = {"Cabin.png", "City.png", "Forest.png", "Hill.png", "PlainGrass.png", "PlainRiver.png"};
	
	private int x, y;
	
	public gameMap(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void paint( Graphics g )
	{
		File temp = new File("");
		//path should == absolute path + \NeoMMO\assets\PlainGrass.png
		String path = temp.getAbsolutePath().substring(0, temp.getAbsolutePath().lastIndexOf( containerName )+containerName.length() ) + "\\assets\\";
		BufferedImage[] imgs = new BufferedImage[images.length];
		Random rand = new Random();
		
		try
		{
			for( int i = 0; i < images.length; i++ )
			{
				imgs[i] = ImageIO.read( new File( path + images[i] ) );
			}
			
			for(int x = 0; x < this.x; x++)
				for(int y = 0; y < this.y; y++)
					if( x % 2 == 0)
						g.drawImage( imgs[rand.nextInt(6)], 205*x, 200*y, null);
					else
						g.drawImage( imgs[rand.nextInt(6)], 205*x, 200*y+100, null);
			
		}
		catch(IOException e)
		{
			System.out.println(e);
			System.out.println("Can't find assets file, try pulling again.");
		}
		
	}
}
