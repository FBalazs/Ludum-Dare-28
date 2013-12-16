package hd.ld28.gui;

import hd.ld28.Game;
import hd.ld28.render.RenderingHelper;
import hd.ld28.world.World;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GuiGameOver extends Gui
{
	public long time;
	public boolean win;
	public GuiButton btnBack;
	
	public GuiGameOver(Gui parent, int x, int y, int width, int height, boolean win, long lifetime)
	{
		super(parent, x, y, width, height);
		
		this.time = lifetime/Game.instance.requestedUPS;
		this.win = win;
	}
	
	@Override
	public void init()
	{
		this.subs.clear();
		
		this.btnBack = new GuiButton(this, this.width/2-86, this.height*9/10-23, 86*2, 23*2, this.win ? "Next" : "Retry");
		
		this.subs.add(this.btnBack);
		
		super.init();
	}
	
	int s = 20, ds = 1, c = 0;
	
	@Override
	public void update(int mouseX, int mouseY, boolean pressed)
	{
		this.s += ds;
		if(this.s <= 20 || 40 <= this.s)
			this.ds *= -1;
		
		if(Game.instance.random.nextInt(Game.instance.requestedUPS/10) == 0)
			this.c = Game.instance.random.nextInt();
		
		if(this.btnBack.wasClicked())
		{
			if(this.win)
				World.SIZE *= 2;
			Game.instance.world = new World();
			Game.instance.setCurrentGui(null);
		}
		
		super.update(mouseX, mouseY, pressed);
	}
	
	@Override
	public void render(Graphics g)
	{
		g.setColor(new Color(0, 0, 0, 128));
		g.fillRect(0, 0, this.width, this.height);
		
		if(this.win)
		{
			g.setColor(new Color(this.c));
			g.setFont(new Font("SansSerif", Font.BOLD, this.s));
			RenderingHelper.drawCenteredString(g, this.width/2, this.height/2, "You won the game, after "+this.time+" sec!");
		}
		else
		{
			g.setColor(Color.red);
			g.setFont(new Font("SansSerif", Font.BOLD, 20));
			RenderingHelper.drawCenteredString(g, this.width/2, this.height/2, "You lost the game, after "+this.time+" sec!");
		}
		
		super.render(g);
	}
}
