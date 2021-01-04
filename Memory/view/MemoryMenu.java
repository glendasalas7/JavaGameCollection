package Memory.view;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import Memory.model.Stats;

public class MemoryMenu {
	private JFrame window;
	private Stats stats = new Stats();
	public MemoryMenu(JFrame window){
		this.window = window;
		window.setTitle("M E M O R Y : THE GAME");
	}
	
	public MemoryMenu(JFrame window, Stats stats){
		this.window = window;
		this.stats = stats;
		// window.setTitle("M E M O R Y : THE GAME");
	}

	public void enter(){
		JPanel menuPanel = new JPanel();
		MemoryMenuCanvas canvas = new MemoryMenuCanvas(this, 800, 300);
		JButton playBUTTON = new JButton("PLAY");
		JButton statsBUTTON = new JButton("VIEW STATS");
		playBUTTON.setFont(new Font("Courier", Font.PLAIN, 40));
		playBUTTON.setPreferredSize(new Dimension(345, 100));
		statsBUTTON.setFont(new Font("Courier", Font.PLAIN, 40));
		statsBUTTON.setPreferredSize(new Dimension(345, 100));
		menuPanel.add(canvas);
		menuPanel.add(playBUTTON);
		menuPanel.add(statsBUTTON);
		window.add(menuPanel);
		playBUTTON.addActionListener(e -> {
			var memory = new MemoryGameScreen(window, stats);
			window.getContentPane().removeAll();//clear window so you can load new window
			memory.stepIn();
			window.pack();
			window.revalidate();
		}
		);
		statsBUTTON.addActionListener(e -> {
			var statsScreen = new StatsScreen(window, stats);
			window.getContentPane().removeAll();//clear window so you can load new window
			statsScreen.stepIn();
			window.pack();
			window.revalidate();
		}
		);
}
}