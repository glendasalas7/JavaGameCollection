package model.ObserverPattern;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import view.InvasionMenu;

public class HealthNotifier implements Subject{

    private JPanel panel = new JPanel();
    private JPanel tempPanel = new JPanel();
    private final ArrayList<Observer> observers = new ArrayList<>();
    private Boolean active = false;

    public HealthNotifier(JPanel panel){
        this.panel = panel;
        tempPanel = panel;
    }



    public void healthUpdate(int size){
        if(size == 1){
            panel.setBackground(Color.RED);
        }
        else if(size == 2){
            panel.setBackground(Color.ORANGE);
        }
        else if(size == 3){
            panel.setBackground(Color.YELLOW);
        }else if(size == 4){
            panel.setBackground(Color.GREEN);
        }
        notifyListeners();
        active = true;
    }

    public void returnPane() {
		InvasionMenu.setSouthPanel(getTempPanel());
    }
    
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
            o.actionPerformed(panel);
        }
    }
    public void setOriginalPanel(JPanel originalPanel) {
        this.panel = originalPanel;
    }
    public JPanel getOriginalPanel() {
        return panel;
    }
    public void setTempPanel(JPanel tempPanel) {
        this.tempPanel = tempPanel;
    }
    public JPanel getTempPanel() {
        return tempPanel;
    }
    public Boolean getActive() {
        return active;
    }

	
}