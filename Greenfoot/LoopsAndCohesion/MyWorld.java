import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World
{
    private int acts = 0;
    private int carSpawnDelay = 30;
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false); 
        addObject(new PlayerCar(), 300, 350);
    }
    
    public void act(){
        if(acts % carSpawnDelay == 0){
            int r = Greenfoot.getRandomNumber(600);
            addObject(new EnemyCar(), r, 0);
        }
        if(acts % (4*30) == 0){
            carSpawnDelay = carSpawnDelay -1;
        }
        acts = acts + 1;
    }
}
