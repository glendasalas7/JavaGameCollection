package AlienInvader.model.HealthLevels;

import javax.swing.JPanel;
import AlienInvader.view.AlienBoard;

public class HealthChanger implements Observer {
    private AlienBoard gameBoard;

    public HealthChanger(AlienBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void actionPerformed(JPanel panel) {
        AlienBoard.setSouthPanel(panel);
    }
}
