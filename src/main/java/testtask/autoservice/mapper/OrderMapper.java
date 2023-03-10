package testtask.autoservice.mapper;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import testtask.autoservice.dto.request.OrderRequestDto;
import testtask.autoservice.dto.response.OrderResponseDto;
import testtask.autoservice.model.Goods;
import testtask.autoservice.model.Order;
import testtask.autoservice.model.ServiceModel;
import testtask.autoservice.model.enums.OrderStatus;
import testtask.autoservice.service.CarService;
import testtask.autoservice.service.GoodsService;

@Component
@RequiredArgsConstructor
public class OrderMapper implements RequestDtoMapper<OrderRequestDto, Order>,
        ResponseDtoMapper<OrderResponseDto, Order> {
    private final CarService carService;
    private final GoodsService goodsService;

    @Override
    public Order mapToModel(OrderRequestDto dto) {
        Order order = new Order();
        order.setCar(carService.findById(dto.getCarId()));
        order.setProblemDescription(dto.getProblemDescription());
        order.setGoods(goodsService.findAllByIds(dto.getGoodsIds()));
        order.setOrderStatus(OrderStatus.valueOf(dto.getOrderStatus().toUpperCase()));
        order.setServices(List.of());
        return order;
    }

    @Override
    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(order.getId());
        responseDto.setCarId(order.getCar().getId());
        responseDto.setProblemDescription(order.getProblemDescription());
        responseDto.setAcceptanceDate(order.getAcceptanceDate());
        responseDto.setGoodsIds(order.getGoods().stream()
                .map(Goods::getId)
                .toList());
        responseDto.setOrderStatus(order.getOrderStatus().name());
        responseDto.setFinishDate(order.getFinishDate());
        responseDto.setServices(order.getServices().stream()
                .map(ServiceModel::getId)
                .toList());
        return responseDto;
    }
}
