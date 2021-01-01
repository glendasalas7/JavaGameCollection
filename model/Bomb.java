package model;

import java.awt.Graphics2D;

import model.StrategyPattern.Animation;

import java.awt.Color;

public class Bomb extends GameElement {

	public static final int SIZE = 10;
	public static final int UNIT_MOVE = 5;

	public Bomb(int x, int y) {
		super(x, y, Color.green, true, SIZE, SIZE);
	}

	@Override
	public void render(Graphics2D g2) {
		g2.setColor(color);
		if (filled)
			g2.fillOval(x, y, width, height);
		else
			g2.drawOval(x, y, width, height);
	}

	@Override
	public void animate() {
		super.y += UNIT_MOVE;
	}

	@Override
	public void setAnimation(Animation animation) {
	}

	@Override
	public void setActive(boolean status) {
	}



}
