package testtask.autoservice.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {
    private LocalDateTime timestamp;
    private String error;
}
