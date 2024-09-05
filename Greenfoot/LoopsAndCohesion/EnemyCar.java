import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyCar extends Actor
{
    public EnemyCar(){
        turn(180);
    }
    
    public void act() 
    {
        setLocation(getX(),getY()+3);
        if(getY()>450){
            getWorld().removeObject(this);
        }
    }    
}
