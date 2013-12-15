package hd.ld28.entity;

import java.awt.Color;

import hd.ld28.render.WorldRenderer;
import hd.ld28.world.World;

public class Entity
{
	public World world;
	public int x, y, nx, ny;
	public int moveTime, maxMoveTime;
	public boolean shouldRemove;
	
	public Entity(World world, int x, int y)
	{
		this.world = world;
		this.x = x;
		this.y = y;
		this.nx = x;
		this.ny = y;
		this.moveTime = 0;
		this.shouldRemove = false;
	}
	
	public void update()
	{
		if(this.moveTime > 0)
			this.moveTime--;
		else
		{
			this.x = this.nx;
			this.y = this.ny;
		}
	}
	
	public void render(WorldRenderer renderer, float partialTick)
	{
		renderer.setColor(Color.white);
		renderer.fillRect(this.x*this.world.TILE_SIZE-this.world.TILE_SIZE/2, this.y*this.world.TILE_SIZE-this.world.TILE_SIZE/2, this.world.TILE_SIZE, this.world.TILE_SIZE);
	}
}
