package casestudy1;

public interface PaymentDAO {

    void savePayment(Payment p);

    Payment findPayment(Long id);
}
