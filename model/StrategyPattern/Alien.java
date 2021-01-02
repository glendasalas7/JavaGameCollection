package model.StrategyPattern;

import java.awt.Graphics2D;
import java.awt.Color;
import model.GameElement;

public class Alien extends GameElement{
    
    private Animation animation;
    public static final int SIZE = 33;
    public static int UNIT_MOVE = 7;
    private boolean active;

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
        if(active == false){
        super.x += UNIT_MOVE;
        }
        if(active == true){
        super.y += UNIT_MOVE;
        }
    }

    public Animation getAnimation() {
        return animation;
    }

    @Override
    public int getX() {
        return super.getX();
    }
    @Override
    public int getY() {
        return super.getY();
    }
    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean getActive() { 
        return active;
    }
    
}
