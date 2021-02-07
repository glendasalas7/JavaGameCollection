package AlienInvader.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import AlienInvader.model.AlienActivity.Animation;
import java.awt.image.BufferedImage;

public class Bomb extends GameElement {

	public static final int SIZE = 10;
	public static final int UNIT_MOVE = 3;

	public Bomb(int x, int y) {
		super(x, y, SIZE, SIZE);
	}

	@Override
	public void render(Graphics2D g2) {
		try {
			BufferedImage shipIMG = ImageIO.read(new File("AlienInvader/images/bomb.png"));
			Image temp = shipIMG.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			g2.drawImage(temp, x, y, null);
		} catch (Exception e) {
		}
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

	@Override
	public boolean getActive() {
		return false;
	}
}
