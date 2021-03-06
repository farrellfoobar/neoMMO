package NeoMMOshare;

import java.io.Serializable;
import java.util.Random;

public class Tile implements Serializable
{
	private String img;
	Random rand = new Random();
	private static final String[] images = {"CabinBig.png", "CityBig.png", "ForestBig.png", "HillBig.png", "PlainGrassBig.png", "PlainRiverBig.png"};
	
	public Tile()
	{
		this.img = images[ rand.nextInt(images.length) ];
	}
	
	public Tile( String tile)
	{
		boolean contains = false;
		for( String s : images )
			if( s.equals(tile) )
			{
				contains = true;
				break;
			}
		
		if( !contains )
			throw new IllegalArgumentException();
		
		this.img = tile;
	}

	public Tile( Tile t )
	{
		this.img = t.img;
	}
	
	public String getImg()
	{
		return img;
	}
	
}
