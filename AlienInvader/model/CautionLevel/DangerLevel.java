package AlienInvader.model.CautionLevel;

import javax.swing.JLabel;
import AlienInvader.model.PlayerShip;

public class DangerLevel implements ShooterState {

	public DangerLevel(JLabel label) {
		label.setText("WARNING");
	}

	@Override
	public void goNext(PlayerShip context, JLabel label) {
	}

	@Override
	public void goBack(PlayerShip context, JLabel label) {
		context.setState(new CautionLevel(label));
	}

}
