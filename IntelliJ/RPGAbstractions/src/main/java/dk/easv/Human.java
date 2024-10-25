package dk.easv;

public class Human extends Player {
    private int mana;

    public Human(String name) {
        super(name);
    }

    @Override
    public void draw() {
        System.out.println("      ////\\\\\\\\\n" +
                "      |      |\n" +
                "     @  O  O  @\n" +
                "      |  ~   |         \\__\n" +
                "       \\ -- /          |\\ |\n" +
                "     ___|  |___        | \\|\n" +
                "    /          \\      /|__|\n" +
                "   /            \\    / /\n" +
                "  /  /| .  . |\\  \\  / /\n" +
                " /  / |      | \\  \\/ /\n" +
                "<  <  |      |  \\   /\n" +
                " \\  \\ |  .   |   \\_/\n" +
                "  \\  \\|______|\n" +
                "    \\_|______|\n" +
                "      |      |\n" +
                "      |  |   |\n" +
                "      |  |   |\n" +
                "      |__|___|\n" +
                "      |  |  |\n" +
                "      (  (  |\n" +
                "      |  |  |\n" +
                "      |  |  |\n" +
                "     _|  |  |\n" +
                " cccC_Cccc___)");
    }


}
