package testtask.autoservice.dto.response;

import lombok.Data;

@Data
public class CarResponseDto {
    private Long id;
    private String brand;
    private String model;
    private Integer yeaOfIssue;
    private String carNumber;
    private Long ownerId;
}
