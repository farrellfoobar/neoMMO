package NeoMMOshare;

import java.io.Serializable;
import java.util.Random;

public class Tile implements Serializable
{
	private String tile;
	Random rand = new Random();
	private static final String[] images = {"Cabin.png", "City.png", "Forest.png", "Hill.png", "PlainGrass.png", "PlainRiver.png"};
	
	public Tile()
	{
		tile = images[ rand.nextInt(images.length) ];
	}
	
}
