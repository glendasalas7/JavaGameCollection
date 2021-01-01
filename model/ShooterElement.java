package model;
import java.awt.Graphics2D;
import model.StrategyPattern.Animation;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ShooterElement extends GameElement {

	public static final int SIZE = 20;

	public ShooterElement(int x, int y, Color color, boolean filled) {
		super(x, y, color, filled, SIZE, SIZE);
	}

	@Override
	public void render(Graphics2D g2) {
		// g2.setColor(color);
		// if (super.filled) {
		// 	g2.fillRect(x, y, width, height);
		// } else {
		// 	g2.drawRect(x, y, width, height);
		// }
		try {
			BufferedImage shipIMG = ImageIO.read(new File("pictures/ship.png"));
			Image temp = shipIMG.getScaledInstance(width, height, Image.SCALE_SMOOTH);

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
