package dk.easv;

import java.util.Scanner;

public class Main {

    public static void main(String[] arguments) {
        System.out.println("Welcome to my shop, traveller");
        System.out.println("please type your age:");
        Scanner scanner = new Scanner(System.in);
        int age = scanner.nextInt();
        System.out.println("Your age is " + age + " years old");
        
        System.out.println("#####***** Ye Olde Liquor Shop *****#####");
        Drink beer = new Drink("Beer", 25.0,4.7);
        System.out.println(beer);
/*
        if (age>15)
            System.out.println("Beer 4.7% - 25 kr");
        System.out.println("Orange Juice 0% - 66 kr");*/
    }
}