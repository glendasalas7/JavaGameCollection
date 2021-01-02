package model.StatePattern;
/*
 * Participant: ConcreteState
 */
import javax.swing.JLabel;
import model.PlayerShip;

public class CautionLevel implements ShooterState {

	public CautionLevel(JLabel label) {
		label.setText("CAUTION");
	}

	@Override
	public void goNext(PlayerShip context, JLabel label) {
		context.setState(new DangerLevel(label));
	}

	@Override
	public void goBack(PlayerShip context, JLabel label) {
		context.setState(new SafeLevel(label));
	}

}
