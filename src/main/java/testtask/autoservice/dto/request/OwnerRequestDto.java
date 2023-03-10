package testtask.autoservice.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class OwnerRequestDto {
    @NotNull
    private List<Long> carsIds;
    @NotNull
    private List<Long> ordersIds;
}
