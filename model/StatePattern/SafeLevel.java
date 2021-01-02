package model.StatePattern;
/*
 * Participant: ConcreteState
 */
import javax.swing.JLabel;
import model.PlayerShip;

public class SafeLevel implements ShooterState {
	
	public SafeLevel(JLabel label) {
		label.setText("SAFE");
	}

	@Override
	public void goNext(PlayerShip context, JLabel label){
		context.setState(new CautionLevel(label));
	}
	@Override
	public void goBack(PlayerShip context, JLabel label) {}

}
