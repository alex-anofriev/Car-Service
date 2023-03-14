package testtask.autoservice.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import testtask.autoservice.model.Car;
import testtask.autoservice.model.Goods;
import testtask.autoservice.model.Order;
import testtask.autoservice.model.Owner;
import testtask.autoservice.model.ServiceModel;
import testtask.autoservice.model.enums.OrderStatus;
import testtask.autoservice.repository.OrderRepository;
import testtask.autoservice.service.MasterService;
import testtask.autoservice.service.OwnerService;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    private OrderServiceImpl orderService;
    private OrderRepository orderRepository;
    private OwnerService ownerService;
    private MasterService masterService;

    @BeforeEach
    void setUp() {
        orderRepository = Mockito.mock(OrderRepository.class);
        ownerService = Mockito.mock(OwnerService.class);
        masterService = Mockito.mock(MasterService.class);
        orderService = new OrderServiceImpl(orderRepository, ownerService, masterService);
    }

    @Test
    void updateStatus_SettingFinishedTime_Ok() {
        OrderStatus orderStatus = OrderStatus.FINISHED;
        Long orderId = 1L;
        Order order = new Order();
        order.setOrderStatus(OrderStatus.IN_PROGRESS);
        order.setServices(List.of());
        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        orderService.updateStatus(orderId, orderStatus);
        Assertions.assertEquals(orderStatus, order.getOrderStatus());
        Assertions.assertNotNull(order.getFinishDate());
    }

    @Test
    void updateStatus_NotSettingFinishDate_Ok() {
        OrderStatus orderStatus = OrderStatus.IN_PROGRESS;
        Long orderId = 1L;
        Order order = new Order();
        order.setOrderStatus(OrderStatus.ACCEPTED);
        order.setServices(List.of());
        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        orderService.updateStatus(orderId, orderStatus);
        Assertions.assertEquals(orderStatus, order.getOrderStatus());
        Assertions.assertNull(order.getFinishDate());
    }

    @Test
    void calculateCostForClint_DiagnosticIsFree() {
        Owner ownerOne = new Owner();
        Car carOne = new Car();
        carOne.setOwner(ownerOne);
        Order order = new Order();
        order.setCar(carOne);
        ownerOne.setOrders(List.of(order));

        ServiceModel serviceModelOne = new ServiceModel();
        serviceModelOne.setIsService(false);
        serviceModelOne.setPrice(BigDecimal.valueOf(500.00));

        ServiceModel serviceModelTwo = new ServiceModel();
        serviceModelTwo.setIsService(true);
        serviceModelTwo.setPrice(BigDecimal.valueOf(1000.00));
        order.setServices(List.of(serviceModelOne, serviceModelTwo));

        Goods goods = new Goods();
        goods.setName("oil");
        goods.setPrice(BigDecimal.valueOf(300));
        order.setGoods(List.of(goods));
        order.setAgreementToRepair(true);

        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        BigDecimal bigDecimal = orderService.calculateCostForClint(1L);
        Assertions.assertEquals(new BigDecimal("1277.00"), bigDecimal.round(new MathContext(6)));
    }

    @Test
    void calculateCostForClint_DiagnosticIsNotFree() {
        Owner ownerOne = new Owner();
        Car carOne = new Car();
        carOne.setOwner(ownerOne);
        Order order = new Order();
        order.setCar(carOne);
        ownerOne.setOrders(List.of(order));

        ServiceModel serviceModelOne = new ServiceModel();
        serviceModelOne.setIsService(false);
        serviceModelOne.setPrice(BigDecimal.valueOf(500.00));

        ServiceModel serviceModelTwo = new ServiceModel();
        serviceModelTwo.setIsService(true);
        serviceModelTwo.setPrice(BigDecimal.valueOf(1000.00));
        order.setServices(List.of(serviceModelOne, serviceModelTwo));

        Goods goods = new Goods();
        goods.setName("oil");
        goods.setPrice(BigDecimal.valueOf(300));
        order.setGoods(List.of(goods));
        order.setAgreementToRepair(false);

        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        BigDecimal bigDecimal = orderService.calculateCostForClint(1L);
        Assertions.assertEquals(new BigDecimal("1767.00"), bigDecimal.round(new MathContext(6)));
    }
}