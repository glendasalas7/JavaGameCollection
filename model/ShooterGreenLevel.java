package model;
/*
 * Participant: ConcreteState
 */

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShooterGreenLevel implements ShooterState {
	private JLabel label;
	
	public ShooterGreenLevel(JLabel label) {
		label.setText("SAFE");
	}

	@Override
	public void goNext(Shooter context, JLabel label){
		context.setState(new ShooterYellowLevel(label));
	}
	@Override
	public void goBack(Shooter context, JLabel label) {

	}

}
