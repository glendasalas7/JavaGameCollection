package model;
/*
 * Participant: ConcreteState
 */

import javax.swing.JLabel;

public class ShooterYellowLevel implements ShooterState {

	private JLabel label;

	public ShooterYellowLevel(JLabel label) {
		label.setText("CAUTION");
	}

	@Override
	public void goNext(Shooter context, JLabel label) {
		context.setState(new ShooterDangerLevel(label));
	}

	@Override
	public void goBack(Shooter context, JLabel label) {
		context.setState(new ShooterGreenLevel(label));
	}

}
