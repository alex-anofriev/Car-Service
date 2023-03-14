package testtask.autoservice.service;

import java.util.List;
import testtask.autoservice.model.Car;
import testtask.autoservice.model.Owner;

public interface CarService extends CommonMethods<Car> {

    void deleteAllById(Owner owner);

    List<Car> findAllByIds(List<Long> carsIds);
}
