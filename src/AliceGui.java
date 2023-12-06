//imports for drawing Images(used for drawing Alice)
import java.awt.Graphics2D;

public class AliceGui {
    private AliceHeart[] heartArray = new AliceHeart[3];

    public AliceGui()
    {
        heartArray[0] = new AliceHeart(50, 50, true); //1st heart
        heartArray[1] = new AliceHeart(150, 50, true); //2nd heart
        heartArray[2] = new AliceHeart(250, 50, true); //3rd heart
    }

    public void updateHealth(AlicePlayer a){
        if(a.getPlayerHealth() == 3){
            heartArray[0] = new AliceHeart(50, 50, true); //1st heart FULL
            heartArray[1] = new AliceHeart(150, 50, true); //2nd heart FULL
            heartArray[2] = new AliceHeart(250, 50, true); //3rd heart FULL
        }
        else if(a.getPlayerHealth() == 2){
            heartArray[0] = new AliceHeart(50, 50, true); //1st heart FULL
            heartArray[1] = new AliceHeart(150, 50, true); //2nd heart FULL
            heartArray[2] = new AliceHeart(250, 50, false); //3rd heart EMPTY
        }
        else if(a.getPlayerHealth() == 1){
            heartArray[0] = new AliceHeart(50, 50, true); //1st heart FULL
            heartArray[1] = new AliceHeart(150, 50, false); //2nd heart EMPTY
            heartArray[2] = new AliceHeart(250, 50, false); //3rd heart EMPTY
        }
        else{
            heartArray[0] = new AliceHeart(50, 50, false); //1st heart EMPTY
            heartArray[1] = new AliceHeart(150, 50, false); //2nd heart EMPTY
            heartArray[2] = new AliceHeart(250, 50, false); //3rd heart EMPTY'
            death();
        }
    }

    public void drawHeartArray(Graphics2D g)
    {
        for(int i = 0; i < 3; i++)
        {
            heartArray[i].drawSelf(g);
        }
    }

    public void death(){
        System.out.println("You died");
    }
}
