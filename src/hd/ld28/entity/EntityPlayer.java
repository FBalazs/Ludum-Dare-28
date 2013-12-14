package hd.ld28.entity;

import java.awt.event.KeyEvent;

import hd.ld28.Game;
import hd.ld28.world.World;

public class EntityPlayer extends Entity
{
	public EntityPlayer(World world, int x, int y)
	{
		super(world, x, y);
		this.maxMoveTime = Game.instance.requestedUPS/5;
	}
	
	@Override
	public void update()
	{
		super.update();
		
		if(this.moveTime == 0)
		{
			this.x = this.nx;
			this.y = this.ny;
			
			if(Game.instance.input.isKeyDown(KeyEvent.VK_W) && this.y > 0 && this.world.getTileAt(this.x, this.y-1).isWalkable() && this.world.getTileAt(this.nx, this.ny-1).isWalkable())
				this.ny--;
			if(Game.instance.input.isKeyDown(KeyEvent.VK_S) && this.y < 255 && this.world.getTileAt(this.x, this.y+1).isWalkable() && this.world.getTileAt(this.nx, this.ny+1).isWalkable())
				this.ny++;
			if(Game.instance.input.isKeyDown(KeyEvent.VK_A) && this.x > 0 && this.world.getTileAt(this.x-1, this.y).isWalkable() && this.world.getTileAt(this.nx-1, this.ny).isWalkable())
				this.nx--;
			if(Game.instance.input.isKeyDown(KeyEvent.VK_D) && this.x < 255 && this.world.getTileAt(this.x+1, this.y).isWalkable() && this.world.getTileAt(this.nx+1, this.ny).isWalkable())
				this.nx++;
			
			/*System.out.println(Game.instance.input.isKeyDown(KeyEvent.VK_W)+" "
							+Game.instance.input.isKeyDown(KeyEvent.VK_S)+" "
							+Game.instance.input.isKeyDown(KeyEvent.VK_A)+" "
							+Game.instance.input.isKeyDown(KeyEvent.VK_D));*/
			
			if(this.x != this.nx || this.y != this.ny)
				this.moveTime = this.maxMoveTime;
		}
	}
}
