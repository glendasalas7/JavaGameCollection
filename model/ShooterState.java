package model;
/*
 * Participant: State
 */

import javax.swing.JLabel;
import javax.swing.JPanel;

import view.GameBoard;

public interface ShooterState {

	void goNext(Shooter context, JLabel panel);
	void goBack(Shooter context, JLabel panel);
}