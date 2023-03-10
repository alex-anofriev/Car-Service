package testtask.autoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testtask.autoservice.model.Goods;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {
}
