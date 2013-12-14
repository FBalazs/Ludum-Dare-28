package hd.ld28.world;

import hd.ld28.render.Texture;
import hd.ld28.render.WorldRenderer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Tile
{
	public static List<Tile> tiles;
	
	public static int TILE_GRASS,
					TILE_LEAVES,
					TILE_WATER,
					TILE_ROCK;
	
	public static void init()
	{
		tiles = new ArrayList<Tile>();
		
		TILE_GRASS = new Tile().register(Texture.TILE_GRASS, new Color(0, 128, 0).getRGB());
		TILE_LEAVES = new TileWall().register(Texture.TILE_LEAVES, new Color(0, 192, 0).getRGB());
		TILE_WATER = new TileFluid().register(Texture.TILE_WATER, new Color(0, 0, 192).getRGB());
		TILE_ROCK = new TileRock().register(Texture.TILE_ROCK, new Color(80, 64, 64).getRGB());
	}
	
	
	
	public int id, textureId, mapcolor;
	
	public int register(int texture, int color)
	{
		this.id = tiles.size();
		this.textureId = texture;
		this.mapcolor = color;
		tiles.add(this);
		return this.id;
	}
	
	public void renderAt(WorldRenderer renderer, World world, float partialTick, int x, int y)
	{
		renderer.fillTexturedRect(x*world.TILE_SIZE-world.TILE_SIZE/2, y*world.TILE_SIZE-world.TILE_SIZE/2, world.TILE_SIZE, world.TILE_SIZE, this.textureId);
	}
	
	public int getWalkSpeed()
	{
		return 12;
	}
	
	public boolean isWalkable()
	{
		return true;
	}
	
	public boolean isFluid()
	{
		return false;
	}
}
