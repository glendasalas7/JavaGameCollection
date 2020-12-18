package model.StrategyPattern;
import java.awt.Graphics2D;
public interface Animation {

   public void render(Graphics2D g2);
   public void setX(int x);
   public void setY(int y);
}
