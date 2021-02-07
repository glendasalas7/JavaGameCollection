package Memory.view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import Memory.controller.ButtonClickListener;

public class MemoryBoard {
	// private Stats stats;
	private JFrame window;
	private ArrayList<JButton> memoryCards;
	private JButton exitButton = new JButton("Exit");

	public MemoryBoard(JFrame window) {
		this.window = window;

	}

	public void stepIn() {
		JPanel memoryPanel = new JPanel();
		memoryPanel.setBorder(new LineBorder(Color.BLACK, 3));
		window.add(memoryPanel);// add menuPanel to window
		GridLayout memoryLayout = new GridLayout(2, 5, 5, 5); // height, width
		memoryPanel.setLayout(memoryLayout);
		ArrayList<String> cardlist = new ArrayList<String>();
		cardlist.add("$");
		cardlist.add("!");
		cardlist.add("$");
		cardlist.add("!");
		cardlist.add("%");
		cardlist.add("%");
		cardlist.add("&");
		cardlist.add("&");
		cardlist.add("#");
		cardlist.add("#");
		Collections.shuffle(cardlist);
		ButtonClickListener buttonClickListener = new ButtonClickListener(this, cardlist);
		memoryCards = new ArrayList<>();
		for (var c : cardlist) {
			memoryCards.add(new JButton(""));
		}
		for (var c : memoryCards) {
			c.addActionListener(buttonClickListener);
			memoryPanel.add(c);
		}
		memoryPanel.add(exitButton);
		exitButton.addActionListener(buttonClickListener);
	}

	public ArrayList<JButton> getmemoryCards() {
		return memoryCards;
	}

	public JButton getExitButton() {
		return exitButton;
	}

	public JFrame getWindow() {
		return window;
	}

}