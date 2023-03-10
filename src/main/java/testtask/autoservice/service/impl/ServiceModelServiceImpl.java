package testtask.autoservice.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import testtask.autoservice.exceptions.DataProcessingException;
import testtask.autoservice.model.ServiceModel;
import testtask.autoservice.model.enums.ServiceStatus;
import testtask.autoservice.repository.ServiceModelRepository;
import testtask.autoservice.service.ServiceModelService;

@Service
@RequiredArgsConstructor
public class ServiceModelServiceImpl implements ServiceModelService {
    private final ServiceModelRepository serviceRepository;

    @Override
    public ServiceModel findById(Long id) {
        return serviceRepository.findById(id).orElseThrow(
                () -> new DataProcessingException("Service with id: " + id + " not found"));
    }

    @Override
    public ServiceModel save(ServiceModel serviceModel) {
        return serviceRepository.save(serviceModel);
    }

    @Override
    public ServiceModel update(ServiceModel serviceModel) {
        return serviceRepository.save(serviceModel);
    }

    @Override
    public List<ServiceModel> findAll() {
        return serviceRepository.findAll();
    }

    @Override
    public ServiceModel updateStatus(Long id, ServiceStatus status) {
        ServiceModel service = findById(id);
        service.setServiceStatus(status);
        return update(service);
    }
}
