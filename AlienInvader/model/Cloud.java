package AlienInvader.model;

import java.awt.Graphics2D;
import java.io.File;
import javax.imageio.ImageIO;
import AlienInvader.model.AlienActivity.Animation;
import java.awt.image.BufferedImage;

public class Cloud extends GameElement {
	public static final int UNIT_MOVE = 8;
	public static final int SIZE = 15;

	public Cloud(int x, int y) {
		super(x, y, SIZE, SIZE);
	}

	@Override
	public void render(Graphics2D g2) {
		try {
			BufferedImage clouds = ImageIO.read(new File("AlienInvader/images/clouds.png"));

			g2.drawImage(clouds, x, y, null);
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