package testtask.autoservice.mapper;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import testtask.autoservice.dto.request.MasterRequestDto;
import testtask.autoservice.dto.response.MasterResponseDto;
import testtask.autoservice.model.Master;
import testtask.autoservice.model.Order;

@Component
@RequiredArgsConstructor
public class MasterMapper implements RequestDtoMapper<MasterRequestDto, Master>,
        ResponseDtoMapper<MasterResponseDto, Master> {

    @Override
    public Master mapToModel(MasterRequestDto dto) {
        Master master = new Master();
        master.setName(dto.getName());
        master.setSurname(dto.getSurname());
        master.setFatherName(dto.getFatherName());
        master.setCompletedOrders(List.of());
        return master;
    }

    @Override
    public MasterResponseDto mapToDto(Master master) {
        MasterResponseDto responseDto = new MasterResponseDto();
        responseDto.setId(master.getId());
        responseDto.setName(master.getName());
        responseDto.setSurname(master.getSurname());
        responseDto.setFatherName(master.getFatherName());
        responseDto.setOrderIds(master.getCompletedOrders().stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        return responseDto;
    }
}
