package hd.ld28.world;

public class TileFluid extends Tile
{
	@Override
	public float getWalkSpeed()
	{
		return 0.66F;
	}
	
	@Override
	public boolean isFluid()
	{
		return true;
	}
}
