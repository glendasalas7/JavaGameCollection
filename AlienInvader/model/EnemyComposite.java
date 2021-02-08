package AlienInvader.model;

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import AlienInvader.model.AlienActivity.Alien;
import AlienInvader.model.AlienActivity.Animation;
import AlienInvader.model.AlienActivity.EmptyAlien;
import AlienInvader.model.AlienActivity.FullAlien;
import AlienInvader.model.CautionLevel.CautionLevel;
import AlienInvader.model.CautionLevel.DangerLevel;
import AlienInvader.model.CautionLevel.SafeLevel;
import AlienInvader.model.HealthLevels.HealthNotifier;
import AlienInvader.view.AlienBoard;
import AlienInvader.view.TextDraw;

public class EnemyComposite extends GameElement {

	public static final int NROWS = 2;
	public static final int NCOLS = 15;
	public static final int ENEMY_SIZE = 19;// size of enemy block
	public static final int UNIT_MOVE = 15; // speed

	private AlienBoard gameboard;
	private HealthNotifier gameComments;
	private ArrayList<ArrayList<GameElement>> rows;
	private ArrayList<GameElement> bombs;
	private ArrayList<GameElement> ships;
	private ArrayList<GameElement> aliens;
	private ArrayList<GameElement> clouds;
	private FullAlien fullAlien;
	private EmptyAlien emptyAlien;
	private Cloud cloud;
	private int lostComponents = 0;
	private boolean movingToRight = true;
	private Random random = new Random();
	private int enemies;

	public EnemyComposite(AlienBoard gameboard, HealthNotifier gameComments) {
		this.gameboard = gameboard;
		this.gameComments = gameComments;
		rows = new ArrayList<>();
		bombs = new ArrayList<>();
		ships = new ArrayList<>();
		aliens = new ArrayList<>();
		clouds = new ArrayList<>();
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

		for (var c : clouds) {
			c.render(g2);
		}
	}

	@Override
	public void animate() {
		int dx = UNIT_MOVE;
		if (movingToRight) {
			if (rightEnd() >= AlienBoard.WIDTH - PlayerShipElement.SIZE) {
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
		// animate aliens falling
		for (var a : aliens) {
			a.animate();
		}

		for (var c : clouds) {
			c.animate();
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

	public void dropCloud() {
		Random rand = new Random();
		int randx = rand.nextInt(670);// edit
		clouds.add(new Cloud(randx, -80));
	}

	public void removeCloudsOutOfBound() {
		var remove = new ArrayList<GameElement>();
		for (var c : clouds) {
			if (c.y >= AlienBoard.HEIGHT) {
				remove.add(c);
			}
		}
		clouds.removeAll(remove);
	}

	public void dropShips() {
		Random rand = new Random();
		int randX = rand.nextInt(690);
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
		int randx = rand.nextInt(690);// edit
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

	public void processCollision(PlayerShip shooter) {
		var removeBullets = new ArrayList<GameElement>();
		var removeEnemies = new ArrayList<GameElement>();
		var removeComponents = new ArrayList<GameElement>();
		var removeBombs = new ArrayList<GameElement>();
		ArrayList<GameElement> newComponents = new ArrayList<>();

		for (var row : rows) {
			for (var enemy : row) {
				if (enemy.y >= 439) {
					gameOver();
				}
			}
		}

		// bullets vs enemies
		for (var row : rows) {
			for (var enemy : row) {
				for (var bullet : shooter.getWeapons()) {
					if (enemy.collideWith(bullet)) {
						AlienBoard.score += 10;
						gameboard.getScoreCard().setText("Score: " + AlienBoard.score);
						AlienBoard.enemies -= 1;
						AlienBoard.enemyCount.setText("Enemies Left: " + enemies);
						removeBullets.add(bullet);
						removeEnemies.add(enemy);
					}
				}
			}
			shooter.getWeapons().removeAll(removeBullets);
			row.removeAll(removeEnemies);
			removeEnemies.clear();
			removeBullets.clear();

			if (AlienBoard.enemies == 0) {
				gameboard.getCanvas().getGameElements().clear();
				gameboard.getCanvas().getGameElements().add(new TextDraw("YOU WIN!", 75, 150, Color.GREEN, 100));
				gameboard.getScoreCard().setText("Score: " + AlienBoard.score);
				AlienBoard.score = 0;
			}
		}
		// end bullets vs enemies

		// bombs vs shooter
		for (var b : bombs) {
			for (var player : shooter.getComponents()) {
				if (b.collideWith(player)) {
					if (shooter.getComponentSize() != 1) {
						shooter.goNextState();
					}
					removeBombs.add(b);
					lostComponents++;
					removeComponents.add(player);
				}
			}
			shooter.getComponents().removeAll(removeComponents);
			gameComments.healthUpdate(shooter.getComponentSize());

			if (shooter.getComponentSize() == 1) {
				int x = shooter.getComponents().get(0).getX();
				shooter.setX(x - PlayerShipElement.SIZE + 29);
			}
			if (shooter.getComponentSize() == 2) {
				int x = shooter.getComponents().get(0).getX();
				if (shooter.getComponents().get(1).getY() == shooter.getComponents().get(0).getY()) {
					shooter.setX(x - PlayerShipElement.SIZE + 38);
				} else
					shooter.setX(x - PlayerShipElement.SIZE + 29);
			}
		}

		bombs.removeAll(removeBombs);
		removeBombs.clear();
		removeComponents.clear();

		if (shooter.getComponentSize() == 0) {
			gameOver();
		}
		// end bomb vs shooter

		// bullets vs bombs
		for (var b : bombs) {
			for (var bullet : shooter.getWeapons()) {
				if (b.collideWith(bullet)) {
					AlienBoard.score += 2;
					gameboard.getScoreCard().setText("Score: " + AlienBoard.score);
					removeBombs.add(b);
					removeBullets.add(bullet);
				}
			}
		}
		shooter.getWeapons().removeAll(removeBullets);
		bombs.removeAll(removeBombs);
		removeBombs.clear();
		removeBullets.clear();
		// end bullet vs bombs

		// Aquiring Alien Fuel assistance
		for (var a : aliens) {
			for (var player : shooter.getComponents()) {
				if (a.collideWith(player) && lostComponents > 0) {
					if (a.getActive() == true) {
						lostComponents = 0;
						int x = player.getX();
						int y = 443;
						var b1 = new PlayerShipElement(x, y);
						var b2 = new PlayerShipElement(x + PlayerShipElement.SIZE, y);
						var b3 = new PlayerShipElement(x, y - PlayerShipElement.SIZE);
						var b4 = new PlayerShipElement(x + PlayerShipElement.SIZE, y - PlayerShipElement.SIZE);
						newComponents.add(b1);
						newComponents.add(b2);
						newComponents.add(b3);
						newComponents.add(b4);
						shooter.setComponents(newComponents);
						gameComments.healthUpdate(shooter.getComponentSize());
						shooter.setX(x - PlayerShipElement.SIZE + 38);
						shooter.setY(y);
						emptyAlien = new EmptyAlien();
						a.setActive(false);

						a.setAnimation(emptyAlien);
						shooter.setState(new SafeLevel(AlienBoard.getComment()));
					}
				}
			}
		}

		// enemies vs shooter
		for (var row : rows) {
			for (var enemy : row) {
				for (var player : shooter.getComponents()) {
					if (enemy.collideWith(player)) {
						removeComponents.add(player);
						removeEnemies.add(enemy);
					}
				}
			}
			shooter.getComponents().removeAll(removeComponents);
			gameComments.healthUpdate(shooter.getComponentSize());
			row.removeAll(removeEnemies);
			removeEnemies.clear();
			removeComponents.clear();

			if (rows.size() == 0 && shooter.getComponentSize() != 0) {
				gameboard.getCanvas().getGameElements().clear();
				gameboard.getCanvas().getGameElements().add(new TextDraw("YOU WIN!", 73, 150, Color.GREEN, 100));
				gameboard.getScoreCard().setText("Score: " + AlienBoard.score);
				AlienBoard.score = 0;
			}
		} // end enemies vs shooter

		// Aquiring an extra ship
		for (var s : ships) {
			for (var player : shooter.getComponents()) {
				if (s.collideWith(player) && lostComponents != 0) {
					ships.remove(s);
					// newComponents.clear();
					lostComponents--;
					int x = player.getX();
					int y = 443;
					var b1 = new PlayerShipElement(x, y);
					var b2 = new PlayerShipElement(x + PlayerShipElement.SIZE, y);
					var b3 = new PlayerShipElement(x, y - PlayerShipElement.SIZE);
					var b4 = new PlayerShipElement(x + PlayerShipElement.SIZE, y - PlayerShipElement.SIZE);

					if (shooter.getComponentSize() == 1) {
						newComponents.add(b1);
						newComponents.add(b2);
						shooter.setComponents(newComponents);
						gameComments.healthUpdate(shooter.getComponentSize());
						shooter.setX(x - PlayerShipElement.SIZE + 29);
						shooter.setY(y);
						break;

					}
					if (shooter.getComponentSize() == 2) {
						newComponents.add(b1);
						newComponents.add(b2);
						newComponents.add(b3);
						shooter.setComponents(newComponents);
						gameComments.healthUpdate(shooter.getComponentSize());
						shooter.setX(x - PlayerShipElement.SIZE + 38);
						shooter.setY(y);
						break;

					}
					if (shooter.getComponentSize() == 3) {
						newComponents.add(b1);
						newComponents.add(b2);
						newComponents.add(b3);
						newComponents.add(b4);
						shooter.setComponents(newComponents);
						gameComments.healthUpdate(shooter.getComponentSize());
						shooter.setX(x - PlayerShipElement.SIZE + 38);
						shooter.setY(y);
						break;
					}
					if (shooter.getComponentSize() == 4) {
						shooter.setState(new SafeLevel(AlienBoard.getComment()));
					}
					if (shooter.getComponentSize() == 3) {
						shooter.setState(new SafeLevel(AlienBoard.getComment()));
					}
					if (shooter.getComponentSize() == 2) {
						shooter.setState(new CautionLevel(AlienBoard.getComment()));
					}
					if (shooter.getComponentSize() == 1) {
						shooter.setState(new DangerLevel(AlienBoard.getComment()));
					}
				}
			}
		}
		// end ships vs shooter
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

	private void gameOver() {
		gameboard.getCanvas().getGameElements().clear();
		gameboard.getCanvas().getGameElements().add(new TextDraw("GAME OVER!", 47, 140, Color.MAGENTA, 95));
		gameboard.getScoreCard().setText("Score: " + AlienBoard.score);
		AlienBoard.score = 0;
		AlienBoard.playing = 0;
		gameboard.getCanvas().getGameElements().add(new TextDraw("EXIT", 605, 450, Color.RED, 35));
	}
}
