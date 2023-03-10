package testtask.autoservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CarRequestDto {
    @NotBlank(message = "Brand can not be blank")
    private String brand;
    @NotBlank(message = "Model can not be blank")
    private String model;
    @Min(0)
    private Integer yearOfIssue;
    @NotBlank(message = "Number can not be blank")
    private String carNumber;
    @Min(0)
    private Long ownerId;
}
