
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
            System.out.println("You pull the lever and part of the wall " +
                "slides open revealing a staircase.");
            Room cellar = new Room("in a dark and dusty cellar");
            setExit("up", cellar);
            cellar.setExit("down", this);
            changeDescription("in the Spooky Tavern" +
            ".  \nThere is a secret lever hidden as a beer tap." +
            "\nAn opening in the wall reveals a staircase leading down.");
        }
        else
        {
            super.press(command);
        }
    }

    
}