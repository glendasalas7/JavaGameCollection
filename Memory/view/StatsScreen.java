package Memory.view;

import java.awt.Container;
import java.util.ArrayList;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import Memory.model.Stats;

public class StatsScreen {
	private JTextArea textArea = new JTextArea();
	private JFrame window;
	private Stats stats;

	public StatsScreen(JFrame w) {
		window = w;
	}

	public void stepIn() {
		Container c = window.getContentPane();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		ArrayList<Long> times = stats.getTimeRecords();
		int count = stats.getGameCount();
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setEditable(true);
		textArea.setText("");

		for (int i = 0; i < count; i++) {
			textArea.append(i + 1 + ". Completed in " + times.get(i) + " seconds! \n");
		}
		JButton exitButton = new JButton("EXIT");
		panel.add(scrollPane);
		panel.add(exitButton);
		c.add(panel);

		exitButton.addActionListener(e -> {
			window.getContentPane().removeAll();
			var memMenu = new MemoryMenu(window);
			memMenu.enter();
			window.pack();
			window.revalidate();
		});
	}
}