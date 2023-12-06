//imports for drawing Images
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class AliceKnight{
    //0-death, 1-idle, 2-slash, 3-walk 4-hurt
    private int currentState;
    
    boolean fightMode;

    //health
    private int enemyHealth;
    
    //location
    private int xCoordinate, yCoordinate;

    //facing right or not?
    private boolean orientationRight;

    private int animationCounter;

    private long lastFrameTime;

    private long slashTime;

    private long hurtTime;

    private boolean dead;
    private boolean corpse;

    private static int knightsKilled;
    private static int fightingKnights;

    private String[][] rightKnightFrames = {
        {"knight death 1.png", "knight death 2.png", "knight death 3.png","knight death 4.png","knight death 5.png","knight death 6.png","knight death 7.png","knight death 8.png", "knight death 9.png"},
        {"knight idle 1.png", "knight idle 2.png", "knight idle 3.png","knight idle 4.png"},
        {"knight slash 1.png", "knight slash 2.png", "knight slash 3.png","knight slash 4.png","knight slash 5.png","knight slash 6.png","knight slash 7.png", "knight slash 8.png", "knight slash 9.png","knight slash 10.png"},
        {"knight walk 1.png", "knight walk 2.png", "knight walk 3.png","knight walk 4.png","knight walk 5.png","knight walk 6.png","knight walk 7.png","knight walk 8.png"},
        {"knight death 2.png"}
    };

    private String[][] leftKnightFrames = {
        {"knight death 1 left.png", "knight death 2 left.png", "knight death 3 left.png","knight death 4 left.png","knight death 5 left.png","knight death 6 left.png","knight death 7 left.png","knight death 8 left.png", "knight death 9 left.png"},
        {"knight idle 1 left.png", "knight idle 2 left.png", "knight idle 3 left.png","knight idle 4 left.png"},
        {"knight slash 1 left.png", "knight slash 2 left.png", "knight slash 3 left.png","knight slash 4 left.png","knight slash 5 left.png","knight slash 6 left.png","knight slash 7 left.png", "knight slash 8 left.png", "knight slash 9 left.png","knight slash 10 left.png"},
        {"knight walk 1 left.png", "knight walk 2 left.png", "knight walk 3 left.png","knight walk 4 left.png","knight walk 5 left.png","knight walk 6 left.png","knight walk 7 left.png","knight walk 8 left.png"},
        {"knight death 2 left.png"}
    };

    private final int IDLE_FRAME_TIME = 250;
    private final int WALK_FRAME_TIME = 170;
    private final int SLASH_FRAME_TIME = 150;
    private final int DEAD_FRAME_TIME = 125;
    private final int HURT_FRAME_TIME = 200;

    public AliceKnight(){
        xCoordinate = 1600;
        yCoordinate = 760;
        animationCounter = 0;
        enemyHealth = 5;
        lastFrameTime = -1;

        fightMode = false;

        currentState = 1;

        slashTime = -1;

        hurtTime = -1;

        dead = false;
        corpse = false;

        fightingKnights = 0;
    }

    public AliceKnight(int x, int y){
        xCoordinate = x;
        yCoordinate = y;
        animationCounter = 0;
        enemyHealth = 5;
        lastFrameTime = -1;

        fightMode = false;

        currentState = 1;

        slashTime = -1;

        hurtTime = -1;

        dead = false;
        corpse = false;
        
        fightingKnights = 0;
    }

    public String toString()
    {
        return "" + enemyHealth;
    }

    public int getXCoordinate(){
        return xCoordinate;
    }

    public int getYCoordinate(){
        return yCoordinate;
    }

    public int getEnemyHealth(){
        return enemyHealth;
    }

    public void setXCoordinate(int newX){
        xCoordinate = newX;
    }

    public void setYCoordinate(int newY){
        yCoordinate = newY;
    }

    public void setEnemyHealth(int newHealth){
        enemyHealth = newHealth;
    }
    
    public void drawSelf(Graphics2D g)
    {
        ImageIcon knight;

        if(orientationRight) 
        {
            knight = new ImageIcon(AlicePlayer.class.getResource(rightKnightFrames[currentState][animationCounter]));
        }
        else
        {
            knight = new ImageIcon(AliceHeart.class.getResource(leftKnightFrames[currentState][animationCounter]));
        }

        Image i = knight.getImage();
        
        g.drawImage(i, xCoordinate, yCoordinate, 150, 230, null);
    }

    public void animateSelf()
    {
        //if to update if KNIGHT is facing right or not
        if(orientationRight)
        {
            //if to check KNIGHT current Action
            if(currentState == 0) //death
            {
                //if time passed for the next frame to occur
                if(DEAD_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime && corpse == false)
                {
                    //next frame stuff so update frame time
                    lastFrameTime = System.currentTimeMillis();

                    //next frame itself goes up
                    animationCounter++;

                    //if all frames are done, reset cycle 
                    if(animationCounter >= 9)
                    {
                        animationCounter = 8;
                        corpse = true;
                    }
                }
            }
            else if(currentState == 1) //idle
            {
                if(IDLE_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 4)
                        animationCounter = 0;
                }
            }
            else if(currentState == 2) //slash
            {
                if(SLASH_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 10)
                        animationCounter = 0;
                }
            }
            else if(currentState == 3) //walk
            {
                if(WALK_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 8)
                        animationCounter = 0;
                }
            }
            else if(currentState == 4)//hurt
            {
                if(HURT_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 1)
                        animationCounter = 0;
                }
            }
        }
        else
        {
            //if to check KNIGHT current Action
            if(currentState == 0) //death
            {
                //if time passed for the next frame to occur
                if(DEAD_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    //next frame stuff so update frame time
                    lastFrameTime = System.currentTimeMillis();

                    //next frame itself goes up
                    animationCounter++;

                    //if all frames are done, reset cycle 
                    if(animationCounter >= 9)
                    {
                        animationCounter = 7;
                    }
                }
            }
            else if(currentState == 1) //idle
            {
                if(IDLE_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 4)
                        animationCounter = 0;
                }
            }
            else if(currentState == 2) //slash
            {
                if(SLASH_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 10)
                        animationCounter = 0;
                }
            }
            else if(currentState == 3) //walk
            {
                if(WALK_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 8)
                        animationCounter = 0;
                }
            }
            else if(currentState == 4)//hurt
            {
                if(HURT_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 1)
                        animationCounter = 0;
                }
            }
        }
    }


    public void attack(AlicePlayer a){
        if(!dead)
        {
            if(a.getXCoordinate() < xCoordinate && orientationRight)
            {
                setWalkLeft();
            }
            else if(a.getXCoordinate() > xCoordinate && !orientationRight)
            {
                setWalkRight();
            }
            else if((xCoordinate - a.getXCoordinate() >= 160 && xCoordinate - a.getXCoordinate() >= 0) || (a.getXCoordinate() - xCoordinate >= 55 && a.getXCoordinate() - xCoordinate >= 0))
            {
                if(xCoordinate > a.getXCoordinate())
                {
                setWalkLeft();
                }
                else
                {
                    setWalkRight();
                }
            }
            else
            {
                startSlash();
            }
        }
        
    }

    public void fightMovement(AlicePlayer pPlayer)
    {
        if(currentState == 3)
        {
            //walk
            if(orientationRight)
            {
                xCoordinate += 5;
            }
            else
            {
                xCoordinate -= 5;
            }
        }
        else if(currentState == 2)
        {
            //Slash
            long timer = System.currentTimeMillis() - slashTime;
            if(timer >= SLASH_FRAME_TIME * 9)
            {
                currentState = 1;
                slashTime = -1;
            }
            else if((timer >= SLASH_FRAME_TIME * 6) && (timer <= SLASH_FRAME_TIME * 7))
            {
                if(orientationRight && pPlayer.getXCoordinate() - xCoordinate <= 55 && pPlayer.getXCoordinate() - xCoordinate > -105)
                {
                    pPlayer.takeDamage();
                }
                else if(!orientationRight && xCoordinate - pPlayer.getXCoordinate() <= 160 && xCoordinate - pPlayer.getXCoordinate() >= 0)
                {
                    pPlayer.takeDamage();
                }
            }
        }
        else if(currentState == 4)
        {
            //Slash
            if(System.currentTimeMillis() - hurtTime >= HURT_FRAME_TIME)
            {
                currentState = 1;
                hurtTime = -1;
            }
            
            int vx = -1;
            if((xCoordinate <= -65 && orientationRight) || (xCoordinate >= 1740 && !orientationRight))
                vx = 0;
            else
                vx = 15;

            if(orientationRight)
            {
                xCoordinate -= vx;
            }
            else
            {
                xCoordinate += vx;
            }
        }
    }

    private void setDead()
    {
        if(!dead)
        {
            currentState = 0;
            fightMode = false;
            knightsKilled++;
            dead = true;
        }
    }

    public boolean getDead()
    {
        return dead;
    }
    
    public int getFightingKnights()
    {
        return fightingKnights;
    }

    private void takeDamage()
    {
        if(currentState != 4 && !dead)
        {
            currentState = 4;
            hurtTime = System.currentTimeMillis();
            enemyHealth--;
            if(enemyHealth <= 0)
            {
                setDead();
            }
        }
    }

    public void checkHurt(AlicePlayer pPlayer)
    {
        if(pPlayer.getCurrentState() == 6 && pPlayer.getHitWindowOpen())
        {
            
            if(pPlayer.getOrientationRight() && xCoordinate - pPlayer.getXCoordinate() <= 160 && xCoordinate - pPlayer.getXCoordinate()  > 0)
            {
                this.takeDamage();
            }
            else if(!pPlayer.getOrientationRight() && pPlayer.getXCoordinate() - xCoordinate <= 160 && pPlayer.getXCoordinate() - xCoordinate >= 0)
            {
                this.takeDamage();
            }
        }
    }

    private void startSlash()
    {
        if(currentState == 1 || currentState == 3)
        {
            currentState = 2;
            slashTime = System.currentTimeMillis();
        }
        
    }

    private void setWalkRight()
    {
        if(currentState == 1 || currentState == 3)
        {
            orientationRight = true;
            currentState = 3;
        }
    }

    private void setWalkLeft()
    {
        if(currentState == 1 || currentState == 3)
        {
            orientationRight = false;
            currentState = 3;
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

    public void beginBattle()
    {
        if(!fightMode)
        {
            fightMode = true;
            fightingKnights++;
        }
    }

    public void defeated(){
        currentState = 0;
        knightsKilled++;
    }

    public int getKnightsKilled(){
        return knightsKilled;
    }

    public boolean getFightMode()
    {
        return fightMode;
    }
    

    
    
}
