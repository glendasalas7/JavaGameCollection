package AlienInvader.view;

import java.awt.Dimension;
import java.awt.Font;
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

public class AlienCanvas extends JPanel {
	private static final long serialVersionUID = 1L;
	private ArrayList<GameElement> gameElements = new ArrayList<>();
	private int width;
	private int height;
	private boolean img;

	public AlienCanvas(int width, int height, boolean img) {
		setBackground(Color.BLUE);
		this.img = img;
		this.width = width;
		this.height = height;
		setPreferredSize(new Dimension(width, height));
	}

	@Override
	public void paintComponent(Graphics g2) {
			super.paintComponent(g2);
			g2.setColor(Color.green);
			g2.setFont(new Font("Courier", Font.BOLD, 90));
			g2.drawString("A L I E N", 215, 120);
			g2.drawString("I N V A S I O N", 100, 250);
	}

	public ArrayList<GameElement> getGameElements() {
		return gameElements;
	}
}
