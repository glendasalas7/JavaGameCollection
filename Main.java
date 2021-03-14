import java.awt.Dimension;
import javax.swing.JFrame;
import view.MainMenu;

public class Main{
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(500, 200);
        window.setPreferredSize(new Dimension(700, 500));
        window.setResizable(false);
        var mainMenu = new MainMenu(window);
        mainMenu.enter();
        window.pack();
        window.setVisible(true);
    }
}