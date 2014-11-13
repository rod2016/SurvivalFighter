import java.util.ArrayList;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.08
 */

public class Game 
{
    private Parser parser;
    private ParserWithFileInput parserWithFileInput;
    private Room currentRoom;
    private Health health;
    private int currentHealthNum;
     private ArrayList<String> itemsInPack;
    private ArrayList<String> boatItems;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        
        
        createRoomsAndItems();
        
        parser = new Parser();
        parserWithFileInput = new ParserWithFileInput();
        health = new Health();
        currentHealthNum = health.getHealthNum();
        itemsInPack = new ArrayList<String>();
    }
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Survival Fighter!");
        System.out.println("You have just woken up from what seems like a very long nap.");
        System.out.println("The last thing you remember was jumping out of an exploding plane");
        System.out.println("and landing in cold water as the freezing temperature slowly lulled you to sleep.");
        System.out.println("You wake up on a beautiful island surrouded by a large body of water and deep dark jungle.");
        System.out.println("After regaining your seses you decide that the best way to make it out is building a boat.");
        System.out.println("You notice many things you can use around you and can bet that if you venture deep enough");
        System.out.println("in the jungle you will find all the items you need in order to build a good boat.");       
        System.out.println("");
        System.out.println("You can travel North, West, East and West. Here's what you need to know: ");
         System.out.println("");
        System.out.println("Each of the directions will lead to a different region of the island");
        System.out.println("");
        System.out.println("You have a *health bar* that will notify you of your health status whenever you enter a new region of the island ");
        System.out.println("");
        System.out.println("You have a *backpack* where you can gather up to 5 items and use/discard them at any point in your adventure");
        System.out.println("");
        System.out.println("Expect dangers and obstacles along the way.");
        System.out.println("");
        System.out.println("Try to pick up as many useful items as you can.");
        System.out.println("");
        System.out.println("Eat sometimes to increase your health status bar.");
        System.out.println("");
        System.out.println("Take care....if you can.");
        System.out.println("");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
        System.out.println(health.getHealthString(currentHealthNum));
        System.out.println("your pack contains: " +printItemsInPack());
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRoomsAndItems()
    {
        Room room1, room2, room3, room4, room5, SecretRoom, room6, beach, ocean;
      
        // create the rooms
        beach = new Room("on a really warm, nice, beautiful beach");  
        room1 = new Room("in the Smelly Seaweed Area");
        room2 = new SpecialRoomOffice("in the Spooky Tavern" +
            ".  There is a lever disguised as a beer tap.");
        room3 = new Room("in the Haunted Hunting Grounds");
        room4 = new SecretSwampOffice("in the Sleepy Swamp Area" +
            ".  There is a secret button disguised as a frog.");
        room5 = new Room("in the Creepy Canopy Area");

        room6 = new Room("in the Deathly Graveyard Area");
         
        ocean = new Room("in Death Ocean");    /// needs to be a special room 
        // initialise room exits
        room1.setExit("up", room4);
        room1.setExit("down",beach );
        room1.setExit("right", room3);
        room1.setExit("left", room2);
        
        
        room2.setExit("up", room6);
        room2.setExit("right", room1);
        room2.setExit("down", beach);
        
        
        
        
        
        
        room3.setExit("up", room5);
        room3.setExit("left", room1);
        
        room4.setExit("down", room1);
        room4.setExit("right", room5);
        room4.setExit("left", room6);
        
        
        room5.setExit("down", room3);
        room5.setExit("left", room4);
       
        
        
        room6.setExit("down", room2);
        room6.setExit("right", room4);
     
        currentRoom = beach;  // start game outside
        beach.setExit("up", room1);
        beach.setExit("down", ocean);
        
        
        
        
        
        Item sword, bat, gun;
        
        sword = new Item("this will hurt things");
        bat = new Item("this will hurt things");
        gun = new Item("this will hurt things");
        
        room1.setItem(sword, "sword");
        room1.setItem(bat, "bat");
        room1.setItem(gun, "gun");
        
    }
    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            if(currentHealthNum <= 0){
                finished = true;
            } 
        }
        if(currentHealthNum <= 0)
        {
             System.out.println("You died");
             
            }
        System.out.println("Thank you for playing.  Good bye.");
    }
    public void playWithFileInput() 
    {            
        printWelcome();
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            if(currentHealthNum <= 0){
                finished = true;
            } 
        }
        if(currentHealthNum <= 0)
        {
             System.out.println("You died");
             
            }
        System.out.println("Thank you for playing.  Good bye.");
    }
   
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("get")) {
           pickUpItem(command);
        }
        else if (commandWord.equals("die")) {
           die();
        }
         else if (commandWord.equals("press")) {
            currentRoom.press(command);
            System.out.println(currentRoom.getLongDescription());
        }
         else if (commandWord.equals("pull")) {
            currentRoom.press(command);
            System.out.println(currentRoom.getLongDescription());
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            System.out.println("your pack contains: " +printItemsInPack());
            System.out.println(health.getHealthString(currentHealthNum));
        }
    }
    
    public void pickUpItem(Command command){
        
         if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Get what?");
            return;
        }

        String item = command.getSecondWord();
        Boolean isItInRoom = currentRoom.availabilityCheck(item);
        
        if (isItInRoom == false) {
            System.out.println("Item not in this room");
        }
        else {
            
            itemsInPack.add(item);
            System.out.println("you picked up the "+item);
        }
        
        
    }
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
      
    public String printItemsInPack()
    {
        int i = 1;
        int numberOfItemsInPack = itemsInPack.size();
        String itemsInPackList = new String();
        if (itemsInPack.size() == 0){
            return "you have nothing in you bag";
        }
        
        while(i <= itemsInPack.size())
        {
            
            itemsInPackList = itemsInPackList +" "+ itemsInPack.get(i-1);
            i++;
        }
        return itemsInPackList;
        
    }
    public void die()
    {
       currentHealthNum = currentHealthNum - 1;
       System.out.println("Hurt by 1");
    }
}
