package hd.ld28.gui;

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
		// TODO
		super.render(g);
	}
}
