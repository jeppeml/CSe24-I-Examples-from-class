import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ChessBoard extends World
{
    public ChessBoard()
    {    
        super(8, 8, 50);
        for(int i=0;i<8;i++){
            addObject(new BlackPawn(), i, 1);
            addObject(new WhitePawn(), i, 6);
        }
        
    }
}
