package NeoMMOshare;

import java.util.ArrayList;

import NeoMMOserver.gameServer;

public class gameMap 
{
	private ArrayList< ArrayList< Tile > > map; //calling this map makes this class very confusing
	
	public gameMap(int size)
	{
		this.map = new ArrayList< ArrayList< Tile > >();
		
		for( int x = 0; x < size; x++)
		{
			this.map.set(x, new ArrayList<Tile>() );
			for( int y = 0; y < size; y++)
				this.map.get(x).set(y, new Tile() );
		}
	}
	
	public gameMap(int width, int length)
	{
		this.map = new ArrayList< ArrayList< Tile > >();
		
		for( int x = 0; x < width; x++)
		{
			this.map.set(x, new ArrayList<Tile>(length) );
			for( int y = 0; y < length; y++)
				this.map.get(x).set(y, new Tile() );
		}
	}
	
	public gameMap()
	{
		this.map = new ArrayList< ArrayList< Tile > >();
	}

	public void setTile(int x, int y, Tile t)
	{
		this.map.get(x).set(y, t);
	}
	
	private int[][] getAdjacent(int x, int y)
	{
		return new int[][] { {x-1,y}, {x,y-1}, {x+1,y}, {x,y+1}, {x+1,y+1}, {x-1,y-1} };
	}
	
	public Tile getTile(int x, int y)
	{
		return map.get(x).get(y);
	}
}
