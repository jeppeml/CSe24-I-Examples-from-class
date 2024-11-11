package dk.easv.payment;

public class GrandmotherPayment implements IPayment{
    @Override
    public String getPaymentMethod() {
        return "Grandmother";
    }

    @Override
    public void handlePayment() {
        System.out.println("Grandmother says NO!");
    }
}
