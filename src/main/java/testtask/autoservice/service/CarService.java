package testtask.autoservice.service;

import java.util.List;
import testtask.autoservice.model.Car;

public interface CarService extends CommonMethods<Car> {

    void delete(Car car);

    List<Car> findAllById(List<Long> carsIds);
}
