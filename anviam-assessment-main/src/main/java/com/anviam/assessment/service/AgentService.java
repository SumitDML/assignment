package com.anviam.assessment.service;

import com.anviam.assessment.entity.Agent;
import com.anviam.assessment.model.request.CreateAgentRequest;
import com.anviam.assessment.model.response.UIBean;

import java.util.List;

public interface AgentService {

   UIBean createNewAgent(CreateAgentRequest createAgentRequest);
   UIBean findAgentById(Long id);
    UIBean getAllAgents(Integer pageSize ,Integer pageNumber);
}
