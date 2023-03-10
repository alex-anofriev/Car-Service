package testtask.autoservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MasterRequestDto {
    @NotBlank(message = "Name can not be blank")
    private String name;
    @NotBlank(message = "Surname can not be blank")
    private String surname;
    @NotBlank(message = "Farther name can not be blank")
    private String fatherName;
}
