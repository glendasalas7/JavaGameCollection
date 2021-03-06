package Memory.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Memory.model.Stats;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class EndGame {
	private JFrame window;
	private long completionTime;

	public EndGame(JFrame window, long time) {
		this.window = window;
		window.setTitle("YOU DID IT!");
		completionTime = time;
	}

	public void stepIn() {
		Container cp = window.getContentPane();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		panel.setPreferredSize(new Dimension(400, 200));
		JLabel finisingStatement = new JLabel("<html>YOU DID IT! <br> Completed in: " + completionTime + " seconds!");
		JButton exitButton = new JButton("EXIT");
		panel.add(finisingStatement);
		panel.add(exitButton);
		cp.add(BorderLayout.CENTER, panel);
		exitButton.addActionListener(e -> {
			window.getContentPane().removeAll();
			var memMenu = new MemoryMenu(window);
			memMenu.enter();
			window.pack();
			window.revalidate();
		});
	}
}