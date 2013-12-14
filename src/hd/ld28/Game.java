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
		if(this.world.TILE_SIZE < 16)
			this.world.TILE_SIZE = 16;
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
		int mx = this.world.player.x-64;
		int my = this.world.player.y-64;
		if(mx < 0)
			mx = 0;
		if(mx > 128)
			mx = 128;
		if(my < 0)
			my = 0;
		if(my > 128)
			my = 128;
		g.drawImage(this.world.mapImage, this.applet.getWidth()-128, this.applet.getHeight()-128, this.applet.getWidth(), this.applet.getHeight(), mx, my, mx+128, my+128, null);
		g.setColor(Color.red);
		g.fillRect(this.applet.getWidth()-128+this.world.player.x-mx-1, this.applet.getHeight()-128+this.world.player.y-my-1, 3, 3);
		
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
