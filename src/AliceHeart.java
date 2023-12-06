//imports for drawing Images(used for drawing Alice)
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class AliceHeart {
    private boolean heartFull;
    private int xCoordinate;
    private int yCoordinate;
    

    public AliceHeart()
    {
        heartFull = true;
    }

    public AliceHeart(int x, int y, boolean full)
    {
        xCoordinate = x;
        yCoordinate = y;
        heartFull = full;
    }

    public void setHeartFull()
    {
        heartFull = true;
    }

    public void setHeartEmpty()
    {
        heartFull = false;
    }

    public void setXCoordinate(int x)
    {
        xCoordinate = x;
    }

    public void setYCoordinate(int y)
    {
        yCoordinate = y;
    }

    public void drawSelf(Graphics2D g)
    {
        ImageIcon heart;

        if(heartFull) 
        {
            heart = new ImageIcon(AliceHeart.class.getResource("full_heart.png"));
        }
        else
        {
            heart = new ImageIcon(AliceHeart.class.getResource("empty_heart.png"));
        }

        Image i = heart.getImage();
        
        g.drawImage(i, xCoordinate, yCoordinate, 75, 70, null);
    }
}
