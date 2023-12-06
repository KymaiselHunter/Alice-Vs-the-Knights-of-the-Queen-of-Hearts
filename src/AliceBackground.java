import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class AliceBackground{
    //x and y coordinates
    private int xCoordinateGrass, xCoordinateForeground, xCoordinateMidground, xCoordinateBackground, xCoordinateStable;
    private int yCoordinate, xSize, ySize;

    //Declaring each image layer
        ImageIcon b0 = new ImageIcon(getClass().getResource("Background0.png"));
        Image i0 = b0.getImage();
        
        ImageIcon b1 = new ImageIcon(getClass().getResource("Background1.png"));
        Image i1 = b1.getImage();

        ImageIcon b2 = new ImageIcon(getClass().getResource("Background2.png"));
        Image i2 = b2.getImage();

        ImageIcon b3 = new ImageIcon(getClass().getResource("Background3.png"));
        Image i3 = b3.getImage();

        ImageIcon b4 = new ImageIcon(getClass().getResource("Background4.png"));
        Image i4 = b4.getImage();

        ImageIcon b5 = new ImageIcon(getClass().getResource("Background5.png"));
        Image i5 = b5.getImage();

        ImageIcon b6 = new ImageIcon(getClass().getResource("Background6.png"));
        Image i6 = b6.getImage();

        ImageIcon b7 = new ImageIcon(getClass().getResource("Background7.png"));
        Image i7 = b7.getImage();
 
        ImageIcon b8 = new ImageIcon(getClass().getResource("Background8.png"));
        Image i8 = b8.getImage();

        ImageIcon b9 = new ImageIcon(getClass().getResource("Background9.png"));
        Image i9 = b9.getImage();

        ImageIcon b10 = new ImageIcon(getClass().getResource("Background10.png"));
        Image i10 = b10.getImage();

        ImageIcon b11 = new ImageIcon(getClass().getResource("Background11.png"));
        Image i11 = b11.getImage();

    public AliceBackground(){

        xCoordinateGrass = 0;
        xCoordinateForeground = 0;
        xCoordinateMidground = 0;
        xCoordinateBackground = 0;
        xCoordinateStable = 0;

        yCoordinate = -1600;

        xSize = 4992;
        ySize = 2808;
    }

    public void animateLeft()
    {
        xCoordinateGrass -= 10;
        if(Math.abs(xCoordinateGrass) > xSize) xCoordinateGrass = 0;
        xCoordinateBackground -= 1;
        if(Math.abs(xCoordinateBackground) > xSize) xCoordinateBackground = 0;
        xCoordinateMidground -= 2;
        if(Math.abs(xCoordinateMidground) > xSize) xCoordinateMidground = 0;
        xCoordinateForeground -= 3;
        if(Math.abs(xCoordinateForeground) > xSize) xCoordinateForeground = 0;
    }

    public void animateRight()
    {
        xCoordinateGrass += 10;
        if(Math.abs(xCoordinateGrass) > xSize) xCoordinateGrass = 0;
        xCoordinateBackground += 1;
        if(Math.abs(xCoordinateBackground) > xSize) xCoordinateBackground = 0;
        xCoordinateMidground += 2;
        if(Math.abs(xCoordinateMidground) > xSize) xCoordinateMidground = 0;
        xCoordinateForeground += 3;
        if(Math.abs(xCoordinateForeground) > xSize) xCoordinateForeground = 0;
    }

    public void drawGrass(Graphics2D g){
        g.drawImage(i10, xCoordinateGrass - xSize, yCoordinate, xSize, ySize, null);
        g.drawImage(i10, xCoordinateGrass, yCoordinate, xSize, ySize, null);
        g.drawImage(i10, xCoordinateGrass + xSize, yCoordinate, xSize, ySize, null);
        g.drawImage(i11, xCoordinateGrass - xSize, yCoordinate, xSize, ySize, null);
        g.drawImage(i11, xCoordinateGrass, yCoordinate, xSize, ySize, null);
        g.drawImage(i11, xCoordinateGrass + xSize, yCoordinate, xSize, ySize, null);
    }
    public void drawBackground(Graphics2D g){

        //Drawing each layer
        
        g.drawImage(i0, xCoordinateStable, yCoordinate, xSize, ySize, null);
        g.drawImage(i1, xCoordinateStable, yCoordinate, xSize, ySize, null);
        g.drawImage(i2, xCoordinateStable, yCoordinate, xSize, ySize, null);

        g.drawImage(i3, xCoordinateBackground - xSize, yCoordinate, xSize, ySize, null);
        g.drawImage(i3, xCoordinateBackground, yCoordinate, xSize, ySize, null);
        g.drawImage(i3, xCoordinateBackground + xSize, yCoordinate, xSize, ySize, null);

        g.drawImage(i4, xCoordinateBackground - xSize, yCoordinate, xSize, ySize, null);
        g.drawImage(i4, xCoordinateBackground, yCoordinate, xSize, ySize, null);
        g.drawImage(i4, xCoordinateBackground + xSize, yCoordinate, xSize, ySize, null);

        g.drawImage(i5, xCoordinateMidground - xSize, yCoordinate, xSize, ySize, null);
        g.drawImage(i5, xCoordinateMidground, yCoordinate, xSize, ySize, null);
        g.drawImage(i5, xCoordinateMidground + xSize, yCoordinate, xSize, ySize, null);

        g.drawImage(i6, xCoordinateMidground - xSize, yCoordinate, xSize, ySize, null);
        g.drawImage(i6, xCoordinateMidground, yCoordinate, xSize, ySize, null);
        g.drawImage(i6, xCoordinateMidground + xSize, yCoordinate, xSize, ySize, null);

        g.drawImage(i7, xCoordinateMidground - xSize, yCoordinate, xSize, ySize, null);
        g.drawImage(i7, xCoordinateMidground, yCoordinate, xSize, ySize, null);
        g.drawImage(i7, xCoordinateMidground + xSize, yCoordinate, xSize, ySize, null);

        g.drawImage(i8, xCoordinateForeground - xSize, yCoordinate, xSize, ySize, null);
        g.drawImage(i8, xCoordinateForeground, yCoordinate, xSize, ySize, null);
        g.drawImage(i8, xCoordinateForeground + xSize, yCoordinate, xSize, ySize, null);

        g.drawImage(i9, xCoordinateForeground - xSize, yCoordinate, xSize, ySize, null);
        g.drawImage(i9, xCoordinateForeground, yCoordinate, xSize, ySize, null);
        g.drawImage(i9, xCoordinateForeground + xSize, yCoordinate, xSize, ySize, null);
    }
}