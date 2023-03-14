package testtask.autoservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ServiceModelRequestDto {
    @Min(0)
    private Long orderId;
    @Min(0)
    private Long masterId;
    @Min(0)
    private BigDecimal price;
    @NotBlank(message = "Service status can not be blank")
    private String serviceStatus;
    @NotNull
    private Boolean isService;
}
