package AlienInvader.model;
import java.awt.Graphics2D;
import AlienInvader.model.StrategyPattern.Animation;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class PlayerShipElement extends GameElement {

	public static final int SIZE = 20;

	public PlayerShipElement(int x, int y) {
		super(x, y, SIZE, SIZE);
	}

	@Override
	public void render(Graphics2D g2) {
		try {
			BufferedImage shipIMG = ImageIO.read(new File("AlienInvader/pictures/ship.png"));
			Image temp = shipIMG.getScaledInstance(width, height, Image.SCALE_SMOOTH);

			g2.drawImage(temp, x, y, null);
		} catch (Exception e) {
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
