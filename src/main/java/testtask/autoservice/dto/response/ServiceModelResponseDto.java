package testtask.autoservice.dto.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ServiceModelResponseDto {
    private Long id;
    private Long orderId;
    private Long masterId;
    private BigDecimal totalPrice;
    private String serviceStatus;
}
