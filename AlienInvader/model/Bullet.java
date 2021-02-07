package AlienInvader.model;

import java.awt.Color;
import java.awt.Graphics2D;
import AlienInvader.model.AlienActivity.Animation;

public class Bullet extends GameElement {
	public static final int WIDTH = 5;
	public static final int UNIT_MOVE = 30;

	public Bullet(int x, int y) {
		super(x, y, WIDTH, WIDTH + 3);
	}

	@Override
	public void render(Graphics2D g2) {
		g2.setColor(Color.red);
		g2.fillRect(x, y, width, height);
	}

	@Override
	public void animate() {
		super.y -= UNIT_MOVE;
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
