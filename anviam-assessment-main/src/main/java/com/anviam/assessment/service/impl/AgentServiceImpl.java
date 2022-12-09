package com.anviam.assessment.service.impl;

import com.anviam.assessment.entity.Agent;
import com.anviam.assessment.entity.Manager;
import com.anviam.assessment.exception.InvalidArguementExceptions;
import com.anviam.assessment.exception.ItemNotFoundException;
import com.anviam.assessment.model.request.CreateAgentRequest;
import com.anviam.assessment.model.response.AgentResponse;
import com.anviam.assessment.model.response.UIBean;
import com.anviam.assessment.repository.AgentRepository;
import com.anviam.assessment.repository.ManagerRepository;
import com.anviam.assessment.service.AgentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    ManagerRepository managerRepository;


    @Override
    public UIBean createNewAgent(CreateAgentRequest createAgentRequest) {
            Agent existingAgent = agentRepository.findAgentByEmail(createAgentRequest.getEmail());
            if(existingAgent == null){
                String id = UUID.randomUUID().toString().replace("-","").substring(0,8);
                Agent agent = modelMapper.map(createAgentRequest, Agent.class);
                agent.setIdNumber(id);
                Manager existingManager = managerRepository.findById(createAgentRequest.getManagerId()).orElse(null);
                if(existingManager != null){
                    agent.setReportingManager(existingManager);
                    existingManager.setAgent(agent);
                }else {
                    throw new InvalidArguementExceptions("Invalid Manager ID");
                }
                Agent savedAgent = agentRepository.save(agent);

                AgentResponse agentResponse = modelMapper.map(savedAgent,AgentResponse.class);
                return new UIBean(agentResponse) ;
            }
            else {
                throw new InvalidArguementExceptions("Agent Already Exists!");
            }

    }

    @Override
    public UIBean findAgentById(Long id) {
        Agent existingAgent = agentRepository.findById(id).orElse(null);
        if(existingAgent != null){
            AgentResponse agentResponse = modelMapper.map(existingAgent, AgentResponse.class);
            return new UIBean(agentResponse);
        }
        else {
            throw new ItemNotFoundException("Sorry! Agent Does not Exist!");
        }
    }

    @Override
    public UIBean getAllAgents(Integer pageSize, Integer pageNumber) {
        Pageable p = PageRequest.of(pageNumber,pageSize);

        Page<Agent> pageItems= agentRepository.findAll(p);
        List<Agent>  allAgents = pageItems.getContent();
        if(allAgents != null){
            List<AgentResponse> agentsList = new ArrayList<>();
            allAgents.forEach(a-> {
                agentsList.add(modelMapper.map(a, AgentResponse.class));
            });
            return new UIBean(agentsList);
        }
        else {
            throw new ItemNotFoundException("Sorry! No Agent Found! ");
        }
    }
}
