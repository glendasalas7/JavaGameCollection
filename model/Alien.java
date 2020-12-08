package model;

import java.awt.Graphics2D;
import model.SrategyPattern.Animation;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Alien extends GameElement{
    
    private Animation animation;
    private Graphics2D g2;
    public static final int SIZE = 7;
    public static final int UNIT_MOVE = 5;

    public Alien(int x, int y, Animation animation) {
        super(x, y, Color.green, true, SIZE, SIZE);
        this.animation = animation;
        animation.setX(super.x);
        animation.setY(super.y);
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

	@Override
	public void render(Graphics2D g2) {
        animation.setX(super.x);
        animation.setY(super.y);
        animation.render(g2);
	}

    public void animate() {
        super.y += UNIT_MOVE;
    }

    @Override
    public int getX() {
        // TODO Auto-generated method stub
        return super.getX();
    }
    @Override
    public int getY() {
        // TODO Auto-generated method stub
        return super.getY();
    }
}
