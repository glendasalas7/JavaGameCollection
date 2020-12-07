package model;


import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

import java.awt.Color;

public class Potion extends GameElement{
    public static final int SIZE = 7;
    public static final int UNIT_MOVE = 5;
    
    public Potion(int x, int y) {
        super(x, y, Color.green, true, SIZE, SIZE);
    }

    @Override
    public void render(Graphics2D g2) {
            try{
                BufferedImage potionIMG = ImageIO.read(new File("pictures/potion.png"));
                Image temp = potionIMG.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

                g2.drawImage(temp, x, y, null);
            } catch(Exception e){
                System.out.println("Image file load error");
            } 
    }

    @Override
    public void animate() {
        super.y += UNIT_MOVE;
    }
}
