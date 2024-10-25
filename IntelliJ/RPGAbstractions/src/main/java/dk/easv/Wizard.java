package dk.easv;

public class Wizard extends Player{

    public Wizard(String name) {
        super(name);
    }

    public void doMagicDamage(Player p){
        p.takeDamage(baseDamage);
    }
}
