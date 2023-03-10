package testtask.autoservice.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import testtask.autoservice.exceptions.DataProcessingException;
import testtask.autoservice.model.Goods;
import testtask.autoservice.repository.GoodsRepository;
import testtask.autoservice.service.GoodsService;

@Service
@RequiredArgsConstructor
public class GoodsServiceImpl implements GoodsService {
    private final GoodsRepository goodsRepository;

    @Override
    public Goods findById(Long id) {
        return goodsRepository.findById(id).orElseThrow(
                () -> new DataProcessingException("Goods with id: " + id + " not found"));
    }

    @Override
    public Goods save(Goods entity) {
        return goodsRepository.save(entity);
    }

    @Override
    public Goods update(Goods entity) {
        return goodsRepository.save(entity);
    }

    @Override
    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }

    @Override
    public List<Goods> findAllByIds(List<Long> goodsIds) {
        return goodsRepository.findAllById(goodsIds);
    }
}
