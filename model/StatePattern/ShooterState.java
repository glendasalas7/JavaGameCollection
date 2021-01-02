package model.StatePattern;
/*
 * Participant: State
 */
import javax.swing.JLabel;

import model.PlayerShip;
public interface ShooterState {

	void goNext(PlayerShip context, JLabel panel);
	void goBack(PlayerShip context, JLabel panel);
}
