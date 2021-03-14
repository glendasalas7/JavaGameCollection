package view;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
public class MainMenuCanvas extends JPanel {
    private static final long serialVersionUID = 1L;

    public MainMenuCanvas(int width, int height){
		setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
    }

	@Override
	public void paintComponent(Graphics g2){
        super.paintComponent(g2);
		g2.setColor(Color.green);
		g2.setFont(new Font("Courier", Font.BOLD, 70));
        g2.drawString("J A V A", 215, 70);
        g2.drawString("G A M E", 205, 165);
        g2.drawString("C O L L E C T I O N ", 35, 250);
	}
}
