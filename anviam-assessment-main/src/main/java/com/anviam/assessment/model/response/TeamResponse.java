package com.anviam.assessment.model.response;

import com.anviam.assessment.entity.Agent;
import com.anviam.assessment.entity.Manager;
import com.anviam.assessment.model.AgentDTO;
import com.anviam.assessment.model.ManagerDTO;

import javax.persistence.*;
import java.util.Set;

public class TeamResponse {


    private String name;

    private Set<ManagerDTO> managers;

    private Set<AgentDTO> agents;

    public TeamResponse(String name, Set<ManagerDTO> managers, Set<AgentDTO> agents) {
        this.name = name;
        this.managers = managers;
        this.agents = agents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ManagerDTO> getManagers() {
        return managers;
    }

    public void setManagers(Set<ManagerDTO> managers) {
        this.managers = managers;
    }

    public Set<AgentDTO> getAgents() {
        return agents;
    }

    public void setAgents(Set<AgentDTO> agents) {
        this.agents = agents;
    }

    public TeamResponse() {
    }
}
