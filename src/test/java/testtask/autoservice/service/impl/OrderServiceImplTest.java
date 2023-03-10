package testtask.autoservice.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import testtask.autoservice.model.Master;
import testtask.autoservice.model.Order;
import testtask.autoservice.model.ServiceModel;
import testtask.autoservice.model.enums.OrderStatus;
import testtask.autoservice.repository.OrderRepository;
import testtask.autoservice.service.MasterService;
import testtask.autoservice.service.OwnerService;

import java.util.List;
import java.util.Optional;

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
}