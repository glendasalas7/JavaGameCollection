package model.SrategyPattern;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class AquiredAlien implements Animation{;
    private int x;
    private int y;

    @Override
    public void render(Graphics2D g2) {
        try {
            BufferedImage potionIMG = ImageIO.read(new File("pictures/ship.png"));
            Image temp = potionIMG.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    
            g2.drawImage(temp, y, x, null);
        } catch (Exception e) {
            System.out.println("Image file load error");
        } 
    }

    @Override
    public void setX(int y) {
        this.y = y;

    }

    @Override
    public void setY(int x) {
        this.x = x;

    }
}
