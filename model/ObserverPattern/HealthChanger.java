package model.ObserverPattern;
import javax.swing.JPanel;
import view.SpaceGameBoard;

public class HealthChanger implements Observer {
    private SpaceGameBoard gameBoard;
    public HealthChanger(SpaceGameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void actionPerformed(JPanel panel) {
		SpaceGameBoard.setSouthPanel(panel);
	}
}
