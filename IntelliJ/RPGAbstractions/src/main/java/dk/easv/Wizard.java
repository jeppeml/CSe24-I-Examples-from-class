package dk.easv;

public class Wizard extends Player{

    public Wizard(String name) {
        super(name);
    }

    public void doMagicDamage(Player p){
        p.takeDamage(baseDamage);
    }

    public void draw(){
        System.out.println( "     __/\\__\n" +
                ". _  \\\\''//\n" +
                "-( )-/_||_\\\n" +
                " .'. \\_()_/\n" +
                "  |   | . \\\n" +
                "  |   | .  \\\n" +
                " .'. ,\\_____'.");
    }
}
