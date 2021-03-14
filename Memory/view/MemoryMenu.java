package Memory.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import Memory.controller.MenuClickListener;

public class MemoryMenu {
	private JFrame window;

	public MemoryMenu(JFrame window) {
		this.window = window;
		window.setTitle("M E M O R Y");
	}

	public void enter() {
		JPanel menuPanel = new JPanel();
		MemoryCanvas canvas = new MemoryCanvas(this, 700, 500);
		MenuClickListener clickListener = new MenuClickListener(window);
		canvas.addMouseListener(clickListener);
		menuPanel.add(canvas);
		window.add(menuPanel);
	}
}