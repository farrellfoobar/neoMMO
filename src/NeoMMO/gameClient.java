package NeoMMO;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import NeoMMOshare.gameMap;

public class gameClient 
{
	static final String ip = "atlasNetwork.dynu.net";
	static final int port = 9090;
	static protected gameMap view = new gameMap(1,1);
	static clientPlayer player;
	
	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException 
    {
		JFrame frame = new JFrame("NeoMMO");
		gameMap map = new gameMap(9, 5);
		frame.setPreferredSize( new Dimension(500, 500) );
		map.setPreferredSize( new Dimension(400, 400) );
		
		frame.add(map, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		
		player = new clientPlayer(ip, port);
    }
	
}

