package AlienInvader.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import AlienInvader.model.GameElement;
import view.MainMenu;

public class AlienCanvas extends JPanel {
	private static final long serialVersionUID = 1L;
	private AlienBoard gameBoard;
	private int width;
	private int height;
	private ArrayList<GameElement> gameElements = new ArrayList<>();

	public AlienCanvas(AlienBoard gameBoard, int width, int height) {
		this.gameBoard = gameBoard;
		setBackground(Color.BLUE);
		this.width = width;
		this.height = height;
		setPreferredSize(new Dimension(width, height));
	}

	@Override
	public void paintComponent(Graphics g) {
		BufferedImage starBG;
		try{
			starBG = ImageIO.read(new File("AlienInvader/pictures/planet.png"));
			Image temp = starBG.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			g.drawImage(temp, 0,0,null);
		}catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("OOPS!");
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