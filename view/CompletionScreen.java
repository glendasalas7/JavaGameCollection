package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Stats;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class CompletionScreen {
	private JFrame window;
	private long completionTime;
	private Stats s;

	public CompletionScreen(JFrame window, long time, Stats s){
		this.window = window;
		window.setTitle("YOU DID IT!");
		completionTime = time;
		this.s = s;
	}

	public void stepIn(){
		Container cp = window.getContentPane();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));

		panel.setPreferredSize(new Dimension(400, 200));
		JLabel finisingStatement = new JLabel("<html>YOU DID IT! <br> Completed in: " + completionTime + " seconds!");
		JButton exitButton = new JButton("EXIT");

		panel.add(finisingStatement);
		panel.add(exitButton);

		cp.add(BorderLayout.CENTER, panel);

		exitButton.addActionListener(e ->{
			window.getContentPane().removeAll();
			var menu = new MemoryMenu(window, s);
			menu.stepIn();
			window.pack();
			window.revalidate();
		}
		);
	}
}