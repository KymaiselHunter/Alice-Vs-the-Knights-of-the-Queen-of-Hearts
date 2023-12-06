//imports for drawing Images(used for drawing Alice)
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class AlicePlayer 
{
    //x and y coordinates
    private int xCoordinate, yCoordinate, vx;
    
    //facing right or not?
    private boolean orientationRight;

    //0-idle, 1-run, 2-jump, 3-up to fall, 4-fall, 5-slide, 6-attack, 7-hurt, 8-dead
    private int currentState;
    //variable to keep count of animation frame
    private short animationCounter;
    //variable to keep count of last animation update
    private long lastFrameTime;
    //variable to keep count of jumpStage for time
    private long jumpStageTime;
    //variable to keep count of slide
    private long slideStageTime;
    private long lastSlideTime;
    private long attackTime;
    private long hurtTime;

    //Final variables for time between each frame
    private final int IDLE_FRAME_TIME = 250;
    private final int RUN_FRAME_TIME = 100;
    private final int JUMP_FRAME_TIME = 100;
    private final int UP_TO_FALL_FRAME_TIME = 25;
    private final int FALL_FRAME_TIME = 100;
    private final int SLIDE_FRAME_TIME = 150;
    private final int ATTACK_FRAME_TIME = 50;
    private final int HURT_FRAME_TIME = 250;
    private final int DEAD_FRAME_TIME = 250;

    //slide cooldown time
    private final int SLIDE_COOLDOWN_TIME = 1750;

    //fighting enemy or not
    private boolean fightMode;
    
    //health to keep track of how close one is to death state
    private int playerHealth;
    private boolean heart;

    //used to compare time of hit to enemy
    private boolean hitWindowOpen;

    /*
         ______________
        |             |
        |             | <-- Cursor Parking Lot ($5/hr)
        |_____________|
     */ 
    //construct the player
    public AlicePlayer()
    {
        orientationRight = true;
        currentState = 0;
        animationCounter = 0;

        xCoordinate = 300;
        yCoordinate = 745;

        vx = 12;

        lastFrameTime = -1;

        jumpStageTime = -1;
        slideStageTime = -1;

        lastSlideTime = -1;

        fightMode = false;

        playerHealth = 3;

        heart = true;

        hitWindowOpen = false;
    }

    public String toString()
    {
        String str = "";
        //str += currentState;
        str += xCoordinate;
        return str;
    }

    /*
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
     */
    public void drawSelf(Graphics2D g) 
    {
        ImageIcon warrior;
        if(orientationRight)
        {
            if(currentState == 0){ //if idle
                if(animationCounter == 0)
                {
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Idle_1.png"));
                } 
                else if(animationCounter == 1) 
                {
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Idle_2.png"));
                }
                else if(animationCounter == 2) 
                {
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Idle_3.png"));
                }
                else if(animationCounter == 3) 
                {
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Idle_4.png"));
                }
                else if(animationCounter == 4) 
                {
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Idle_5.png"));
                }
                else //5
                {
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Idle_6.png"));
                }
            }    
            else if(currentState == 1) //run alice run
            {
                if(animationCounter == 0)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Run_1.png"));
                else if(animationCounter == 1)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Run_2.png"));
                else if(animationCounter == 2) 
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Run_3.png"));
                else if(animationCounter == 3) 
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Run_4.png"));
                else if(animationCounter == 4) 
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Run_5.png"));
                else if(animationCounter == 5) 
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Run_6.png"));
                else if(animationCounter == 6) 
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Run_7.png"));
                else //7
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Run_8.png"));
            }
            else if(currentState == 2) //jump
            {
                if(animationCounter == 0)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Jump_1.png"));
                else if(animationCounter == 1)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Jump_2.png"));
                else //2
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Jump_3.png"));
            }
            else if(currentState == 3) //up to fall
            {
                if(animationCounter == 0)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_UptoFall_1.png"));
                else
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_UptoFall_2.png"));
            }
            else if(currentState == 4) //fall
            {
                if(animationCounter == 0)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Fall_1.png"));
                else if(animationCounter == 1)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Fall_2.png"));
                else
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Fall_3.png"));
            }
            else if(currentState == 5) //slide
            {
                if(animationCounter == 0)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior-Slide_1.png"));
                else if(animationCounter == 1)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior-Slide_2.png"));
                else if(animationCounter == 2)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior-Slide_3.png"));
                else if(animationCounter == 3)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior-Slide_4.png"));
                else
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior-Slide_5.png"));
            }
            else if(currentState == 6) //attack
            {
                if(animationCounter == 0)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_1.png"));
                else if(animationCounter == 1)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_2.png"));
                else if(animationCounter == 2)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_3.png"));
                else if(animationCounter == 3)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_4.png"));
                else if(animationCounter == 4)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_5.png"));
                else if(animationCounter == 5)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_6.png"));
                else if(animationCounter == 6)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_7.png"));
                else if(animationCounter == 7)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_8.png"));
                else if(animationCounter == 8)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_9.png"));
                else if(animationCounter == 9)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_10.png"));
                else if(animationCounter == 10)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_11.png"));
                else
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_12.png"));
            }
            else if(currentState == 7) //hurt
            {
                if(animationCounter == 0)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_hurt_1.png"));
                else if(animationCounter == 1)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_hurt_2.png"));
                else if(animationCounter == 2)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_hurt_3.png"));
                else
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_hurt_4.png"));
            }
            else if(currentState == 8) //dead
            {
                if(animationCounter == 0)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Death_1.png"));
                else if(animationCounter == 1)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Death_2.png"));
                else if(animationCounter == 2)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Death_3.png"));
                else if(animationCounter == 3)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Death_4.png"));
                else if(animationCounter == 4)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Death_5.png"));
                else if(animationCounter == 5)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Death_6.png"));
                else if(animationCounter == 6)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Death_7.png"));
                else if(animationCounter == 7)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Death_8.png"));
                else if(animationCounter == 8)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Death_9.png"));
                else if(animationCounter == 9)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Death_10.png"));
                else 
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Death_11.png"));

            }
            else//in case not run(CHANGE LATER)
            {
                warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Idle_1.png"));
            }
        }
        else//left side
        {
            if(currentState == 0)//if idle
                if(animationCounter == 0)
                {
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Idle_1 left.png"));
                } 
                else if(animationCounter == 1) 
                {
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Idle_2 left.png"));
                }
                else if(animationCounter == 2) 
                {
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Idle_3 left.png"));
                }
                else if(animationCounter == 3) 
                {
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Idle_4 left.png"));
                }
                else if(animationCounter == 4) 
                {
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Idle_5 left.png"));
                }
                else //5
                {
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Idle_6 left.png"));
                }
            else if(currentState == 1) //run alice run
            {
                if(animationCounter == 0)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Run_1 left.png"));
                else if(animationCounter == 1)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Run_2 left.png"));
                else if(animationCounter == 2) 
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Run_3 left.png"));
                else if(animationCounter == 3) 
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Run_4 left.png"));
                else if(animationCounter == 4) 
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Run_5 left.png"));
                else if(animationCounter == 5) 
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Run_6 left.png"));
                else if(animationCounter == 6) 
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Run_7 left.png"));
                else //7
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Run_8 left.png"));
            }
            else if(currentState == 2) //jump
            {
                if(animationCounter == 0)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Jump_1 left.png"));
                else if(animationCounter == 1)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Jump_2 left.png"));
                else //2
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Jump_3 left.png"));
            }
            else if(currentState == 3) //up to fall
            {
                if(animationCounter == 0)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_UptoFall_1 left.png"));
                else
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_UptoFall_2 left.png"));
            }
            else if(currentState == 4) //fall
            {
                if(animationCounter == 0)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Fall_1 left.png"));
                else if(animationCounter == 1)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Fall_2 left.png"));
                else
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Fall_3 left.png"));
            }
            else if(currentState == 5) //slide
            {
                if(animationCounter == 0)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior-Slide_1 left.png"));
                else if(animationCounter == 1)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior-Slide_2 left.png"));
                else if(animationCounter == 2)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior-Slide_3 left.png"));
                else if(animationCounter == 3)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior-Slide_4 left.png"));
                else
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior-Slide_5 left.png"));
            }
            else if(currentState == 6) //attack
            {
                if(animationCounter == 0)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_1 left.png"));
                else if(animationCounter == 1)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_2 left.png"));
                else if(animationCounter == 2)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_3 left.png"));
                else if(animationCounter == 3)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_4 left.png"));
                else if(animationCounter == 4)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_5 left.png"));
                else if(animationCounter == 5)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_6 left.png"));
                else if(animationCounter == 6)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_7 left.png"));
                else if(animationCounter == 7)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_8 left.png"));
                else if(animationCounter == 8)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_9 left.png"));
                else if(animationCounter == 9)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_10 left.png"));
                else if(animationCounter == 10)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_11 left.png"));
                else
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Attack_12 left.png"));
            }
            else if(currentState == 7) //hurt
            {
                if(animationCounter == 0)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_hurt_1 left.png"));
                else if(animationCounter == 1)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_hurt_2 left.png"));
                else if(animationCounter == 2)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_hurt_3 left.png"));
                else
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_hurt_4 left.png"));
            }
            else if(currentState == 8) //dead
            {
                if(animationCounter == 0)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Death_1 left.png"));
                else if(animationCounter == 1)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Death_2 left.png"));
                else if(animationCounter == 2)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Death_3 left.png"));
                else if(animationCounter == 3)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Death_4 left.png"));
                else if(animationCounter == 4)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Death_5 left.png"));
                else if(animationCounter == 5)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Death_6 left.png"));
                else if(animationCounter == 6)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Death_7 left.png"));
                else if(animationCounter == 7)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Death_8 left.png"));
                else if(animationCounter == 8)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Death_9 left.png"));
                else if(animationCounter == 9)
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Death_10 left.png"));
                else 
                    warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Death_11 left.png"));

            }
            else//in case not run
            {
                warrior = new ImageIcon(AlicePlayer.class.getResource("Warrior_Idle_1 left.png"));
            }
        }
        Image i = warrior.getImage();
        g.drawImage(i, xCoordinate, yCoordinate, 300, 250, null);
    }

    //keeps track and updates what the current 
    public void animateSelf()
    {
        //if to update if she's facing right or not
        if(orientationRight)
        {
            //if to check her current Action
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
                    if(animationCounter >= 6)
                    {
                        animationCounter = 0;
                    }
                }
            }
            else if(currentState == 1) //run
            {
                if(RUN_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 8)
                        animationCounter = 0;
                }
            }
            else if(currentState == 2) //jump
            {
                if(JUMP_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 3)
                        animationCounter = 0;
                }
            }
            else if(currentState == 3) //uptofall
            {
                if(UP_TO_FALL_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 2)
                        animationCounter = 0;
                }
            }
            else if(currentState == 4) //fall
            {
                if(FALL_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 3)
                        animationCounter = 0;
                }
            }
            else if(currentState == 5) //slide
            {
                if(SLIDE_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 5)
                        animationCounter = 0;
                }
            }
            else if(currentState == 6) //attack
            {
                if(ATTACK_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 12)
                        animationCounter = 0;
                }
            }
            else if(currentState == 7) //hurt
            {
                if(HURT_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 4)
                        animationCounter = 0;
                }
            }
            else if(currentState == 8) //dead
            {
                if(DEAD_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 11)
                        animationCounter = 0;
                }
            }
        }
        else
        {
            //if to check her current Action
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
                    if(animationCounter >= 6)
                    {
                        animationCounter = 0;
                    }
                }
            }
            else if(currentState == 1) //run
            {
                if(RUN_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 8)
                        animationCounter = 0;
                }
            }
            else if(currentState == 2) //jump
            {
                if(JUMP_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 3)
                        animationCounter = 0;
                }
            }
            else if(currentState == 3) //uptofall
            {
                if(UP_TO_FALL_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 2)
                        animationCounter = 0;
                }
            }
            else if(currentState == 4) //fall
            {
                if(FALL_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 3)
                        animationCounter = 0;
                }
            }
            else if(currentState == 5) //slide
            {
                if(SLIDE_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 5)
                        animationCounter = 0;
                }
            }
            else if(currentState == 6) //attack
            {
                if(ATTACK_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 12)
                        animationCounter = 0;
                }
            }
            else if(currentState == 7) //hurt
            {
                if(HURT_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 4)
                        animationCounter = 0;
                }
            }
            else if(currentState == 8) //dead
            {
                if(DEAD_FRAME_TIME <= System.currentTimeMillis() - lastFrameTime)
                {
                    lastFrameTime = System.currentTimeMillis();
                    animationCounter++;
                    if(animationCounter >= 11)
                        animationCounter = 0;
                }
            }
        }
    }

    public int getCurrentState(){
        return currentState;
    }

    public boolean getOrientationRight(){
        return orientationRight;
    }

    public int getPlayerHealth(){
        return playerHealth;
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

    public void setPlayerHealth(int newHealth){
        playerHealth = newHealth;
    }

    public void setRunRight()
    {
        if(currentState == 1 && orientationRight == false)
        {
            setIdle();
        }
        else if(currentState == 0 && currentState < 2)
        {
            currentState = 1;
            orientationRight = true;
        }
    }

    public void setRunLeft()
    {
        if(currentState == 1 && orientationRight == true)
        {
            setIdle();
        }
        else if(currentState == 0 && currentState < 2)
        {
            currentState = 1;
            orientationRight = false;
        }
    }

    public void setIdle()
    {
        if(currentState < 2)
        {
            currentState = 0;
        } 
    }

    public void startJump()
    {
        if(currentState == 0 || currentState == 1)
        {
            currentState = 2;
            lastSlideTime -= 1750;//jump removes slide cooldown
        }
        else if(currentState == 5 && System.currentTimeMillis() - slideStageTime >= SLIDE_FRAME_TIME * 2)//interupt slide with jump
        {
            currentState = 2;
            slideStageTime = -1;
            lastSlideTime -= 1750;//if interupted by jump, slide cool down is less
        }
    }

    //starts
    public void startSlide()
    {
        if((currentState == 0 || currentState == 1) && (System.currentTimeMillis() - lastSlideTime >= SLIDE_COOLDOWN_TIME))
        {
            currentState = 5;
            lastSlideTime = System.currentTimeMillis();
            slideStageTime = System.currentTimeMillis();
        }
    }

    //starts the attack animation if running or idle
    public void attack()
    {
        if(currentState <= 1)
        {
            currentState = 6;
            attackTime = System.currentTimeMillis();
        }
    }

    public void move()
    {
        //Jump stuff
        if(currentState == 2)
        {
            //jump up
            if(jumpStageTime == -1)
            {
                jumpStageTime = System.currentTimeMillis();
            }
            else if(System.currentTimeMillis() - jumpStageTime >= JUMP_FRAME_TIME * 3)
            {
                currentState = 3;
                jumpStageTime = System.currentTimeMillis();
            }
            else
            {
                yCoordinate -= 15;
            }
        }
        else if(currentState == 3)
        {
            //mid air coyote time
            if(System.currentTimeMillis() - jumpStageTime >= UP_TO_FALL_FRAME_TIME * 3)
            {
                currentState = 4;
                jumpStageTime = System.currentTimeMillis();
            }
        }
        else if(currentState == 4)
        {
            //fall
            if(System.currentTimeMillis() - jumpStageTime >= FALL_FRAME_TIME * 3)
            {
                currentState = 0;
                jumpStageTime = -1;

                //in case u dont land in starting spot, u go back to coord[FIX FOR FIGHTMODE]
                if(!fightMode) xCoordinate = 300;
                yCoordinate = 745;
            }
            else
            {
                yCoordinate += 15;//character move down when falling
            }
        }

        //locks character into a slide and unlocks after animation finish
        if(currentState == 5)
        {
            //end slide
            if(System.currentTimeMillis() - slideStageTime >= SLIDE_FRAME_TIME * 5)
            {
                currentState = 0;
                slideStageTime = -1;
            }
            
        }

        //locks character into an attack and unlocks after animation finish
        if(currentState == 6)
        {
            if(System.currentTimeMillis() - attackTime >= ATTACK_FRAME_TIME * 12)
            {
                currentState = 0;
                attackTime = -1;
                hitWindowOpen = false;
            }
            else if(System.currentTimeMillis() - attackTime >= ATTACK_FRAME_TIME * 5 && System.currentTimeMillis() - attackTime <= ATTACK_FRAME_TIME * 8)
            {
                hitWindowOpen = true;
            }
            else if (System.currentTimeMillis() - attackTime >= ATTACK_FRAME_TIME * 9)
            {
                hitWindowOpen = true;
            }
            else
            {
                hitWindowOpen = false;
            }
        }

        //locks character into a hurt state and unlocks after animation finish
        if(currentState == 7)
        {
            if(System.currentTimeMillis() - hurtTime >= HURT_FRAME_TIME * 4)
            {
                currentState = 0;
                hurtTime = -1;
                heart = true;
            }
            else if(heart)
            {
                heart = false;
                playerHealth--;
            }

            if((xCoordinate <= -65 && orientationRight) || (xCoordinate >= 1740 && !orientationRight))
                vx = 0;
            else
                vx = 5;

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

    public boolean getHitWindowOpen()
    {
        return hitWindowOpen;
    }

    //will be used to start fights when an enemy is spotted
    public void setFightMode(boolean b)
    {
        fightMode = b;
    }

    //used to see if the background should be moving or if the character should move
    public boolean getFightMode()
    {
        return fightMode;
    }

    //character actually moves across static background for this method
    //this will be used for when fighing enemies
    public void fightMovement(boolean aPressed, boolean dPressed)
    {
        //hitting barriers
        if((xCoordinate <= -65 && !orientationRight) || (xCoordinate >= 1740 && orientationRight))
            vx = 0;
        else
            vx = 12;
        //running
        if(orientationRight)
        {
            if(dPressed && !aPressed && (currentState >= 2 && currentState <= 4)) 
                xCoordinate += vx;
            else if(currentState >= 2 && currentState <= 4)
                vx = 0;
            else if(currentState == 1 || currentState == 5)
                xCoordinate += vx;  
        }
        else
        {
            if(aPressed && !dPressed && (currentState >= 2 && currentState <= 4))
                xCoordinate -= vx;
            else if(currentState >= 2 && currentState <= 4)
                vx = 0;
            else if(currentState == 1 || currentState == 5)
                xCoordinate -= vx; 
        }
    }

    public void checkKnightBattle(AliceKnight pKnight)
    {
        if(Math.abs(xCoordinate - pKnight.getXCoordinate()) <= 800 && !pKnight.getDead())
        {
            fightMode = true;
            pKnight.beginBattle();
        } 
        else if(pKnight.getFightingKnights() == pKnight.getKnightsKilled())
        {
            fightMode = false;
        }
    }

    public void playBattle(AliceKnight pKnight)
    {
        pKnight.attack(this);
        pKnight.fightMovement(this);

        pKnight.checkHurt(this);
    }

    public void checkBossBattle(AliceBoss pBoss)
    {
        if(Math.abs(xCoordinate - pBoss.getXCoordinate()) <= 1100 && !pBoss.getDead())
        {
            fightMode = true;
            pBoss.beginBattle();
        } 
        else if(pBoss.getDead())
        {
            fightMode = false;
        }
    }

    public void takeDamage(){
        if(currentState != 7)
        {
            currentState = 7;
            hurtTime = System.currentTimeMillis();
        }
    }

    public void recenterPlayer(AliceBackground pBackground)
    {
        if(Math.abs(xCoordinate- 300) >= 10)
        {
            if(xCoordinate > 300)
            {
                pBackground.animateLeft();
                xCoordinate -= 10;
            }
            else
            {
                pBackground.animateRight();
                xCoordinate += 10;
            }
        }
    }

    public void death(){
        currentState = 8;
    }
}
