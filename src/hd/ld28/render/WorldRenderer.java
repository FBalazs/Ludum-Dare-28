package hd.ld28.render;

import hd.ld28.Game;
import hd.ld28.entity.Entity;
import hd.ld28.world.Tile;
import hd.ld28.world.World;

import java.awt.Color;
import java.awt.Graphics;

public class WorldRenderer
{
	private Graphics g;
	private World world;
	
	private int px, py;
	
	public WorldRenderer(Graphics g, World world)
	{
		this.g = g;
		this.world = world;
	}
	
	public void render(float partialTick)
	{
		int mt = (int)(this.world.player.maxMoveTime/this.world.getTileAt(this.world.player.x, this.world.player.y).getWalkSpeed());
		this.px = this.world.player.x*this.world.TILE_SIZE+(int)((this.world.player.nx-this.world.player.x)*(mt-this.world.player.moveTime+partialTick)*this.world.TILE_SIZE/mt)-Game.instance.getWidth()/2;
		this.py = this.world.player.y*this.world.TILE_SIZE+(int)((this.world.player.ny-this.world.player.y)*(mt-this.world.player.moveTime+partialTick)*this.world.TILE_SIZE/mt)-Game.instance.getHeight()/2;
		
		int rx = Game.instance.applet.getWidth()/this.world.TILE_SIZE/2;
		int ry = Game.instance.applet.getHeight()/this.world.TILE_SIZE/2;
		for(int i = this.world.player.x-rx-1; i <= this.world.player.x+rx+1; i++)
			for(int j = this.world.player.y-ry-1; j <= this.world.player.y+ry+1; j++)
				if(0 <= i && i < World.SIZE && 0 <= j && j < World.SIZE)
					Tile.tiles.get(this.world.getTileIdAt(i, j)).renderAt(this, this.world, partialTick, i, j);
		
		for(Entity entity : this.world.entities)
			if(Math.abs(entity.x-this.world.player.x)+Math.abs(entity.y-this.world.player.y) < rx+ry)
				entity.render(this, partialTick);
	}
	
	public void setColor(Color c)
	{
		this.g.setColor(c);
	}
	
	public void fillRect(int x, int y, int width, int height)
	{
		this.g.fillRect(x-this.px, y-this.py, width, height);
	}
	
	public void fillTexturedRect(int x, int y, int width, int height, int textureId)
	{
		RenderingHelper.fillTexturedRect(this.g, x-this.px, y-this.py, width, height, textureId);
	}
}
