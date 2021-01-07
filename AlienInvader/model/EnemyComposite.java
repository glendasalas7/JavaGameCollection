package AlienInvader.model;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import AlienInvader.model.StrategyPattern.Alien;
import AlienInvader.model.ObserverPattern.HealthNotifier;
import AlienInvader.model.StatePattern.DangerLevel;
import AlienInvader.model.StatePattern.SafeLevel;
import AlienInvader.model.StatePattern.CautionLevel;
import AlienInvader.model.StrategyPattern.Animation;
import AlienInvader.model.StrategyPattern.EmptyAlien;
import AlienInvader.model.StrategyPattern.FullAlien;
import AlienInvader.view.AlienBoard;
import AlienInvader.view.TextDraw;

public class EnemyComposite extends GameElement {

	public static final int NROWS = 2;
	public static final int NCOLS = 15;
	public static final int ENEMY_SIZE = 15;// size of enemy block
	public static final int UNIT_MOVE = 4; // speed

	private AlienBoard gameboard;
	private HealthNotifier gameComments;
	private ArrayList<ArrayList<GameElement>> rows;
	private ArrayList<GameElement> bombs;
	private ArrayList<GameElement> ships;
	private ArrayList<GameElement> aliens;
	private FullAlien fullAlien;
	private EmptyAlien emptyAlien;

	private int lostComponents = 0;
	private boolean movingToRight = true;
	private Random random = new Random();
	private int score;
	private int enemies;

	public EnemyComposite(AlienBoard gameboard, HealthNotifier gameComments) {
		this.gameboard = gameboard;
		this.gameComments = gameComments;
		rows = new ArrayList<>();
		bombs = new ArrayList<>();
		ships = new ArrayList<>();
		aliens = new ArrayList<>();
		enemies = NROWS * NCOLS;
		AlienBoard.enemies = enemies;

		for (int r = 0; r < NROWS; r++) { // populate enemies
			var oneRow = new ArrayList<GameElement>();
			rows.add(oneRow);
			for (int c = 0; c < NCOLS; c++) {
				oneRow.add(new Enemy(c * ENEMY_SIZE * 2, r * ENEMY_SIZE * 2, ENEMY_SIZE));
			}
		}
	}

	@Override
	public void render(Graphics2D g2) {
		// render enemy array
		for (var r : rows) {
			for (var e : r) {
				e.render(g2);
			}
		}
		// render bombs
		for (var b : bombs) {
			b.render(g2);
		}
		// render ships
		for (var s : ships) {
			s.render(g2);
		}
		// render aliens
		for (var a : aliens) {
			a.render(g2);
		}
	}

	@Override
	public void animate() {
		int dx = UNIT_MOVE;
		if (movingToRight) {
			if (rightEnd() >= AlienBoard.WIDTH) {
				for (var row : rows) {
					for (var r : row)// enemy moves down - from right
						r.y += 20;
				}
				dx = -dx;
				movingToRight = false;
			}
		} else {
			dx = -dx;
			if (leftEnd() <= 0) {
				dx = -dx;
				movingToRight = true;
				for (var row : rows) {// enemy moves down - from left
					for (var r : row)
						r.y += 20;
				}
				dx = -dx;
				movingToRight = true;
			}
		}
		for (var row : rows) {
			for (var e : row)
				e.x += dx;
		}
		// animate bombs
		for (var b : bombs) {
			b.animate();
		}
		// animate ships
		for (var s : ships) {
			s.animate();
		}
		//animate aliens falling
		for (var a : aliens) {
			a.animate();
		}

	}

	private int rightEnd() {
		int xEnd = -100;
		for (var row : rows) {
			if (row.size() == 0)
				continue;
			int x = row.get(row.size() - 1).x + ENEMY_SIZE;
			if (x > xEnd)
				xEnd = x;
		}
		return xEnd;
	}

	private int leftEnd() {
		int xEnd = 9000;
		for (var row : rows) {
			if (row.size() == 0)
				continue;
			int x = row.get(0).x;
			if (x < xEnd)
				xEnd = x;
		}
		return xEnd;
	}

	public void dropBombs() {
		for (var row : rows) {
			for (var e : row) {
				if (random.nextFloat() < 0.1F) {// very small
					bombs.add(new Bomb(e.x, e.y));
				}
			}
		}
	}

	public void removeBombsOutOfBound() {
		var remove = new ArrayList<GameElement>();
		for (var b : bombs) {
			if (b.y >= AlienBoard.HEIGHT) {
				remove.add(b);
			}
		}
		bombs.removeAll(remove);
	}

	public void dropShips() {
		Random rand = new Random();
		int randX = rand.nextInt(575);
		ships.add(new HelperShips(randX, 0));
	}

	public void removeShipsOutOfBound() {
		var remove = new ArrayList<GameElement>();
		for (var s : ships) {
			if (s.y >= AlienBoard.HEIGHT) {
				remove.add(s);
			}
		}
		ships.removeAll(remove);
	}

	public void dropAliens() {
		Random rand = new Random();
		int randx = rand.nextInt(200);
		fullAlien = new FullAlien();
		Alien alien = new Alien(randx, 0, fullAlien);
		alien.setActive(true);
		aliens.add(alien);
	}

	public void removeAliensOutOfBound() {
		var remove = new ArrayList<GameElement>();
		for (var a : aliens) {
			if (a.y >= AlienBoard.HEIGHT) {
				remove.add(a);
			}
		}
		aliens.removeAll(remove);
	}

	public void processCollision(PlayerShip shooter){
		var removeBullets = new ArrayList<GameElement>();
		//bullets vs enemies
		for(var row: rows){
			var removeEnemies = new ArrayList<GameElement>();
			for(var enemy: row){
				for(var bullet: shooter.getWeapons()){
					if (enemy.collideWith(bullet)){
						score =	AlienBoard.score +10;
						AlienBoard.scoreBoard.setText("Score: " + score);
						AlienBoard.score = score;
						enemies = AlienBoard.enemies -1;
						AlienBoard.enemyCount.setText("Enemies Left: " + enemies);
						AlienBoard.enemies = enemies;
						removeBullets.add(bullet);
						removeEnemies.add(enemy);
					}
				}
			}
			row.removeAll(removeEnemies);
			if(AlienBoard.enemies == 0){
				gameboard.getCanvas().getGameElements().clear();
				gameboard.getCanvas().getGameElements().add(new TextDraw("YOU WIN!", 75, 150, Color.GREEN, 100));
				gameboard.getCanvas().getGameElements().add(new TextDraw("Score: " + AlienBoard.score, 210, 200, Color.GREEN, 35));

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
					if(shooter.getComponentSize() != 1){
						shooter.goNextState();
					}
					removeBombs.add(b);
					lostComponents++;
					removeComponents.add(player);
				}
			}
			shooter.getComponents().removeAll(removeComponents);
			gameComments.healthUpdate(shooter.getComponentSize());
			if(shooter.getComponentSize() == 1){
				int x = shooter.getComponents().get(0).getX();
				shooter.setX(x-PlayerShipElements.SIZE+29);
			}
			if(shooter.getComponentSize() == 2){
				int size = PlayerShipElements.SIZE;
				int x = shooter.getComponents().get(0).getX();
				if(shooter.getComponents().get(1).getY() == shooter.getComponents().get(0).getY()){
					shooter.setX(x-PlayerShipElements.SIZE+38);
				}
				else shooter.setX(x-PlayerShipElements.SIZE+29);
		
			}
			// bombs.removeAll(removeBombs);
		}
		bombs.removeAll(removeBombs);

		if(shooter.getComponentSize() == 0){
			gameboard.getCanvas().getGameElements().clear();
			gameboard.getCanvas().getGameElements().add(new TextDraw("GAME OVER!", 200, 100, Color.MAGENTA, 35));
			gameboard.getCanvas().getGameElements().add(new TextDraw("Score: " + AlienBoard.score, 210, 200, Color.GREEN, 35));
			score = 0;
		}
		//end bomb vs shooter

		//bullets vs bombs
		removeBullets.clear();
		for(var b: bombs){
			for(var bullet: shooter.getWeapons()){
				if(b.collideWith(bullet)){
					score =	AlienBoard.score +2;
					AlienBoard.scoreBoard.setText("Score: " + score);
					AlienBoard.score = score;
					removeBombs.add(b);
					removeBullets.add(bullet);
				}
			}
		}
		shooter.getWeapons().removeAll(removeBullets);
		bombs.removeAll(removeBombs);
		//end bullet vs bombs
	
		// alien vs player
		for(var a: aliens){
			ArrayList<GameElement> newComponents = new ArrayList<>();
			for(var player: shooter.getComponents()){
				if(a.collideWith(player) && lostComponents > 0){	
					if(a.getActive() == true){
						lostComponents = 0;
						int size = PlayerShipElements.SIZE;
						int x = player.getX();
						int y = 300-size;
						var b1 = new PlayerShipElements(x, y);
						var b2 = new PlayerShipElements(x+size, y);
						var b3 = new PlayerShipElements(x, y-size);
						var b4 = new PlayerShipElements(x+size, y-size);
						newComponents.add(b1);
						newComponents.add(b2);
						newComponents.add(b3);
						newComponents.add(b4);
						shooter.setComponents(newComponents);
						gameComments.healthUpdate(shooter.getComponentSize());
						shooter.setX(x-size+38);
						shooter.setY(y);
						emptyAlien = new EmptyAlien();	
						a.setActive(false);
						// Alien.UNIT_MOVE = 10;
						a.setAnimation(emptyAlien);
						shooter.setState(new SafeLevel(AlienBoard.getComment()));
					}
				}
			}
		}

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
						gameboard.getCanvas().getGameElements().add(new TextDraw("Score: " + AlienBoard.score, 215, 200, Color.GREEN, 35));
						score = 0;
					}
					if(rows.size() == 0 && shooter.getComponentSize() !=0){
						gameboard.getCanvas().getGameElements().clear();
						gameboard.getCanvas().getGameElements().add(new TextDraw("YOU WIN!", 73, 150, Color.GREEN, 100));
						gameboard.getCanvas().getGameElements().add(new TextDraw("Score: " + AlienBoard.score, 215, 200, Color.GREEN, 35));
						score = 0;
					}
				}
			}
			shooter.getComponents().removeAll(removeComponents);
			gameComments.healthUpdate(shooter.getComponentSize());
			row.removeAll(removeEnemies);
		}//end enemies vs shooter

		//ships vs shooter
		for(var s: ships){
			ArrayList<GameElement> newComponents = new ArrayList<>();
			for(var player: shooter.getComponents()){
				if(s.collideWith(player) && lostComponents !=0){
					ships.remove(s);
					newComponents.clear();
					lostComponents--;
					int size = PlayerShipElements.SIZE;
					int x = player.getX();
					int y = 300-size;
					var b1 = new PlayerShipElements(x, y);
					var b2 = new PlayerShipElements(x+size, y);
					var b3 = new PlayerShipElements(x, y-size);
					var b4 = new PlayerShipElements(x+size, y-size);

					if(shooter.getComponentSize() == 1){
						newComponents.add(b1);
						newComponents.add(b2);
						shooter.setComponents(newComponents);
				     	gameComments.healthUpdate(shooter.getComponentSize());
						shooter.setX(x-size+29);
						shooter.setY(y);
						break;
				
					}if(shooter.getComponentSize() == 2){
						newComponents.add(b1);
						newComponents.add(b2);
						newComponents.add(b3);
						shooter.setComponents(newComponents);
					    gameComments.healthUpdate(shooter.getComponentSize());
						shooter.setX(x-size+38);
						shooter.setY(y);
						break;

					}if(shooter.getComponentSize() == 3){
						newComponents.add(b1);
						newComponents.add(b2);
						newComponents.add(b3);
						newComponents.add(b4);
						shooter.setComponents(newComponents);
					    gameComments.healthUpdate(shooter.getComponentSize());
						shooter.setX(x-size+38);
						shooter.setY(y);
						break;
					}
					if(shooter.getComponentSize() == 4){
						shooter.setState(new SafeLevel(AlienBoard.getComment()));
						}
					if(shooter.getComponentSize() == 3){
						shooter.setState(new SafeLevel(AlienBoard.getComment()));
						}
					if(shooter.getComponentSize() == 2){
						shooter.setState(new CautionLevel(AlienBoard.getComment()));
					}
					if(shooter.getComponentSize() == 1){
						shooter.setState(new DangerLevel(AlienBoard.getComment()));
					}
					
				}
			}
		}
		//end ships vs shooter
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
