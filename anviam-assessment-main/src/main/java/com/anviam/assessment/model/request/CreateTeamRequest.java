package com.anviam.assessment.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;


public class CreateTeamRequest {
    @NotBlank(message = "Team name cannot be blank!")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CreateTeamRequest(String name) {
        this.name = name;
    }
}
