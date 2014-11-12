import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.08
 */

public class Item 
{
    private String description;
    private HashMap<Room, String> itemInRoom;
    private ArrayList itemsInPack;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Item(String description) 
    {
        this.description = description;
        itemInRoom = new HashMap<Room, String>();
        itemsInPack = new ArrayList();
        
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setItem(Room thisRoom, String itemName) 
    {
       itemInRoom.put(thisRoom, itemName);
    }


    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getItemsInRoom()
    {
        String returnString = "Items in room:";
        Set<Room> keys = itemInRoom.keySet();
        for(Room itemName : keys) {
            returnString += " " + itemName;
        }
        return returnString;
    }
    
    private String dropItemsInRoom()
    {
        String returnString = "Drop item in room:";
        
        for()
        
        
        
        
        
    }
    


   
}

