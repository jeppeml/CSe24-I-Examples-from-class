package dk.easv.payment;

public class MobilePayPayment implements IPayment{
    @Override
    public String getPaymentMethod() {
        return "MobilePay";
    }

    @Override
    public void handlePayment() {
        System.out.println("Contacting the bank for mobile pay..");
        System.out.println("Opening app for payment");
    }
}
