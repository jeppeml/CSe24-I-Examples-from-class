import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class PlayerCar extends Actor
{
    private int health = 100;
    private Counter healthCounter = new Counter("Health:");
    
    public PlayerCar(){
        
    }
    
    public void addedToWorld(World world){
        world.addObject(healthCounter, 50, 20);
        healthCounter.setValue(health);
    }
    
    public void act()  
    {
        if(Greenfoot.isKeyDown("a")){
            move(-5);
        }
        if(Greenfoot.isKeyDown("d")){
            move(5);
        }
        
        EnemyCar enemy = (EnemyCar)getOneIntersectingObject(EnemyCar.class);
        if(enemy!=null){
            getWorld().removeObject(enemy);
            health = health - 5;
            
            if(health<=0){
                Greenfoot.setWorld(new EndWorld());
            }
        }
        healthCounter.setValue(health);
    }    
}
