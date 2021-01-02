
package model;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import model.StrategyPattern.Animation;

public class Enemy extends GameElement {

	public Enemy(int x, int y, int size, Color color, boolean filled) {
		super(x, y, color, filled, size, size);
	}

	@Override
	public void render(Graphics2D g2) {
		// g2.setColor(color);
		// if (filled)
		// 	g2.fillRect(x, y, width, height);
		// else
		// 	g2.drawRect(x, y, width, height);

		try {
			BufferedImage enemyIMG = ImageIO.read(new File("pictures/enemy_ship.png"));
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
