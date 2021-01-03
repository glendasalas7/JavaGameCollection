package view;

import java.awt.Container;
import java.util.ArrayList;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import model.Stats;

public class StatsScreen {
	private JTextArea textArea = new JTextArea();
	private JFrame window;
	private Stats s;
	public StatsScreen(JFrame w, Stats s){
		window = w;
		this.s = s;
	}
	public void stepIn(){
		Container c = window.getContentPane();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		ArrayList<Long> times = s.getTimeRecords();
		int count = s.getGameCount();
		JScrollPane scrollPane = new JScrollPane(textArea); 
		textArea.setEditable(true);
		textArea.setText("");

		for(int i = 0; i < count; i++){
			textArea.append(i+1 + ". Completed in " + times.get(i) + " seconds! \n");		
		}	
		JButton exitButton = new JButton("EXIT");
		panel.add(scrollPane);
		panel.add(exitButton);
		c.add(panel);
		
		exitButton.addActionListener(e ->{
			window.getContentPane().removeAll();
			var memMenu = new MemoryMenu(window, s);
			memMenu.enter();
			window.pack();
			window.revalidate();
		}
		);
	}
}