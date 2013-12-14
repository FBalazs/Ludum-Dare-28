package hd.ld28.render;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class RenderingHelper
{
	public static void drawTexturedRect(Graphics g, int x, int y, int width, int height, int textureId)
	{
		BufferedImage img = Texture.getTextureById(textureId);
		g.drawImage(img, x, y, x+width, y+height, 0, 0, img.getWidth(), img.getHeight(), null);
	}
}
