package testtask.autoservice.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class MasterResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String fatherName;
    private List<Long> orderIds;
}
