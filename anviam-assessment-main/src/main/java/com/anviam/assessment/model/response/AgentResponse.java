package com.anviam.assessment.model.response;


import com.anviam.assessment.model.TeamDTO;

public class AgentResponse {
    private String idNumber;
    private String firstName;
    private String lastName;
    private String email;
    private TeamDTO team;

    public AgentResponse(String idNumber, String firstName, String lastName, String email, TeamDTO team) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.team = team;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    public AgentResponse() {
    }
}
