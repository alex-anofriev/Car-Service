package testtask.autoservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import testtask.autoservice.dto.request.ServiceModelRequestDto;
import testtask.autoservice.dto.request.ServiceStatusRequestDto;
import testtask.autoservice.dto.response.ServiceModelResponseDto;
import testtask.autoservice.mapper.ServiceModelMapper;
import testtask.autoservice.mapper.ServiceStatusMapper;
import testtask.autoservice.model.ServiceModel;
import testtask.autoservice.service.ServiceModelService;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
@Tag(name = "Services", description = "Resources to work with service model")
public class ServiceController {
    private final ServiceModelService modelService;
    private final ServiceModelMapper serviceModelMapper;
    private final ServiceStatusMapper serviceStatusMapper;

    @PostMapping
    @Operation(summary = "Save new service")
    public ResponseEntity<ServiceModelResponseDto> save(
            @RequestBody @Valid ServiceModelRequestDto requestDto) {
        ServiceModel serviceModel = serviceModelMapper.mapToModel(requestDto);
        return ResponseEntity.ok(serviceModelMapper
                .mapToDto(modelService.save(serviceModel)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update service by id")
    public ResponseEntity<ServiceModelResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody ServiceModelRequestDto requestDto) {
        ServiceModel serviceModel = serviceModelMapper.mapToModel(requestDto);
        serviceModel.setId(id);
        return ResponseEntity.ok(serviceModelMapper
                .mapToDto(modelService.update(serviceModel)));
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Change status of service")
    public ResponseEntity<ServiceModelResponseDto> updatePaymentStatus(
            @PathVariable Long id,
            @RequestBody ServiceStatusRequestDto requestDto) {
        ServiceModel serviceModel = modelService
                .updateStatus(id, serviceStatusMapper.mapToModel(requestDto));
        return ResponseEntity.ok(serviceModelMapper.mapToDto(serviceModel));
    }
}
