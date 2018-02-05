package NeoMMOshare;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import NeoMMOserver.gameServer;

public class gameMap extends JPanel implements Serializable
{
	private ArrayList< ArrayList< Tile > > map; //calling this map makes this class very confusing
	static final String containerName = "neoMMO";
	static final String[] images = {"Cabin.png", "City.png", "Forest.png", "Hill.png", "PlainGrass.png", "PlainRiver.png"};
	
	public gameMap(int size)
	{
		this.map = new ArrayList< ArrayList< Tile > >(size);
		
		for( int x = 0; x < size; x++)
		{
			this.map.add(x, new ArrayList<Tile>() );
			for( int y = 0; y < size; y++)
				this.map.get(x).add(y, new Tile() );
		}
	}
	
	public gameMap(int width, int length)
	{
		this.map = new ArrayList< ArrayList< Tile > >();
		
		for( int x = 0; x < width; x++)
		{
			this.map.add(x, new ArrayList<Tile>(length) );
			for( int y = 0; y < length; y++)
				this.map.get(x).add(y, new Tile() );
		}
	}
	
	public gameMap()
	{
		this.map = new ArrayList< ArrayList< Tile > >();
	}

	public void setTile(int x, int y, Tile t)
	{
		this.map.get(x).set(y, t);	//add moves other elements, set overwrites iit
	}
	
	private int[][] getAdjacent(int x, int y)
	{
		return new int[][] { {x-1,y}, {x,y-1}, {x+1,y}, {x,y+1}, {x+1,y+1}, {x-1,y-1} };
	}
	
	public Tile getTile(int x, int y)
	{
		return map.get(x).get(y);
	}
	
	@Override
	public void paint( Graphics g )
	{
		try
		{			
			for(int x = 0; x < map.size(); x++)
				for(int y = 0; y < map.get(0).size(); y++)
					if( x % 2 == 0)	//every other collumn is offset for hexagons
						g.drawImage( this.getTile(x, y).getImage(), 205*x, 200*y, null);
					else
						g.drawImage( this.getTile(x, y).getImage(), 205*x, 200*y+100, null);
			
		}
		catch(IOException e)
		{
			System.out.println(e);
			System.out.println("Can't find assets file, try pulling again.");
		}

	}
	
}
