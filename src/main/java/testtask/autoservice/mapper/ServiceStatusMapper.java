package testtask.autoservice.mapper;

import org.springframework.stereotype.Component;
import testtask.autoservice.dto.request.ServiceStatusRequestDto;
import testtask.autoservice.model.enums.ServiceStatus;

@Component
public class ServiceStatusMapper implements
        RequestDtoMapper<ServiceStatusRequestDto, ServiceStatus> {
    @Override
    public ServiceStatus mapToModel(ServiceStatusRequestDto dto) {
        return ServiceStatus.valueOf(dto.getStatus());
    }
}
