package hd.ld28;

import hd.ld28.render.Texture;
import hd.ld28.world.Tile;
import hd.ld28.world.World;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	public static Game instance;
	
	
	
	public GameApplet applet;
	public InputHandler input;
	public Random random;
	public World world;
	public boolean isRunning;
	
	public final int requestedUPS = 50;
	public float partialTick;
	public int ups, rps;
	private int cups, crps;
	private long currentTime, lastUpdateTime, lastClockTime;
	
	public Game(GameApplet applet)
	{
		instance = this;
		this.applet = applet;
		this.input = new InputHandler(this);
		this.random = new Random(System.currentTimeMillis());
	}
	
	public void init()
	{
		Texture.init();
		Tile.init();
		this.world = new World();
		this.addKeyListener(this.input);
		this.addMouseListener(this.input);
		this.addMouseWheelListener(this.input);
		this.addMouseMotionListener(this.input);
	}
	
	public void onKeyTyped(char c, int i)
	{
		
	}
	
	public void onMouseClicked(int x, int y, int button)
	{
		
	}
	
	public void onMouseWheelMoved(int amount)
	{
		this.world.TILE_SIZE += amount;
		if(this.world.TILE_SIZE < 4)
			this.world.TILE_SIZE = 4;
		if(this.world.TILE_SIZE > 64)
			this.world.TILE_SIZE = 64;
	}
	
	public void update()
	{
		this.world.update();
	}
	
	public void render()
	{
		if(this.getBufferStrategy() == null)
			this.createBufferStrategy(2);
		Graphics g = this.getBufferStrategy().getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, this.applet.getWidth(), this.applet.getHeight());
		
		this.world.render(g, this.partialTick);
		
		if(this.isRunning)
		{
			if(this.getBufferStrategy() == null)
				this.createBufferStrategy(2);
			g.dispose();
			this.getBufferStrategy().show();
		}
	}
	
	@Override
	public void run()
	{
		this.isRunning = true;
		while(this.isRunning)
		{
			this.currentTime = System.currentTimeMillis();
			
			if(this.currentTime-this.lastClockTime >= 1000L)
			{
				this.applet.showStatus("UPS: "+this.cups+" RPS: "+this.crps);
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
			this.render();
			this.crps++;
		}
	}
	
	public void destroy()
	{
		
	}
}
