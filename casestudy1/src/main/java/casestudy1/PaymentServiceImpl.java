package casestudy1;

public class PaymentServiceImpl implements PaymentService {

    private PaymentDAO paymentDAO = new PaymentDAOImpl();
    private OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public void addPaymentToOrder(Long orderId, Payment payment) {

        PurchaseOrder order = orderDAO.findOrder(orderId);

        if (order != null) {
            payment.setOrder(order);
            paymentDAO.savePayment(payment);
        }
    }

    @Override
    public Payment findPayment(Long paymentId) {

        return paymentDAO.findPayment(paymentId);
    }
}
