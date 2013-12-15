package hd.ld28.render;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class RenderingHelper
{
	public static void fillTexturedRect(Graphics g, int x, int y, int width, int height, int textureId)
	{
		BufferedImage img = Texture.getTextureById(textureId);
		g.drawImage(img, x, y, x+width, y+height, 0, 0, img.getWidth(), img.getHeight(), null);
	}
	
	public static void drawCenteredString(Graphics g, int x, int y, String str)
	{
		g.drawString(str, x-g.getFontMetrics().stringWidth(str)/2, y+g.getFontMetrics().getHeight()/3);
	}
}
