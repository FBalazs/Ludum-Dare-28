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
			if(Game.instance.applet.isKeyDown(KeyEvent.VK_W) && this.y > 0 && this.world.getTileAt(this.x, this.y-1).isWalkable())
				this.ny--;
			else if(Game.instance.applet.isKeyDown(KeyEvent.VK_S) && this.y < 255 && this.world.getTileAt(this.x, this.y+1).isWalkable())
				this.ny++;
			else if(Game.instance.applet.isKeyDown(KeyEvent.VK_A) && this.x > 0 && this.world.getTileAt(this.x-1, this.y).isWalkable())
				this.nx--;
			else if(Game.instance.applet.isKeyDown(KeyEvent.VK_D) && this.x < 255 && this.world.getTileAt(this.x+1, this.y).isWalkable())
				this.nx++;
			
			/*System.out.println(Game.instance.applet.isKeyDown(KeyEvent.VK_W)+" "
							+Game.instance.applet.isKeyDown(KeyEvent.VK_S)+" "
							+Game.instance.applet.isKeyDown(KeyEvent.VK_A)+" "
							+Game.instance.applet.isKeyDown(KeyEvent.VK_D));*/
			
			if(this.x != this.nx || this.y != this.ny)
			{
				this.moveTime = this.maxMoveTime;
				System.out.println(this.nx+" "+this.ny);
			}
		}
	}
}
