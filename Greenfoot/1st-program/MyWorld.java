import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World
{
    public MyWorld()
    {    
        super(900, 700, 1); 
        for(int i=0;i<10000;i++){
            addObject(new Lobster(), 350, 350);
        }
        
    }
}
