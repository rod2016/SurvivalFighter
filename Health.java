
/**
 * Write a description of class Health here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Health
{
    // instance variables - replace the example below with your own
    private int healthNum;
    private String healthString;

    /**
     * Constructor for objects of class Health
     */
    public Health()
    {
        healthNum = 10;
        healthString = getHealthString(healthNum);
        
    }

    public String getHealthString(int healthNum)
    {
        switch(healthNum) {
            case 1:
            healthString = "|#         |";
            break;
            case 2:
            healthString = "|##        |";
            break;
            case 3:
            healthString = "|###       |";
            break;
            case 4:
            healthString = "|####      |";
            break;
            case 5:
            healthString = "|#####     |";
            break;
            case 6:
            healthString = "|######    |";
            break;
            case 7:
            healthString = "|#######   |";
            break;
            case 8:
            healthString = "|########  |";
            break;
            case 9:
            healthString = "|######### |";
            break;
            case 10:
            healthString = "|##########|";
            break;
        }
        return healthString = "You Health level is: " +healthString;
    }
    
    public int getHealthNum()
    {
        return healthNum;
        
    }
    
}
