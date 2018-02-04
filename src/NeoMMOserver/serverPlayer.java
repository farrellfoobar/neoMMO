package NeoMMOserver;

import java.util.ArrayList;
import NeoMMOshare.Tile;

public class serverPlayer
{
	private int x, y;
	private int movementPoints = 4;
	private int maxMovementPoints = 8;
	private int movementPerTurn = 4;
	private int viewX = 10, viewY = 6;
	
	public void increment()	//increment shouldn't exist in PlayerInterface as only the server should be able to call it
	{
		if( movementPoints + movementPerTurn <  maxMovementPoints)
			movementPoints += movementPerTurn;
	}
	
	public ArrayList< ArrayList<Tile >> move(String xin, String yin)	//NOTE: this method returns Boolean not boolean: Boolean is an object wrapper that allows us to call toString.
	{
		int x = Integer.parseInt(xin);
		int y = Integer.parseInt(yin);
		boolean out = false;
		if( Math.abs( this.x - x) + Math.abs( this.y - x ) <= movementPoints)
		{
			this.x = x;
			this.y = y;
			out = true;
		}
		
		ArrayList< ArrayList<Tile> > visable = new ArrayList< ArrayList<Tile> >();
		
		
		for( int xoff = -viewX/2; xoff <= viewX/2; xoff++)
			for( int yoff = -viewY/2; yoff <= viewY/2; yoff++ )
				visable.get( xoff+viewX/2 ).set(yoff+viewX/2, gameServer.map.getTile(x+xoff, y+yoff) );
		
		return visable;
	}
}
