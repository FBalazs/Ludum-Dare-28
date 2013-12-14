package hd.ld28;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JApplet;

public class GameApplet extends JApplet implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener
{
	private static final long serialVersionUID = 1L;
	
	
	
	private Game game;
	public int mouseX, mouseY;
	private boolean[] keys = new boolean[256];
	private boolean[] mouseButtons = new boolean[16];
	
	public boolean isKeyDown(int key)
	{
		return this.keys[key];
	}
	
	public boolean isMouseButtonDown(int button)
	{
		return this.mouseButtons[button];
	}
	
	@Override
	public void init()
	{
		System.out.println("[APPLET] Initializing...");
		this.setSize(800, 600);
		
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseWheelListener(this);
		this.addMouseMotionListener(this);
		
		this.game = new Game(this);
		this.game.init();
		this.add(this.game);
	}
	
	@Override
	public void start()
	{
		System.out.println("[APPLET] Starting...");
		new Thread(this.game, "Game Thread").start();
	}
	
	@Override
	public void stop()
	{
		System.out.println("[APPLET] Stopping...");
		this.game.isRunning = false;
	}
	
	@Override
	public void destroy()
	{
		System.out.println("[APPLET] Destroying...");
	}
	
	
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		this.keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
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
		System.err.println(e.getX()+" "+e.getY());
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
