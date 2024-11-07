package dk.easv;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world of RPG!");
        Human peter = new Human("Peter the gruesome");
        Human ben = new Human("Ben the slayer of dark demons");

        System.out.println(peter);
        System.out.println(ben);

        ArrayList<Player> al = new ArrayList<>();
        al.add(new Human("Benny"));
        al.add(new Human("Louis"));
        al.add(new Human("John"));

        Orc o = new Orc("Brullo");
        al.add(o);

        al.get(2).attack(al.get(1));

        int[] myArray = new int[2];
        myArray[0]=2;
        myArray[1]=7;

        int[] current = myArray;
        int[] newBigger = new int[current.length * 8];

        for (int i = 0; i < myArray.length-1; i++) {
            newBigger[i]=current[i];
        }
        myArray = newBigger;

        newBigger[2] = 8;


        System.out.println("Peter attacks Ben, what a bastard");
        peter.attack(ben);

        peter.setName("Petter the nice one");
        System.out.println(peter);
        System.out.println(ben);

        System.out.println("A wild Orc appears");
        Orc schitchy = new Orc("Schirtxzt");
        schitchy.attack(ben);
        ben.attack(schitchy);

        Wizard merlin = new Wizard("Merlin");

        List<IDrawable> entities = new ArrayList<>();
        entities.add(peter);
        entities.add(ben);
        entities.add(schitchy);
        entities.add(merlin);
        merlin.draw();

            for (IDrawable p : entities) {
                p.draw();
            }


    }
}