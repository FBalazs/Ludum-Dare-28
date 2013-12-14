package hd.ld28.world;

import hd.ld28.render.Texture;
import hd.ld28.render.WorldRenderer;

public class TileRock extends Tile
{
	@Override
	public void renderAt(WorldRenderer renderer, World world, float partialTick, int x, int y)
	{
		renderer.fillTexturedRect(x*world.TILE_SIZE-world.TILE_SIZE/2, y*world.TILE_SIZE-world.TILE_SIZE/2, world.TILE_SIZE, world.TILE_SIZE, Texture.TILE_GRASS);
		renderer.fillTexturedRect(x*world.TILE_SIZE-world.TILE_SIZE/2, y*world.TILE_SIZE-world.TILE_SIZE/2, world.TILE_SIZE, world.TILE_SIZE, this.textureId);
	}
}
