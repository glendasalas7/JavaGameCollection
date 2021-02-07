package AlienInvader.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import AlienInvader.model.GameElement;

public class AlienCanvas extends JPanel {
	private static final long serialVersionUID = 1L;

	private int width;
	private int height;
	private ArrayList<GameElement> gameElements = new ArrayList<>();

	public AlienCanvas(int width, int height) {
		this.width = width;
		this.height = height;
		setPreferredSize(new Dimension(width, height));
	}

	@Override
	public void paintComponent(Graphics g) {
		BufferedImage starBG;
		try {
			starBG = ImageIO.read(new File("AlienInvader/images/planet.png"));
			Image temp = starBG.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			g.drawImage(temp, 0, 0, null);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (var e : gameElements) {
			e.render(g2);
		}
	}

	public ArrayList<GameElement> getGameElements() {
		return gameElements;
	}
}