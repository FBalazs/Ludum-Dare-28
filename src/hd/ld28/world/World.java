package hd.ld28.world;

import hd.ld28.Game;
import hd.ld28.entity.Entity;
import hd.ld28.entity.EntityGift;
import hd.ld28.entity.EntityKid;
import hd.ld28.entity.EntityPlayer;
import hd.ld28.gui.GuiGameOver;
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
	
	public static int SIZE = 32;
	public int TILE_SIZE = 32;
	public int KIDS = 4;
	
	public Random random;
	public WorldRenderer renderer;
	public BufferedImage mapImage;
	
	public long lifetime;
	
	public World()
	{
		this.lifetime = 0;
		this.tiles = new int[SIZE][SIZE];
		this.entities = new ArrayList<Entity>();
		this.random = Game.instance.random;
		this.generate();
		
		int x = SIZE/2, y = SIZE/2;
		while(!Tile.tiles.get(this.tiles[x][y]).isWalkable())
		{
			x = this.random.nextInt(SIZE);
			y = this.random.nextInt(SIZE);
		}
		this.player = new EntityPlayer(this, x, y);
		
		for(int i = 0; i < this.KIDS; i++)
		{
			x = this.random.nextInt(SIZE);
			y = this.random.nextInt(SIZE);
			while(this.tiles[x][y] != Tile.GRASS)
			{
				x = this.random.nextInt(SIZE);
				y = this.random.nextInt(SIZE);
			}
			this.entities.add(new EntityKid(this, x, y, i));
		}
		
		for(int i = 0; i < this.KIDS; i++)
		{
			x = this.random.nextInt(SIZE);
			y = this.random.nextInt(SIZE);
			while(this.tiles[x][y] != Tile.GRASS)
			{
				x = this.random.nextInt(SIZE);
				y = this.random.nextInt(SIZE);
			}
			this.entities.add(new EntityGift(this, x, y));
		}
	}
	
	public void setTileIdAt(int x, int y, int id)
	{
		this.tiles[x][y] = id;
		this.mapImage.setRGB(x, y, Tile.tiles.get(id).mapcolor);
	}
	
	public Tile getTileAt(int x, int y)
	{
		return Tile.tiles.get(this.tiles[x][y]);
	}
	
	public int getTileIdAt(int x, int y)
	{
		return this.tiles[x][y];
	}
	
	public void generateLakes()
	{
		for(int l = 0; l < SIZE/1024; l++)
		{
			int x = this.random.nextInt(SIZE);
			int y = this.random.nextInt(SIZE);
			int w = this.random.nextInt(16);
			int h = this.random.nextInt(16);
			
			if(w > 0 && h > 0 && x+w < SIZE && y+h < SIZE)
			{
				for(int i = x; i < x+w; i++)
					for(int j = y; j < y+h; j++)
						this.tiles[i][j] = Tile.WATER;
			}
		}
	}
	
	public void generate()
	{
		for(int i = 0; i < SIZE; i++)
			for(int j = 0; j < SIZE; j++)
				this.tiles[i][j] = this.random.nextInt(2);
				
		for(int a = 0; a < 5; a++)
		{
			int[][] ptiles = this.tiles;
			for(int i = 0; i < SIZE; i++)
				for(int j = 0; j < SIZE; j++)
				{
					int s = 0;
					for(int ni = i-1; ni <= i+1; ni++)
						for(int nj = j-1; nj <= j+1; nj++)
							if(0 <= ni && ni < SIZE && 0 <= nj && nj < SIZE && ptiles[ni][nj] == Tile.LEAVES)
								s++;
					this.tiles[i][j] = s > 4 ? Tile.LEAVES : Tile.GRASS;
				}
		}
		
		for(int i = 0; i < SIZE; i++)
			for(int j = 0; j < SIZE; j++)
				if(this.tiles[i][j] == Tile.LEAVES)
				{
					int s = 0;
					for(int ni = i-1; ni <= i+1; ni++)
						for(int nj = j-1; nj <= j+1; nj++)
							if(0 <= ni && ni < SIZE && 0 <= nj && nj < SIZE && (this.tiles[ni][nj] == Tile.LEAVES || this.tiles[ni][nj] == Tile.TREE))
								s++;
					if(this.random.nextInt(s)/2 != 0)
						this.tiles[i][j] = Tile.TREE;
				}
		
		this.generateLakes();
		
		for(int i = 0; i < SIZE*SIZE/256; i++)
		{
			int x = this.random.nextInt(SIZE);
			int y = this.random.nextInt(SIZE);
			if(this.tiles[x][y] == Tile.GRASS)
				this.tiles[x][y] = Tile.ROCK;
		}
		
		this.mapImage = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i < SIZE; i++)
			for(int j = 0; j < SIZE; j++)
				this.mapImage.setRGB(i, j, this.getTileAt(i, j).mapcolor);
	}
	
	public void update()
	{
		for(Entity entity : this.entities)
			entity.update();
		this.player.update();
		for(int i = 0; i < this.entities.size(); i++)
			if(this.entities.get(i).shouldRemove)
			{
				this.entities.remove(i);
				i--;
			}
		
		this.lifetime++;
		
		if(this.player.gifts == this.KIDS)
		{
			Game.instance.setCurrentGui(new GuiGameOver(null, 0, 0, Game.instance.getWidth(), Game.instance.getHeight(), true, this.lifetime));
		}
	}
	
	public void render(Graphics g, float partialTick)
	{
		this.renderer = new WorldRenderer(g, this);
		this.renderer.render(partialTick);
		g.setColor(Color.white);
		if(this.player.hasGift)
			RenderingHelper.fillTexturedRect(g, Game.instance.getWidth()/2-this.TILE_SIZE/2-Direction.getDeltaX(this.player.dir)*this.TILE_SIZE/3, Game.instance.getHeight()/2-this.TILE_SIZE/2-Direction.getDeltaY(this.player.dir)*this.TILE_SIZE/3, this.TILE_SIZE, this.TILE_SIZE, Texture.ENTITY_PLAYER_GIFT);
		RenderingHelper.fillTexturedRect(g, Game.instance.getWidth()/2-this.TILE_SIZE/2, Game.instance.getHeight()/2-this.TILE_SIZE/2, this.TILE_SIZE, this.TILE_SIZE, Texture.ENTITY_PLAYER[this.player.anim]);
	}
}
