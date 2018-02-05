package NeoMMOshare;

import java.util.Random;

public class Tile 
{
	private String tile;
	Random rand = new Random();
	static final String[] images = {"Cabin.png", "City.png", "Forest.png", "Hill.png", "PlainGrass.png", "PlainRiver.png"};
	
	public Tile()
	{
		tile = images[ rand.nextInt(images.length) ];
	}
	
}
