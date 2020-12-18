package model.StatePattern;
/*
 * Participant: ConcreteState
 */
import javax.swing.JLabel;
import model.Shooter;

public class DangerLevel implements ShooterState {

	public DangerLevel(JLabel label) {
		label.setText("WARNING");
	}

	@Override
	public void goNext(Shooter context, JLabel label) {}

	@Override
	public void goBack(Shooter context, JLabel label) {
		context.setState(new CautionLevel(label));

	}

}
