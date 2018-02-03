package NeoMMOserver;

import java.util.ArrayList;
import NeoMMOshare.Tile;

public class Map 
{
	private ArrayList< ArrayList< Tile > > layout; //calling this map makes this class very confusing
	
	public Map(int size)
	{
		ArrayList< ArrayList< Tile > > map = new ArrayList< ArrayList< Tile > >();
		
		for( int x = 0; x < size; x++)
			for( int y = 0; y < size; y++)
				this.layout.get(x).set(y, new Tile() );
	}
	
	public Map()
	{
		ArrayList< ArrayList< Tile > > map = new ArrayList< ArrayList< Tile > >();
	}
	
	public Map(int r, int x, int y)
	{
		ArrayList< ArrayList< Tile > > map = new ArrayList< ArrayList< Tile > >();
		
		for(int k = r, j = 0; k >= 0; k--, j++ )
			this.layout.get(k+x).set(j+y, gameServer.map.getTile(k+x, j+y) );
			
	}
	
	public Map getRadialMap(int r, int x, int y)
	{
		return new Map(r, x, y);
	}
	
	private int[][] getAdjacent(int x, int y)
	{
		return new int[][] { {x-1,y}, {x,y-1}, {x+1,y}, {x,y+1}, {x+1,y+1}, {x-1,y-1} };
	}
	
	public Tile getTile(int x, int y)
	{
		return layout.get(x).get(y);
	}
}