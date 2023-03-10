package testtask.autoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testtask.autoservice.model.ServiceModel;

@Repository
public interface ServiceModelRepository extends JpaRepository<ServiceModel, Long> {
}
