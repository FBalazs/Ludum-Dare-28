package hd.ld28.gui;

import hd.ld28.Game;

import java.awt.Color;
import java.awt.Graphics;

public class GuiMainMenu extends Gui
{
	public GuiButton btnResume,
					btnNewGame,
					btnControls,
					btnAbout,
					btnExit;
	
	public GuiMainMenu(Gui parent, int x, int y, int width, int height)
	{
		super(parent, x, y, width, height);
	}
	
	@Override
	public void init()
	{
		this.isFocused = true;
		this.btnResume = new GuiButton(this, this.width/2-86, this.height/2-23, 2*86, 2*23, "Resume Game");
		this.btnResume.isFocused = true;
		
		super.init();
	}
	
	@Override
	public void update(int mouseX, int mouseY, boolean pressed)
	{
		super.update(mouseX, mouseY, pressed);
		
		if(this.btnResume.wasClicked())
			Game.instance.setCurrentGui(null);
	}
	
	@Override
	public void render(Graphics g)
	{
		g.setColor(new Color(0, 0, 0, 128));
		g.fillRect(0, 0, this.width, this.height);
		super.render(g);
	}
}
