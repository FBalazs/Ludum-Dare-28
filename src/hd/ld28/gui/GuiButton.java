package hd.ld28.gui;

import hd.ld28.render.RenderingHelper;
import hd.ld28.render.Texture;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GuiButton extends Gui
{
	public String text;
	public int state, clicks;
	
	public GuiButton(Gui parent, int x, int y, int width, int height, String text)
	{
		super(parent, x, y, width, height);
		this.text = text;
	}
	
	public boolean wasClicked()
	{
		if(this.clicks > 0)
		{
			this.clicks--;
			return true;
		}
		return false;
	}
	
	@Override
	public void onMouseClicked(int x, int y, int button)
	{
		if(button == 0 && this.x <= x && x <= this.x+this.width && this.y <= y && y <= this.y+this.height)
			this.clicks++;
		super.onMouseClicked(x, y, button);
	}
	
	@Override
	public void update(int mouseX, int mouseY, boolean pressed)
	{
		if(this.x <= mouseX && mouseX <= this.x+this.width && this.y <= mouseY && mouseY <= this.y+this.height)
			if(pressed)
				this.state = 2;
			else
				this.state = 1;
		else
			this.state = 0;
		super.update(mouseX, mouseY, pressed);
	}
	
	@Override
	public void render(Graphics g)
	{
		g.setColor(Color.white);
		RenderingHelper.fillTexturedRect(g, this.x, this.y, this.width, this.height, Texture.GUI_BUTTON[this.state]);
		g.setFont(new Font("SansSerif", Font.BOLD, 16));

		g.setColor(new Color(0x3a592d));
		RenderingHelper.drawCenteredString(g, this.x+this.width/2 + 2, this.y+this.height/2 + 2, this.text);

		if(this.state != 1)
		{
			g.setColor(new Color(0x213219));
			RenderingHelper.drawCenteredString(g, this.x+this.width/2, this.y+this.height/2, this.text);
		} else {
			g.setColor(new Color(0x213219));
			RenderingHelper.drawCenteredString(g, this.x+this.width/2 + 2, this.y+this.height/2 + 2, this.text);
		}
		super.render(g);
	}
}
