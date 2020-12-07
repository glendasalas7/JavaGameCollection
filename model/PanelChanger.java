package model;
import javax.swing.JPanel;
import model.ObserverPattern.Observer;
import view.GameBoard;

public class PanelChanger implements Observer {
    private GameBoard gameBoard;

    public PanelChanger(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void actionPerformed(JPanel panel) {
		GameBoard.setSouthPanel(panel);
	}
}
