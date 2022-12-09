package com.anviam.assessment.model.response;

import com.anviam.assessment.entity.Team;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AgentResponse {
    private String idNumber;
    private String firstName;

    private String lastName;
    private String email;
    private TeamResponse team;

}
