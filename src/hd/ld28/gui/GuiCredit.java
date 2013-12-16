package hd.ld28.gui;

import hd.ld28.Game;
import hd.ld28.render.RenderingHelper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GuiCredit extends Gui
{
	public GuiButton btnBack;
	
	public GuiCredit(Gui parent, int x, int y, int width, int height)
	{
		super(parent, x, y, width, height);
	}
	
	@Override
	public void init()
	{
		this.isFocused = true;

		this.btnBack = new GuiButton(this, this.width/2-86, this.height*9/10-23, 86*2, 23*2, "Back");
		this.btnBack.isFocused = true;
		
		this.subs.add(this.btnBack);
		
		super.init();
	}
	
	@Override
	public void update(int mouseX, int mouseY, boolean pressed)
	{
		super.update(mouseX, mouseY, pressed);
		
		if(this.btnBack.wasClicked())
			Game.instance.setCurrentGui(this.parent);
	}
	
	@Override
	public void render(Graphics g)
	{
		g.setColor(new Color(0, 0, 0, 128));
		g.fillRect(0, 0, this.width, this.height);
		
		g.setColor(Color.white);
		g.setFont(new Font("SansSerif", Font.PLAIN, 20));
		
		RenderingHelper.drawCenteredString(g, this.width/2, this.height/2-25, "Tamastom - destroying");
		RenderingHelper.drawCenteredString(g, this.width/2, this.height/2, "Dawars - textures and programming");
		RenderingHelper.drawCenteredString(g, this.width/2, this.height/2+25, "FBalazs - programming");
		
		super.render(g);
	}
}
