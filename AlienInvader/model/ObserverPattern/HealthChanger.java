package AlienInvader.model.ObserverPattern;
import javax.swing.JPanel;
import AlienInvader.view.AlienMenuScreen;

public class HealthChanger implements Observer {
    private AlienMenuScreen gameBoard;
    public HealthChanger(AlienMenuScreen gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void actionPerformed(JPanel panel) {
		AlienMenuScreen.setSouthPanel(panel);
	}
}
