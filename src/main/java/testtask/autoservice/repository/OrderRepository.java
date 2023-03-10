package testtask.autoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testtask.autoservice.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
