package hd.ld28.entity;

import java.awt.Color;

import hd.ld28.Game;
import hd.ld28.render.Texture;
import hd.ld28.render.WorldRenderer;
import hd.ld28.world.Direction;
import hd.ld28.world.PathFinding;
import hd.ld28.world.World;

public class EntityKid extends Entity
{
	public int[] path;
	public int pathprogress, texture, dir;
	public boolean hasGift, looking;
	
	public EntityKid(World world, int x, int y, int id)
	{
		super(world, x, y);
		this.path = new int[0];
		this.pathprogress = 0;
		this.maxMoveTime = Game.instance.requestedUPS*2/5;
		this.texture = id;
		this.hasGift = false;
		this.mapcolor = Color.green.getRGB();
		this.dir = Direction.UP;
		this.looking = false;
	}
	
	public void move()
	{
		if(this.pathprogress >= this.path.length)
		{
			int x = this.x+this.world.random.nextInt(32)-16;
			int y = this.y+this.world.random.nextInt(32)-16;
			if(0 <= x && x < this.world.SIZE && 0 <= y && y < this.world.SIZE && this.world.getTileAt(x, y).isWalkable())
			{
				this.path = PathFinding.findPath(this.world, this.x, this.y, x, y);
				this.pathprogress = 0;
			}
		}
		else
		{
			this.dir = this.path[this.pathprogress];
			this.nx += Direction.getDeltaX(this.dir);
			this.ny += Direction.getDeltaY(this.dir);
			this.moveTime = (int)(this.maxMoveTime/this.world.getTileAt(this.x, this.y).getWalkSpeed());
			this.pathprogress++;
		}
	}
	
	@Override
	public void update()
	{
		super.update();
		
		if(this.moveTime == 0)
		{
			this.x = this.nx;
			this.y = this.ny;
			
			
			if((this.world.player.x-this.x)*(this.world.player.x-this.x)+(this.world.player.y-this.y)*(this.world.player.y-this.y) > 3*3)
			{
				if(this.looking)
				{
					this.world.player.hasChild = false;
					this.looking = false;
				}
				this.move();
			}
			else if(!this.world.player.hasChild)
			{
				this.world.player.hasChild = true;
				this.looking = true;
			}
			else if(!this.looking)
			{
				this.move();
			}
		}
	}
	
	@Override
	public void render(WorldRenderer renderer, float partialTick)
	{
		int mt = (int)(this.maxMoveTime/this.world.getTileAt(this.x, this.y).getWalkSpeed());
		int x = this.x*this.world.TILE_SIZE+(int)((this.nx-this.x)*(mt-this.moveTime+partialTick)*this.world.TILE_SIZE/mt);
		int y = this.y*this.world.TILE_SIZE+(int)((this.ny-this.y)*(mt-this.moveTime+partialTick)*this.world.TILE_SIZE/mt);
		renderer.fillTexturedRect(x-this.world.TILE_SIZE/2, y-this.world.TILE_SIZE/2, this.world.TILE_SIZE, this.world.TILE_SIZE, Texture.ENTITY_CHILDREN[this.texture][this.dir]);
	}
}
