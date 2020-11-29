// package model.Berries;
// // participant: ConcreteSubject
// import java.util.ArrayList;
// import model.GameElement;
// import java.awt.image.BufferedImage;
// import java.awt.Graphics2D;
// import java.awt.Image;
// import java.io.File;
// import javax.imageio.ImageIO;
// import java.awt.Color;

// public class Berry extends GameElement implements Subject {

//     private ArrayList<Observer> observers;
//     private String name;
//     public static final int SIZE = 7;
// 	public static final int UNIT_MOVE = 5;

//     public Berry(int x, int y) {
//         super(x, y, Color.green, true, SIZE, SIZE);
//     }

//     public void click() {
//         notifyListeners();
//     }

//     @Override
//     public void addListener(Observer o) {
//         observers.add(o);
//     }

//     @Override
//     public void removeListener(Observer o) {
//         observers.remove(o);
//     }

//     @Override
//     public void notifyListeners() {
//         for (Observer o : observers) {
//             o.actionPerformed("Button:" + name + " has clicked.");
//         }
//     }

//     @Override
//     public void render(Graphics2D g2) {
//     //         try{
//     //             BufferedImage berryIMG = ImageIO.read(new File("model/pictures/images.png"));
//     //             Image temp = berryIMG.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
//     //             BufferedImage resizedImage = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
//     //             g2 = resizedImage.createGraphics();
//     //             g2.drawImage(temp, 0, 0, null);
//     //         } catch(Exception e){
//     //             System.out.println("Image file load error");
//     //         }
//     // }
//         g2.setColor(Color.MAGENTA);
//         g2.fillOval(x, y, 20, 20);
//         System.out.println(x);
//         System.out.println(y);
// }

//     @Override
//     public void animate() {
//         super.y += UNIT_MOVE;
//     }
    
// }
