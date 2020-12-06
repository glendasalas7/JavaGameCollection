package model;

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import view.GameBoard;
import view.TextDraw;

public class EnemyComposite extends GameElement {

	public static final int NROWS = 2;
	public static final int NCOLS = 15;
	public static final int ENEMY_SIZE = 14;//size of enemy block
	public static final int UNIT_MOVE = 4; //speed

	private GameBoard gameboard;
	private ArrayList<ArrayList<GameElement>> rows;
	private ArrayList<GameElement> bombs;
	private ArrayList<GameElement> potions;
	private int lostComponents = 0;
	// private ArrayList<Observer> observers = new ArrayList<>();
    // private Shooter shooter;
	private boolean movingToRight = true;
	private Random random = new Random();
	private int score;
	private int enemies;

	public EnemyComposite(GameBoard gameboard){
		this.gameboard = gameboard;
		rows = new ArrayList<>();
		bombs = new ArrayList<>();
		potions = new ArrayList<>();
		// lostComponents = new ArrayList<>();
		enemies = NROWS*NCOLS;
		GameBoard.enemies = enemies;

		for(int r = 0; r < NROWS; r++){ //populate enemies
			var oneRow = new ArrayList<GameElement>();
			rows.add(oneRow);
			for(int c = 0; c < NCOLS; c++){
				oneRow.add(new Enemy(
					c * ENEMY_SIZE * 2, r * ENEMY_SIZE * 2, ENEMY_SIZE, Color.yellow, true));
			}
		}
	}

	@Override
	public void render(Graphics2D g2) {
		//render enemy array
		for(var r: rows){
			for(var e: r){
				e.render(g2);
			}
		}
		//render bombs
		for(var b: bombs){
			b.render(g2);
		}
		//render berries
		for(var p: potions){
			p.render(g2);
		}
	}

	@Override
	public void animate() {
		int dx = UNIT_MOVE;
		if(movingToRight){
			if(rightEnd() >= GameBoard.WIDTH){
				for(var row: rows){
					for(var r:row)//enemy moves down - from right
						r.y += 20;
				}
				dx = -dx;
				movingToRight = false;
			}
		}else{
			dx = -dx;
			if(leftEnd() <= 0){
				dx = -dx;
				movingToRight = true;
				for(var row: rows){//enemy moves down - from left
					for(var r:row)
						r.y += 20;
				}
				dx = -dx;
				movingToRight = true;
			}
		}
		for(var row: rows){
			for(var e:row)
				e.x += dx;
		}
		//animate bombs
		for(var b: bombs){
			b.animate();
		}
		//animate berries
		for(var p:potions){
			p.animate();
		}
	}

	private int rightEnd(){
		int xEnd = -100;
		for(var row: rows){
			if(row.size() == 0) continue;
			int x = row.get(row.size()-1).x + ENEMY_SIZE;
			if(x > xEnd) xEnd = x;
		}
		return xEnd;
	}
	private int leftEnd(){
		int xEnd = 9000;
		for(var row: rows){
			if(row.size() == 0) continue;
			int x = row.get(0).x;
			if(x < xEnd) xEnd = x;
		}
		return xEnd;
	}

	public void dropBombs(){
		for(var row: rows){
			for(var e: row){
				if(random.nextFloat() < 0.1F){//very small
					bombs.add(new Bomb(e.x, e.y));
				}
			}
		}
	}

	public void removeBombsOutOfBound(){
		var remove = new ArrayList<GameElement>();
		for(var b: bombs){
			if(b.y >= GameBoard.HEIGHT){
				remove.add(b);
			}
		}
		bombs.removeAll(remove);
	}

	public void dropPotions(){
		Random rand = new Random();
		int randX = rand.nextInt(575);
		// this.addListener(this);
		potions.add(new Potion(randX, 0));
	}

	public void removePotionsOutOfBound(){
		var remove = new ArrayList<GameElement>();
		for(var p: potions){
			if(p.y >= GameBoard.HEIGHT){
				remove.add(p);
			}
		}
		potions.removeAll(remove);
	}

	public void processCollision(Shooter shooter){
		var removeBullets = new ArrayList<GameElement>();

		//bullets vs enemies
		for(var row: rows){
			var removeEnemies = new ArrayList<GameElement>();
			for(var enemy: row){
				for(var bullet: shooter.getWeapons()){
					if (enemy.collideWith(bullet)){
						score =	GameBoard.score +10;
						GameBoard.scoreBoard.setText("Score: " + score);
						GameBoard.score = score;
						enemies = GameBoard.enemies -1;
						GameBoard.enemyCount.setText("Enemies Left: " + enemies);
						GameBoard.enemies = enemies;
						removeBullets.add(bullet);
						removeEnemies.add(enemy);
					}
				}
			}
			row.removeAll(removeEnemies);
			if(GameBoard.enemies == 0){
				gameboard.getCanvas().getGameElements().clear();
				gameboard.getCanvas().getGameElements().add(new TextDraw("YOU WIN!", 75, 150, Color.GREEN, 100));
				score = 0;
			}
		}
		shooter.getWeapons().removeAll(removeBullets);
		//end bullets vs enemies

		//bombs vs shooter
		var removeComponents = new ArrayList<GameElement>();
		var removeBombs = new ArrayList<GameElement>();

		for(var b: bombs){
			for(var player: shooter.getComponents()){
				if(b.collideWith(player)){
					removeBombs.add(b);
					// lostComponents.add(player);
					lostComponents++;
					removeComponents.add(player);
					break;
				}
			}
			shooter.getComponents().removeAll(removeComponents);
			// bombs.removeAll(removeBombs);
		}

		if(shooter.getComponentSize() == 0){
			gameboard.getCanvas().getGameElements().clear();
			gameboard.getCanvas().getGameElements().add(new TextDraw("GAME OVER!", 200, 100, Color.MAGENTA, 35));
			gameboard.getCanvas().getGameElements().add(new TextDraw("Score: " + GameBoard.score, 210, 200, Color.GREEN, 35));
			score = 0;
		}
		//end bomb vs shooter

		//bullets vs bombs
		removeBullets.clear();
		for(var b: bombs){
			for(var bullet: shooter.getWeapons()){
				if(b.collideWith(bullet)){
					score =	GameBoard.score +2;
					GameBoard.scoreBoard.setText("Score: " + score);
					GameBoard.score = score;
					removeBombs.add(b);
					removeBullets.add(bullet);
				}
			}
		}
		shooter.getWeapons().removeAll(removeBullets);
		bombs.removeAll(removeBombs);
	
		//enemies vs shooter
		for(var row: rows){
			var removeEnemies = new ArrayList<GameElement>();
			for(var enemy: row){	
				for(var player: shooter.getComponents()){
					if (enemy.collideWith(player)){
						removeComponents.add(player);
						removeEnemies.add(enemy);
					}
					if(enemy.y >= 275){
						gameboard.getCanvas().getGameElements().clear();
						gameboard.getCanvas().getGameElements().add(new TextDraw("GAME OVER!", 200, 100, Color.MAGENTA, 35));
						gameboard.getCanvas().getGameElements().add(new TextDraw("Score: " + GameBoard.score, 215, 200, Color.GREEN, 35));
						score = 0;
					}
					if(rows.size() == 0 && shooter.getComponentSize() !=0){
						gameboard.getCanvas().getGameElements().clear();
						gameboard.getCanvas().getGameElements().add(new TextDraw("YOU WIN!", 73, 150, Color.GREEN, 100));
						gameboard.getCanvas().getGameElements().add(new TextDraw("Score: " + GameBoard.score, 215, 200, Color.GREEN, 35));
						score = 0;
					}
				}
			}
			shooter.getComponents().removeAll(removeComponents);
			row.removeAll(removeEnemies);
		}
		// //potion -> shooter
		// for(var p: potions){
		// 	for(var player: shooter.getComponents()){
		// 		if(p.collideWith(player)){
		// 			player.drink();
		// 		}
		// 	}
		// }
		GameComments gameComments = new GameComments(gameboard);

		for(var p: potions){
			ArrayList<GameElement> newComponents = new ArrayList<>();
			for(var player: shooter.getComponents()){
				if(p.collideWith(player) && lostComponents > 0){
					gameComments.sendMessage();
					potions.remove(p);
					newComponents.clear();
					lostComponents--;
					int size = ShooterElement.SIZE;
					int x= player.getX();
					int y = 300-size;
					var b1 = new ShooterElement(x-size, y, Color.MAGENTA, false);
					var b2 = new ShooterElement(x, y, Color.MAGENTA, false);
					var b3 = new ShooterElement(x-size, y-size, Color.MAGENTA, false);
					var b4 = new ShooterElement(x, y-size, Color.MAGENTA, false);


					if(shooter.getComponentSize() == 1){
						System.out.println(shooter.getComponentSize());
						newComponents.add(b1);
						newComponents.add(b2);
						shooter.setComponents(newComponents);
						shooter.setX(x-size);
						shooter.setY(y);
						return;
						// newComponents.clear();
					}else if(shooter.getComponentSize() == 2){
						System.out.println(shooter.getComponentSize());

						newComponents.add(b1);
						newComponents.add(b2);
						newComponents.add(b3);
						shooter.setComponents(newComponents);
						shooter.setX(x-size);
						shooter.setY(y);
						return;
						// newComponents.clear();
					}else if(shooter.getComponentSize() == 3){
						System.out.println(shooter.getComponentSize());

						newComponents.add(b1);
						newComponents.add(b2);
						newComponents.add(b3);
						newComponents.add(b4);
						shooter.setComponents(newComponents);
						shooter.setX(x-size);
						shooter.setY(y);
						return;
						// newComponents.clear();
					}
				}
			}
		}		
	}

	// //potion -> shooter
	@Override
	public void actionPerformed(Shooter shooter) {
		// System.out.println("HELLO!");
		// var removePotions = new ArrayList<GameElement>();
		// GameElement newBlock;

		// for(var p: potions){
		// 	for(var player: shooter.getComponents()){
		// 		if(p.collideWith(player)){
		// 			removePotions.add(p);
		// 			if(lostComponents.size() >=0){
		// 				newBlock = lostComponents.get(0);
		// 				shooter.getComponents().add(newBlock);	
		// 			}
		// 		}
		// 	}
		// 	potions.removeAll(removePotions);
		// }
	}
	@Override
	public int getY() {
		return super.getY();
	}
	@Override
	public int getX() {
		return super.getX();
	}
}

