package testtask.autoservice.service;

import java.math.BigDecimal;
import java.util.List;
import testtask.autoservice.model.Goods;
import testtask.autoservice.model.Order;
import testtask.autoservice.model.enums.OrderStatus;

public interface OrderService extends CommonMethods<Order> {

    Order addGoods(Long id, Goods goods);

    Order updateStatus(Long id, OrderStatus orderStatus);

    BigDecimal calculateCostForClint(Long id);

    List<Order> findAllByIds(List<Long> orderIds);
}
