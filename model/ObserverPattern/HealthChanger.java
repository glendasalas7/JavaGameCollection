package model.ObserverPattern;
import javax.swing.JPanel;
import view.InvasionMenu;

public class HealthChanger implements Observer {
    private InvasionMenu gameBoard;
    public HealthChanger(InvasionMenu gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void actionPerformed(JPanel panel) {
		InvasionMenu.setSouthPanel(panel);
	}
}
