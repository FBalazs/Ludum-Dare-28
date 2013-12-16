package hd.ld28.render;

import hd.ld28.Game;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Texture
{
	private static List<BufferedImage> textures;
	
	public static int BLANK,
					TILE_GRASS,
					TILE_LEAVES,
					TILE_TREE,
					TILE_WATER,
					TILE_ROCK,
					ENTITY_GIFT,
					ENTITY_PLAYER_GIFT;
	public static int[] ENTITY_PLAYER;
	public static int GUI_BUTTON;
	public static int[] ENTITY_CHILDREN;

	public static void init()
	{
		textures = new ArrayList<BufferedImage>();
		
		BLANK = 0;
		BufferedImage img = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);
		img.setRGB(0, 0, Color.black.getRGB());
		img.setRGB(1, 0, Color.yellow.getRGB());
		img.setRGB(0, 1, Color.yellow.getRGB());
		img.setRGB(1, 1, Color.black.getRGB());
		textures.add(img);
		
		TILE_GRASS = loadTexture("textures/tiles/grass.png");
		TILE_LEAVES = loadTexture("textures/tiles/leaves.png");
		TILE_TREE = loadTexture("textures/tiles/tree.png");
		TILE_WATER = loadTexture("textures/tiles/water.png");
		TILE_ROCK = loadTexture("textures/tiles/rock.png");
		ENTITY_GIFT = loadTexture("textures/entities/gift.png");
		ENTITY_PLAYER_GIFT = loadTexture("textures/entities/playerGift.png");

		ENTITY_PLAYER = new int[3];
		for(int i = 0; i < 3; i++)
			ENTITY_PLAYER[i] = loadTexture("textures/entities/player"+i+".png");

		ENTITY_CHILDREN = new int[3];
		for(int i = 0; i < 3; i++)
			ENTITY_CHILDREN[i] = loadTexture("textures/entities/kid"+(i+1)+".png");

		GUI_BUTTON = loadTexture("textures/gui/button.png");
	}
	
	public static int loadTexture(String path)
	{
		try
		{
			BufferedImage img = ImageIO.read(Game.instance.applet.getClass().getClassLoader().getResource(path));
			textures.add(img);
			return textures.size()-1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public static BufferedImage getTextureById(int id)
	{
		return textures.get(id);
	}
}