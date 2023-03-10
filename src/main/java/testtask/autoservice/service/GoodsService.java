package testtask.autoservice.service;

import java.util.List;
import testtask.autoservice.model.Goods;

public interface GoodsService extends CommonMethods<Goods> {

    List<Goods> findAllByIds(List<Long> goodsIds);
}
