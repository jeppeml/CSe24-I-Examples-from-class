package dk.easv.payment;

public class BitcoinPayment implements IPayment{
    @Override
    public String getPaymentMethod() {
        return "Bitcoin";
    }

    @Override
    public void handlePayment() {
        System.out.println("Contacting server..");
        System.out.println("Doing magical bitcoin stuff..");
    }
}
