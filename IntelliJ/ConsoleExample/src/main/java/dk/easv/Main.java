package dk.easv;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] arguments) {


        System.out.println("Welcome to my shop, traveller");
        System.out.println("please type your age:");
        Scanner scanner = new Scanner(System.in);
        int age = scanner.nextInt();
        System.out.println("Your age is " + age + " years old");

        ArrayList<Drink> drinks = new ArrayList<>();
        drinks.add(new Drink("Beer", 25.0,4.7));
        drinks.add(new Drink("Orange Juice", 66,0));
        drinks.add(new Drink("Vodka", 143,41));


        System.out.println("#####***** Ye Olde Liquor Shop *****#####");
        for (int i = 0; i < drinks.size(); i++) {
            Drink current = drinks.get(i);

            if (age>=18){
                System.out.println(i + " " + current);
            }
            else if (age>=16 && current.getAlcPercentage()<16.5){
                System.out.println(i + " " + current);
            }
            else if(current.getAlcPercentage()<16.5){
                System.out.println(i + " " + current);
            }

        }
        /*
        if (age>15 && alc>16.5)
            System.out.println(beer);
        System.out.println(orangeJuice);*/
    }
}
