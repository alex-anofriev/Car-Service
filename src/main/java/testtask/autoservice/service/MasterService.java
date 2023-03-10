package testtask.autoservice.service;

import java.math.BigDecimal;
import java.util.List;
import testtask.autoservice.model.Master;
import testtask.autoservice.model.Order;

public interface MasterService extends CommonMethods<Master> {

    List<Order> findOrders(Long id);

    BigDecimal calculateSalary(Long id);

    List<Master> update(List<Master> masters);
}
