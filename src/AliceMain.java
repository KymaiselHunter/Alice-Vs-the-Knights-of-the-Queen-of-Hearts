//import base shit
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Font;

//import 2d graphics (sprites)
import java.awt.Graphics2D;
import java.awt.Image;

import javax.lang.model.type.ArrayType;
import javax.print.attribute.standard.PDLOverrideSupported;
import javax.swing.ImageIcon;


public class AliceMain extends JComponent implements KeyListener, MouseListener, MouseMotionListener
{
    //instance variables
    private int WIDTH;
    private int HEIGHT;

    //main character instantiate
    private AlicePlayer alice;

    //background instantiate
    private AliceBackground background;

    //booleans in case both left and right is pushed and so u can hold down a direction after jump and slide
    private boolean aPressed,dPressed;

    //make gui
    private AliceGui aliceGUI = new AliceGui();

    //declare knight
    //AliceKnight knightOne;

    //declare boss
    AliceBoss boss;
    
    //knight array
    AliceKnight[] knightsOfHearts = new AliceKnight[15];

    //Default Constructor
    public AliceMain()
    {
        //initializing instance variables
        WIDTH = 1920;
        HEIGHT = 1080;
        
        //Setting up the GUI
        JFrame gui = new JFrame(); //This makes the gui box
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Makes sure program can close
        gui.setTitle("HopperHacks 2023 : Alice Vs the Knights of the Queen of Hearts || A - Go Left; D - Go Right; Space - Jump; Ctrl - Slide; LeftClick - Attack"); //This is the title of the game, you can change it
        gui.setPreferredSize(new Dimension(WIDTH + 5, HEIGHT + 30)); //Setting the size for gui
        gui.setResizable(false); //Makes it so the gui cant be resized
        gui.getContentPane().add(this); //Adding this class to the gui
        /*If after you finish everything, you can declare your buttons or other things
        *at this spot. AFTER gui.getContentPane().add(this) and BEFORE gui.pack();
        */
        gui.pack(); //Packs everything together
        gui.setLocationRelativeTo(null); //Makes so the gui opens in the center of screen
        gui.setVisible(true); //Makes the gui visible
        gui.addKeyListener(this);//stating that this object will listen to the keyboard
        gui.addMouseListener(this); //stating that this object will listen to the Mouse
        gui.addMouseMotionListener(this); //stating that this object will acknowledge when the Mouse move

        //player declaration
        alice = new AlicePlayer();

        //background declare
        background = new AliceBackground();

        //declare button booleans
        aPressed = false;
        dPressed = false;

        //assign knights
        knightsOfHearts[0] = new AliceKnight(3000, 760);

        knightsOfHearts[1] = new AliceKnight(5000, 760);
        knightsOfHearts[2] = new AliceKnight(5500, 760);
        
        knightsOfHearts[3] = new AliceKnight(9300, 760);
        knightsOfHearts[4] = new AliceKnight(9500, 760);
        knightsOfHearts[5] = new AliceKnight(9700, 760);

        knightsOfHearts[6] = new AliceKnight(12300, 760);
        knightsOfHearts[7] = new AliceKnight(12500, 760);
        knightsOfHearts[8] = new AliceKnight(12700, 760);
        knightsOfHearts[9] = new AliceKnight(13000, 760);

        knightsOfHearts[10] = new AliceKnight(16000, 760);
        knightsOfHearts[11] = new AliceKnight(16500, 760);
        knightsOfHearts[12] = new AliceKnight(17500, 760);
        knightsOfHearts[13] = new AliceKnight(17300, 760);
        knightsOfHearts[14] = new AliceKnight(16300, 760);
        

        //assign boss
        boss = new AliceBoss(20000,600);
    }
      
    //All your UI drawing goes in here
    public void paintComponent(Graphics g)
    {
        //declaring 2d Graphics
        Graphics2D g2d = (Graphics2D)g;
        
        //background graphics(2d)
        background.drawBackground(g2d);

        //boss draw
        boss.drawSelf(g2d);

        //knight draw
        for(int i = 0; i < knightsOfHearts.length; i++)  
        {
            knightsOfHearts[i].drawSelf(g2d);
        }
        
        
        //gui draw hearts
        aliceGUI.drawHeartArray(g2d);

        //player graphics(2d required)
        alice.drawSelf(g2d);

        //draw grass
        background.drawGrass(g2d);
    }
    
    public void loop()
    {
        //making the autonomous circle move
        //handling when the circle collides with the edges
        //handling the collision of the circle with the rectangle
        aliceGUI.updateHealth(alice);

        //System.out.println(knightsOfHearts[0].getFightingKnights() + " " + knightsOfHearts[0].getKnightsKilled() +" "+ knightsOfHearts[0].getDead());
       // System.out.println(boss.getFightMode());

        if(aPressed && dPressed)
        {
            alice.setIdle();
        }
        else
        {
            if(aPressed)
            {
                alice.setRunLeft();
            }
            if(dPressed)
            {
                alice.setRunRight();
            }
            
        }

        //alice.setFightMode(true);

        alice.move();
        alice.animateSelf();
        
        if(!alice.getFightMode())
        {
            if((alice.getCurrentState() >= 1 && alice.getCurrentState() <= 5) && alice.getOrientationRight()) 
            {
                background.animateLeft();
            }
            else if((alice.getCurrentState() >= 1 && alice.getCurrentState() <= 5) && !alice.getOrientationRight()) 
            {
                background.animateRight();
            }
            //knight movement offscreen and out of conflict 
            for(int i = 0; i < knightsOfHearts.length; i++)  
            {
                knightsOfHearts[i].passiveMove(alice);
            }
            boss.passiveMove(alice);

           alice.recenterPlayer(background);
        }
        else
        {
            alice.fightMovement(aPressed, dPressed);
            for(int i = 0; i < knightsOfHearts.length; i++)  
            {
                if(knightsOfHearts[i].getFightMode())
                {
                    alice.playBattle(knightsOfHearts[i]);
                }
            }
            if(boss.getFightMode())
            {
                boss.bossMovement();
            }
        }

        //knight movement
        for(int i = 0; i < knightsOfHearts.length; i++)  
        {
            knightsOfHearts[i].animateSelf();
            alice.checkKnightBattle(knightsOfHearts[i]);
        }
        
        alice.checkBossBattle(boss);
        

        //boss movement
        boss.animateSelf();

        //alice.checkBossBattle(boss);


        //Do not write below this
        repaint();
    }
    
    //This method will acknowledge user input
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == 32) //jump
        {
            alice.startJump();
        }
        else if(key == 65) //left-a
        {
            aPressed = true;
        }
        else if(key == 68) //right-d
        {
            dPressed = true;
        }
        else if(key == 17)//ctrl
        {
            alice.startSlide();
        }
    }

    //These methods are required by the compiler.
    //You might write code in these methods depending on your goal.
    public void keyTyped(KeyEvent e)
    {

    }
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == 32) //jump
        {
            
        }
        else if(key == 65) //left-a
        {
            aPressed = false;
            alice.setIdle();
        }
        else if(key == 68) //right-d
        {
            dPressed = false;
            alice.setIdle();
        }
    }
    public void mousePressed(MouseEvent e)
    {
        //alice.attack();
    }
    public void mouseReleased(MouseEvent e)
    {

    }
    public void mouseClicked(MouseEvent e)
    {
        alice.attack();
    }
    public void mouseEntered(MouseEvent e)
    {

    }
    public void mouseExited(MouseEvent e)
    {

    }
    public void mouseMoved(MouseEvent e)
    {
        
    }
    public void mouseDragged(MouseEvent e)
    {

    }
    
    public void start(final int ticks){
        Thread gameThread = new Thread(){
            public void run(){
                while(true){
                    loop();
                try{
                    Thread.sleep(1000 / ticks);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
             }
        }
    };
    gameThread.start();
    }
    
    public static void main(String[] args)
    {
        AliceMain g = new AliceMain();
        
        g.start(60);
    }
}
