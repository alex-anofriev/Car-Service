package testtask.autoservice.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import testtask.autoservice.model.Master;
import testtask.autoservice.model.Order;
import testtask.autoservice.model.ServiceModel;
import testtask.autoservice.model.enums.ServiceStatus;
import testtask.autoservice.repository.MasterRepository;
import testtask.autoservice.service.ServiceModelService;

class MasterServiceImplTest {

    private MasterServiceImpl masterService;
    private MasterRepository masterRepository;
    private ServiceModelService serviceModelService;

    @BeforeEach
    void setUp() {
        masterRepository = Mockito.mock(MasterRepository.class);
        serviceModelService = Mockito.mock(ServiceModelService.class);
        masterService = new MasterServiceImpl(masterRepository, serviceModelService);
    }

    @Test
    void calculateSalary_Ok() {
        ServiceModel serviceModelOne = new ServiceModel();
        serviceModelOne.setId(1L);
        serviceModelOne.setPrice(new BigDecimal("500.00"));
        serviceModelOne.setServiceStatus(ServiceStatus.NOT_PAID);

        Order orderOne = new Order();
        orderOne.setServices(List.of(serviceModelOne));

        Long masterId = 1L;
        Master masterOne = new Master();
        masterOne.setCompletedOrders(List.of(orderOne));
        Mockito.when(masterRepository.findById(masterId)).thenReturn(Optional.of(masterOne));

        BigDecimal actual = masterService.calculateSalary(masterId);
        Assertions.assertEquals(new BigDecimal("200.00"), actual.round(new MathContext(5)));
    }
}