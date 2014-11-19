import java.util.Set;
import java.util.HashMap;



public class Item 
{
    private String description;
    private HashMap<Room, String> itemInRoom;    
    
    
    public Item(String description) 
    {
        this.description = description;
        itemInRoom = new HashMap<Room, String>();
        
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     *
    public void setItem(Room thisRoom, String itemName) 
    {
       itemInRoom.put(thisRoom, itemName);
    }


    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     *
    private String getItemsInRoom()
    {
        String returnString = "Items in room:";
        Set<Room> keys = itemInRoom.keySet();
        for(Room itemName : keys) {
            returnString += " " + itemName;
        }
        return returnString;
    }
    */

   
}

