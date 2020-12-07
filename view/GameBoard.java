package view;

import java.awt.Container;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import controller.KeyController;
import controller.TimerListener;
import model.EnemyComposite;
import model.GameComments;
import model.PanelChanger;
import model.Shooter;
import model.ShooterElement;
import model.ObserverPattern.Observer;

public class GameBoard{
	private final JFrame window;
	private MyCanvas canvas;
	private Timer timer;
	private Shooter shooter;
	private TimerListener timerListener;
	private EnemyComposite enemyComposite;
	private GameComments gameComments;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 300;
	public static final int FPS = 20;
	public static final int DELAY = 1000/FPS;
	public static int score = 0;
	public static int enemies = 0;
	public static JLabel scoreBoard = new JLabel("");
	public static JLabel enemyCount = new JLabel("");
	public static JPanel southPanel = new JPanel();
	public static ArrayList<Observer> observers;

	public GameBoard(JFrame window){
		this.window = window;
	}

	public void init(){
		Container cp = window.getContentPane();
		canvas = new MyCanvas(this, WIDTH, HEIGHT);	
		cp.add(BorderLayout.CENTER, canvas);
		canvas.addKeyListener(new KeyController(this));
		canvas.requestFocusInWindow();
		canvas.setFocusable(true);


		JButton startButton = new JButton("Start");
		JButton quitButton = new JButton("Quit");
		startButton.setFocusable(false);
		quitButton.setFocusable(false);
		southPanel.add(startButton);
		southPanel.add(quitButton);
		southPanel.add(scoreBoard);
		southPanel.add(enemyCount);
		gameComments = new GameComments(southPanel);
		gameComments.addListener(new PanelChanger(this));
		cp.add(BorderLayout.SOUTH, southPanel);
		canvas.getGameElements().add(new TextDraw("Click <Start> to play", 100, 100, Color.yellow, 30));
		timerListener = new TimerListener(this);
		timer = new Timer(DELAY, timerListener);

		startButton.addActionListener(event ->{
			shooter = new Shooter(GameBoard.WIDTH /2, GameBoard.HEIGHT - ShooterElement.SIZE);
			enemyComposite = new EnemyComposite(this, gameComments);
			canvas.getGameElements().clear();
			score = 0;
			GameBoard.scoreBoard.setText("Score: " + score);
			GameBoard.enemyCount.setText("Enemies Left: " + enemies);
			canvas.getGameElements().add(shooter);
			canvas.getGameElements().add(enemyComposite);
			timer.start();
		});

		quitButton.addActionListener(event -> System.exit(0));
	}
	public MyCanvas getCanvas() {
		return canvas;
	}
	public Timer getTimer() {
		return timer;
	}
	public TimerListener getTimerListener() {
		return timerListener;
	}
	public Shooter getShooter() {
		return shooter;
	}
	public EnemyComposite getEnemyComposite() {
		return enemyComposite;
	}
	public JFrame getWindow() {
		return window;
	}
	public static JPanel getSouthpanel() {
		return southPanel;
	}
	public static void setSouthPanel(JPanel southPanel) {
		GameBoard.southPanel = southPanel;
	}
	public static int getEnemies() {
		return enemies;
	}
	public static void setEnemies(int enemies) {
		GameBoard.enemies = enemies;
	}
	public static void setEnemyCount(JLabel enemyCount) {
		GameBoard.enemyCount = enemyCount;
	}
	public static JLabel getEnemyCount() {
		return enemyCount;
	}

}
