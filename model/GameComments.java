package model;

import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Berries.Observer;
import model.Berries.Subject;
import view.GameBoard;

public class GameComments extends GameElement {

    private GameBoard gameboard;
    private JPanel originalPanel;
    private JPanel tempPanel;

    public GameComments(GameBoard gameboard) {
        this.gameboard = gameboard;
    }

    public void showPane(){
        // originalPanel = gameboard.getSouthpanel();
    }

    public void returnPane(){
        
        
    }


    @Override
    public void render(Graphics2D g2) {}
    @Override
    public void animate() {}
}