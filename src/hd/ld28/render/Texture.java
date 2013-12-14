package hd.ld28.render;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Texture
{
	private static List<BufferedImage> textures;
	
	public static void init()
	{
		textures = new ArrayList<BufferedImage>();
		
		
	}
	
	public static int loadTexture(String path)
	{
		try
		{
			BufferedImage img = ImageIO.read(new File(path));
			textures.add(img);
			return textures.size()-1;
		}
		catch(IOException e)
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