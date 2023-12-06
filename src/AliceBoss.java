import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class AliceBoss {
    private int bossHealth;
    private int xCoordinate, yCoordinate;
    private int currentState; 
    private short animationCounter;

    private long lastFrameTime;
    private final int IDLE_FRAME_TIME = 200;
    private final int ATTACK_FRAME_TIME = 400;
    private final int DEAD_FRAME_TIME = 250;

    private boolean dead;
    private boolean fightMode;

    private long lastProjectileFire;
    private long lastIdle;
    private long lastRandomUpDown;

    private int vy;

    public AliceBoss(){
        bossHealth = 10;
        xCoordinate = 0;
        yCoordinate = 0;
        currentState = 0;
        animationCounter = 0;

        vy = 1;

        dead = false;
        fightMode = false;

        lastProjectileFire = -1;
        lastIdle = -1;
        lastRandomUpDown = -1;
    }

    public AliceBoss(int x, int y){
        bossHealth = 10;
        xCoordinate = x;
        yCoordinate = y;
        currentState = 0;
        animationCounter = 0;

        vy = 1;

        dead = false;
        fightMode = false;

        lastProjectileFire = -1;
        lastIdle = -1;
        lastRandomUpDown = -1;
    }

    public void drawSelf(Graphics2D g){
        ImageIcon boss;

        if(currentState == 0){ //if idle
            if(animationCounter == 0)
            {
                boss = new ImageIcon(AliceBoss.class.getResource("boss0.png"));
            } 
            else if(animationCounter == 1) 
            {
                boss = new ImageIcon(AliceBoss.class.getResource("boss1.png"));
            }
            else if(animationCounter == 2) 
            {
                boss = new ImageIcon(AliceBoss.class.getResource("boss2.png"));
            }
            else if(animationCounter == 3) 
            {
                boss = new ImageIcon(AliceBoss.class.getResource("boss3.png"));
            }
            else if(animationCounter == 4) 
            {
                boss = new ImageIcon(AliceBoss.class.getResource("boss4.png"));
            }
            else
            {
                boss = new ImageIcon(AliceBoss.class.getResource("boss5.png"));
            }
        }
        else if(currentState == 1){ //if attacking
            if(animationCounter == 0)
            {
                boss = new ImageIcon(AliceBoss.class.getResource("boss6.png"));
            } 
            else if(animationCounter == 1) 
            {
                boss = new ImageIcon(AliceBoss.class.getResource("boss7.png"));
            }
            else
            {
                boss = new ImageIcon(AliceBoss.class.getResource("boss8.png"));
            }
        }
        else{ //if dead
            boss = new ImageIcon(AliceBoss.class.getResource("boss7.png"));
        }
        Image i = boss.getImage();
        g.drawImage(i, xCoordinate, yCoordinate, i.getWidth(null), i.getHeight(null), null);
    }

    public void animateSelf(){
        if(currentState == 0) //idle
        {
            //if time passed for the next frame to occur
            if(IDLE_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
            {
                //next frame stuff so update frame time
                lastFrameTime = System.currentTimeMillis();

                //next frame itself goes up
                animationCounter++;
                
                //if all frames are done, reset cycle 
                if(animationCounter >= 5)
                {
                    animationCounter = 0;
                }

                if(Math.random() < 0.3)
                {
                    animationCounter = 0;
                    lastIdle = -1;
                    shootProjectile();
                }
            }
        }
        else if(currentState == 1) //attack
        {
            if(ATTACK_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
            {
                lastFrameTime = System.currentTimeMillis();
                animationCounter++;
                if(animationCounter >= 3)
                    animationCounter = 0;
            }
        }
        else{ //dead
            yCoordinate-=2;
        }
    }

    public void bossMovement()
    {
        //y = 300
        //y = 600
        if(fightMode)
        {
            //loop so after going through all idle stages, it shoots
            if(currentState == 1)//shoot
            {
                if(System.currentTimeMillis() - lastProjectileFire >= ATTACK_FRAME_TIME*3)
                {
                    lastProjectileFire = -1;
                    this.becomeIdle();
                    System.out.println("test");
                }
            }
            else if(currentState == 0)
            {
                //System.out.println(System.currentTimeMillis() - lastProjectileFire + " "+ATTACK_FRAME_TIME*3);
                if(System.currentTimeMillis() - lastIdle >= IDLE_FRAME_TIME * 10)
                {
                    lastIdle = -1;
                    this.shootProjectile();
                    System.out.println("test2");
                }
            }

            //loop so it goes upside down
            yCoordinate +=vy;

            if(yCoordinate < 300 || yCoordinate > 600)
            {
                vy*=-1;
            }
            else if(lastRandomUpDown - System.currentTimeMillis() >= 1000)
            {
                lastRandomUpDown = System.currentTimeMillis();
                
                if(Math.random() < 0.1)
                    vy *= -1;
            }
            
        }
    }

    //move with background
    public void passiveMove(AlicePlayer pPlayer)
    {
        if(!pPlayer.getFightMode() && pPlayer.getOrientationRight() && pPlayer.getCurrentState() >= 1)
        {
            xCoordinate -= 10;
        }
        else if(!pPlayer.getFightMode() && !pPlayer.getOrientationRight() && pPlayer.getCurrentState() >= 1)
        {   
            xCoordinate += 10;
        }
    }

    public int getXCoordinate()
    {
        return xCoordinate;
    }

    public boolean getDead()
    {
        return dead;
    }

    public void beginBattle()
    {
        if(!fightMode)
        {
            fightMode = true;
            //start with an attack
            shootProjectile();
        }
    }

    public boolean getFightMode()
    {
        return fightMode;
    }

    public void shootProjectile()
    {
        currentState = 1;
        lastProjectileFire = System.currentTimeMillis();
    }

    public void becomeIdle()
    {
        currentState = 0;
        lastIdle = System.currentTimeMillis();
    }
    /* 
    public void attack(){
        currentState = 1;
        new AliceProjectile();
    }

    public void act(){
        int r = (int)Math.random()*5 + 1;
        
        yCoordinate -= r; 
        if(yCoordinate > 2000 || yCoordinate < 100)
        {
            r*=-1;
        }

        if(r == 2) attack();
        else currentState = 0;
        
    }*/ 
}