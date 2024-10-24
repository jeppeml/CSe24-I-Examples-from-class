package dk.easv.payment;

public class VisaPayment implements IPayment{
    @Override
    public String getPaymentMethod() {
        return "Visa";
    }

    @Override
    public void handlePayment() {
        System.out.println("Contacting the card issuer for VISA..");
        System.out.println("Please swipe your card");
    }
}
