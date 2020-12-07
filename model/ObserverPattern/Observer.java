package model.ObserverPattern;


import javax.swing.JPanel;

// defines an updating interface for objects that should be
// be notified of changes in a subject

public interface Observer {

    void actionPerformed(JPanel jpanel);
}
