package testtask.autoservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import testtask.autoservice.dto.request.OwnerRequestDto;
import testtask.autoservice.dto.response.OrderResponseDto;
import testtask.autoservice.dto.response.OwnerResponseDto;
import testtask.autoservice.mapper.OrderMapper;
import testtask.autoservice.mapper.OwnerMapper;
import testtask.autoservice.model.Order;
import testtask.autoservice.model.Owner;
import testtask.autoservice.service.OwnerService;

@RestController
@RequestMapping("/owners")
@RequiredArgsConstructor
@Tag(name = "Owners", description = "Resources to work with owners")
public class OwnerController {
    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;
    private final OrderMapper orderMapper;

    @PostMapping
    @Operation(summary = "Save new owner")
    public ResponseEntity<OwnerResponseDto> save(@RequestBody @Valid OwnerRequestDto requestDto) {
        Owner owner = ownerMapper.mapToModel(requestDto);
        return ResponseEntity.ok(ownerMapper.mapToDto(ownerService.save(owner)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update owner by id")
    public ResponseEntity<OwnerResponseDto> update(@PathVariable Long id,
                                                   @RequestBody @Valid OwnerRequestDto requestDto) {
        Owner owner = ownerMapper.mapToModel(requestDto);
        owner.setId(id);
        return ResponseEntity.ok(ownerMapper.mapToDto(ownerService.update(owner)));
    }

    @GetMapping("/{id}/orders")
    @Operation(summary = "Get list of orders by owner id")
    List<OrderResponseDto> getAllOrdersById(@PathVariable Long id) {
        List<Order> orders = ownerService.findOrdersById(id);
        return orders.stream()
                .map(orderMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
