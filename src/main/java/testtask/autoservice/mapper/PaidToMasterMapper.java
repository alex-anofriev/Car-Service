package testtask.autoservice.mapper;

import org.springframework.stereotype.Component;
import testtask.autoservice.dto.request.PaidToMasterRequestDto;
import testtask.autoservice.model.enums.ServiceStatus;

@Component
public class PaidToMasterMapper implements
        RequestDtoMapper<PaidToMasterRequestDto, ServiceStatus> {
    @Override
    public ServiceStatus mapToModel(PaidToMasterRequestDto dto) {
        return ServiceStatus.valueOf(dto.getStatus());
    }
}
