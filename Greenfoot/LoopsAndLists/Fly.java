import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fly here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fly extends Actor
{
    private int acts=0;
    public void act() 
    {
        acts++;
        int randTurn = Greenfoot.getRandomNumber(180)-90;
        turn(randTurn);
        move(8);
        // Add your action code here.
        
        MyWorld world = (MyWorld)getWorld();
        if(acts%(4*30)==0){
                Fly fly = new Fly();
                world.addFly(fly);
                world.addObject(fly,getX(),getY());
        }
    }    
}
