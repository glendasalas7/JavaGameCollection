package model;
/*
 * Participant: ConcreteState
 */

import javax.swing.JLabel;

public class BulletStateExplosion implements BulletState {

	private JLabel label;

	public BulletStateExplosion(JLabel label) {
		label.setText("CAUTION");
	}

	@Override
	public void goNext(Shooter context, JLabel label) {
		context.setState(new BulletStateDone(label));
	}

	@Override
	public void goBack(Shooter context, JLabel label) {
		context.setState(new BulletStateFired(label));
	}

}
