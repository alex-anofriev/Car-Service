package testtask.autoservice.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class OwnerResponseDto {
    private Long id;
    private List<Long> carsIds;
    private List<Long> ordersIds;
}
