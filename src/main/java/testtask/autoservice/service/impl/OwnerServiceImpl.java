package testtask.autoservice.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import testtask.autoservice.exceptions.DataProcessingException;
import testtask.autoservice.model.Order;
import testtask.autoservice.model.Owner;
import testtask.autoservice.repository.OwnerRepository;
import testtask.autoservice.service.CarService;
import testtask.autoservice.service.OwnerService;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final CarService carService;

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElseThrow(
                () -> new DataProcessingException("Owner with id: " + id + " not found"));
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner update(Owner owner) {
        Owner ownerFromDb = findById(owner.getId());
        ownerFromDb.getCars().stream()
                .filter(car -> !owner.getCars().contains(car))
                .forEach(carService::delete);
        return ownerRepository.save(owner);
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    @Override
    public List<Order> findOrdersById(Long id) {
        return findById(id).getOrders();
    }
}
