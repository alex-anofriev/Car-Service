package testtask.autoservice.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import testtask.autoservice.exceptions.DataProcessingException;
import testtask.autoservice.model.Car;
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
    public Car save(Car entity) {
        return carRepository.save(entity);
    }

    @Override
    public Car update(Car entity) {
        return carRepository.save(entity);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public void delete(Car car) {
        carRepository.delete(car);
    }

    @Override
    public List<Car> findAllById(List<Long> carsIds) {
        return carRepository.findAllById(carsIds);
    }
}
