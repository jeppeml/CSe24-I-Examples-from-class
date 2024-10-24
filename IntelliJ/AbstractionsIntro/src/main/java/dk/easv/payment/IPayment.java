package dk.easv.payment;

public interface IPayment {
    public String getPaymentMethod();
    public void handlePayment();
}
