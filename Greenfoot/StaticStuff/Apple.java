import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Apple here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Apple extends Actor
{
    public static int numOfApples = 0;
    public int numOfApplesNonStatic = 0;
    
    public void act() 
    {
        numOfApplesNonStatic++;
        numOfApples++;//Greenfoot.getRandomNumber(1000);
        getWorld().getObjects(Apple.class).size();
    }   
    
    
}
