package hd.ld28.world;

public class Direction
{
	public static final int UP = 0,
							DOWN = 1,
							LEFT = 2,
							RIGHT = 3,
							UP_LEFT = 4,
							UP_RIGHT = 5,
							DOWN_LEFT = 6,
							DOWN_RIGHT = 7,
							NONE = 8;
	
	public static int getDeltaX(int dir)
	{
		if(dir == LEFT || dir == UP_LEFT || dir == DOWN_LEFT)
			return -1;
		if(dir == RIGHT || dir == UP_RIGHT || dir == DOWN_RIGHT)
			return 1;
		return 0;
	}
	
	public static int getDeltaY(int dir)
	{
		if(dir == UP || dir == UP_LEFT || dir == UP_RIGHT)
			return -1;
		if(dir == DOWN || dir == DOWN_LEFT || dir == DOWN_RIGHT)
			return 1;
		return 0;
	}
	
	public static int getDirFromDelta(int x, int y)
	{
		if(x < 0)
		{
			if(y < 0)
			{
				return UP_LEFT;
			}
			else if(y == 0)
			{
				return LEFT;
			}
			else
			{
				return DOWN_LEFT;
			}
		}
		else if(x == 0)
		{
			if(y < 0)
			{
				return UP;
			}
			else if(y == 0)
			{
				return NONE;
			}
			else
			{
				return DOWN;
			}
		}
		else
		{
			if(y < 0)
			{
				return UP_RIGHT;
			}
			else if(y == 0)
			{
				return RIGHT;
			}
			else
			{
				return DOWN_RIGHT;
			}
		}
	}
}
