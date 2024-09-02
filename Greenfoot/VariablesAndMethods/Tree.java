import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tree here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tree extends Actor
{
    private OurPlanet planet = (OurPlanet)getWorld();
    public void act() 
    {
        planet = (OurPlanet)getWorld();
        planet.createPositiveImpact();
    }    
}
