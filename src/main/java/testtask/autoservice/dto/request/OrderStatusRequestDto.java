package testtask.autoservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderStatusRequestDto {
    @NotBlank(message = "Order status can not be blank")
    private String orderStatus;
}
