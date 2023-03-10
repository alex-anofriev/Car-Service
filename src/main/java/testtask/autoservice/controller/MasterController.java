package testtask.autoservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.math.BigDecimal;
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
import testtask.autoservice.dto.request.MasterRequestDto;
import testtask.autoservice.dto.response.MasterResponseDto;
import testtask.autoservice.dto.response.OrderResponseDto;
import testtask.autoservice.mapper.MasterMapper;
import testtask.autoservice.mapper.OrderMapper;
import testtask.autoservice.model.Master;
import testtask.autoservice.model.Order;
import testtask.autoservice.service.MasterService;

@RestController
@RequestMapping("/masters")
@RequiredArgsConstructor
@Tag(name = "Masters", description = "Resources to work with masters")
public class MasterController {
    private final MasterService masterService;
    private final MasterMapper masterMapper;
    private final OrderMapper orderMapper;

    @PostMapping
    @Operation(summary = "Save new master")
    public ResponseEntity<MasterResponseDto> save(@RequestBody @Valid MasterRequestDto requestDto) {
        Master master = masterMapper.mapToModel(requestDto);
        return ResponseEntity.ok(masterMapper.mapToDto(masterService.save(master)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update master by id")
    public ResponseEntity<MasterResponseDto> update(
            @PathVariable Long id,
            @RequestBody @Valid MasterRequestDto requestDto) {
        Master master = masterMapper.mapToModel(requestDto);
        master.setId(id);
        return ResponseEntity.ok(masterMapper
                .mapToDto(masterService.update(master)));
    }

    @GetMapping("/{id}/orders")
    @Operation(summary = "Get list of orders by masters` id")
    public List<OrderResponseDto> findOrdersByMasterId(@PathVariable Long id) {
        List<Order> orders = masterService.findOrders(id);
        return orders.stream()
                .map(orderMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/salary")
    @Operation(summary = "Get salary of master by id")
    public BigDecimal getMasterSalary(@PathVariable Long id) {
        return masterService.calculateSalary(id);
    }
}
