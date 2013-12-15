package hd.ld28;

import hd.ld28.entity.Entity;
import hd.ld28.render.Texture;
import hd.ld28.world.Tile;
import hd.ld28.world.World;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
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
	
	public void drawProgress(int percent, String msg)
	{
		if(this.getBufferStrategy() == null)
			this.createBufferStrategy(2);
		Graphics g = this.getBufferStrategy().getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(Color.GREEN);
		g.setFont(new Font("SansSerif", Font.PLAIN, 20));
		
		g.drawString(msg, this.getWidth()/2-g.getFontMetrics().stringWidth(msg)/2, this.getHeight()/2-20);
		g.drawRect(this.getWidth()/8-2, this.getHeight()/2-12, this.getWidth()*2/3+4, 24);
		g.fillRect(this.getWidth()/8, this.getHeight()/2-10, this.getWidth()*2/3*percent/100+1, 21);
		
		g.dispose();
		if(this.getBufferStrategy() == null)
			this.createBufferStrategy(2);
		this.getBufferStrategy().show();
	}
	
	public void init()
	{
		this.drawProgress(0, "Loading...");
		Texture.init();
		this.drawProgress(33, "Loading...");
		Tile.init();
		this.drawProgress(66, "Loading...");
		this.world = new World();
		this.drawProgress(100, "Loading...");
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
		g.setColor(Color.yellow);
		for(Entity entity : this.world.entities)
			if(0 <= entity.x-mx && entity.x-mx < 128 && 0 <= entity.y-my && entity.y-my < 128)
				g.fillRect(this.applet.getWidth()-128+entity.x-mx, this.applet.getHeight()-128+entity.y-my, 1, 1);
		g.setColor(Color.red);
		g.fillRect(this.applet.getWidth()-128+this.world.player.x-mx-1, this.applet.getHeight()-128+this.world.player.y-my-1, 3, 3);
		
		if(this.getBufferStrategy() == null)
			this.createBufferStrategy(2);
		g.dispose();
		this.getBufferStrategy().show();
	}
	
	@Override
	public void run()
	{
		this.init();
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
		this.destroy();
	}
	
	public void destroy()
	{
		
	}
}
