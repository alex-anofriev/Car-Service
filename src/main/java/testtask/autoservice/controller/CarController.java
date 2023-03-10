package testtask.autoservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import testtask.autoservice.dto.request.CarRequestDto;
import testtask.autoservice.dto.response.CarResponseDto;
import testtask.autoservice.mapper.CarMapper;
import testtask.autoservice.model.Car;
import testtask.autoservice.service.CarService;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
@Tag(name = "Car", description = "Resources to work with cars")
public class CarController {
    private final CarMapper carMapper;
    private final CarService carService;

    @PostMapping
    @Operation(summary = "Save new car")
    public ResponseEntity<CarResponseDto> save(@RequestBody @Valid CarRequestDto requestDto) {
        Car car = carMapper.mapToModel(requestDto);
        return ResponseEntity.ok(carMapper.mapToDto(carService.save(car)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update car by its id")
    public ResponseEntity<CarResponseDto> updateCar(@PathVariable Long id,
                                                    @RequestBody @Valid CarRequestDto requestDto) {
        Car car = carMapper.mapToModel(requestDto);
        car.setId(id);
        return ResponseEntity.ok(carMapper.mapToDto(carService.update(car)));
    }
}
