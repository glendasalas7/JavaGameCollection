package model.StatePattern;
/*
 * Participant: ConcreteState
 */
import javax.swing.JLabel;
import model.Shooter;

public class CautionLevel implements ShooterState {

	public CautionLevel(JLabel label) {
		label.setText("CAUTION");
	}

	@Override
	public void goNext(Shooter context, JLabel label) {
		context.setState(new DangerLevel(label));
	}

	@Override
	public void goBack(Shooter context, JLabel label) {
		context.setState(new SafeLevel(label));
	}

}
