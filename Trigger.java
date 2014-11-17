import java.util.Random;
/**
 * Write a description of class Trigger here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Trigger
{
    // instance variables - replace the example below with your own
    private Random randomIntGen;
     
    /**
     * Constructor for objects of class Trigger
     */
    public Trigger()
    {
        randomIntGen = new Random();
    }
     
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public boolean fire(int prob)
    {
        
     int thisInt = randomIntGen.nextInt(prob);
       if(thisInt == prob-1){
           return true;
        }
      return false;
         
    }
}
