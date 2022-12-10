package com.anviam.assessment.model.response;

import com.anviam.assessment.entity.Agent;
import com.anviam.assessment.entity.Team;
import com.anviam.assessment.model.AgentDTO;
import com.anviam.assessment.model.TeamDTO;

import javax.persistence.*;
import java.util.Set;

public class ManagerResponse {
    private String firstName;

    private String lastName;

    private String idNumber;

    private String email;



    private AgentDTO agent;


    private Set<TeamDTO> managedTeams;

    public ManagerResponse(String firstName, String lastName, String idNumber, String email, AgentDTO agent, Set<TeamDTO> managedTeams) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.email = email;
        this.agent = agent;
        this.managedTeams = managedTeams;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AgentDTO getAgent() {
        return agent;
    }

    public void setAgent(AgentDTO agent) {
        this.agent = agent;
    }

    public Set<TeamDTO> getManagedTeams() {
        return managedTeams;
    }

    public void setManagedTeams(Set<TeamDTO> managedTeams) {
        this.managedTeams = managedTeams;
    }

    public ManagerResponse() {
    }
}
