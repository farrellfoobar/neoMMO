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
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
//To avoid copying code, and to keep the code consistent bettween classes this class imports the RMIserverInerface located in the NeoMMOserver but in the future
//when the server and client are seperated both will need a copy of the interface
import NeoMMOserver.RMIserverInterface;

public class gameClient 
{
	//static final String ip = "atlasNetwork.dynu.net";
	//static final int port = 9090;
	
	public static void main(String[] args) throws IOException, NotBoundException 
    {
		/*
		//UI
		JFrame frame = new JFrame("NeoMMO");
		gameMap map = new gameMap(9, 5);
		frame.setPreferredSize( new Dimension(500, 500) );
		map.setPreferredSize( new Dimension(400, 400) );
		
		frame.add(map, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		//END UI */
		
		//NETWORKING
        //RMIserverInterface obj = (RMIserverInterface)Naming.lookup("/atlasNetwork.dynu.net/RmiServer");
        //System.out.println(obj.getMessage());  
        
		
		final String ip = "atlasNetwork.dynu.net";
		Socket serverSocket = new Socket(ip, 9090);
		BufferedReader input =new BufferedReader(new InputStreamReader( serverSocket.getInputStream() ));
		 
		String responce = null; 
		while(true)
		{
			responce = input.readLine();
			if( responce != null)
				 System.out.println( responce );
		}
        
    }//end main
	
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
		String path = "assets" + File.separatorChar; 
		File f = new File(path + "City.png");
		System.out.println(f.getAbsolutePath());
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
