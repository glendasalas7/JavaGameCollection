package model;

import java.awt.Graphics2D;
import model.SrategyPattern.Animation;
import java.awt.Color;

public class Alien extends GameElement {
    
    private Animation animation;
    private Graphics2D g2;
    public static final int SIZE = 7;
    public static final int UNIT_MOVE = 5;
    
    public Alien(int x, int y) {
        super(x, y, Color.green, true, SIZE, SIZE);
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }
    
    public void update() {
        animation.animate();
        render(g2);
    }

	@Override
	public void render(Graphics2D g2) {
		
	}

    @Override
    public void animate() {
    }
    @Override
    public int getY() {
        return super.getY();
    }
    @Override
    public int getX() {
        return super.getX();
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
