package hd.ld28.world;

import hd.ld28.render.Texture;
import hd.ld28.render.WorldRenderer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Tile
{
	public static List<Tile> tiles;
	
	public static int GRASS,
					LEAVES,
					TREE,
					LOG,
					WATER,
					ROCK;
	
	public static void init()
	{
		tiles = new ArrayList<Tile>();
		
		GRASS = new Tile().register(Texture.TILE_GRASS, new Color(0, 128, 0).getRGB());
		LEAVES = new TileWall().register(Texture.TILE_LEAVES, new Color(0, 192, 0).getRGB());
		TREE = new TileObject().register(Texture.TILE_TREE, new Color(0, 80, 0).getRGB());
		LOG = new TileWalkableObject().register(Texture.TILE_LOG, new Color(128, 128, 0).getRGB());
		WATER = new TileFluid().register(Texture.TILE_WATER, new Color(0, 0, 192).getRGB());
		ROCK = new TileObject().register(Texture.TILE_ROCK, new Color(80, 64, 64).getRGB());
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
	
	public float getWalkSpeed()
	{
		return 1F;
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
