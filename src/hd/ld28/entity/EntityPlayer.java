package hd.ld28.entity;

import java.awt.event.KeyEvent;

import hd.ld28.Game;
import hd.ld28.gui.GuiGameOver;
import hd.ld28.world.Direction;
import hd.ld28.world.Tile;
import hd.ld28.world.World;

public class EntityPlayer extends Entity
{
	public int anim, animTime, danim, dir, gifts;
	public boolean hasGift, pspace;
	
	public EntityPlayer(World world, int x, int y)
	{
		super(world, x, y);
		this.anim = 0;
		this.animTime = 0;
		this.danim = 1;
		this.dir = Direction.UP;
		this.hasGift = false;
		this.maxMoveTime = Game.instance.requestedUPS/5;
		this.gifts = 0;
	}
	
	@Override
	public void update()
	{
		super.update();
		
		if(Game.instance.input.isKeyDown(KeyEvent.VK_SPACE))
			if(!this.pspace)
			{
				boolean gift = false;
				for(Entity entity : this.world.entities)
					if(entity instanceof EntityKid
						&& (entity.x-this.x)*(entity.x-this.x)+(entity.y-this.y)*(entity.y-this.y) < 3*3
						&& this.hasGift)
					{
						EntityKid kid = (EntityKid)entity;
						if(kid.hasGift)
						{
							Game.instance.setCurrentGui(new GuiGameOver(null, 0, 0, Game.instance.getWidth(), Game.instance.getHeight(), false, this.world.lifetime));
						}
						else
						{
							this.gifts++;
							kid.hasGift = true;
							this.hasGift = false;
							gift = true;
						}
					}
				
				if(!gift)
				{
					int tx = this.x+Direction.getDeltaX(this.dir);
					int ty = this.y+Direction.getDeltaY(this.dir);
					if(0 <= tx && tx < this.world.SIZE && 0 <= ty && ty < this.world.SIZE)
						if(this.world.getTileIdAt(tx, ty) == Tile.LEAVES)
							this.world.setTileIdAt(tx, ty, Tile.GRASS);
						else if(this.world.getTileIdAt(tx, ty) == Tile.TREE)
							this.world.setTileIdAt(tx, ty, Tile.LOG);
				}
			}
			else
				this.pspace = true;
		else
			this.pspace = false;
		
		if(this.moveTime == 0)
		{
			this.x = this.nx;
			this.y = this.ny;
			
			boolean keyUp = Game.instance.input.isKeyDown(KeyEvent.VK_W);
			boolean keyDown = Game.instance.input.isKeyDown(KeyEvent.VK_S);
			boolean keyLeft = Game.instance.input.isKeyDown(KeyEvent.VK_A);
			boolean keyRight = Game.instance.input.isKeyDown(KeyEvent.VK_D);
			
			if(keyUp && this.ny > 0 && this.world.getTileAt(this.x, this.y-1).isWalkable() && this.world.getTileAt(this.nx, this.ny-1).isWalkable())
				this.ny--;
			if(keyDown && this.ny < this.world.SIZE-1 && this.world.getTileAt(this.x, this.y+1).isWalkable() && this.world.getTileAt(this.nx, this.ny+1).isWalkable())
				this.ny++;
			if(keyLeft && this.nx > 0 && this.world.getTileAt(this.x-1, this.y).isWalkable() && this.world.getTileAt(this.nx-1, this.ny).isWalkable())
				this.nx--;
			if(keyRight && this.nx < this.world.SIZE-1 && this.world.getTileAt(this.x+1, this.y).isWalkable() && this.world.getTileAt(this.nx+1, this.ny).isWalkable())
				this.nx++;
			
			/*System.out.println(Game.instance.input.isKeyDown(KeyEvent.VK_W)+" "
							+Game.instance.input.isKeyDown(KeyEvent.VK_S)+" "
							+Game.instance.input.isKeyDown(KeyEvent.VK_A)+" "
							+Game.instance.input.isKeyDown(KeyEvent.VK_D));*/
			
			if(this.x != this.nx || this.y != this.ny)
			{
				this.dir = Direction.getDirFromDelta(this.nx-this.x, this.ny-this.y);
				if(this.x != this.nx && this.y != this.ny)
					this.maxMoveTime = (int)(Game.instance.requestedUPS*Math.sqrt(2)/5);
				else
					this.maxMoveTime = Game.instance.requestedUPS/5;
				this.moveTime = (int)(this.maxMoveTime/this.world.getTileAt(this.x, this.y).getWalkSpeed());
			}
			else
			{
				int dx = 0;
				int dy = 0;
				if(keyUp)
					dy--;
				if(keyDown)
					dy++;
				if(keyLeft)
					dx--;
				if(keyRight)
					dx++;
				this.dir = Direction.getDirFromDelta(dx, dy);
			}
		}
		else
		{
			if(this.animTime < Game.instance.requestedUPS/8)
				this.animTime++;
			else
			{
				this.anim += danim;
				if(this.anim == 0 || this.anim == 2)
					this.danim *= -1;
				this.animTime = 0;
			}
		}
	}
}
