package hd.ld28.entity;

import hd.ld28.render.Texture;
import hd.ld28.render.WorldRenderer;
import hd.ld28.world.World;

public class EntityGift extends Entity
{
	public EntityGift(World world, int x, int y)
	{
		super(world, x, y);
	}
	
	@Override
	public void render(WorldRenderer renderer, float partialTick)
	{
		renderer.fillTexturedRect(this.x*this.world.TILE_SIZE-this.world.TILE_SIZE/2, this.y*this.world.TILE_SIZE-this.world.TILE_SIZE/2, this.world.TILE_SIZE, this.world.TILE_SIZE, Texture.ENTITY_GIFT);
	}
}
