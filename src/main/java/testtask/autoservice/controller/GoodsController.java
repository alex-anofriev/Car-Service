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
import testtask.autoservice.dto.request.GoodsRequestDto;
import testtask.autoservice.dto.response.GoodsResponseDto;
import testtask.autoservice.mapper.GoodsMapper;
import testtask.autoservice.model.Goods;
import testtask.autoservice.service.GoodsService;

@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
@Tag(name = "Goods", description = "Resources to work with goods")
public class GoodsController {
    private final GoodsMapper goodsMapper;
    private final GoodsService goodsService;

    @PostMapping
    @Operation(summary = "Save new example of goods")
    public ResponseEntity<GoodsResponseDto> save(@RequestBody @Valid GoodsRequestDto requestDto) {
        Goods goods = goodsMapper.mapToModel(requestDto);
        return ResponseEntity.ok(goodsMapper.mapToDto(goodsService.save(goods)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an example of goods by id")
    public ResponseEntity<GoodsResponseDto> update(@PathVariable Long id,
                                                   @RequestBody @Valid GoodsRequestDto requestDto) {
        Goods goods = goodsMapper.mapToModel(requestDto);
        goods.setId(id);
        return ResponseEntity.ok(goodsMapper.mapToDto(goodsService.update(goods)));
    }
}
