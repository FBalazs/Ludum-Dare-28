package hd.ld28.entity;

import hd.ld28.Game;
import hd.ld28.world.Direction;
import hd.ld28.world.World;

public class EntityKid extends Entity
{
	public int[] path;
	public int pathprogress;
	
	public EntityKid(World world, int x, int y)
	{
		super(world, x, y);
		this.path = new int[0];
		this.pathprogress = 0;
		this.maxMoveTime = Game.instance.requestedUPS*2/5;
	}
	
	@Override
	public void update()
	{
		super.update();
		
		if(this.moveTime == 0)
		{
			if(this.pathprogress >= this.path.length)
			{
				int x = this.world.random.nextInt(256);
				int y = this.world.random.nextInt(256);
				if(this.world.getTileAt(x, y).isWalkable())
				{
					// TODO path finding
				}
			}
			else
			{
				this.nx += Direction.getDeltaX(this.path[this.pathprogress]);
				this.ny += Direction.getDeltaY(this.path[this.pathprogress]);
				this.moveTime = (int)(this.maxMoveTime/this.world.getTileAt(this.x, this.y).getWalkSpeed());
				this.pathprogress++;
			}
		}
	}
}
