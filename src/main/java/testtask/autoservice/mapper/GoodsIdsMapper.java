package testtask.autoservice.mapper;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import testtask.autoservice.dto.request.GoodsIdsDto;
import testtask.autoservice.model.Goods;
import testtask.autoservice.service.GoodsService;

@Component
@RequiredArgsConstructor
public class GoodsIdsMapper {
    private final GoodsService goodsService;

    public List<Goods> mapToModel(GoodsIdsDto dto) {
        return goodsService.findAllByIds(dto.getGoodsIds());
    }
}
