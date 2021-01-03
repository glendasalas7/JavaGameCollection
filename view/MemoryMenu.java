package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import model.Stats;

public class MemoryMenu {
	
	private JFrame window;
	private Stats s = new Stats();
	public MemoryMenu(JFrame window){
		this.window = window;
		window.setTitle("M E M O R Y : THE GAME");
	}
	
	public MemoryMenu(JFrame window, Stats s){
		this.window = window;
		this.s = s;
		// window.setTitle("M E M O R Y : THE GAME");
	}

	public void stepIn(){
		JPanel menuPanel = new JPanel();
		MenuCanvas canvas = new MenuCanvas(this, 800, 300);
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
			var memory = new GameScreen(window, s);
			window.getContentPane().removeAll();//clear window so you can load new window
			memory.stepIn();
			window.pack();
			window.revalidate();
		}
		);
		statsBUTTON.addActionListener(e -> {
			var ss = new StatsScreen(window, s);
			window.getContentPane().removeAll();//clear window so you can load new window
			ss.stepIn();
			window.pack();
			window.revalidate();
		}
		);
}
}