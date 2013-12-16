package hd.ld28.gui;

import hd.ld28.Game;
import hd.ld28.render.RenderingHelper;

import java.awt.*;

public class GuiMainMenu extends Gui
{
	public GuiButton btnNewGame,
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

		this.btnNewGame = new GuiButton(this, this.width/2-86, this.height/2-23, 86*2, 23*2, "Play");
		this.btnNewGame.isFocused = true;

		this.btnInstruction = new GuiButton(this, this.width/2-86, this.height/2-23 + 60, 86*2, 23*2, "Instructions");

		this.btnCredit = new GuiButton(this, this.width/2-86, this.height/2-23 + 60*2, 86*2, 23*2, "Credit");

		//this.btnExit = new GuiButton(this, this.width/2-86, this.height/2-23 + 60*3, 86*2, 23*2, "Exit");

		super.init();
	}
	
	@Override
	public void update(int mouseX, int mouseY, boolean pressed)
	{
		super.update(mouseX, mouseY, pressed);

		if(this.btnNewGame.wasClicked())
			Game.instance.setCurrentGui(null);
		if(this.btnCredit.wasClicked())
			Game.instance.setCurrentGui(new GuiCredit(this, 0, 0, this.width, this.height));
		if(this.btnInstruction.wasClicked())
			Game.instance.setCurrentGui(new GuiInstruction(this, 0, 0, this.width, this.height));
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
