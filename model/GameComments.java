package model;

import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Berries.Observer;
import model.Berries.Subject;
import view.GameBoard;

public class GameComments extends GameElement implements Subject{

    private GameBoard gameboard;
    private JPanel originalPanel;
    private JPanel tempPanel;
    private ArrayList<Observer> observers;

    public GameComments(GameBoard gameboard) {
        this.gameboard = gameboard;

        
    }

    public void messagePanel(){
        originalPanel = gameboard.getSouthpanel();
        tempPanel = new JPanel();

        // tempPanel.add(startButton);
		// tempPanel.add(quitButton);
		// tempPanel.add(scoreBoard);
		// tempPanel.add(enemyCount);
		

    }

    public void returnPane(){

    }

    public void sendMessage(){
        System.out.println("WOO");
        // notifyListeners();
    }

    @Override
    public void render(Graphics2D g2) {}
    
    @Override
    public void animate() {}

    @Override
    public void actionPerformed(Shooter shooter) {}

    @Override
    public void addListener(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeListener(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyListeners() {
        for (Observer o: observers) {
            // o.actionPerformed("Button:"+name + " has clicked.");
        }
    }
}