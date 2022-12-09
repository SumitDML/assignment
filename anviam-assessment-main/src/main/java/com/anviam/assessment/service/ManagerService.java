package com.anviam.assessment.service;

import com.anviam.assessment.model.request.CreateAgentRequest;
import com.anviam.assessment.model.request.CreateManagerRequest;
import com.anviam.assessment.model.response.UIBean;

public interface ManagerService {
    UIBean createNewManager(CreateManagerRequest createManagerRequest);



}
