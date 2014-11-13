
/**
 * Write a description of class SecretSwampOffice here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SecretSwampOffice extends Room
{

    

   public SecretSwampOffice(String description)
    {
        super(description);
    }
    public void press(Command command)
    {
        if(command.getSecondWord().equals("frog"))
        {
            System.out.println("You push the frog and a wild crocodile appears.  " +
                "It opens its mouth, revealing a staircase.");
            Room crocodile = new Room("in the pit of the crocodile's belly");
            setExit("up", crocodile);
            crocodile.setExit("down", this);
            changeDescription("in the Sleepy Swamp Area" +
            ".  \nThere is a secret button hidden as a frog." +
            "\nA crocodile appears and reveals a staircase leading down to its belly.");
        }
        else
        {
            super.press(command);
        }
    }

    
}