package testtask.autoservice.service;

import java.util.List;
import testtask.autoservice.model.Order;
import testtask.autoservice.model.Owner;

public interface OwnerService extends CommonMethods<Owner> {

    List<Order> findOrdersById(Long id);
}
