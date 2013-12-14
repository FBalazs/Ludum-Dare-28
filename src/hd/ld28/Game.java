package hd.ld28;

import java.awt.Color;
import java.awt.Graphics;

public class Game
{
	public static Game instance;
	
	
	
	public GameApplet applet;
	
	public final int requestedUPS = 50;
	public float partialTick;
	public int ups, rps;
	private int cups, crps;
	private long currentTime, lastUpdateTime, lastClockTime;
	
	public Game(GameApplet applet)
	{
		instance = this;
		this.applet = applet;
	}
	
	public void init()
	{
		
	}
	
	int x = 10, y = 10, dx = 1, dy = 0;
	
	public void update()
	{
		x += dx;
		y += dy;
		if(x < 10 || x > 100)
			dx *= -1;
		if(y < 10 || y > 100)
			dy *= -1;
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, this.applet.getWidth(), this.applet.getHeight());
		
		g.setColor(Color.red);
		g.fillRect(x, y, 30, 30);
	}
	
	public void runTick(Graphics g)
	{
		this.currentTime = System.currentTimeMillis();
		
		if(this.currentTime-this.lastClockTime >= 1000L)
		{
			System.out.println("UPS: "+this.cups+" RPS: "+this.crps);
			this.ups = this.cups;
			this.rps = this.crps;
			this.cups = 0;
			this.crps = 0;
			this.lastClockTime = this.currentTime;
		}
		
		this.partialTick = (this.currentTime-this.lastUpdateTime)*this.requestedUPS/1000F;
		if(this.partialTick >= 1F)
		{
			this.lastUpdateTime = this.currentTime;
			this.update();
			this.cups++;
			this.partialTick = 0F;
		}
		this.render(g);
		this.crps++;
	}
	
	public void destroy()
	{
		
	}
}
