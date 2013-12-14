package hd.ld28;

import java.awt.Graphics;

import javax.swing.JApplet;

public class GameApplet extends JApplet
{
	private static final long serialVersionUID = 1L;
	
	
	
	private Game game;
	
	@Override
	public void init()
	{
		System.out.println("[APPLET] Initializing...");
		this.setSize(800, 600);
		this.game = new Game(this);
	}
	
	@Override
	public void start()
	{
		System.out.println("[APPLET] Starting...");
	}
	
	@Override
	public void paint(Graphics g)
	{
		this.game.runTick(g);
		this.repaint();
	}
	
	@Override
	public void stop()
	{
		System.out.println("[APPLET] Stopping...");
	}
	
	@Override
	public void destroy()
	{
		System.out.println("[APPLET] Destroying...");
	}
}
