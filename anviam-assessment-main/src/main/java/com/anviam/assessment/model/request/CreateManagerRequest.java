package com.anviam.assessment.model.request;

import com.anviam.assessment.entity.Team;
import com.anviam.assessment.model.TeamDTO;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;


public class CreateManagerRequest {
    @NotBlank(message = "First name cannot be null!")
    private String firstName;
    @NotBlank(message = "Last name cannot be null")
    private String lastName;
    @Email
    @NotBlank(message = "Email name cannot be null")
    private String email;

    @NotNull(message = "Cannot be null")
    private Set<TeamDTO> managedTeams;

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

    public CreateManagerRequest(String firstName, String lastName, String email, Set<TeamDTO> managedTeams) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.managedTeams = managedTeams;
    }

    public Set<TeamDTO> getManagedTeams() {
        return managedTeams;
    }

    public void setManagedTeams(Set<TeamDTO> managedTeams) {
        this.managedTeams = managedTeams;
    }

    public CreateManagerRequest() {
    }
}
