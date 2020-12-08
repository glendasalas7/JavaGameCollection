package model;
/*
 * Participant: ConcreteState
 */

import javax.swing.JLabel;

public class ShooterDangerLevel implements ShooterState {
	private JLabel label;

	public ShooterDangerLevel(JLabel label) {
		label.setText("WARNING");

	}

	@Override
	public void goNext(Shooter context, JLabel label) {
		context.setState(null); // end state
	}

	@Override
	public void goBack(Shooter context, JLabel label) {
		context.setState(new ShooterYellowLevel(label));

	}

}
