package testtask.autoservice.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class GoodsIdsDto {
    @NotNull
    private List<Long> goodsIds;
}
