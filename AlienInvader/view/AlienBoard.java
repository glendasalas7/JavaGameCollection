package AlienInvader.view;

import java.awt.Container;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import AlienInvader.controller.KeyController;
import AlienInvader.controller.MouseClickListener;
import AlienInvader.controller.TimerListener;
import AlienInvader.model.EnemyComposite;
import AlienInvader.model.PlayerShip;
import AlienInvader.model.HealthLevels.HealthChanger;
import AlienInvader.model.HealthLevels.HealthNotifier;
import AlienInvader.model.HealthLevels.Observer;
import view.MainMenu;
import java.awt.Font;

public class AlienBoard {
	private final JFrame window;
	private AlienCanvas canvas;
	private Timer timer;
	private PlayerShip shooter;
	private TimerListener timerListener;
	private EnemyComposite enemyComposite;
	private HealthNotifier gameComments;
	private TextDraw scoreCard;
	public static final int FPS = 20;
	public static final int DELAY = 1000 / FPS;
	public static final int WIDTH = 700;
	public static final int HEIGHT = 500;
	public static int score = 0;
	public static int enemies = 0;
	public static int playing = -1;
	public static JLabel enemyCount = new JLabel("");
	public static JPanel southPanel = new JPanel();
	public static ArrayList<Observer> observers;
	public static JLabel comment = new JLabel("");
	private Container cp;

	public AlienBoard(JFrame window) {
		this.window = window;
	}

	public void init() {
		cp = window.getContentPane();
		canvas = new AlienCanvas(WIDTH, HEIGHT);
		cp.add(canvas);
		canvas.addKeyListener(new KeyController(this));
		MouseClickListener clickListener = new MouseClickListener(this);
		canvas.addMouseListener(clickListener);
		canvas.requestFocusInWindow();
		canvas.setFocusable(true);
		comment.setFont(new Font("Courier", Font.BOLD, 16));
		enemyCount.setFont(new Font("Courier", Font.BOLD, 16));
		southPanel.add(comment);
		southPanel.add(enemyCount);
		gameComments = new HealthNotifier(southPanel);
		gameComments.addListener(new HealthChanger(this));
		canvas.getGameElements().add(new TextDraw("SPACE", 192, 118, Color.BLACK, 95));
		canvas.getGameElements().add(new TextDraw("INVADER", 148, 222, Color.BLACK, 95));
		canvas.getGameElements().add(new TextDraw("SPACE", 185, 125, Color.GREEN, 95));
		canvas.getGameElements().add(new TextDraw("INVADER", 140, 230, Color.GREEN, 95));
		canvas.getGameElements().add(new TextDraw("PLAY", 175, 400, Color.MAGENTA, 40));
		canvas.getGameElements().add(new TextDraw("RETURN", 400, 400, Color.MAGENTA, 40));
		timerListener = new TimerListener(this);
		timer = new Timer(DELAY, timerListener);
	}

	public void startGame() {
		score = 0;
		shooter = new PlayerShip(MainMenu.WIDTH / 2, 443);
		scoreCard = new TextDraw("Score = 0", 575, 15, Color.WHITE, 15);
		playing = 1;
		enemyComposite = new EnemyComposite(this, gameComments);
		canvas.getGameElements().clear();
		AlienBoard.enemyCount.setText("Enemies Left: " + enemies);
		canvas.getGameElements().add(new TextDraw("RETURN", 610, 455, Color.RED, 15));
		canvas.getGameElements().add(new TextDraw("RESTART", 530, 455, Color.GREEN, 15));
		canvas.getGameElements().add(shooter);
		canvas.getGameElements().add(enemyComposite);
		canvas.getGameElements().add(scoreCard);
		timer.start();
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

	public TextDraw getScoreCard() {
		return scoreCard;
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
