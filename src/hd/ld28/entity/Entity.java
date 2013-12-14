package hd.ld28.entity;

import hd.ld28.render.WorldRenderer;
import hd.ld28.world.World;

public class Entity
{
	public World world;
	public int x, y, nx, ny;
	public int moveTime, maxMoveTime;
	
	public Entity(World world, int x, int y)
	{
		this.world = world;
		this.x = x;
		this.y = y;
		this.nx = x;
		this.ny = y;
		this.moveTime = 0;
	}
	
	public void update()
	{
		if(this.moveTime > 0)
			this.moveTime--;
		else if(this.x != this.nx || this.y != this.ny)
		{
			this.x = this.nx;
			this.y = this.ny;
		}
	}
	
	public void render(WorldRenderer renderer, float partialTick)
	{
		
	}
}
