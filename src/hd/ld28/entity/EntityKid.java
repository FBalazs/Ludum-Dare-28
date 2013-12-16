package hd.ld28.entity;

import hd.ld28.render.Texture;
import hd.ld28.render.WorldRenderer;
import hd.ld28.world.World;

public class EntityKid extends Entity {

	public boolean hasGift = true;
	public int dir = 0;

	public EntityKid(World world, int x, int y) {
		super(world, x, y);
	}

	@Override
	public void update()
	{
		if(this.world.player.nx == this.x && this.world.player.ny == this.y && this.world.player.hasGift)
		{
			this.world.player.hasGift = false;
			//add
		}
	}

	@Override
	public void render(WorldRenderer renderer, float partialTick)
	{
		renderer.fillTexturedRect(this.x*this.world.TILE_SIZE-this.world.TILE_SIZE/2, this.y*this.world.TILE_SIZE-this.world.TILE_SIZE/2, this.world.TILE_SIZE, this.world.TILE_SIZE, Texture.ENTITY_GIFT);
	}
}
