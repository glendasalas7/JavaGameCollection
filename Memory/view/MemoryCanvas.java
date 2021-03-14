package Memory.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class MemoryCanvas extends JPanel {
    private static final long serialVersionUID = 1L;
    private MemoryMenu menuScreen;
    private int width;
    private int height;

    public MemoryCanvas(MemoryMenu menuScreen, int width, int height) {
        this.menuScreen = menuScreen;
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paintComponent(Graphics g) {

        BufferedImage memBG;
        try {
            memBG = ImageIO.read(new File("Memory/images/hall.png"));
            Image temp = memBG.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            g.drawImage(temp, 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.setFont(new Font("Courier", Font.BOLD, 95));
        g2.drawString("M E M O R Y", 75, 120);
        g2.drawString("G A M E ", 170, 200);
        g2.setColor(Color.RED);
        g2.drawString("M E M O R Y", 70, 130);
        g2.drawString("G A M E ", 165, 210);
        g2.setFont(new Font("Courier", Font.BOLD, 40));
        g2.drawString("RETURN", 50, 420);
        g2.setColor(Color.YELLOW);
        g2.drawString("PLAY", 300, 420); /// 120 330
        g2.setColor(Color.GRAY);
        g2.drawString("STATS ", 525, 420);

    }
}
