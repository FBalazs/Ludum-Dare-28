package hd.ld28;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener
{
	private Game game;
	public int mouseX, mouseY;
	private boolean[] keys = new boolean[256];
	private boolean[] mouseButtons = new boolean[16];
	
	public InputHandler(Game game)
	{
		this.game = game;
	}
	
	public boolean isKeyDown(int key)
	{
		return this.keys[key];
	}
	
	public boolean isMouseButtonDown(int button)
	{
		return this.mouseButtons[button];
	}
	
	
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() < this.keys.length)
			this.keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() < this.keys.length)
			this.keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		this.game.onKeyTyped(e.getKeyChar(), e.getKeyCode());
	}
	
	
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		this.mouseButtons[e.getButton()-1] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		this.mouseButtons[e.getButton()-1] = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		this.game.onMouseClicked(e.getX(), e.getY(), e.getButton()-1);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		this.game.onMouseWheelMoved(-e.getWheelRotation());
	}
	
	

	@Override
	public void mouseDragged(MouseEvent e)
	{
		this.mouseX = e.getX();
		this.mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		this.mouseX = e.getX();
		this.mouseY = e.getY();
	}

	

	@Override
	public void mouseEntered(MouseEvent e)
	{
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		
	}
}
