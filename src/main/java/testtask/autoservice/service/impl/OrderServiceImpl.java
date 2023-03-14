package testtask.autoservice.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testtask.autoservice.exceptions.DataProcessingException;
import testtask.autoservice.model.Goods;
import testtask.autoservice.model.Master;
import testtask.autoservice.model.Order;
import testtask.autoservice.model.Owner;
import testtask.autoservice.model.ServiceModel;
import testtask.autoservice.model.enums.OrderStatus;
import testtask.autoservice.repository.OrderRepository;
import testtask.autoservice.service.MasterService;
import testtask.autoservice.service.OrderService;
import testtask.autoservice.service.OwnerService;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private static final BigDecimal DEFAULT_PRICE = BigDecimal.valueOf(500.00);
    private static final double DISCOUNT_FOR_GOODS = 0.01;
    private static final double DISCOUNT_FOR_SERVICES = 0.02;
    private final OrderRepository orderRepository;
    private final OwnerService ownerService;
    private final MasterService masterService;

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new DataProcessingException("Order with id: " + id + " not found"));
    }

    @Override
    @Transactional
    public Order save(Order order) {
        order.setAcceptanceDate(LocalDate.now());
        orderRepository.save(order);
        Owner owner = order.getCar().getOwner();
        owner.getOrders().add(order);
        ownerService.update(owner);
        return order;
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order addGoods(Long id, Goods goods) {
        Order order = findById(id);
        order.getGoods().add(goods);
        return update(order);
    }

    @Override
    public Order updateStatus(Long id, OrderStatus orderStatus) {
        Order order = findById(id);
        if (orderStatus.equals(OrderStatus.FINISHED)
                || orderStatus.equals(OrderStatus.FINISHED_NOT_SUCCESSFULLY)) {
            order.setFinishDate(LocalDate.now());
            List<Master> masters = order.getServices().stream()
                    .map(ServiceModel::getMaster)
                    .distinct()
                    .peek(master -> master.getCompletedOrders().add(order))
                    .collect(Collectors.toList());
            masterService.update(masters);
        }
        order.setOrderStatus(orderStatus);
        return update(order);
    }

    @Override
    public BigDecimal calculateCostForClint(Long id) {
        Order order = findById(id);
        if (!order.getAgreementToRepair()) {
            order.getServices().stream()
                    .filter(x -> !x.getIsService())
                    .forEach(x -> x.setIsService(true));
        }
        BigDecimal price = calculateGoodsPriceWithDiscount(order)
                .add(calculateServicesPriceWithDiscount(order));
        order.setCostForClient(price);
        orderRepository.save(order);
        return price;
    }

    @Override
    public List<Order> findAllByIds(List<Long> orderIds) {
        return orderRepository.findAllById(orderIds);
    }

    private BigDecimal calculateGoodsPriceWithDiscount(Order order) {
        int ordersAmount = order.getCar().getOwner().getOrders().size();
        double discount = ordersAmount * DISCOUNT_FOR_GOODS;
        BigDecimal totalPrice = order.getGoods().stream()
                .map(Goods::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalPrice.subtract(totalPrice.multiply(BigDecimal.valueOf(discount)));
    }

    private BigDecimal calculateServicesPriceWithDiscount(Order order) {
        int ordersAmount = order.getCar().getOwner().getOrders().size();
        double discount = ordersAmount * DISCOUNT_FOR_SERVICES;
        BigDecimal totalPrice = order.getServices().stream()
                .filter(ServiceModel::getIsService)
                .map(ServiceModel::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalPrice.subtract(totalPrice.multiply(BigDecimal.valueOf(discount)));
    }
}
