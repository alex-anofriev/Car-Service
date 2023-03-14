package testtask.autoservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import testtask.autoservice.exceptions.DataProcessingException;
import testtask.autoservice.model.Car;
import testtask.autoservice.model.Owner;
import testtask.autoservice.repository.CarRepository;
import testtask.autoservice.service.CarService;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(
                () -> new DataProcessingException("Car with id: " + id + " not found"));
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car update(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public void deleteAllById(Owner owner) {
        carRepository.deleteAllById(owner.getCars().stream()
                .map(Car::getId)
                .collect(Collectors.toList()));
    }

    @Override
    public List<Car> findAllByIds(List<Long> carsIds) {
        return carRepository.findAllById(carsIds);
    }
}
