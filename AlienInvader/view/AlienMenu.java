package AlienInvader.view;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.MainMenu;

public class AlienMenu{
	private JFrame window;
	public AlienMenu(JFrame window){
		this.window = window;
		window.setTitle("A L I E N   I N V A S I O N");
	}

	public void enter(){
		JPanel menuPanel = new JPanel();
		AlienCanvas canvas = new AlienCanvas(800, 300, false);
		JButton playBUTTON = new JButton("PLAY");
		JButton returnBUTTON = new JButton("RETURN");
		playBUTTON.setFont(new Font("Courier", Font.PLAIN, 30));
		playBUTTON.setPreferredSize(new Dimension(500, 65));
		returnBUTTON.setFont(new Font("Courier", Font.PLAIN, 30));
		returnBUTTON.setPreferredSize(new Dimension(500, 65));
		menuPanel.add(canvas);
		menuPanel.add(playBUTTON);
		menuPanel.add(returnBUTTON);
		window.add(menuPanel);
		playBUTTON.addActionListener(e -> {
			// var memory = new MemoryBoard(window, stats);
			// window.getContentPane().removeAll();//clear window so you can load new window
			// memory.stepIn();
			// window.pack();
			// window.revalidate();
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
