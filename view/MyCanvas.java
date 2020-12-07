package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JPanel;

import controller.TimerListener;
import model.GameComments;
import model.GameElement;
import model.ObserverPattern.Observer;

public class MyCanvas extends JPanel{
	private static final long serialVersionUID = 1L;
	private GameBoard gameBoard;
	private GameComments gameComments;
	private TimerListener timerListener;

	private ArrayList<GameElement> gameElements = new ArrayList<>();

	public MyCanvas(GameBoard gameBoard, int width, int height) {
		this.gameBoard = gameBoard;
		setBackground(Color.BLUE);
		setPreferredSize(new Dimension(width, height));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (var e : gameElements) {
			e.render(g2);
		}
	}

	public ArrayList<GameElement> getGameElements() {
		return gameElements;
	}
}
