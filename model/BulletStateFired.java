package model;
/*
 * Participant: ConcreteState
 */

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BulletStateFired implements BulletState {
	private JLabel label;
	
	public BulletStateFired(JLabel label) {
		label.setText("SAFE");
	}

	@Override
	public void goNext(Shooter context, JLabel label){
		context.setState(new BulletStateExplosion(label));
	}
	@Override
	public void goBack(Shooter context, JLabel label) {
		context.setState(null);

	}

}
