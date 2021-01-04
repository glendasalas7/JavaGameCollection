package view;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import AlienInvader.view.AlienMenuScreen;
import Memory.view.MemMenuScreen;
import java.awt.Dimension;
import java.awt.Font;

public class MainMenu {
	private JFrame window;
	public MainMenu(JFrame window){
		this.window = window;
	}

	public void enter(){
		JPanel menuPanel = new JPanel();

		JButton memBUTTON = new JButton("PLAY MEMORY");
		JButton spaceInvBUTTON = new JButton("PLAY SPACE INVASION");
		memBUTTON.setFont(new Font("Courier", Font.PLAIN, 40));
		memBUTTON.setPreferredSize(new Dimension(345, 100));
		spaceInvBUTTON.setFont(new Font("Courier", Font.PLAIN, 40));
		spaceInvBUTTON.setPreferredSize(new Dimension(345, 100));
		menuPanel.add(memBUTTON);
		menuPanel.add(spaceInvBUTTON);
		window.add(menuPanel);
		memBUTTON.addActionListener(e -> {
			MemMenuScreen memoryMenu = new MemMenuScreen(window);
			window.getContentPane().removeAll();
			memoryMenu.enter();
			window.pack();
			window.revalidate();
		}
		);
		spaceInvBUTTON.addActionListener(e -> {
			AlienMenuScreen spaceGame = new AlienMenuScreen(window);
			window.getContentPane().removeAll();
			spaceGame.enter();
			window.pack();
			window.revalidate();
		}
		);
}
}