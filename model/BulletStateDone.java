package model;
/*
 * Participant: ConcreteState
 */

import javax.swing.JLabel;

public class BulletStateDone implements BulletState {
	private JLabel label;

	public BulletStateDone(JLabel label) {
		label.setText("WARNING");

	}

	@Override
	public void goNext(Shooter context, JLabel label) {
		context.setState(null); // end state
	}

	@Override
	public void goBack(Shooter context, JLabel label) {
		context.setState(new BulletStateExplosion(label));

	}

}
