package AlienInvader.controller;

import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

import AlienInvader.view.AlienBoard;
import Memory.view.MemoryBoard;
import view.MainMenu;

public class MouseClickListener implements MouseInputListener {
    private JFrame window;
    private MemoryBoard memory;
    private AlienBoard alienBoard;

    public MouseClickListener(AlienBoard alienBoard) {
        this.alienBoard = alienBoard;
        this.window = alienBoard.getWindow();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(e.getX());
        System.out.println(e.getY());
        // begin playing alien invader
        if (e.getX() >= 177 && e.getX() <= 280) {
            if (e.getY() >= 370 && e.getY() <= 401) {
                alienBoard.startGame();
            }
        }

        // return to java connection menu
        if (e.getX() >= 398 && e.getX() <= 567) {
            if (e.getY() >= 366 && e.getY() <= 402) {
                alienBoard.getCanvas().getGameElements().clear();
                var mainMenu = new MainMenu(window);
                window.getContentPane().removeAll();
                mainMenu.enter();
                window.pack();
                window.revalidate();
            }
        }

        if (AlienBoard.playing == 1) {

            // restart alien invader game
            if (e.getX() >= 255 && e.getX() <= 604) {
                if (e.getY() >= 445 && e.getY() <= 457) {
                    alienBoard.startGame();
                }
            }
            // return to alien invader menu

            if (e.getX() >= 610 && e.getX() <= 676) {
                if (e.getY() >= 442 && e.getY() <= 457) {
                    var mainMenu = new MainMenu(window);
                    window.getContentPane().removeAll();
                    mainMenu.enter();
                    window.pack();
                    window.revalidate();
                }
            }
        }
        if (AlienBoard.playing == 0) {
            if (e.getX() >= 600 && e.getX() <= 689) {
                if (e.getY() >= 420 && e.getY() <= 456) {
                    var mainMenu = new MainMenu(window);
                    window.getContentPane().removeAll();
                    mainMenu.enter();
                    window.pack();
                    window.revalidate();
                }
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
