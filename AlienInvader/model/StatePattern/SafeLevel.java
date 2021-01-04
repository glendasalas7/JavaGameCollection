package AlienInvader.model.StatePattern;
import javax.swing.JLabel;
import AlienInvader.model.PlayerShip;

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
