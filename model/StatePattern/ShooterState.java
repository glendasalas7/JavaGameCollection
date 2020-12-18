package model.StatePattern;
/*
 * Participant: State
 */
import javax.swing.JLabel;

import model.Shooter;
public interface ShooterState {

	void goNext(Shooter context, JLabel panel);
	void goBack(Shooter context, JLabel panel);
}
