package testtask.autoservice.mapper;

import org.springframework.stereotype.Component;
import testtask.autoservice.dto.request.OrderStatusRequestDto;
import testtask.autoservice.model.enums.OrderStatus;

@Component
public class OrderStatusMapper implements RequestDtoMapper<OrderStatusRequestDto, OrderStatus> {
    @Override
    public OrderStatus mapToModel(OrderStatusRequestDto dto) {
        return OrderStatus.valueOf(dto.getOrderStatus());
    }
}
