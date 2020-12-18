package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import model.Bullet;
import model.Shooter;
import view.GameBoard;

public class TimerListener implements ActionListener{
	
	public enum EventType{
		KEY_RIGHT, KEY_LEFT, KEY_SPACE
	}

	private GameBoard gameBoard;
	private LinkedList<EventType> eventQueue;
	private final int BOMB_DROP_FREQ = 8;
	private final int POTION_DROP_FREQ = 50;
	private final int ALIEN_FREQ = 90;
	private int frameCounter = 0;
	private int frameCounter2 = 0;
	private int frameCounter3 = 0;

	public TimerListener(GameBoard gameBoard){
		this.gameBoard = gameBoard;
		eventQueue = new LinkedList<>();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		++frameCounter;
		++frameCounter2;
		++frameCounter3;
		update();
		processEventQueue();
		processCollision();
		gameBoard.getCanvas().repaint();
	}
	private void processEventQueue(){
		while (!eventQueue.isEmpty()){
			var e = eventQueue.getFirst();
			eventQueue.removeFirst();
			Shooter shooter = gameBoard.getShooter();
			if (shooter == null) return;
			switch(e){
				case KEY_LEFT:
					shooter.moveLeft();
					break;
				case KEY_RIGHT:
					shooter.moveRight();
					break;
				case KEY_SPACE:
					if(shooter.canFireMoreBullets())
						shooter.getWeapons().add(new Bullet(shooter.x, shooter.y));
					break;
			}
		}
		if (frameCounter == BOMB_DROP_FREQ){
			gameBoard.getEnemyComposite().dropBombs();
			frameCounter = 0;
		}
		if(frameCounter2 == POTION_DROP_FREQ){
			gameBoard.getEnemyComposite().dropPotions();
			frameCounter2 = 0;
		}
		if(frameCounter3 == ALIEN_FREQ){
			gameBoard.getEnemyComposite().dropAliens();
			frameCounter3 = 0;
		}
		
	}

	private void processCollision(){
		var shooter = gameBoard.getShooter();
		var enemyComposite = gameBoard.getEnemyComposite();

		shooter.removeBulletsOutOfBounds();
		enemyComposite.removeBombsOutOfBound();
		enemyComposite.removePotionsOutOfBound();
		enemyComposite.removeAliensOutOfBound();
		enemyComposite.processCollision(shooter);
	}

	private void update(){
		for(var e: gameBoard.getCanvas().getGameElements())
			e.animate();
	}

	public LinkedList<EventType> getEventQueue() {
		return eventQueue;
	}
	public GameBoard getGameBoard() {
		return gameBoard;
	}


	
}
