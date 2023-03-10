package testtask.autoservice.service;

import testtask.autoservice.model.ServiceModel;
import testtask.autoservice.model.enums.ServiceStatus;

public interface ServiceModelService extends CommonMethods<ServiceModel> {

    ServiceModel updateStatus(Long id, ServiceStatus status);
}
