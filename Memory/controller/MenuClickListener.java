package Memory.controller;

import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;
import Memory.view.MemoryBoard;
import view.MainMenu;

public class MenuClickListener implements MouseInputListener {
    private JFrame window;
    private MemoryBoard memory;

    public MenuClickListener(JFrame window) {
        this.window = window;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // play button
        if (e.getX() >= 298 && e.getX() <= 408) {
            if (e.getY() >= 385 && e.getY() <= 422) {
                memory = new MemoryBoard(window);
                window.getContentPane().removeAll();// clear window so you can load new window
                memory.stepIn();
                window.pack();
                window.revalidate();
            }
        }
        // stats button
        // if (e.getX() >= 398 && e.getX() <= 604) {
        // if (e.getY() >= 279 && e.getY() <= 332) {

        // }
        // }
        // return button
        if (e.getX() >= 52 && e.getX() <= 216) {
            if (e.getY() >= 385 && e.getY() <= 422) {
                var mainMenu = new MainMenu(window);
                window.getContentPane().removeAll();
                mainMenu.enter();
                window.pack();
                window.revalidate();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
