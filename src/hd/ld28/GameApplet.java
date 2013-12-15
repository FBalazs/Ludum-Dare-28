package hd.ld28;

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
		//this.game.init();
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
}
