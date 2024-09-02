import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Person here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Person extends Actor
{
    private int countActs = 0;
    //private OurPlanet planet = (OurPlanet)getWorld();
    public void act() 
    {
        countActs++;
        OurPlanet planet = (OurPlanet)getWorld();
        planet.createNegativeImpact();
        doMoves();
        chopTrees();
    }   
    
    private void chopTrees(){
        OurPlanet planet = (OurPlanet)getWorld();
        Tree tree = (Tree)getOneIntersectingObject(Tree.class);
        if(tree!=null){
            planet.removeObject(tree);
        }
    }
    private void doMoves(){
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
    }
}
