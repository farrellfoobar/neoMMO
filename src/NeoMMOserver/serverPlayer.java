package NeoMMOserver;

import java.util.ArrayList;
import NeoMMOshare.Tile;
import NeoMMOshare.gameMap;

public class serverPlayer
{
	private int x, y;
	private int movementPoints = 4;
	private int maxMovementPoints = 8;
	private int movementPerTurn = 4;
	private int viewX = 9, viewY = 5;
	
	public void increment()	//increment shouldn't exist in PlayerInterface as only the server should be able to call it
	{
		if( movementPoints + movementPerTurn <  maxMovementPoints)
			movementPoints += movementPerTurn;
	}
	
	public gameMap move(String xin, String yin)	//NOTE: this method returns Boolean not boolean: Boolean is an object wrapper that allows us to call toString.
	{
		int x = Integer.parseInt(xin);
		int y = Integer.parseInt(yin);
		boolean out = false;
		if( Math.abs( this.x - x) + Math.abs( this.y - x ) <= movementPoints)
		{
			this.x = x;
			this.y = y;
		}
		
		gameMap m = new gameMap(viewX, viewY);
		
		for( int xoff = 0; xoff < viewX; xoff++)
			for( int yoff = 0; yoff < viewY; yoff++ )
			{
				m.setTile(xoff, yoff, gameServer.map.getTile(xoff, yoff));
			}

		return m;
	}
	
	public gameMap getView()	//since move returns the map this method is for when the client first connects
	{
		System.out.println("at getView()");
		gameMap m = new gameMap(viewX, viewY);
		
		for( int xoff = 0; xoff < viewX; xoff++)
			for( int yoff = 0; yoff < viewY; yoff++ )
			{
				m.setTile(xoff, yoff, gameServer.map.getTile(this.x + xoff, this.y + yoff));
				System.out.println( gameServer.map.getTile(xoff, yoff) );
			}
		
		System.out.println("dim before return: " + m.getMapWidth() + m.getMapHeight() );
		System.out.println("map size: " + m.getMapWidth() + ", " + m.getMapHeight());
		return m;
	}
}
