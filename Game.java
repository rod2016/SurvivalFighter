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
 * @authors Ioana Bondar, Roderick Jackson, Iron Meng, Sally Tang
 * Fall 2014 - MIST 4600
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

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRoomsAndItems()
    {
        Room room1, room2, room3, room4, room5, room6, beach, ocean;
      
        // create the rooms
        room1 = new Room("outside the main entrance of the university");
        room2 = new Room("in a lecture theater");
        room3 = new Room("in the campus pub");
        room4 = new Room("in a computing lab");
        room5 = new Room("in the computing admin office");
        room6 = new Room("in the computing admin office");
        beach = new Room("in the computing admin office");    
        ocean = new Room("in the computing admin office");    /// needs to be a special room 
        // initialise room exits
        room1.setExit("up", room4);
        room1.setExit("down",beach );
        room1.setExit("right", room3);
        room1.setExit("left", room2);
        
        
        room2.setExit("up", room6);
        room2.setExit("right", room4);
        
        room3.setExit("up", room5);
        room3.setExit("left", room1);
        
        room4.setExit("down", room1);
        room4.setExit("right", room5);
        room4.setExit("left", room6);
        
        room5.setExit("down", room3);
        room5.setExit("left", room4);
        
        room6.setExit("down", room2);
        room6.setExit("left", room4);
     
        currentRoom = beach;  // start game outside
        beach.setExit("up", room1);
        
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
             System.out.println("Want to play again");
             Command command = parser.getCommand();
             String YN command.getCommandWord();
             boolean Play = YN.isYesOrNo(YN);
             while (false){
             System.out.println("that is not Y or N");
             Command command = parser.getCommand();
             String YN command.getCommandWord();
             boolean Play = YN.isYesOrNo(YN);
            }
            
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    
     public boolean isYesOrNo(String YN)
     {
         if(YN.equals("y")|| YN.equals("n")){
             return true;
            }
          else{
              return false;
            }
     }
     
    public void playWithFileInput() 
    {            
        printWelcome();
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parserWithFileInput.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Survival Fighter!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
        System.out.println(health.getHealthString(currentHealthNum));
        System.out.println("your pack contains: " +printItemsInPack());
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
            
            System.out.println(health.getHealthString(currentHealthNum));
            System.out.println(printItemsInPack());
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
        
        if (isItInRoom == true) {
            itemsInPack.add(item);
            System.out.println("you picked up the "+item);
        }
        else {
            System.out.println("Item not in this room");
        }
        
        public void dropItem(Comamand command)
          {
         if(!command.hasSecondWord()) {
                 System.out.println("Drop what?");
            return;}
            
            String item = command.getSecondWord();
            
            Boolean isPackFull = currentRoom.packCheck(item);
            
            if(isPackFull == false){
                System.out.println("No items to drop")}
                
                else {
                    System.out.println("drop item from pack");}
                    
                }
    }
        
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
