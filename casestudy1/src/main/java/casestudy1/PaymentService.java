package casestudy1;

public interface PaymentService {

    void addPaymentToOrder(Long orderId, Payment payment);

    Payment findPayment(Long paymentId);
}
