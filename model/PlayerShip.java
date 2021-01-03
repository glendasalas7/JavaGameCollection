package model;

import java.awt.Graphics2D;
import java.util.ArrayList;
import model.StatePattern.SafeLevel;
import model.StatePattern.ShooterState;
import model.StrategyPattern.Animation;
import view.SpaceGameBoard;

public class PlayerShip extends GameElement {

	public static final int UNIT_MOVE = 10;
	public static final int MAX_BULLETS = 3;
	private ArrayList<GameElement> components = new ArrayList<>();
	private ArrayList<GameElement> weapons = new ArrayList<>();
	private ShooterState state;

	public PlayerShip(int x, int y) {
		super(x, y, 0, 0);
		state = new SafeLevel(SpaceGameBoard.getComment()); // initial state
		var size = PlayerShipElements.SIZE;
		var s1 = new PlayerShipElements(x - size, y - size);
		var s2 = new PlayerShipElements(x, y - size);
		var s3 = new PlayerShipElements(x - size, y);
		var s4 = new PlayerShipElements(x, y);
		components.add(s1);
		components.add(s2);
		components.add(s3);
		components.add(s4);
	}

	public void moveRight() {
		// if(components.get(0).x < 560){
		super.x += UNIT_MOVE;
		for (var c : components) {
			c.x += UNIT_MOVE;
		}
	}

	public void moveLeft() {
		// if(components.get(0).x > 5){
		super.x -= UNIT_MOVE;
		for (var c : components) {
			c.x -= UNIT_MOVE;
		}
	}

	public boolean canFireMoreBullets() {
		return weapons.size() < MAX_BULLETS;
	}

	public void removeBulletsOutOfBounds() {
		var remove = new ArrayList<GameElement>();
		for (var w : weapons) {
			if (w.y < 0)
				remove.add(w);
		}
		weapons.removeAll(remove);
	}

	public ArrayList<GameElement> getWeapons() {
		return weapons;
	}

    public void goNextState() {
        state.goNext(this, SpaceGameBoard.getComment());
	}

	public void goBackState() {
        state.goBack(this, SpaceGameBoard.getComment());
    }

    public void setState(ShooterState state) {
        this.state = state;
    }

	@Override
	public void render(Graphics2D g2) {
		for (var c : components) {
			c.render(g2);
		}
		for (var w : weapons)
			w.render(g2);
	}

	@Override
	public void animate() {
		for (var w : weapons)
			w.animate();
	}

	public ArrayList<GameElement> getComponents() {
		return components;
	}

	public int getComponentSize() {
		return components.size();
	}

	public void setComponents(ArrayList<GameElement> components) {
		this.components = components;
	}

	@Override
	public void setX(int x) {
		super.setX(x);
	}

	@Override
	public void setY(int y) {
		super.setY(y);
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



