package casestudy1;

import java.util.List;

public interface OrderDAO {

    void saveOrder(PurchaseOrder o);

    PurchaseOrder findOrder(Long id);

    List<PurchaseOrder> findOrdersByUser(Long userId);
}
