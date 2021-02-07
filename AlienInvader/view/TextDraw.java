package AlienInvader.view;

import java.awt.Graphics2D;
import AlienInvader.model.GameElement;
import AlienInvader.model.AlienActivity.Animation;

import java.awt.Color;
import java.awt.Font;

public class TextDraw extends GameElement {
	private String text;
	private int size;
	private Color color;

	public TextDraw(String text, int x, int y, Color color, int size) {
		super(x, y, 0, 0);
		this.color = color;
		this.text = text;
		this.size = size;
	}

	@Override
	public void render(Graphics2D g2) {
		g2.setColor(color);
		g2.setFont(new Font("Courier", Font.BOLD, size));
		g2.drawString(text, x, y);
	}

	@Override
	public void animate() {
	}

	@Override
	public void setAnimation(Animation animation) {
	}

	@Override
	public void setActive(boolean status) {
	}

	@Override
	public boolean getActive() {
		return false;
	}

}