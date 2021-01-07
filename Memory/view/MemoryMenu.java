package Memory.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import Memory.model.Stats;
import view.MainMenu;

public class MemoryMenu {
	private JFrame window;
	private Stats stats = new Stats();
	public MemoryMenu(JFrame window){
		this.window = window;
		window.setTitle("M E M O R Y");
	}
	
	public MemoryMenu(JFrame window, Stats stats){
		this.window = window;
		this.stats = stats;
		window.setTitle("M E M O R Y");
	}

	public void enter(){
		JPanel menuPanel = new JPanel();
		MemoryCanvas canvas = new MemoryCanvas(this, 800, 300);
		JButton playBUTTON = new JButton("PLAY");
		JButton statsBUTTON = new JButton("VIEW STATS");
		JButton returnBUTTON = new JButton("RETURN");
		playBUTTON.setFont(new Font("Courier", Font.PLAIN, 30));
		playBUTTON.setPreferredSize(new Dimension(345, 45));
		statsBUTTON.setFont(new Font("Courier", Font.PLAIN, 30));
		statsBUTTON.setPreferredSize(new Dimension(345, 45));
		returnBUTTON.setFont(new Font("Courier", Font.PLAIN, 30));
		returnBUTTON.setPreferredSize(new Dimension(345, 45));
		menuPanel.add(canvas);
		menuPanel.add(playBUTTON);
		menuPanel.add(statsBUTTON);
		menuPanel.add(returnBUTTON);
		window.add(menuPanel);
		playBUTTON.addActionListener(e -> {
			var memory = new MemoryBoard(window, stats);
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
		returnBUTTON.addActionListener(e -> {
			var mainMenu = new MainMenu(window);
			window.getContentPane().removeAll();//clear window so you can load new window
			mainMenu.enter();
			window.pack();
			window.revalidate();
		}
		);
}
}