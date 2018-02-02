package NeoMMOserver;

import NeoMMOshare.PlayerInterface;

public class Player implements PlayerInterface
{
	private int x, y;
	private int movementPoints = 4;
	private int maxMovementPoints = 8;
	private int movementPerTurn = 4;
	
	public void increment()	//increment shouldn't exist in PlayerInterface as only the server should be able to call it
	{
		if( movementPoints + movementPerTurn <  maxMovementPoints)
			movementPoints += movementPerTurn;
	}
	
	@Override
	public Boolean move(String xin, String yin)	//NOTE: this method returns Boolean not boolean: Boolean is an object wrapper that allows us to call toString.
	{
		System.out.println("move got args: " + xin + " " +yin);
		int x = Integer.parseInt(xin);
		int y = Integer.parseInt(yin);
		boolean out = false;
		if( Math.abs( this.x - x) + Math.abs( this.y - x ) <= movementPoints)
		{
			this.x = x;
			this.y = y;
			out = true;
		}
		if(out)
		{
			System.out.print("Moving to: " + this.x + ", " + this.y);
		}
		return out;
	}
}
