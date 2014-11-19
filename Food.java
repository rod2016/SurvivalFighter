import java.util.Set;
import java.util.HashMap;
/**
 * Write a description of class Food here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Food extends Item
{
   private String description;

   private int healthPower;

    /**
     * Constructor for objects of class Food
     */
    public Food(String description, int pHealthPower)
    
    {

        super(description);
        healthPower = pHealthPower;
        
        
        

        
    }

 
}
