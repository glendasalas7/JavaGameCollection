package model;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import model.StrategyPattern.Animation;
import java.awt.Color;

public class ShipHelper extends GameElement {
    public static final int SIZE = 7;
    public static final int UNIT_MOVE = 5;

    public ShipHelper(int x, int y) {
        super(x, y, Color.green, true, SIZE, SIZE);
    }

    @Override
    public void render(Graphics2D g2) {
        try {
            BufferedImage potionIMG = ImageIO.read(new File("pictures/helper_ship.png"));
            Image temp = potionIMG.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

            g2.drawImage(temp, x, y, null);
        } catch (Exception e) {
            System.out.println("Image file load error");
        }
    }

    @Override
    public void animate() {
        super.y += UNIT_MOVE;
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
