package hd.ld28.gui;

import hd.ld28.Game;
import hd.ld28.render.RenderingHelper;
import hd.ld28.world.World;

import java.awt.*;

public class GuiMainMenu extends Gui
{
	public GuiButton btnNewGame,
					btnResume,
					btnInstruction,
					btnCredit,
					btnExit;
	
	public GuiMainMenu(Gui parent, int x, int y, int width, int height)
	{
		super(parent, x, y, width, height);
	}
	
	@Override
	public void init()
	{
		this.isFocused = true;
		
		this.btnResume = new GuiButton(this, this.width/2-86, this.height/2-23 - 30, 86*2, 23*2, "Resume");
		this.btnResume.isFocused = true;
		
		this.btnNewGame = new GuiButton(this, this.width/2-86, this.height/2-23 + 30, 86*2, 23*2, "New Game");
		
		this.btnInstruction = new GuiButton(this, this.width/2-86, this.height/2-23 + 90, 86*2, 23*2, "Instructions");
		
		this.btnCredit = new GuiButton(this, this.width/2-86, this.height/2-23 + 150, 86*2, 23*2, "Credit");
		
		this.btnExit = new GuiButton(this, this.width/2-86, this.height/2-23 + 210, 86*2, 23*2, "Exit");
		
		this.subs.add(this.btnResume);
		this.subs.add(this.btnNewGame);
		this.subs.add(this.btnInstruction);
		this.subs.add(this.btnCredit);
		//this.subs.add(this.btnExit);
		
		super.init();
	}
	
	@Override
	public void update(int mouseX, int mouseY, boolean pressed)
	{
		super.update(mouseX, mouseY, pressed);
		
		if(this.btnResume.wasClicked())
			Game.instance.setCurrentGui(null);
		if(this.btnNewGame.wasClicked())
		{
			Game.instance.world = new World();
			Game.instance.setCurrentGui(null);
		}
		if(this.btnInstruction.wasClicked())
			Game.instance.setCurrentGui(new GuiInstruction(this, this.x, this.y, this.width, this.height));
		if(this.btnCredit.wasClicked())
			Game.instance.setCurrentGui(new GuiCredit(this, this.x, this.y, this.width, this.height));
		if(this.btnExit.wasClicked())
			Game.instance.applet.stop();
	}
	
	@Override
	public void render(Graphics g)
	{
		g.setColor(new Color(0, 0, 0, 128));
		g.fillRect(0, 0, this.width, this.height);
		g.setColor(new Color(0xd80303));
		g.setFont(new Font("SansSerif", Font.BOLD, 50));
		RenderingHelper.drawCenteredString(g, this.width/2, this.height/2 - 100, "Present Delivery");
		super.render(g);
	}
}
