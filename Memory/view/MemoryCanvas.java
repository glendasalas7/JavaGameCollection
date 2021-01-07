package Memory.view;
import Memory.view.MemoryMenu;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
public class MemoryCanvas extends JPanel {
    private static final long serialVersionUID = 1L;
    private MemoryMenu menuScreen;

    public MemoryCanvas(MemoryMenu menuScreen, int width, int height){
		this.menuScreen = menuScreen;
		setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
    }

	@Override
	public void paintComponent(Graphics g2){
        super.paintComponent(g2);
		g2.setColor(Color.RED);
		g2.setFont(new Font("Courier", Font.BOLD, 70));
        g2.drawString("M E M O R Y", 200, 100);
        g2.setColor(Color.WHITE);
		g2.drawString("G A M E ", 250, 250);
	}
}
