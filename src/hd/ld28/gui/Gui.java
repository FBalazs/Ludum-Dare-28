package hd.ld28.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Gui
{
	public Gui parent;
	public int x, y, width, height;
	public int focused;
	public boolean isFocused;
	public List<Gui> subs;
	
	public Gui(Gui parent, int x, int y, int width, int height)
	{
		this.parent = parent;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.focused = -1;
		this.isFocused = false;
		this.subs = new ArrayList<Gui>();
	}
	
	public void onKeyTyped(char c, int i)
	{
		for(Gui sub : this.subs)
			sub.onKeyTyped(c, i);
		if(this.focused != -1)
		{
			switch(i)
			{
				case KeyEvent.VK_UP:
					this.subs.get(this.focused).isFocused = false;
					this.focused--;
					break;
				case KeyEvent.VK_DOWN:
					this.subs.get(this.focused).isFocused = false;
					this.focused++;
					break;
				case KeyEvent.VK_ENTER:
					this.subs.get(this.focused).onMouseClicked(this.subs.get(this.focused).x+this.subs.get(this.focused).width/2, this.subs.get(this.focused).y+this.subs.get(this.focused).height/2, 0);
					break;
			}
			if(this.focused < 0)
				this.focused = this.subs.size()-1;
			if(this.focused >= this.subs.size())
				this.focused = 0;
			this.subs.get(this.focused).isFocused = true;
		}
	}
	
	public void onMouseClicked(int x, int y, int button)
	{
		for(Gui sub : this.subs)
			sub.onMouseClicked(x, y, button);
	}
	
	public void onMouseWheelMoved(int amount)
	{
		for(Gui sub : this.subs)
			sub.onMouseWheelMoved(amount);
	}
	
	public void init()
	{
		for(Gui sub : this.subs)
			sub.init();
	}
	
	public void update(int mouseX, int mouseY, boolean pressed)
	{
		for(Gui sub : this.subs)
			sub.update(mouseX, mouseY, pressed);
	}
	
	public void render(Graphics g)
	{
		for(Gui sub : this.subs)
			sub.render(g);
	}
	
	public void close()
	{
		for(Gui sub : this.subs)
			sub.close();
	}
}
