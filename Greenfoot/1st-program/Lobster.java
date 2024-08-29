import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Lobster extends Actor
{
    public void act() 
    {
        if(isAtEdge()){
            setRotation(getRotation()-180);
        }
        int rand = Greenfoot.getRandomNumber(45)-20;
        move(15);
        turn(rand);
        
    }    
}
