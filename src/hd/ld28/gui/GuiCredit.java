package hd.ld28.gui;

import hd.ld28.Game;
import hd.ld28.render.RenderingHelper;

import java.awt.*;

public class GuiCredit extends Gui
{
	public GuiButton btnBack;

	public GuiCredit(Gui parent, int x, int y, int width, int height)
	{
		super(parent, x, y, width, height);
	}
	
	@Override
	public void init()
	{
		this.isFocused = true;

		this.btnBack = new GuiButton(this, this.width/2-86, this.height/2-23 + 60*2, 86*2, 23*2, "Back");

		super.init();
	}
	
	@Override
	public void update(int mouseX, int mouseY, boolean pressed)
	{
		super.update(mouseX, mouseY, pressed);
		
		if(this.btnBack.wasClicked())
			Game.instance.setCurrentGui(new GuiMainMenu(null, 0, 0, this.width, this.height));
	}
	
	@Override
	public void render(Graphics g)
	{
		g.setColor(new Color(0, 0, 0, 128));
		g.fillRect(0, 0, this.width, this.height);

		g.setColor(new Color(198, 23, 35));
		g.setFont(new Font("SansSerif", Font.BOLD, 50));
		RenderingHelper.drawCenteredString(g, this.width/2, this.height/2 - 100, "Present Delivery");

		g.setColor(new Color(0xffffff));
		g.setFont(new Font("SansSerif", Font.BOLD, 16));
		int textWidth = this.width / 2 - 100;
		g.drawString("Credits:", textWidth - 10, this.height / 2 - 50);
		g.drawString("This game was made in 72 Hours", textWidth, this.height / 2 - 30);
		g.drawString("This is our first game!", textWidth, this.height / 2 - 10);
		g.drawString("By FBalazs & Dawars", textWidth, this.height / 2 + 10);
		super.render(g);
	}
}
