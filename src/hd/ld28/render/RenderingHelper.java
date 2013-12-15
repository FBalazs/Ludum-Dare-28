package hd.ld28.render;

import java.awt.Graphics;
import java.awt.Image;

public class RenderingHelper
{
	public static void fillTexturedRect(Graphics g, int x, int y, int width, int height, int textureId)
	{
		Image img = Texture.getTextureById(textureId);
		g.drawImage(img, x, y, x+width, y+height, 0, 0, img.getWidth(null), img.getHeight(null), null);
	}
}
