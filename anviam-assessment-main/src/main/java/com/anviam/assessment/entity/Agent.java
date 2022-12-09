package com.anviam.assessment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter

@NoArgsConstructor
public class Agent {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "agent_fname")
    private String firstName;
    @Column(name = "agent_lname")
    private String lastName;
    @Column(name = "agent_id")
    private String idNumber;

    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_manager_id")
    private Manager reportingManager;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Manager getReportingManager() {
        return reportingManager;
    }

    public void setReportingManager(Manager reportingManager) {
        this.reportingManager = reportingManager;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Agent(Long id, String email, String firstName, String lastName, String idNumber, Manager reportingManager, Team team) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.reportingManager = reportingManager;
        this.team = team;
    }
}
