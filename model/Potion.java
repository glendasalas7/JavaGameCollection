package model;

// participant: ConcreteSubject
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import model.Berries.Observer;
import model.Berries.Subject;

import java.awt.Color;

public class Potion extends GameElement implements Subject{
    public static final int SIZE = 7;
    public static final int UNIT_MOVE = 5;
    private ArrayList<Observer> observers = new ArrayList<>();
    
    public Potion(int x, int y) {
        super(x, y, Color.green, true, SIZE, SIZE);
    }


    @Override
    public void render(Graphics2D g2) {
            try{
                BufferedImage berryIMG = ImageIO.read(new File("pictures/potion.png"));
                Image temp = berryIMG.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

                g2.drawImage(temp, x, y, null);
            } catch(Exception e){
                System.out.println("Image file load error");
            } 
}


    @Override
    public void animate() {
        super.y += UNIT_MOVE;
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
    public void notifyListeners(String string) {
    }
    
}
