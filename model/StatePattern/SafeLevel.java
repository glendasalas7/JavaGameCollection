package model.StatePattern;
/*
 * Participant: ConcreteState
 */
import javax.swing.JLabel;
import model.Shooter;

public class SafeLevel implements ShooterState {
	
	public SafeLevel(JLabel label) {
		label.setText("SAFE");
	}

	@Override
	public void goNext(Shooter context, JLabel label){
		context.setState(new CautionLevel(label));
	}
	@Override
	public void goBack(Shooter context, JLabel label) {}

}
