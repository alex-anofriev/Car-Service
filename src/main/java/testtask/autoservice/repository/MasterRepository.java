package testtask.autoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testtask.autoservice.model.Master;

@Repository
public interface MasterRepository extends JpaRepository<Master, Long> {

}
