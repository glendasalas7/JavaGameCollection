package AlienInvader.model.ObserverPattern;
import javax.swing.JPanel;
import AlienInvader.view.AlienInvaderMenu;

public class HealthChanger implements Observer {
    private AlienInvaderMenu gameBoard;
    public HealthChanger(AlienInvaderMenu gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void actionPerformed(JPanel panel) {
		AlienInvaderMenu.setSouthPanel(panel);
	}
}
