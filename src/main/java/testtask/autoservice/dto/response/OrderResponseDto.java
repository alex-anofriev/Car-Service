package testtask.autoservice.dto.response;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class OrderResponseDto {
    private Long id;
    private Long carId;
    private String problemDescription;
    private LocalDate acceptanceDate;
    private List<Long> services;
    private List<Long> goodsIds;
    private String orderStatus;
    private LocalDate finishDate;
}
