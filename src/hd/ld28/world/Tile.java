package hd.ld28.world;

import hd.ld28.render.Texture;
import hd.ld28.render.WorldRenderer;

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
		
		TILE_GRASS = new Tile().register(Texture.TILE_GRASS);
		TILE_LEAVES = new TileWall().register(Texture.TILE_LEAVES);
		TILE_WATER = new TileFluid().register(Texture.TILE_WATER);
		TILE_ROCK = new TileRock().register(Texture.TILE_ROCK);
	}
	
	
	
	public int id, textureId;
	
	public int register(int texture)
	{
		this.id = tiles.size();
		this.textureId = texture;
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
