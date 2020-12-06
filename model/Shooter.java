package model;

import java.awt.Graphics2D;
import java.util.ArrayList;

import model.Berries.Observer;
import java.awt.Color;

public class Shooter extends GameElement{
	
	public static final int UNIT_MOVE = 10;
	public static final int MAX_BULLETS = 3;
	private ArrayList<GameElement> components = new ArrayList<>();
	private ArrayList<GameElement> weapons = new ArrayList<>();
	// public int x = super.x;
	// public int y = super.y;

	public Shooter(int x, int y){
		super(x, y, 0, 0);

		var size = ShooterElement.SIZE;
		var s1 = new ShooterElement(x-size, y-size, Color.MAGENTA, false);
		var s2 = new ShooterElement(x, y-size, Color.MAGENTA, false);
		var s3 = new ShooterElement(x-size, y, Color.MAGENTA, false);
		var s4 = new ShooterElement(x, y, Color.MAGENTA, false);
		components.add(s1);
		components.add(s2);
		components.add(s3);
		components.add(s4);	
	}

	public void moveRight(){
		// if(components.get(0).x < 560){
			super.x += UNIT_MOVE;
				for(var c: components){
					c.x += UNIT_MOVE;
				}
			// }
			// setComponents(components);
	}

	 public void moveLeft(){
	 	// if(components.get(0).x > 5){
			super.x -= UNIT_MOVE;
			for(var c: components){
	 			c.x -= UNIT_MOVE;
	 		}
		//  }
		//  setComponents(components);
	}

	public boolean canFireMoreBullets(){
		return weapons.size() < MAX_BULLETS;
	}

	public void removeBulletsOutOfBounds(){
		var remove = new ArrayList<GameElement>();
		for(var w: weapons){
			if(w.y <0) remove.add(w);

		}
		weapons.removeAll(remove);
	}

	public ArrayList<GameElement> getWeapons() {
		return weapons;
	}

	@Override
	public void render(Graphics2D g2) {
		for(var c: components){
			c.render(g2);
		}
		for(var w: weapons)
			w.render(g2);
	}

	@Override
	public void animate() {
		for(var w: weapons)
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
		// TODO Auto-generated method stub
		super.setX(x);
	}
	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		super.setY(y);
	}
}

