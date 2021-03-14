package AlienInvader.model;

import java.awt.Graphics2D;
import java.util.ArrayList;
import AlienInvader.model.AlienActivity.Animation;
import AlienInvader.model.HealthLevel.Observer;

public abstract class GameElement implements Animation {
	public int y;
	public int x;
	public int width;
	public int height;
	public ArrayList<Observer> observers = new ArrayList<>();

	public GameElement(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public GameElement() {
		this(0, 0, 0, 0);
	}

	public boolean collideWith(GameElement another) {
		if (another.x > x + width || x > another.x + another.width || y + height < another.y
				|| y > another.y + another.height)
			return false;
		else
			return true;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ArrayList<Observer> getObservers() {
		return observers;
	}

	public abstract void setActive(boolean status);

	public abstract boolean getActive();

	public abstract void render(Graphics2D g2);

	public abstract void animate();

	public abstract void setAnimation(Animation animation);
}