import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class OurPlanet extends World
{
    private int health = 100;
    private Counter counter = new Counter("Health: ");
    // Constructor
    public OurPlanet()
    {    
        super(600, 400, 1); 
        addObject(counter, 62, 24);
    }
    
    public void act(){
        counter.setValue(health);
    }
    
    public int getHealth(){
        return health;
    }
    
    public void setHealth(int newHealth){
        if(newHealth<0){
            // show invalid message
        }
        
        health = newHealth;
       if(health == 0){
            Greenfoot.stop();
        }
    }
    
    public void createPositiveImpact(){
        health = health + 1;
    }
    
    public void createNegativeImpact(){
        health = health - 1;
    }
}
