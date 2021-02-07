package AlienInvader.model.CautionLevel;

import javax.swing.JLabel;
import AlienInvader.model.PlayerShip;

public interface ShooterState {

	void goNext(PlayerShip context, JLabel panel);

	void goBack(PlayerShip context, JLabel panel);
}
