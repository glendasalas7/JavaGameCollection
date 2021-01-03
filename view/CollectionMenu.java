package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
public class CollectionMenu {
	
	private JFrame window;
	public CollectionMenu(JFrame window){
		this.window = window;
		window.setTitle("J A V A ~ G A M E ~ C O L L E C T I O N");
	}

	public void stepIn(){
		JPanel menuPanel = new JPanel();

		JButton playBUTTON = new JButton("MEMORY");
		JButton statsBUTTON = new JButton("SPACE GAME");
		playBUTTON.setFont(new Font("Courier", Font.PLAIN, 40));
		playBUTTON.setPreferredSize(new Dimension(345, 100));
		statsBUTTON.setFont(new Font("Courier", Font.PLAIN, 40));
		statsBUTTON.setPreferredSize(new Dimension(345, 100));
		menuPanel.add(playBUTTON);
		menuPanel.add(statsBUTTON);
		window.add(menuPanel);
		playBUTTON.addActionListener(e -> {
			MemoryMenu memoryMenu = new MemoryMenu(window);
			window.getContentPane().removeAll();//clear window so you can load new window
			memoryMenu.stepIn();
			window.pack();
			window.revalidate();
		}
		);
		statsBUTTON.addActionListener(e -> {
			SpaceGameBoard spaceGame = new SpaceGameBoard(window);
			window.getContentPane().removeAll();//clear window so you can load new window
			spaceGame.init();
			window.pack();
			window.revalidate();
		}
		);
}
}