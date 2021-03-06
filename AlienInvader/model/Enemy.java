package AlienInvader.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import AlienInvader.model.AlienActivity.Animation;
import java.awt.image.BufferedImage;

public class Enemy extends GameElement {

	public Enemy(int x, int y, int size) {
		super(x, y, size, size);
	}

	@Override
	public void render(Graphics2D g2) {
		try {
			BufferedImage enemyIMG = ImageIO.read(new File("AlienInvader/images/enemy_ship.png"));
			Image temp = enemyIMG.getScaledInstance(width, height, Image.SCALE_SMOOTH);

			g2.drawImage(temp, x, y, null);
		} catch (Exception e) {
			System.out.println("Image file load error");
		}
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
