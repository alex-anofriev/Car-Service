package testtask.autoservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class OrderRequestDto {
    @Min(0)
    private Long carId;
    @NotBlank(message = "Problem description can not be blank")
    private String problemDescription;
    @NotNull(message = "List of goods can not be null")
    private List<Long> goodsIds;
    @NotBlank(message = "Order status can not be blank")
    private String orderStatus;
    @NotNull(message = "Agreement can not be null or blank")
    private Boolean agreementToRepair;
}
