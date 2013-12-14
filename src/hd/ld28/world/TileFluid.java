package hd.ld28.world;

public class TileFluid extends Tile
{
	@Override
	public int getWalkSpeed()
	{
		return 8;
	}
	
	@Override
	public boolean isFluid()
	{
		return true;
	}
}
