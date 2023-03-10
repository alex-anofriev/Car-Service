package testtask.autoservice.mapper;

import org.springframework.stereotype.Component;
import testtask.autoservice.dto.request.GoodsRequestDto;
import testtask.autoservice.dto.response.GoodsResponseDto;
import testtask.autoservice.model.Goods;

@Component
public class GoodsMapper implements RequestDtoMapper<GoodsRequestDto, Goods>,
        ResponseDtoMapper<GoodsResponseDto, Goods> {
    @Override
    public Goods mapToModel(GoodsRequestDto dto) {
        Goods goods = new Goods();
        goods.setName(dto.getName());
        goods.setPrice(dto.getPrice());
        return goods;
    }

    @Override
    public GoodsResponseDto mapToDto(Goods goods) {
        GoodsResponseDto responseDto = new GoodsResponseDto();
        responseDto.setId(goods.getId());
        responseDto.setName(goods.getName());
        responseDto.setPrice(goods.getPrice());
        return responseDto;
    }
}
