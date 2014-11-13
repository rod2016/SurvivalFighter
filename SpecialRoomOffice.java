
/**
 * Goodhue's SpecialRoomOffice
 * 
 * Adding a special capability to just one room.
 * 
 * 2013-11-15
 */
public class SpecialRoomOffice extends Room

{
    /**
     * Constructor for objects of class SpecialRoomOffice
     */
    public SpecialRoomOffice(String description)
    {
        super(description);
    }
    public void press(Command command)
    {
        if(command.getSecondWord().equals("lever"))
        {
            System.out.println("You press the lever and part of the wall " +
                "slides open revealing a staircase.");
            Room cellar = new Room("in a dark and dusty cellar");
            setExit("down", cellar);
            cellar.setExit("up", this);
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