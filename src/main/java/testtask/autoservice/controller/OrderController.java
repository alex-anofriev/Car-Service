package testtask.autoservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import testtask.autoservice.dto.request.GoodsIdsDto;
import testtask.autoservice.dto.request.OrderRequestDto;
import testtask.autoservice.dto.request.OrderStatusRequestDto;
import testtask.autoservice.dto.response.OrderResponseDto;
import testtask.autoservice.mapper.GoodsIdsMapper;
import testtask.autoservice.mapper.OrderMapper;
import testtask.autoservice.mapper.OrderStatusMapper;
import testtask.autoservice.model.Goods;
import testtask.autoservice.model.Order;
import testtask.autoservice.model.enums.OrderStatus;
import testtask.autoservice.service.OrderService;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
@Tag(name = "Orders", description = "Resources to work with orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final GoodsIdsMapper goodsIdsMapper;
    private final OrderStatusMapper orderStatusMapper;

    @PostMapping
    @Operation(summary = "Save new order")
    public ResponseEntity<OrderResponseDto> save(@RequestBody @Valid OrderRequestDto requestDto) {
        Order order = orderMapper.mapToModel(requestDto);
        return ResponseEntity.ok(orderMapper.mapToDto(orderService.save(order)));
    }

    @PostMapping("/{id}/goods")
    @Operation(summary = "Add new goods to order")
    public ResponseEntity<OrderResponseDto> addGoods(@PathVariable Long id,
                                                     @RequestBody GoodsIdsDto goodsIds) {
        Order order = orderService.findById(id);
        List<Goods> goods = goodsIdsMapper.mapToModel(goodsIds);
        order.getGoods().addAll(goods);
        return ResponseEntity.ok(orderMapper.mapToDto(orderService.update(order)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update order by id")
    public ResponseEntity<OrderResponseDto> update(@PathVariable Long id,
                                                   @RequestBody OrderRequestDto requestDto) {
        Order order = orderService.findById(id);
        order.setAgreementToRepair(requestDto.getAgreementToRepair());
        order.setProblemDescription(requestDto.getProblemDescription());
        return ResponseEntity.ok(orderMapper.mapToDto(orderService.update(order)));
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update status of order by id")
    public ResponseEntity<OrderResponseDto> updateStatus(
            @PathVariable Long id,
            @RequestBody OrderStatusRequestDto requestDto) {
        OrderStatus orderStatus = orderStatusMapper.mapToModel(requestDto);
        Order order = orderService.updateStatus(id, orderStatus);
        return ResponseEntity.ok(orderMapper.mapToDto(order));
    }

    @GetMapping("/{id}/total-cost")
    @Operation(summary = "Calculate total cost by order id")
    public BigDecimal totalCostOfOrder(@PathVariable Long id) {
        return orderService.calculateCostForClint(id);
    }
}
