package testtask.autoservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ServiceStatusRequestDto {
    @NotBlank(message = "Status can not be blank")
    private String status;
}
