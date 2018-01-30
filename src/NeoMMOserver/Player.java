package NeoMMOserver;

public class Player 
{
	private int x, y;
	private int movementPoints = 0;
	private int maxMovementPoints = 8;
	private int movementPerTurn = 4;
	
	public void increment()
	{
		if( movementPoints + movementPerTurn <  maxMovementPoints)
			movementPoints += movementPerTurn;
	}
	
	public int[] getPosition()
	{
		return new int[] {x, y};
	}
	
	public boolean move(int x, int y)
	{
		boolean out = false;
		if( Math.abs( this.x - x ) + Math.abs( this.y - y ) <= movementPoints)
		{
			this.x = x;
			this.y = y;
			out = true;
		}
		return out;
	}
}
