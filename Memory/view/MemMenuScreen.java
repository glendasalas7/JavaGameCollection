package Memory.view;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import Memory.model.Stats;

public class MemMenuScreen {
	private JFrame window;
	private Stats stats = new Stats();
	public MemMenuScreen(JFrame window){
		this.window = window;
		window.setTitle("M E M O R Y");
	}
	
	public MemMenuScreen(JFrame window, Stats stats){
		this.window = window;
		this.stats = stats;
		window.setTitle("M E M O R Y");
	}

	public void enter(){
		JPanel menuPanel = new JPanel();
		MemMenuCanvas canvas = new MemMenuCanvas(this, 800, 300);
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
			var memory = new GameScreen(window, stats);
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