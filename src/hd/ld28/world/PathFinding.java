package hd.ld28.world;

public class PathFinding
{
	public static class State
	{
		int s, px, py;
		
		public State(int s, int px, int py)
		{
			this.s = s;
			this.px = px;
			this.py = py;
		}
		
		public State(State src)
		{
			this.s = src.s;
			this.px = src.px;
			this.py = src.py;
		}
	}
	
	public static int[] findPath(World world, int x1, int y1, int x2, int y2)
	{
		int w = Math.abs(x2-x1)*2+1;
		int h = Math.abs(y2-y1)*2+1;
		int x = Math.min(x1, x2)-w/4;
		int y = Math.min(y1, y2)-h/4;
		
		int l = 0;
		State[][] states = new State[w][h];
		for(int i = 0; i < w; i++)
			for(int j = 0; j < h; j++)
				states[i][j] = new State(0, 0, 0);
		states[x1-x][y1-y].s = 1;
		boolean end = false;
		
		for(l = 0; !end; l++)
		{
			end = true;
			State[][] pstates = new State[w][h];
			for(int i = 0; i < w; i++)
				for(int j = 0; j < h; j++)
					pstates[i][j] = new State(states[i][j]);
			
			for(int i = 0; i < w; i++)
				for(int j = 0; j < h; j++)
					if(pstates[i][j].s == 1)
					{
						if(i < w-1 && 0 <= i+x+1 && i+x+1 < world.SIZE && 0 <= j+y && j+y < world.SIZE && world.getTileAt(x+i+1, y+j).isWalkable() && states[i+1][j].s == 0)
						{
							states[i+1][j].s = 1;
							states[i+1][j].px = i;
							states[i+1][j].py = j;
							end = false;
						}
						if(i > 0 && 0 <= i+x-1 && i+x-1 < world.SIZE && 0 <= j+y && j+y < world.SIZE && world.getTileAt(x+i-1, y+j).isWalkable() && states[i-1][j].s == 0)
						{
							states[i-1][j].s = 1;
							states[i-1][j].px = i;
							states[i-1][j].py = j;
							end = false;
						}
						if(j < h-1 && 0 <= i+x && i+x < world.SIZE && 0 <= j+y+1 && j+y+1 < world.SIZE && world.getTileAt(x+i, y+j+1).isWalkable() && states[i][j+1].s == 0)
						{
							states[i][j+1].s = 1;
							states[i][j+1].px = i;
							states[i][j+1].py = j;
							end = false;
						}
						if(j > 0 && 0 <= i+x && i+x < world.SIZE && 0 <= j+y-1 && j+y-1 < world.SIZE && world.getTileAt(x+i, y+j-1).isWalkable() && states[i][j-1].s == 0)
						{
							states[i][j-1].s = 1;
							states[i][j-1].px = i;
							states[i][j-1].py = j;
							end = false;
						}
					}
			
			if(states[x2-x][y2-y].s == 1)
				end = true;
		}
		
		if(states[x2-x][y2-y].s == 1)
		{
			int[] path = new int[l];
			int cx = x2-x;
			int cy = y2-y;
			for(int i = l-1; i >= 0; i--)
			{
				int px = states[cx][cy].px;
				int py = states[cx][cy].py;
				path[i] = Direction.getDirFromDelta(cx-px, cy-py);
				cx = px;
				cy = py;
			}
			System.out.println();
			return path;
		}
		else
			return new int[0];
	}
}
