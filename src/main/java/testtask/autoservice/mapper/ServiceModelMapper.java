package testtask.autoservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import testtask.autoservice.dto.request.ServiceModelRequestDto;
import testtask.autoservice.dto.response.ServiceModelResponseDto;
import testtask.autoservice.model.ServiceModel;
import testtask.autoservice.model.enums.ServiceStatus;
import testtask.autoservice.service.MasterService;
import testtask.autoservice.service.OrderService;

@Component
@RequiredArgsConstructor
public class ServiceModelMapper implements
        RequestDtoMapper<ServiceModelRequestDto, ServiceModel>,
        ResponseDtoMapper<ServiceModelResponseDto, ServiceModel> {
    private final OrderService orderService;
    private final MasterService masterService;

    @Override
    public ServiceModel mapToModel(ServiceModelRequestDto dto) {
        ServiceModel service = new ServiceModel();
        service.setOrder(orderService.findById(dto.getOrderId()));
        service.setMaster(masterService.findById(dto.getMasterId()));
        service.setServiceStatus(ServiceStatus.valueOf(dto.getServiceStatus()));
        service.setPrice(dto.getPrice());
        return service;
    }

    @Override
    public ServiceModelResponseDto mapToDto(ServiceModel serviceModel) {
        ServiceModelResponseDto responseDto = new ServiceModelResponseDto();
        responseDto.setId(serviceModel.getId());
        responseDto.setTotalPrice(serviceModel.getPrice());
        responseDto.setMasterId(serviceModel.getMaster().getId());
        responseDto.setOrderId(serviceModel.getOrder().getId());
        responseDto.setServiceStatus(serviceModel.getServiceStatus().name());
        return responseDto;
    }
}
