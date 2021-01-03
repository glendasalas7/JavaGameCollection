import java.awt.Dimension;

import javax.swing.JFrame;
import view.CollectionMenu;
  
public class Main{
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Java Collection");
        window.setLocation(500, 200);
        window.setPreferredSize(new Dimension(600, 370));
        window.setResizable(false);
        var game = new CollectionMenu(window);
        game.stepIn();
        window.pack();
        window.setVisible(true);
    }
}