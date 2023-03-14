package testtask.autoservice.service.impl;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import testtask.autoservice.exceptions.DataProcessingException;
import testtask.autoservice.model.Master;
import testtask.autoservice.model.Order;
import testtask.autoservice.model.ServiceModel;
import testtask.autoservice.model.enums.ServiceStatus;
import testtask.autoservice.repository.MasterRepository;
import testtask.autoservice.service.MasterService;
import testtask.autoservice.service.ServiceModelService;

@Service
@RequiredArgsConstructor
public class MasterServiceImpl implements MasterService {
    private static final double MASTERS_SALARY_PERCENT = 0.4;
    private final MasterRepository masterRepository;
    private final ServiceModelService serviceModelService;

    @Override
    public Master findById(Long id) {
        return masterRepository.findById(id).orElseThrow(
                () -> new DataProcessingException("Master with id: " + id + " not found"));
    }

    @Override
    public Master save(Master master) {
        return masterRepository.save(master);
    }

    @Override
    public Master update(Master master) {
        Master masterFromDb = findById(master.getId());
        master.setCompletedOrders(masterFromDb.getCompletedOrders());
        return masterRepository.save(master);
    }

    @Override
    public List<Master> update(List<Master> masters) {
        return masterRepository.saveAll(masters);
    }

    @Override
    public List<Master> findAll() {
        return masterRepository.findAll();
    }

    @Override
    public List<Order> findOrders(Long id) {
        return findById(id).getCompletedOrders();
    }

    @Override
    public BigDecimal calculateSalary(Long id) {
        return findById(id).getCompletedOrders()
                .stream()
                .flatMap(order -> order.getServices().stream())
                .filter(service -> service.getServiceStatus().equals(ServiceStatus.NOT_PAID))
                .filter(ServiceModel::getIsService)
                .peek(service -> serviceModelService
                        .updateStatus(service.getId(), ServiceStatus.PAID))
                .map(ServiceModel::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .multiply(new BigDecimal(MASTERS_SALARY_PERCENT));
    }
}
