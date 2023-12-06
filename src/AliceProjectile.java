import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class AliceProjectile {
    
    private int projectileHealth;
    private int xCoordinate, yCoordinate;
    
    public AliceProjectile(){
        projectileHealth = 1;
        xCoordinate = 0;
        yCoordinate = 0;
    }

    public AliceProjectile(int x, int y)
    {
        projectileHealth = 1;
        xCoordinate = x;
        yCoordinate = y;
    }

    public int getXCoordinate(){
        return xCoordinate;
    }

    public int getYCoordinate(){
        return yCoordinate;
    }

    public void setXCoordinate(int newX){
        xCoordinate = newX;
    }

    public void setYCoordinate(int newY){
        yCoordinate = newY;
    }

    public void drawSelf(Graphics2D g){
        ImageIcon h = new ImageIcon(AliceProjectile.class.getResource("hairball.png"));
        Image p = h.getImage();
        g.drawImage(p, xCoordinate, yCoordinate, 20, 20, null);
    }



    
}
