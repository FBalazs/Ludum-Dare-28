package hd.ld28.world;

import hd.ld28.Game;
import hd.ld28.entity.Entity;
import hd.ld28.entity.EntityPlayer;
import hd.ld28.render.RenderingHelper;
import hd.ld28.render.Texture;
import hd.ld28.render.WorldRenderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World
{
	private int[][] tiles;
	public List<Entity> entities;
	public EntityPlayer player;
	
	public int TILE_SIZE = 32;
	
	public Random random;
	public WorldRenderer renderer;
	public BufferedImage mapImage;
	
	public World()
	{
		this.tiles = new int[256][256];
		this.entities = new ArrayList<Entity>();
		this.random = Game.instance.random;
		this.generate();
		int x = 127, y = 127;
		while(!Tile.tiles.get(this.tiles[x][y]).isWalkable())
		{
			x = this.random.nextInt(256);
			y = this.random.nextInt(256);
		}
		this.player = new EntityPlayer(this, x, y);
	}
	
	public Tile getTileAt(int x, int y)
	{
		return Tile.tiles.get(this.tiles[x][y]);
	}
	
	public int getTileIdAt(int x, int y)
	{
		return this.tiles[x][y];
	}
	
	public void generateBSP(int x, int y, int width, int height)
	{
		if((width >= 24 || height >= 24) && this.random.nextInt(width*height/4) != 0)
		{
			if(height >= 24 && (width < 24 || this.random.nextBoolean()))
			{
				int p = this.random.nextInt(height/3+1)+height/3;
				this.generateBSP(x, y, width, p);
				this.generateBSP(x, y+p, width, height-p);
			}
			else
			{
				int p = this.random.nextInt(width/3+1)+width/3;
				this.generateBSP(x, y, p, height);
				this.generateBSP(x+p, y, width-p, height);
			}
		}
		else if(width >= 12 && height >= 12)
		{
			int bl = this.random.nextInt(width/3);
			int br = this.random.nextInt(width/3);
			int bt = this.random.nextInt(height/3);
			int bb = this.random.nextInt(height/3);
			x += bl;
			width -= bl+br;
			y += bt;
			height -= bt+bb;
			//System.out.println("BSP x:"+x+" y:"+y+" w:"+width+" h:"+height);
			for(int i = x; i < x+width; i++)
				for(int j = y; j < y+height; j++)
					this.tiles[i][j] = Tile.TILE_GRASS;
		}
	}
	
	public void generateLakes()
	{
		for(int l = 0; l < 32; l++)
		{
			int x = this.random.nextInt(256);
			int y = this.random.nextInt(256);
			int w = this.random.nextInt(16);
			int h = this.random.nextInt(16);
			
			if(w > 0 && h > 0 && x+w < 256 && y+h < 256)
			{
				for(int i = x; i < x+w; i++)
					for(int j = y; j < y+h; j++)
						this.tiles[i][j] = Tile.TILE_WATER;
			}
		}
	}
	
	public void generate()
	{
		for(int i = 0; i < 256; i++)
			for(int j = 0; j < 256; j++)
				this.tiles[i][j] = this.random.nextInt(2);
				//this.tiles[i][j] = Tile.TILE_LEAVES;
		
		for(int a = 0; a < 5; a++)
		{
			int[][] ptiles = this.tiles;
			for(int i = 0; i < 256; i++)
				for(int j = 0; j < 256; j++)
				{
					int s = 0;
					for(int ni = i-1; ni <= i+1; ni++)
						for(int nj = j-1; nj <= j+1; nj++)
							if(0 <= ni && ni < 256 && 0 <= nj && nj < 256 && ptiles[ni][nj] == 1)
								s++;
					this.tiles[i][j] = s > 4 ? 1 : 0;
				}
		}
		
		//this.generateBSP(1, 1, 254, 254);
		
		
		this.generateLakes();
		
		for(int i = 0; i < 1024; i++)
		{
			int x = this.random.nextInt(256);
			int y = this.random.nextInt(256);
			if(this.tiles[x][y] == Tile.TILE_GRASS)
				this.tiles[x][y] = Tile.TILE_ROCK;
		}
		
		this.mapImage = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i < 256; i++)
			for(int j = 0; j < 256; j++)
				this.mapImage.setRGB(i, j, this.getTileAt(i, j).mapcolor);
	}
	
	public void update()
	{
		for(Entity entity : this.entities)
			entity.update();
		this.player.update();
	}
	
	public void render(Graphics g, float partialTick)
	{
		this.renderer = new WorldRenderer(g, this);
		this.renderer.render(partialTick);
		g.setColor(Color.white);
		RenderingHelper.fillTexturedRect(g, Game.instance.applet.getWidth()/2-this.TILE_SIZE/2, Game.instance.applet.getHeight()/2-this.TILE_SIZE/2, this.TILE_SIZE, this.TILE_SIZE, Texture.ENTITY_PLAYER[this.player.anim]);
	}
}
