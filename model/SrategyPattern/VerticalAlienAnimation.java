package model.SrategyPattern;

import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import model.Alien;
import java.awt.Graphics2D;

public class VerticalAlienAnimation implements Animation {
    private Alien alien;
    public final int UNITS;
    private int x;
    private int y;
   
    public VerticalAlienAnimation(final int UNITS, int x, int y){
        this.UNITS = UNITS;
        this.x = x;
        this.y = y;
    }

    @Override
    public void animate() {
        int y = alien.getY();   
        alien.setY(y + UNITS);
    }

    @Override
    public void render(Graphics2D g2) {
        try{
            BufferedImage alienIMG = ImageIO.read(new File("pictures/alien.png"));
            Image temp = alienIMG.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

            g2.drawImage(temp, x, y, null);
        } catch(Exception e){
            System.out.println("Image file load error");
        } 
    }
    
}
