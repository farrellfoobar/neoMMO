package NeoMMOshare;

/*
 * This interface makes sure that the NeoMMO.clientPlayer and NeoMMOserver.player  implement the same methods. 
 * The client makes calls on the clientPlayer which then sends a request to the server to make the same change on the server's copy.
 * If the change is valid (doesnt break any game rules) the server makes the change and returns true, and then the client makes the same change.
 * If the change is invalid the server returns false and the client communicates some kind of message to the user. 
 */

public interface PlayerInterface 
{
	public boolean move(String x, String y);
	
}
