package model.ObserverPattern;
import javax.swing.JPanel;
import view.GameBoard;

public class HealthChanger implements Observer {
    private GameBoard gameBoard;

    public HealthChanger(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void actionPerformed(JPanel panel) {
		GameBoard.setSouthPanel(panel);
	}
}
