package hd.ld28.gui;

import hd.ld28.Game;
import hd.ld28.render.RenderingHelper;
import hd.ld28.render.Texture;

import java.awt.*;

public class GuiControls extends Gui
{
	public GuiButton btnBack, btnNext, btnPrev;

	private int page = 0;

	public GuiControls(Gui parent, int x, int y, int width, int height)
	{
		super(parent, x, y, width, height);
	}
	
	@Override
	public void init()
	{
		this.subs.clear();
		this.isFocused = true;

		this.btnBack = new GuiButton(this, this.width/2 - 86, this.height/2-23 + 60*2, 86*2, 23*2, "Back");
		this.btnPrev = new GuiButton(this, this.width/2 - 86 - 300, this.height/2-23, 86*2, 23*2, "<");
		this.btnNext = new GuiButton(this, this.width/2 - 86 + 300, this.height/2-23, 86*2, 23*2, ">");
		
		this.subs.add(this.btnBack);
		this.subs.add(this.btnPrev);
		this.subs.add(this.btnNext);
		
		super.init();
	}
	
	@Override
	public void update(int mouseX, int mouseY, boolean pressed)
	{
		super.update(mouseX, mouseY, pressed);

		if(this.btnBack.wasClicked())
			Game.instance.setCurrentGui(this.parent);
		if(this.btnNext.wasClicked() && this.page < 3)
			this.page++;
		if(this.btnPrev.wasClicked() && this.page > 0)
			this.page--;

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
		int textWidth = this.width / 2 - 180;
		g.drawString("Controls:", textWidth - 10, this.height / 2 - 50);

		g.setFont(new Font("SansSerif", Font.BOLD, 20));

		switch (this.page)
		{
			case 0:
				RenderingHelper.drawCenteredString(g, this.width/2, this.height/2 - 20, "Santa can chop down trees");
				RenderingHelper.drawCenteredString(g, this.width / 2, this.height / 2, "to go through the forest!");
				RenderingHelper.fillTexturedRect(g, this.width/2 -64, this.height/2+32, 128, 32, Texture.BUTTON_SPACE);

				g.setColor(new Color(0x000000));
				g.setFont(new Font("SansSerif", Font.PLAIN, 16));
				RenderingHelper.drawCenteredString(g, this.width/2, this.height/2 + 48, "Space");
			break;
			case 1:
				g.setFont(new Font("SansSerif", Font.BOLD, 20));
				g.setColor(new Color(0xffffff));
				RenderingHelper.drawCenteredString(g, this.width / 2, this.height / 2 - 20, "Santa can also give presents");
				RenderingHelper.drawCenteredString(g, this.width / 2, this.height / 2, "to children with Space");
				RenderingHelper.fillTexturedRect(g, this.width / 2, this.height / 2 + 20, 64, 64, Texture.ENTITY_PLAYER_GIFT);
				RenderingHelper.fillTexturedRect(g, this.width / 2 + 32, this.height / 2 + 16, 64, 64, Texture.ENTITY_PLAYER[1]);
				
				RenderingHelper.fillTexturedRect(g, this.width/2 -64, this.height/2+16, 64, 64, Texture.ENTITY_CHILDREN[3][3]);
			break;
			case 2:
				g.setFont(new Font("SansSerif", Font.BOLD, 20));
				g.setColor(new Color(0xffffff));
				RenderingHelper.drawCenteredString(g, this.width/2, this.height/2 - 20, "Santa moves with arrows");
				RenderingHelper.drawCenteredString(g, this.width/2, this.height/2, "or WASD");
				RenderingHelper.fillTexturedRect(g, this.width/2 -64, this.height/2+32, 128, 32, Texture.BUTTON_SPACE);

				g.setColor(new Color(0x000000));
				g.setFont(new Font("SansSerif", Font.PLAIN, 16));
				RenderingHelper.drawCenteredString(g, this.width/2, this.height/2 + 48, "Space");
			break;
		}
		super.render(g);
	}
}
