import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class MyWorld extends World
{
    private static int worldWidth = 800;
    private static int worldHeight = 600;
    private List<Fly> flys = new ArrayList();
    private int acts=0;
    public MyWorld()
    {    
        super(worldWidth, worldHeight, 1, false); 

        for(int i=0;i<5;i++){
            int randX = Greenfoot.getRandomNumber(worldWidth);
            int randY = Greenfoot.getRandomNumber(worldHeight);
            Fly f = new Fly();
            flys.add(f);
            addObject(f,randX,randY);
        }

    }

    public void act(){
        acts++;
        for(int i =0;i<flys.size();i++){
            Fly f = flys.get(i);
            if(f.getWorld()!=null){
                if(f.getX()>worldWidth || f.getX()<0 ||
                f.getY()>worldHeight || f.getY()<0){
                    flys.remove(f);
                    removeObject(f);
                }
            }

        }
        

    }
    
    public void addFly(Fly f){
        flys.add(f);
    }
}
