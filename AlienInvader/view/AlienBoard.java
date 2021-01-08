package AlienInvader.view;

import java.awt.Container;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import AlienInvader.controller.KeyController;
import AlienInvader.controller.TimerListener;
import AlienInvader.model.EnemyComposite;
import AlienInvader.model.ObserverPattern.HealthNotifier;
import AlienInvader.model.ObserverPattern.HealthChanger;
import AlienInvader.model.PlayerShip;
import AlienInvader.model.PlayerShipElements;
import AlienInvader.model.ObserverPattern.Observer;
import view.MainMenu;

import java.awt.Font;

public class AlienBoard{
	private final JFrame window;
	private AlienCanvas canvas;
	private Timer timer;
	private PlayerShip shooter;
	private TimerListener timerListener;
	private EnemyComposite enemyComposite;
	private HealthNotifier gameComments;
	public static final int FPS = 20;
	public static final int DELAY = 1000/FPS;
	public static final int WIDTH = 700;
	public static final int HEIGHT = 430;

	public static int score = 0;
	public static int enemies = 0;
	public static JLabel scoreBoard = new JLabel("");
	public static JLabel enemyCount = new JLabel("");
	public static JPanel southPanel = new JPanel();
	public static ArrayList<Observer> observers;
	public static JLabel comment = new JLabel("");
	public AlienBoard(JFrame window){
		this.window = window;
	}

	public void init(){
		Container cp = window.getContentPane();
		canvas = new AlienCanvas(this, WIDTH, HEIGHT);
		cp.add(canvas);
		canvas.addKeyListener(new KeyController(this));
		canvas.requestFocusInWindow();
		canvas.setFocusable(true);

		JButton startButton = new JButton("Start");
		JButton quitButton = new JButton("Quit");
		startButton.setFocusable(false);
		quitButton.setFocusable(false);
		comment.setFont(new Font("Courier", Font.BOLD, 16));
		enemyCount.setFont(new Font("Courier", Font.BOLD, 16));
		scoreBoard.setFont(new Font("Courier", Font.BOLD, 16));
		southPanel.add(comment);
		southPanel.add(new JLabel("    "));
		southPanel.add(startButton);
		southPanel.add(quitButton);
		southPanel.add(scoreBoard);
		southPanel.add(new JLabel("  "));
		southPanel.add(enemyCount);
		gameComments = new HealthNotifier(southPanel);
		gameComments.addListener(new HealthChanger(this));
		cp.add(BorderLayout.SOUTH, southPanel);
		canvas.getGameElements().add(new TextDraw("SPACE INVADER", 10, 125, Color.GREEN, 70));
		canvas.getGameElements().add(new TextDraw("Click <Start> to play", 190, 190, Color.MAGENTA, 20));
		timerListener = new TimerListener(this);
		timer = new Timer(DELAY, timerListener);

		startButton.addActionListener(event ->{
			startButton.setText("Restart");
			shooter = new PlayerShip(MainMenu.WIDTH/2, AlienBoard.HEIGHT - PlayerShipElements.SIZE);
			enemyComposite = new EnemyComposite(this, gameComments);
			canvas.getGameElements().clear();
			score = 0;
			AlienBoard.scoreBoard.setText("Score: " + score);
			AlienBoard.enemyCount.setText("Enemies Left: " + enemies);
			canvas.getGameElements().add(shooter);
			canvas.getGameElements().add(enemyComposite);
			timer.start();
		});

		quitButton.addActionListener(event -> System.exit(0));
	}
	public AlienCanvas getCanvas() {
		return canvas;
	}
	public Timer getTimer() {
		return timer;
	}
	public TimerListener getTimerListener() {
		return timerListener;
	}
	public PlayerShip getShooter() {
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
		AlienBoard.southPanel = southPanel;
	}
	public static int getEnemies() {
		return enemies;
	}
	public static void setEnemies(int enemies) {
		AlienBoard.enemies = enemies;
	}
	public static void setEnemyCount(JLabel enemyCount) {
		AlienBoard.enemyCount = enemyCount;
	}
	public static JLabel getEnemyCount() {
		return enemyCount;
	}
	public static JLabel getComment() {
		return comment;
	}
	public static void setComment(JLabel comment) {
		AlienBoard.comment = comment;
	}
}
