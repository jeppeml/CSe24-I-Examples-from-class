import greenfoot.*;  
public class Teddy extends Actor
{
    private OurPlanet planet = (OurPlanet)getWorld();
    private int countActs = 0;
    public void act() 
    {
        planet = (OurPlanet)getWorld();
        int planetHealth = planet.getHealth();
        int modresult = countActs % 10;
        if( modresult == 0 ){
            if(isAtEdge()){
                turn(180);
            }
            else
            {
                turn(Greenfoot.getRandomNumber(90)-45);
            }
        }
        move(3);
        
        Tree tree = (Tree)getOneIntersectingObject(Tree.class);
        if(tree==null){
            planet.addObject(new Tree(), getX(), getY());
        }
        
    }    
}
