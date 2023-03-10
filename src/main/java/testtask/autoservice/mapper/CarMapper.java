package testtask.autoservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import testtask.autoservice.dto.request.CarRequestDto;
import testtask.autoservice.dto.response.CarResponseDto;
import testtask.autoservice.model.Car;
import testtask.autoservice.service.OwnerService;

@Component
@RequiredArgsConstructor
public class CarMapper implements RequestDtoMapper<CarRequestDto, Car>,
        ResponseDtoMapper<CarResponseDto, Car> {
    private final OwnerService ownerService;

    @Override
    public Car mapToModel(CarRequestDto dto) {
        Car car = new Car();
        car.setCarNumber(dto.getCarNumber());
        car.setModel(dto.getModel());
        car.setOwner(ownerService.findById(dto.getOwnerId()));
        car.setBrand(dto.getBrand());
        car.setYearOfIssue(dto.getYearOfIssue());
        return car;
    }

    @Override
    public CarResponseDto mapToDto(Car car) {
        CarResponseDto responseDto = new CarResponseDto();
        responseDto.setId(car.getId());
        responseDto.setBrand(car.getBrand());
        responseDto.setModel(car.getModel());
        responseDto.setOwnerId(car.getOwner().getId());
        responseDto.setYeaOfIssue(car.getYearOfIssue());
        responseDto.setCarNumber(car.getCarNumber());
        return responseDto;
    }
}
