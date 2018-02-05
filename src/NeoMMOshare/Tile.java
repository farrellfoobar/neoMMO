package NeoMMOshare;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

import javax.imageio.ImageIO;

public class Tile implements Serializable
{
	private String tile;
	Random rand = new Random();
	private static final String[] images = {"Cabin.png", "City.png", "Forest.png", "Hill.png", "PlainGrass.png", "PlainRiver.png"};
	static final String containerName = "neoMMO";
	File temp;
	//path should == absolute path + \NeoMMO\assets\PlainGrass.png
	String path;;
	
	public Tile()
	{
		tile = images[ rand.nextInt(images.length) ];
	}
	
	public BufferedImage getImage() throws IOException
	{
		//temp and path are instantiated 
		temp = new File("");
		path = temp.getAbsolutePath().substring(0, temp.getAbsolutePath().lastIndexOf( containerName )+containerName.length() ) + "\\assets\\tiles\\";
		System.out.println(path + tile);
		
		return ImageIO.read( new File(path + tile) );
	}
}
