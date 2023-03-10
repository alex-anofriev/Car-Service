package testtask.autoservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class GoodsRequestDto {
    @NotBlank(message = "Name can not be blank")
    private String name;
    @Min(0)
    private BigDecimal price;
}
