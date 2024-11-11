package dk.easv;

public class Wizard extends Player{
    private int mana = 100;
    public Wizard(String name) {
        super(name);
    }

    @Override
    public void attack(Player player) {
        doMagicDamage(player);
    }
    public void doMagicDamage(Player p){
        if (mana>0) {
            p.takeDamage(baseDamage);
            mana -= 1;
        }
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
