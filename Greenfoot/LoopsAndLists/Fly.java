import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fly here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fly extends Actor
{
    
    public void act() 
    {
        int randTurn = Greenfoot.getRandomNumber(180)-90;
        turn(randTurn);
        move(8);
        // Add your action code here.
    }    
}
