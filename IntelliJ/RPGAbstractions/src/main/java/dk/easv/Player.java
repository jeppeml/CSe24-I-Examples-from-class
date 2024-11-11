package dk.easv;

public abstract class Player implements IDrawable {
    private String name;
    private int hp;
    protected int baseDamage;
    private int speed; // speed of attack

    public Player (String name){
        // default values
        hp = 100;
        baseDamage = 50;
        speed = 10;
        // mandatory value
        this.name = name;
    }

    // read only, no setter
    public int getHp(){
        return hp;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void attack(Player player){
        player.takeDamage(baseDamage);
    }

    public void takeDamage(int dmg){
        hp -= dmg;
    }

    @Override
    public String toString() {
        return "name: " + name +
                "\n" +
                "hp: " + hp +
                "\n" +
                "dmg: " + baseDamage +
                "\n" +
                "speed: " + speed;
    }
}
