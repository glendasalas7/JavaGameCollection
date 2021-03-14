package AlienInvader.model.AlienActivity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class FullAlien implements Animation {
    ;
    private int x;
    private int y;

    @Override
    public void render(Graphics2D g2) {
        try {
            BufferedImage fallingAlienIMG = ImageIO.read(new File("AlienInvader/images/alien_full.png"));
            Image temp = fallingAlienIMG.getScaledInstance(35, 35, Image.SCALE_SMOOTH);

            g2.drawImage(temp, y, x, null);
        } catch (Exception e) {
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
