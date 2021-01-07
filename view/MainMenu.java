package view;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import AlienInvader.view.AlienMenu;
import Memory.view.MemoryMenu;
import java.awt.Dimension;
import java.awt.Font;

public class MainMenu {
	private JFrame window;
	public MainMenu(JFrame window){
		this.window = window;
		window.setTitle("J A V A   G A M E   C O L L E C T I O N");
	}

	public void enter(){
		JPanel menuPanel = new JPanel();
		JButton memBUTTON = new JButton("PLAY MEMORY");
		JButton spaceInvBUTTON = new JButton("PLAY ALIEN INVASION");
		MainMenuCanvas mmCanvas = new MainMenuCanvas(700, 300);
		memBUTTON.setPreferredSize(new Dimension(500, 65));
		spaceInvBUTTON.setPreferredSize(new Dimension(500, 65));
		memBUTTON.setFont(new Font("Courier", Font.PLAIN, 30));
		spaceInvBUTTON.setFont(new Font("Courier", Font.PLAIN, 30));
		menuPanel.add(mmCanvas);
		menuPanel.add(memBUTTON);
		menuPanel.add(spaceInvBUTTON);
		window.add(menuPanel);

		memBUTTON.addActionListener(e -> {
			MemoryMenu memoryMenu = new MemoryMenu(window);
			window.getContentPane().removeAll();
			memoryMenu.enter();
			window.pack();
			window.revalidate();
		}
		);
		spaceInvBUTTON.addActionListener(e -> {
			AlienMenu spaceGame = new AlienMenu(window);
			window.getContentPane().removeAll();
			spaceGame.enter();
			window.pack();
			window.revalidate();
		}
		);
}
}