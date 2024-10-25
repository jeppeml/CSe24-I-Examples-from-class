package dk.easv;

import dk.easv.payment.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<IPayment> paymentMethods = new ArrayList<>();

    public static void main(String[] args) {
        // Add any payment strategies
        paymentMethods.add(new BitcoinPayment());
        paymentMethods.add(new MobilePayPayment());
        paymentMethods.add(new VisaPayment());
        paymentMethods.add(new GrandmotherPayment());

        // Summarize the payments with an index number
        System.out.println("Hello, how do you want to pay?");
        for(int i = 0; i<paymentMethods.size();i++){
            System.out.println(i+": "+paymentMethods.get(i).getPaymentMethod());
        }

        // Choose payment type
        System.out.println("Please select your choice");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        IPayment payment = paymentMethods.get(choice);

        // Do the payment
        payment.handlePayment();
    }

}