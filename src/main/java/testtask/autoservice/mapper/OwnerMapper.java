package testtask.autoservice.mapper;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import testtask.autoservice.dto.request.OwnerRequestDto;
import testtask.autoservice.dto.response.OwnerResponseDto;
import testtask.autoservice.model.Car;
import testtask.autoservice.model.Order;
import testtask.autoservice.model.Owner;
import testtask.autoservice.service.CarService;
import testtask.autoservice.service.OrderService;

@Component
@RequiredArgsConstructor
public class OwnerMapper implements RequestDtoMapper<OwnerRequestDto, Owner>,
        ResponseDtoMapper<OwnerResponseDto, Owner> {
    private final CarService carService;
    private final OrderService orderService;

    @Override
    public Owner mapToModel(OwnerRequestDto dto) {
        Owner owner = new Owner();
        owner.setCars(carService.findAllByIds(dto.getCarsIds()));
        owner.setOrders(orderService.findAllByIds(dto.getOrdersIds()));
        return owner;
    }

    @Override
    public OwnerResponseDto mapToDto(Owner owner) {
        OwnerResponseDto responseDto = new OwnerResponseDto();
        responseDto.setId(owner.getId());
        responseDto.setCarsIds(owner.getCars().stream()
                .map(Car::getId)
                .collect(Collectors.toList()));
        responseDto.setOrdersIds(owner.getOrders().stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        return responseDto;
    }
}
